package com.depromeet.mini_QR.domain.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

import com.depromeet.mini_QR.domain.dto.CommentDto;
import com.depromeet.mini_QR.domain.entity.Comment;
import com.depromeet.mini_QR.domain.entity.SeminarRoom;
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

    @MessageMapping("/updates")
    public void message(Comment message, @PathVariable Map<String, Object> jsonData) {
    	System.out.println("message 전송시작========================================= ");
    	
        template.convertAndSend("/seminar/" + message.getSeminarRoom(), message);
        // message.setSeminarRoom((SeminarRoom)jsonData.get("seminarId"));
        commentService.postComment(message);
        
        System.out.println("seminarId = "+jsonData.get("seminarId"));
        System.out.println("message = "+message);
        
        System.out.println("======================================================= ");
        // @SendTo: /seminar/{seminarId}
    }

}
