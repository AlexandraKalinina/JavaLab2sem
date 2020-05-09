package ru.spring.stompproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.spring.stompproject.model.Queue;

import java.util.Optional;

public interface QueueRepositories extends JpaRepository<Queue, Long> {
    Optional<Queue> findByName(String name);
}
