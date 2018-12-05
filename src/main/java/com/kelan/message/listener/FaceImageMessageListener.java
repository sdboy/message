package com.kelan.message.listener;

import com.kelan.message.handler.SpringWebSocketHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * <p></p>
 *
 * @author JG
 * @version V1.0.0
 * @date 2018/11/26 14:58
 * @see
 */
public class FaceImageMessageListener implements MessageListener {
  private Logger logger = LoggerFactory.getLogger(FaceImageMessageListener.class);
  @Override
  public void onMessage(Message message) {
    TextMessage tm = (TextMessage) message;
    try {
      String text = tm.getText();
      org.springframework.web.socket.TextMessage stm = new org.springframework.web.socket.TextMessage(text);
      System.out.println(text);
      SpringWebSocketHandler.sendMessageToUsers(stm);
    }catch (JMSException e) {
      logger.error(e.getMessage());
    }
  }


}
