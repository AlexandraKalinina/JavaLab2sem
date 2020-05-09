package ru.spring.stompproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.spring.stompproject.model.Queue;
import ru.spring.stompproject.repositories.QueueRepositories;

import java.util.Optional;

@Service
public class QueueServiceImpl implements QueueService {

    @Autowired
    private QueueRepositories queueRepositories;

    @Override
    public Optional<Queue> getQueueByName(String name) {
        return queueRepositories.findByName(name);
    }

    @Override
    public void createQueue(String name) {
        Queue queue = Queue.builder()
                .name(name)
                .build();
        queueRepositories.save(queue);
    }

    @Override
    public boolean checkQueue(String name) {
        Optional<Queue> queue = queueRepositories.findByName(name);
        if (queue.isPresent()) {
            return true;
        } else return false;
    }
}
