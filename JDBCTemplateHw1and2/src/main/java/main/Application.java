package main;

import model.Post;
import model.User;
import repositories.PostRepositories;
import repositories.PostRepositoriesImpl;
import repositories.UserRepositories;
import repositories.UserRepositoriesImpl;
import java.util.List;
import java.util.Optional;

public class Application {
    public static void main(String[] args) {
        UserRepositories ur = new UserRepositoriesImpl();
        PostRepositories pr = new PostRepositoriesImpl();
        //ur.save(new User("Sanya", 19));
        //ur.save(new User("Lada", 15));
        //pr.save(new Post("Music", 3,  3L));
        //pr.save(new Post("Sport", 2, 4L));
        System.out.println(Optional.ofNullable(ur.find(3L)));
        System.out.println(Optional.ofNullable(pr.find(6L)));

        List<User> users = ur.findAll();
        List<Post> posts = pr.findAll();
        System.out.println(users);
        System.out.println(posts);
    }
}
