package ru.itis.hateoas.sem.libraryhateoas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.hateoas.sem.libraryhateoas.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
