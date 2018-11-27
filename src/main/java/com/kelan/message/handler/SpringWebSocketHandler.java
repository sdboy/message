package com.kelan.message.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * <p></p>
 *
 * @author JG
 * @version V1.0.0
 * @date 2018/11/27 15:19
 * @see
 */
public class SpringWebSocketHandler  extends TextWebSocketHandler {
  private static final Map<String, WebSocketSession> users;
  private static Logger logger = LoggerFactory.getLogger(SpringWebSocketHandler.class);
  static {
    users = new HashMap<>();
  }

  public SpringWebSocketHandler() {

  }

  @Override
  public void afterConnectionEstablished(WebSocketSession session) throws Exception {
    System.out.println("connect to the websocket success......当前数量:"+users.size());
    users.add(session);
    //这块会实现自己业务，比如，当用户登录后，会把离线消息推送给用户
    //TextMessage returnMessage = new TextMessage("你将收到的离线");
    //session.sendMessage(returnMessage);
  }
  @Override
  public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
    logger.debug("websocket connection closed......");
    String username= (String) session.getAttributes().get("WEBSOCKET_USERNAME");
    System.out.println("用户"+username+"已退出！");
    users.remove(session);
    System.out.println("剩余在线用户"+users.size());
  }

  @Override
  protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
    super.handleTextMessage(session, message);
  }

  @Override
  public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
    if(session.isOpen()){session.close();}
    logger.debug("websocket connection closed......");
    users.remove(session);
  }

  @Override
  public boolean supportsPartialMessages() {
    return false;
  }

  /**
   * <p>给指定用户发送消息</p>
   * @method sendMessageToUser
   * @author JG
   * @date 2018/11/27 16:33
   * @param [userName, message]
   * @param [userName, message]
   * @return void
   * @since V1.0.0
   * @version V1.0.0
   */
  public void sendMessageToUser(String userName, TextMessage message) {
    for (WebSocketSession user : users) {
      if (user.getAttributes().get("WEBSOCKET_USERNAME").equals(userName)) {
        try {
          if (user.isOpen()) {
            user.sendMessage(message);
          }
        } catch (IOException e) {
          e.printStackTrace();
        }
        break;
      }
    }
  }

  /**
   * <p>发送消息给所有用户</p>
   * @method sendMessageToUsers
   * @author JG
   * @date 2018/11/27 16:34
   * @param message 消息
   * @return void
   * @since V1.0.0
   * @version V1.0.0
   */
  public void sendMessageToUsers(TextMessage message) {
    for (WebSocketSession user : users) {
      try {
        if (user.isOpen()) {
          user.sendMessage(message);
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

}
