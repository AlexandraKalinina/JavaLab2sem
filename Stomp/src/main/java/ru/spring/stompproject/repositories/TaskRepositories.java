package ru.spring.stompproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.spring.stompproject.model.Queue;
import ru.spring.stompproject.model.Task;

import java.util.List;

public interface TaskRepositories extends JpaRepository<Task, Long> {
    /*List<Task> findAllByQueue(Queue queue);*/
    List<Task> findAllByQueue(Queue queue);
}
