package com.depromeet.mini_QR.Test;


import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;

import com.depromeet.mini_QR.config.PersistenceJPAConfig;
import com.depromeet.mini_QR.config.WebConfig;
import com.depromeet.mini_QR.domain.entity.ChatRoom;
import com.depromeet.mini_QR.domain.entity.Comment;
import com.depromeet.mini_QR.domain.repository.ChatRoomRepository;
import com.depromeet.mini_QR.domain.repository.CommentRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {WebConfig.class, PersistenceJPAConfig.class}, loader=AnnotationConfigWebContextLoader.class)
public class EntityTest {
	@Autowired
	ChatRoomRepository chatRoomRepository;
	@Autowired
	CommentRepository commentRepository;

	@Test
	public void test() {		
		ChatRoom chatroom1 = ChatRoom.builder()
				.chatTitle("채팅방1")
				.build();
		ChatRoom chatroom2 = ChatRoom.builder()
				.chatTitle("채팅방2")
				.build();
		Comment c1=Comment.builder()
				.content("댓글1").chatRoom(chatroom1).likeCount(20)
				.build();
		Comment c2=Comment.builder()
				.content("댓글2").chatRoom(chatroom1)
				.build();
		Comment c3=Comment.builder()
				.content("댓글3").chatRoom(chatroom1)
				.build();
		
		chatRoomRepository.save(chatroom1);
		chatRoomRepository.save(chatroom2);
		commentRepository.save(c1);
		commentRepository.save(c2);
		commentRepository.save(c3);

		List<Comment> commentList= commentRepository.findBychatRoom(chatroom1);
		for (Comment comment : commentList) {
			System.out.println(comment);
		}
		ChatRoom findedChatRoom= chatRoomRepository.findByChatTitle("채팅방1");
		
		findedChatRoom.setChatTitle("변경된 채팅방1");
		chatRoomRepository.save(findedChatRoom);
		
		
		commentList= commentRepository.findBychatRoom(chatroom1);
		for (Comment comment : commentList) {
			System.out.println(comment);
		}
		
		
		
			
	}
	
//	public void allDelete() {
//		chatRoomRepository.deleteAll();
//		commentRepository.deleteAll();
//	}
}
