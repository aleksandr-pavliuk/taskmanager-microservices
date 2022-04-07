package ua.com.alex.taskmanager.users.mq.legacy;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

/**
 * @author Alex
 * @link https://intvw.me
 */
//@Component
//@EnableBinding(TodoBinding.class)
public class MessageProducer {

//  private TodoBinding todoBinding;
//
//  public MessageProducer(TodoBinding todoBinding) {
//    this.todoBinding = todoBinding;
//  }
//
//  public void initUserData(Long userId){
//
//    Message message = MessageBuilder.withPayload(userId).build();
//
//    todoBinding.todoOutputChannel().send(message);
//  }
}
