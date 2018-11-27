package com.kelan.message.interceptor;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * <p></p>
 *
 * @author JG
 * @version V1.0.0
 * @date 2018/11/27 15:22
 * @see
 */
public class SpringWebSocketHandlerInterceptor extends HttpSessionHandshakeInterceptor {
  private static Logger logger = LoggerFactory.getLogger(SpringWebSocketHandlerInterceptor.class);
  @Override
  public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
                                 Map<String, Object> attributes) throws Exception {
    if (request instanceof ServletServerHttpRequest) {
      ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
      HttpSession session = servletRequest.getServletRequest().getSession(false);
      if (session != null) {
        //使用sessionId区分WebSocketHandler，以便定向发送消息
        String sessionId = session.getId();
        if (StringUtils.isBlank(sessionId)) {
          logger.error("sessionId is null");
          return false;
        }
        attributes.put("SESSION_ID",sessionId);
      }
    }
    return super.beforeHandshake(request, response, wsHandler, attributes);
  }

  @Override
  public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
                             Exception ex) {
    super.afterHandshake(request, response, wsHandler, ex);
  }
}
