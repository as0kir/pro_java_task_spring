package ru.askir.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import ru.askir.spring.dto.User;
import ru.askir.spring.service.UserService;

import java.util.List;

@ComponentScan
public class Test4App {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Test4App.class);
        UserService userService = context.getBean(UserService.class);

        System.out.println("Очищаем");
        List<User> userList = userService.findAll();
        userList.forEach((user) -> {
            System.out.println("Удаляем запись " + user.toString());
            userService.delete(user);
        });

        System.out.println("Вставка + обновление");
        User user1 = userService.create("user1");
        User user2 = userService.create("user2");
        userService.update(new User(user1.id(), "user1_new"));

        userList = userService.findAll();
        userList.forEach((user) -> {
            System.out.println("запись " + user.toString());
        });

        System.out.println("Удаление");

        userService.delete(user2);

        userList = userService.findAll();
        userList.forEach((user) -> {
            System.out.println("запись " + user.toString());
        });
    }
}
