package com.depromeet.mini_QR.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.depromeet.mini_QR.domain.entity.Comment;
import com.depromeet.mini_QR.domain.repository.CommentRepository;

@Service
public class CommentService {
	@Autowired
	CommentRepository commentRepository;
	
	/**
	 * 작성된 코멘트 메시지 저장 
	 * @param comment
	 */
	public void postComment(Comment comment) {
		commentRepository.save(comment);
	}
}
