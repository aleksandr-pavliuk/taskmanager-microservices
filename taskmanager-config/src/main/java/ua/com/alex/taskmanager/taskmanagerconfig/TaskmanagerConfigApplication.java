package ua.com.alex.taskmanager.taskmanagerconfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class TaskmanagerConfigApplication {

  public static void main(String[] args) {
    SpringApplication.run(TaskmanagerConfigApplication.class, args);
  }

}
