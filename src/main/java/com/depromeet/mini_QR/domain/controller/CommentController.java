package com.depromeet.mini_QR.domain.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.depromeet.mini_QR.domain.dto.RankingSendDto;
import com.depromeet.mini_QR.domain.dto.SeminarRoomDto;
import com.depromeet.mini_QR.domain.entity.Comment;
import com.depromeet.mini_QR.domain.repository.CommentRepository;
import com.depromeet.mini_QR.domain.service.CommentService;

@RestController
@RequestMapping(value = "/api/comments")  //임시 api       /api/{seminarRoomID}/comments 이런 형식예상..
public class CommentController {
	
	@Autowired
	CommentService commentService;
	
	@Autowired
	CommentRepository commentRepository;
	
	@PostMapping(value = "/post")
    public void enrollComment(@RequestBody Comment comment, HttpServletRequest request){
		System.out.println(comment);
		commentService.postComment(comment);
		System.out.println("받아");
	}
	
	
	@PostMapping(value = "/ranking")
	public void getCommentRanking(Long seminarId) {
    	////절대값 수정해주세요 :)
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
