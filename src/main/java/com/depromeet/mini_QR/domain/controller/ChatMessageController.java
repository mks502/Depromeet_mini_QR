package com.depromeet.mini_QR.domain.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

import com.depromeet.mini_QR.domain.dto.CommentDto;
import com.depromeet.mini_QR.domain.dto.CommentSendDto;
import com.depromeet.mini_QR.domain.dto.RankingSendDto;
import com.depromeet.mini_QR.domain.dto.SeminarRoomDto;
import com.depromeet.mini_QR.domain.entity.Comment;
import com.depromeet.mini_QR.domain.entity.SeminarRoom;
import com.depromeet.mini_QR.domain.repository.CommentRepository;
import com.depromeet.mini_QR.domain.repository.SeminarRoomRepository;
import com.depromeet.mini_QR.domain.service.CommentService;

@Controller
public class ChatMessageController {
	private final SimpMessagingTemplate template;
	
	@Autowired
	CommentService commentService;
	@Autowired
	CommentRepository commentRepository;
	
	@Autowired 
	SeminarRoomRepository srRepo;

    @Autowired
    public ChatMessageController(SimpMessagingTemplate template) {
    	System.out.println("먹나요??");
        this.template = template;
    }

    @MessageMapping("/updates")
    public void message(Comment message, @PathVariable Map<String, Object> jsonData) {
    	
    	
    	System.out.println("message 전송시작========================================= ");
    	
        
        // message.setSeminarRoom((SeminarRoom)jsonData.get("seminarId"));
        commentService.postComment(message);
        
        
        //System.out.println("seminarId = "+jsonData.get("seminarId"));
        String id=(String) jsonData.get("seminarId");
        
        SeminarRoom s= srRepo.findBySeminarId(Long.parseLong(id));
        message.setSeminarRoom(s);
        commentRepository.save(message);
        System.out.println(message.getSeminarRoom().getSeminarId());
        String a=message.getSeminarRoom().getSeminarId().toString();
        System.out.println("Aa"+a);
        
        CommentSendDto commentSendDto = CommentSendDto.builder()
        		.type("comment").comment(message)
        		.build();
        
        this.template.convertAndSend("/subscribe/seminar/"+a, commentSendDto);
        
        System.out.println("message = "+message+" "+id);
        
        System.out.println("======================================================= ");
        // @SendTo: /seminar/{seminarId}
    }
    
    @MessageMapping("/like")
    public void like(Comment message, @PathVariable Map<String, Object> jsonData) {
    	
    	
    	System.out.println("message 전송시작========================================= ");
    	
        
        // message.setSeminarRoom((SeminarRoom)jsonData.get("seminarId"));
   
        String id=(String) jsonData.get("seminarId");
        SeminarRoom s= srRepo.findBySeminarId(Long.parseLong(id));
        message.setSeminarRoom(s);
        message.setLikeCount( message.getLikeCount()+1 );
        
        commentRepository.save(message);
        
        System.out.println(message.getSeminarRoom().getSeminarId());
        String a=message.getSeminarRoom().getSeminarId().toString();
        System.out.println("Aa"+a);
        
        CommentSendDto commentSendDto = CommentSendDto.builder()
        		.type("like").comment(message)
        		.build();
        
        
        this.template.convertAndSend("/subscribe/seminar/"+a, commentSendDto);
        
        System.out.println("message = "+message+" "+id);
        
        System.out.println("======================================================= ");
        // @SendTo: /seminar/{seminarId}
    }
    
    @MessageMapping("/unlike")
    public void unlike(Comment message, @PathVariable Map<String, Object> jsonData) {
    	
    	
    	System.out.println("message 전송시작========================================= ");
    	
        
        // message.setSeminarRoom((SeminarRoom)jsonData.get("seminarId"));
   
        String id=(String) jsonData.get("seminarId");
        SeminarRoom s= srRepo.findBySeminarId(Long.parseLong(id));
        message.setSeminarRoom(s);
        message.setLikeCount( message.getLikeCount()-1 );
        
        commentRepository.save(message);
        
        System.out.println(message.getSeminarRoom().getSeminarId());
        String a=message.getSeminarRoom().getSeminarId().toString();
        System.out.println("Aa"+a);
        
        CommentSendDto commentSendDto = CommentSendDto.builder()
        		.type("like").comment(message)
        		.build();
        
        
        
        this.template.convertAndSend("/subscribe/seminar/"+a, commentSendDto);
        
        System.out.println("message = "+message+" "+id);
        
        System.out.println("======================================================= ");
        // @SendTo: /seminar/{seminarId}
    }
    
    ////  완료!!!!!
    public void getCommentRanking(Long seminarId) {
    	////절대값 수정해주세요
    	seminarId = (long) 13;
    		
    	SeminarRoomDto seminarRoomDto = SeminarRoomDto.builder()
    			.seminarId(seminarId)
    			.build();
    	
    	List<Comment> commentList = commentRepository.findTop3BySeminarRoomOrderByLikeCountDesc(seminarRoomDto.toEntity());
    	
    	RankingSendDto rankingSendDto = RankingSendDto.builder()
        		.type("ranking").commentList(commentList)
        		.build();
    	
    	System.out.println(rankingSendDto);
    	
    }

}
