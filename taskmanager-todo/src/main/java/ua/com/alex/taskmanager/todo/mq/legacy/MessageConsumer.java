package ua.com.alex.taskmanager.todo.mq.legacy;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;
import ua.com.alex.taskmanager.todo.sevice.TestDataService;

/**
 * @author Alex
 * @link https://intvw.me
 */
//@EnableBinding(TodoBinding.class)
//@Component
public class MessageConsumer {

//  private TestDataService testDataService;
//
//  public MessageConsumer(TestDataService testDataService) {
//    this.testDataService = testDataService;
//  }
//
//  @StreamListener(target = TodoBinding.INPUT_CHANNEL)
//  public void initTestData(Long userId){
//    testDataService.initTestData(userId);
//  }
}
