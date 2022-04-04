package ua.com.alex.taskmanager.taskmanagerserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class TaskmanagerServerApplication {

  public static void main(String[] args) {
    SpringApplication.run(TaskmanagerServerApplication.class, args);
  }

}
