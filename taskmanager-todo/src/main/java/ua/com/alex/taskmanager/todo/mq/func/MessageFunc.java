package ua.com.alex.taskmanager.todo.mq.func;

import java.util.function.Consumer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import ua.com.alex.taskmanager.todo.sevice.TestDataService;

/**
 * @author Alex
 * @link https://intvw.me
 */
@Configuration
public class MessageFunc {

  private TestDataService testDataService;

  public MessageFunc(TestDataService testDataService) {
    this.testDataService = testDataService;
  }

  @Bean
  public Consumer<Message<Long>> newUserActionConsume() {
    return message -> testDataService.initTestData(message.getPayload());
  }

}
