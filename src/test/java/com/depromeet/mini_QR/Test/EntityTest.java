package com.depromeet.mini_QR.Test;


import java.util.ArrayList;
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
import com.depromeet.mini_QR.domain.dto.SeminarRoomDto;
import com.depromeet.mini_QR.domain.entity.Comment;
import com.depromeet.mini_QR.domain.entity.Member;
import com.depromeet.mini_QR.domain.entity.SeminarRoom;
import com.depromeet.mini_QR.domain.repository.CommentRepository;
import com.depromeet.mini_QR.domain.repository.MemberRepository;
import com.depromeet.mini_QR.domain.repository.SeminarRoomRepository;
import com.depromeet.mini_QR.domain.service.SeminarRoomService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {WebConfig.class, PersistenceJPAConfig.class}, loader=AnnotationConfigWebContextLoader.class)
public class EntityTest {
	@Autowired
	SeminarRoomRepository seminarRoomRepository;
	@Autowired
	CommentRepository commentRepository;
	@Autowired
	SeminarRoomService seminarRoomService;
	@Autowired
	MemberRepository memRepository;

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
		
		SeminarRoom s= SeminarRoom.builder()
				.seminarTitle("새로운세미나생성2")
				.build();
		
		SeminarRoomDto sDto=seminarRoomService.createSeminar(s);
		System.out.println(sDto);
		
//		Member m1 = Member.builder().build();
//		Member m2 = Member.builder().build();
//		Member m3 = Member.builder().build();
////		List<Member> mList = new ArrayList<Member>();
////		mList.add(m1);
////		mList.add(m2);
////		mList.add(m3);
////		memRepository.save(m1);
////		memRepository.save(m2);
////		memRepository.save(m3);
//		System.out.println(m1);
//		memRepository.save(m1);
//		System.out.println(memRepository.count());
	}
	
//	public void allDelete() {
//		seminarRoomRepository.deleteAll();
//		commentRepository.deleteAll();
//	}
}
