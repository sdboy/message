package com.kelan.message.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p></p>
 *
 * @author JG
 * @version V1.0.0
 * @date 2018/11/27 15:19
 * @see
 */
public class SpringWebSocketHandler  extends TextWebSocketHandler {
  private static final Map<String, WebSocketSession> USERS;
  private static Logger logger = LoggerFactory.getLogger(SpringWebSocketHandler.class);
  static {
    USERS = new ConcurrentHashMap<>();
  }

  public SpringWebSocketHandler() {

  }

  @Override
  public void afterConnectionEstablished(WebSocketSession session) throws Exception {
    System.out.println("connect to the websocket success......当前数量:"+USERS.size());
    String sessionId = (String) session.getAttributes().get("SESSION_ID");
    USERS.put(sessionId, session);
     TextMessage returnMessage = new TextMessage("登录成功");
     session.sendMessage(returnMessage);
  }
  @Override
  public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
    logger.debug("websocket connection closed......");
    String sessionId = (String) session.getAttributes().get("SESSION_ID");
    System.out.println("用户" + sessionId + "已退出！");
    USERS.remove(sessionId);
    System.out.println("剩余在线用户" + USERS.size());
  }

  @Override
  protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
    super.handleTextMessage(session, message);
  }

  @Override
  public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
    if(session.isOpen()) {
      session.close();
    }
    logger.debug("websocket connection closed......");
    String sessionId = (String) session.getAttributes().get("SESSION_ID");
    USERS.remove(sessionId);
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
   * @return void
   * @since V1.0.0
   * @version V1.0.0
   */
  public static void sendMessageToUser(String sessionId, TextMessage message) {
    for (Map.Entry<String, WebSocketSession> entry : USERS.entrySet()) {
      WebSocketSession user = entry.getValue();
      if (user.getAttributes().get("SESSION_ID").equals(sessionId)) {
        try {
          if (user.isOpen()) {
            user.sendMessage(message);
          }
        } catch (IOException e) {
          logger.error("给用户发送信息异常");
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
  public static void sendMessageToUsers(TextMessage message) {
    for (Map.Entry<String, WebSocketSession> entry : USERS.entrySet()) {
      try {
        WebSocketSession user = entry.getValue();
        if (user.isOpen()) {
          user.sendMessage(message);
        }
      } catch (IOException e) {
        logger.error("给用户发送信息失败");
        e.printStackTrace();
      }
    }
  }

}
