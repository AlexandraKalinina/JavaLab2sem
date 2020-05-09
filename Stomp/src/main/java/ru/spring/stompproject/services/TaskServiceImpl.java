package ru.spring.stompproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.spring.stompproject.dto.TaskDto;
import ru.spring.stompproject.model.Queue;
import ru.spring.stompproject.model.Task;
import ru.spring.stompproject.repositories.TaskRepositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private QueueService queueService;

    @Autowired
    private TaskRepositories taskRepositories;

    @Override
    public void saveTask(TaskDto taskDto) {
        if (!queueService.checkQueue(taskDto.getQueueName())) {
            queueService.createQueue(taskDto.getQueueName());
        }
        Optional<Queue> queue = queueService.getQueueByName(taskDto.getQueueName());
        Task task = Task.builder()
                .command(taskDto.getCommand())
                .queue(queue.get())
                .data(taskDto.getData())
                .build();
        taskRepositories.save(task);
    }

    @Override
    public List<Task> getListByQueueName(String name) {
        if (queueService.checkQueue(name)) {
            Optional<Queue> queue = queueService.getQueueByName(name);
            return taskRepositories.findAllByQueue(queue.get());
        } else throw new IllegalArgumentException("Queue is not found");
    }

    @Override
    public Optional<Task> getTaskById(Long id) {
        return taskRepositories.findById(id);
    }

    @Override
    public void deleteTask(Task task) {
        taskRepositories.delete(task);
    }
}
