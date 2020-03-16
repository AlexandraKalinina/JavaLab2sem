package ru.spring.project.hw3.repositories;

import ru.spring.project.hw3.model.User;

public interface UserRepositories extends CrudRepositories<User, Long>, CrudRepositoriesUser<User, String> {

}
