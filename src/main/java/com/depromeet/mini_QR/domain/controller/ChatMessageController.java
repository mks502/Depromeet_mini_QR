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

    @MessageMapping("/updates")
    public void message(Comment message) {
        template.convertAndSend("/seminar/39" + message.getSeminarRoom(), message);
        // @SendTo: /seminar/{seminarId}
        // template.convertAndSend("/seminar/{seminarId}" + message.getSeminarRoom(), message);
    }

}
