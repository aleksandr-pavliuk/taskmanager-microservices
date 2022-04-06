package ua.com.alex.taskmanager.todo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableEurekaClient
@ComponentScan(basePackages = {"ua.com.alex.taskmanager"})
@EnableJpaRepositories(basePackages = {"ua.com.alex.taskmanager.todo"})
@EnableFeignClients
@RefreshScope
public class TaskmanagerTodoApplication {

  public static void main(String[] args) {
    SpringApplication.run(TaskmanagerTodoApplication.class, args);
  }

}
