package ru.spring.stompproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.spring.stompproject.model.User;

import java.util.Optional;

public interface UserRepositories extends JpaRepository<User, Long> {
    User findById(long id);
    Optional<User> findByEmail(String email);
}
