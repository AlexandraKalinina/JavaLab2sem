package ru.spring.stompproject.services;

import ru.spring.stompproject.dto.TaskDto;
import ru.spring.stompproject.model.Task;

import java.util.List;
import java.util.Optional;
import java.util.Queue;

public interface TaskService {
    void saveTask(TaskDto taskDto);
    List<Task> getListByQueueName(String name);
    Optional<Task> getTaskById(Long id);
    void deleteTask(Task task);
}
