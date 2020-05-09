package ru.spring.stompproject.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import ru.spring.stompproject.dto.MessageDto;
import ru.spring.stompproject.dto.TaskDto;
import ru.spring.stompproject.helpers.JsonHelper;
import ru.spring.stompproject.model.Task;
import ru.spring.stompproject.services.EmailService;
import ru.spring.stompproject.services.FileService;
import ru.spring.stompproject.services.TaskService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

@Controller
public class SendController {

    @Autowired
    private SimpMessagingTemplate template;

    @Autowired
    private TaskService taskService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private JsonHelper jsonHelper;

    List<Task> tasks;
    Task current_task;
    @MessageMapping("/send")
    public void receiveMessageFromClient(Message<String> message) throws JsonProcessingException {
        String messageForm = message.getPayload();
        MessageDto msg = objectMapper.readValue(messageForm, MessageDto.class);
        StompHeaderAccessor accessor =
                MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
        StompCommand command = accessor.getCommand();
        TaskDto taskDto = TaskDto.builder()
                .command(command.name())
                .queueName(msg.getNameQueue())
                .data(msg.getJson())
                .build();
        taskService.saveTask(taskDto);
        template.convertAndSend("/topic/chat", "Your task is saved");
    }

    @MessageMapping("/subscribe")
    public void subscriber(Message<String> message) throws JsonProcessingException {
        String messageForm = message.getPayload();
        MessageDto msg = objectMapper.readValue(messageForm, MessageDto.class);
        tasks = taskService.getListByQueueName(msg.getNameQueue());
        if (tasks.size() == 0) {
            template.convertAndSend("/topic/chat", "Tasks are over");
        } else {
            int i = 0;
            String task = objectMapper.writeValueAsString(tasks.get(i));
            current_task = tasks.get(i);
            template.convertAndSend("/topic/chat", task);
        }
    }

    @MessageMapping("/accepted")
    public void accepted(Message<String> message) throws JsonProcessingException {
        String body = jsonHelper.getBodyByJson(message.getPayload());
        Map<String, Object> map = objectMapper.readValue(body, new TypeReference<Map<String, Object>>() {
        });
        Integer id = (Integer) map.get("id");
        Optional<Task> task = taskService.getTaskById(Long.parseLong(String.valueOf(id)));
        tasks.remove(task.get());
    }

    @Autowired
    private FileService fileService;

    boolean save = false;
    String nameFile = "";
    @PostMapping("/files")
    public ModelAndView uploadFile(@RequestParam("file") MultipartFile file) {
        nameFile = fileService.uploadFile(file, current_task.getId());
        if (!nameFile.equals("")) {
            save = true;
        }
        return null;
    }

    @Autowired
    private EmailService emailService;

    @MessageMapping("/completed")
    public void completed(Message<String> message) throws JsonProcessingException {
        String body = jsonHelper.getBodyByJson(message.getPayload());
        Map<String, Object> map = objectMapper.readValue(body, new TypeReference<Map<String, Object>>() {
        });
        String data = (String) map.get("data");
        String[] str = data.split("\\+\\+\\+");
        if (save) {
            emailService.sendEmail("File", str[0], str[1],"letters.ftl", nameFile);
            taskService.deleteTask(current_task);
            current_task = null;
            save = false;
        }

    }
    @RequestMapping(value ="/files/{file-name:.+}" , method = RequestMethod.GET)
    public ModelAndView getFile(@PathVariable("file-name") String fileName, HttpServletResponse response) {
        File file = fileService.downloadFile(fileName);
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);
        response.setContentType("application/vnd.ms-excel");
        try {
            Files.copy(file.toPath(), response.getOutputStream());
            response.getOutputStream().flush();
        } catch (IOException e) {
            throw new RuntimeException("IOError writing file to output stream");
        }
        return null;
    }

}
