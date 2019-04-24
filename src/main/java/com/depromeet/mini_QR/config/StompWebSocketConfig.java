package com.depromeet.mini_QR.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;


@EnableWebSocketMessageBroker
@Configuration
public class StompWebSocketConfig implements WebSocketMessageBrokerConfigurer{
	@Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/q-rank-websock").setAllowedOrigins("*").withSockJS();
    }
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
    	registry.setApplicationDestinationPrefixes("/updates");
        registry.enableSimpleBroker("/seminar");
//        registry.setApplicationDestinationPrefixes("/publish");
//        registry.enableSimpleBroker("/subscribe");
    }
	
}
