package ru.spring.stompproject.services;

import ru.spring.stompproject.model.Queue;

import java.util.Optional;

public interface QueueService {
    Optional<Queue> getQueueByName(String name);
    void createQueue(String name);
    boolean checkQueue(String name);
}
