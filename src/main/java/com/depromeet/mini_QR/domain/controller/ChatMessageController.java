package com.depromeet.mini_QR.domain.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

import com.depromeet.mini_QR.domain.dto.CommentSendDto;
import com.depromeet.mini_QR.domain.dto.RankingSendDto;
import com.depromeet.mini_QR.domain.dto.SeminarRoomDto;
import com.depromeet.mini_QR.domain.entity.Comment;
import com.depromeet.mini_QR.domain.entity.SeminarRoom;
import com.depromeet.mini_QR.domain.repository.CommentRepository;
import com.depromeet.mini_QR.domain.repository.SeminarRoomRepository;
import com.depromeet.mini_QR.domain.service.CommentService;

/**
 * 채팅 메시지를 위한 컨트롤러
 * STOMP 방식 WebSocket 통신
 * @author Kimshinje, Makyuseok
 *
 */
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
        this.template = template;
    }

    /**
     * chatMessage 송수신
     * @param message
     * @param jsonData
     */
    @MessageMapping("/updates")
    public void message(Comment message, @PathVariable Map<String, Object> jsonData) {
    	String seminarId = (String)jsonData.get("seminarId");
        SeminarRoom seminarRoom = srRepo.findBySeminarId(Long.parseLong(seminarId));

    	commentService.postComment(message);
        message.setSeminarRoom(seminarRoom);
        commentRepository.save(message);
        CommentSendDto commentSendDto = CommentSendDto.builder()
        		.type("comment").comment(message)
        		.build();

        this.template.convertAndSend("/subscribe/seminar/"+seminarId, commentSendDto);
    }

    /**
     * like된 ChatMessage 송수신
     * @param message
     * @param jsonData
     */
    @MessageMapping("/like")
    public void like(Comment message, @PathVariable Map<String, Object> jsonData) {
        String seminarId = (String) jsonData.get("seminarId");
        String commentId = (String) jsonData.get("commentId");
        SeminarRoom seminarRoom = srRepo.findBySeminarId(Long.parseLong(seminarId));
        Comment comment = commentRepository.findByCommentId(Long.parseLong(commentId));

        message.setSeminarRoom(seminarRoom);
        message.setLikeCount((comment.getLikeCount()+1));
        message.setContent(comment.getContent());
        commentRepository.save(message);
        CommentSendDto commentSendDto = CommentSendDto.builder()
        		.type("like").comment(message)
        		.build();

        this.template.convertAndSend("/subscribe/seminar/"+seminarId, commentSendDto);
        getCommentRanking(Long.parseLong(seminarId));
    }

    /**
     * unlike된 ChatMessage 송수신
     * @param message
     * @param jsonData
     */
    @MessageMapping("/unlike")
    public void unlike(Comment message, @PathVariable Map<String, Object> jsonData) {
        String seminarId = (String) jsonData.get("seminarId");
        String commentId = (String) jsonData.get("commentId");
        SeminarRoom seminarRoom = srRepo.findBySeminarId(Long.parseLong(seminarId));
        Comment comment = commentRepository.findByCommentId(Long.parseLong(commentId));

        message.setSeminarRoom(seminarRoom);
        message.setLikeCount((comment.getLikeCount()-1));
        message.setContent(comment.getContent());
        commentRepository.save(message);

        CommentSendDto commentSendDto = CommentSendDto.builder()
        		.type("like").comment(message)
        		.build();

        this.template.convertAndSend("/subscribe/seminar/"+seminarId, commentSendDto);
        getCommentRanking(Long.parseLong(seminarId));
    }

    /**
     * like랭킹 Top3 ChatMessage를 가져옴
     * @param seminarId
     */
    public void getCommentRanking(Long seminarId) {
    	SeminarRoomDto seminarRoomDto = SeminarRoomDto.builder()
    			.seminarId(seminarId)
    			.build();

    	List<Comment> commentList = commentRepository
    			.findTop3BySeminarRoomOrderByLikeCountDesc(seminarRoomDto.toEntity());

    	RankingSendDto rankingSendDto = RankingSendDto.builder()
        		.type("ranking").commentList(commentList)
        		.build();
    	this.template.convertAndSend("/subscribe/seminar/"+seminarId, rankingSendDto);
    }

}
