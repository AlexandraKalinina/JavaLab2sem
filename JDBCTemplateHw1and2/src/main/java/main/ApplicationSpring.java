package main;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import model.Post;
import org.springframework.jdbc.core.JdbcTemplate;
import repositories.PostRepositories;
import repositories.UserRepositories;
import repositories.spring.PostRepositoriesSpringImpl;
import repositories.spring.UserRepositoriesSpringImpl;

import java.util.List;
import java.util.Optional;

public class ApplicationSpring {
    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        HikariConfig config = new HikariConfig();
        config.setUsername("postgres");
        config.setPassword("123");
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/BdLesson1");
        HikariDataSource dataSource = new HikariDataSource(config);
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        UserRepositories ur = new UserRepositoriesSpringImpl(jdbcTemplate);
        PostRepositories pr = new PostRepositoriesSpringImpl(jdbcTemplate);
        System.out.println(Optional.ofNullable(ur.find(4L)));
        List<Post> posts = pr.findAll();
        System.out.println(posts);
    }
}
