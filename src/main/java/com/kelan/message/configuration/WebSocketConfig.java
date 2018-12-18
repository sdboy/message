package com.kelan.message.configuration;

import com.kelan.message.handler.SpringWebSocketHandler;
import com.kelan.message.interceptor.SpringWebSocketHandlerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * <p></p>
 *
 * @author JG
 * @version V1.0.0
 * @date 2018/11/27 15:06
 * @see
 */
@Configuration
@EnableWebMvc
@EnableWebSocket
public class WebSocketConfig implements WebMvcConfigurer, WebSocketConfigurer {

  @Override
  public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
    registry.addHandler(webSocketHandler(), "/websocket/socketServer.do").
        addInterceptors(new SpringWebSocketHandlerInterceptor());
    registry.addHandler(webSocketHandler(), "/sockjs/socketServer.do").
        addInterceptors(new SpringWebSocketHandlerInterceptor()).withSockJS();
  }
  @Bean
  public TextWebSocketHandler webSocketHandler(){
    return new SpringWebSocketHandler();
  }
}
