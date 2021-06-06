package com.example.redis;

import com.example.redis.model.Student;
import com.example.redis.repository.StudentRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

@SpringBootApplication
public class RedisApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(RedisApplication.class, args);
        StudentRepository studentRepository = run.getBean("studentRepository", StudentRepository.class);
        Student student = new Student(
                "Eng2015001", "John Doe", Student.Gender.MALE, 1);
        Student student1 = new Student(
                "Eng2015002", "John Doe 2", Student.Gender.MALE, 2);
        studentRepository.save(student);
        studentRepository.save(student1);
        Student retrievedStudent =
                studentRepository.findById("Eng2015001").get();
        List<Student> studentList = (List<Student>) studentRepository.findAll();
        System.out.println(student.getGrade());
        System.out.println(studentList.size());
    }
}
