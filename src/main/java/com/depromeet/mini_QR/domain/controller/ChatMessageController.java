package com.depromeet.mini_QR.domain.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.depromeet.mini_QR.domain.entity.Comment;

@Controller
public class ChatMessageController {
	private final SimpMessagingTemplate template;

    @Autowired
    public ChatMessageController(SimpMessagingTemplate template) {
        this.template = template;
    }

    @MessageMapping("/chat/join")
    public void join(Comment message) {
        message.setContent(message.getCommentId() + "님이 입장하셨습니다.");
        template.convertAndSend("/subscribe/chat/room/" + message.getSeminarRoom(), message);
    }
    @MessageMapping("/chat/message")
    public void message(Comment message) {
        template.convertAndSend("/subscribe/chat/room/" + message.getSeminarRoom(), message);
    }

}
