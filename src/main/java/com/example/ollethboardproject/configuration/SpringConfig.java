//package com.example.ollethboardproject.configuration;
//
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.messaging.simp.config.MessageBrokerRegistry;
//import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
//import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
//import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
//
//@Configuration
//@EnableWebSocketMessageBroker
//public class SpringConfig implements WebSocketMessageBrokerConfigurer {
//
//
//    @Override
//    public void registerStompEndpoints(StompEndpointRegistry registry) {
//        //stomp 접속시 url -> ws-stomp
//        registry.addEndpoint("ws-stomp")// 연결될 엔드포인트
//                .setAllowedOrigins("*")
//                .withSockJS();      // SocketJS 를 연결한다는 설정
//    }
//
//    @Override
//    public void configureMessageBroker(MessageBrokerRegistry registry) {
//
//        //메시지를 구독하는 요청 url -> 메시지를 받을 때
//        registry.enableSimpleBroker("/sub");
//
//        // 메시지를 발행하는 요청 url -> 메시지를 보낼 때
//        registry.setApplicationDestinationPrefixes("/pub");
//    }
//}
