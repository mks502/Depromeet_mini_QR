package com.depromeet.mini_QR.domain.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.depromeet.mini_QR.domain.entity.Comment;
import com.depromeet.mini_QR.domain.service.CommentService;

@RestController
@RequestMapping(value = "/api/comments")  //임시 api       /api/{seminarRoomID}/comments 이런 형식예상..
public class CommentController {
	
	@Autowired
	CommentService commentService;
	
	@PostMapping(value = "/post")
    public void enrollComment(@RequestBody Comment comment, HttpServletRequest request){
		System.out.println(comment);
		commentService.postComment(comment);
		System.out.println("받아");
	}
}
