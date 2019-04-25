package com.depromeet.mini_QR.domain.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.depromeet.mini_QR.domain.dto.CommentDto;
import com.depromeet.mini_QR.domain.entity.Comment;
import com.depromeet.mini_QR.domain.service.CommentService;

@Controller
public class ChatMessageController {
	private final SimpMessagingTemplate template;
	
	@Autowired
	CommentService commentService;

    @Autowired
    public ChatMessageController(SimpMessagingTemplate template) {
        this.template = template;
    }

    // @MessageMapping("/")
    @MessageMapping("/updates")
    public void message(Comment message) {
    	System.out.println("message 전송 메소드 들어오나요 ");
        template.convertAndSend("/seminar/" + message.getSeminarRoom(), message);
        commentService.postComment(message);
        System.out.println("message = "+message);
        // @SendTo: /seminar/{seminarId}
    }

}
