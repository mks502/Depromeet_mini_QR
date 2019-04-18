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
import com.depromeet.mini_QR.domain.entity.SeminarRoom;
import com.depromeet.mini_QR.domain.entity.Comment;
import com.depromeet.mini_QR.domain.repository.SeminarRoomRepository;
import com.depromeet.mini_QR.domain.repository.CommentRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {WebConfig.class, PersistenceJPAConfig.class}, loader=AnnotationConfigWebContextLoader.class)
public class EntityTest {
	@Autowired
	SeminarRoomRepository seminarRoomRepository;
	@Autowired
	CommentRepository commentRepository;

	@Test
	public void test() {		
		SeminarRoom seminarRoom1 = SeminarRoom.builder()
				.seminarTitle("채팅방1")
				.build();
		SeminarRoom seminarRoom2 = SeminarRoom.builder()
				.seminarTitle("채팅방2")
				.build();
		Comment c1=Comment.builder()
				.content("댓글1").seminarRoom(seminarRoom1).likeCount(20)
				.build();
		Comment c2=Comment.builder()
				.content("댓글2").seminarRoom(seminarRoom1)
				.build();
		Comment c3=Comment.builder()
				.content("댓글3").seminarRoom(seminarRoom1)
				.build();
		
		seminarRoomRepository.save(seminarRoom1);
		seminarRoomRepository.save(seminarRoom2);
		commentRepository.save(c1);
		commentRepository.save(c2);
		commentRepository.save(c3);

		List<Comment> commentList= commentRepository.findBySeminarRoom(seminarRoom1);
		for (Comment comment : commentList) {
			System.out.println(comment);
		}
		SeminarRoom findedSeminarRoom= seminarRoomRepository.findBySeminarTitle("채팅방1");
		
		findedSeminarRoom.setSeminarTitle("변경된 채팅방1");
		seminarRoomRepository.save(findedSeminarRoom);
		
		
		commentList= commentRepository.findBySeminarRoom(seminarRoom1);
		for (Comment comment : commentList) {
			System.out.println(comment);
		}
		
		
		
			
	}
	
//	public void allDelete() {
//		seminarRoomRepository.deleteAll();
//		commentRepository.deleteAll();
//	}
}
