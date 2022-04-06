package ua.com.alex.taskmanager.users.mq.func;

import lombok.Getter;

import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Sinks;

/**
 * @author Alex
 * @link https://intvw.me
 */
@Service
@Getter
public class MessageFuncAction {

  private MessageFunc messageFunc;

  public MessageFuncAction(MessageFunc streamFunctions) {
    this.messageFunc = streamFunctions;
  }

  public void sendNewUserMessage(Long id) {

    messageFunc.getInnerBus().emitNext(MessageBuilder.withPayload(id).build(), Sinks.EmitFailureHandler.FAIL_FAST);
    System.out.println("Message sent: " + id);
  }
}
