package com.depromeet.mini_QR.Test;

import java.io.IOException;
import java.net.MalformedURLException;
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
import com.depromeet.mini_QR.domain.dto.CommentSendDto;
import com.depromeet.mini_QR.domain.dto.SeminarRoomDto;
import com.depromeet.mini_QR.domain.entity.Comment;
import com.depromeet.mini_QR.domain.entity.SeminarRoom;
import com.depromeet.mini_QR.domain.repository.CommentRepository;
import com.depromeet.mini_QR.domain.service.SeminarRoomService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { WebConfig.class,
		PersistenceJPAConfig.class }, loader = AnnotationConfigWebContextLoader.class)

public class SeminarRoomTest {
	@Autowired
	SeminarRoomService sr;

	@Autowired
	CommentRepository cr;

//	@Test
//	public void test() throws MalformedURLException, IOException{
//		String shortUrl = sr.createShortUrl("https://lvh.me/mini_QR/api/seminar/1");	
//		System.out.println("asdfafa");
//		System.out.println(shortUrl);
//	}
	@Test
	public void test() {

		SeminarRoom seminarRoom = SeminarRoom.builder()
				.seminarId((long) 2)
				.build();

		// System.out.println(cr.findTop3BySeminarRoomOrderByLikeCountDesc(seminarRoom).size());

		List<Comment> commentList = cr
				.findTop3BySeminarRoomOrderByLikeCountDesc(seminarRoom);

		// List를 Comment로 변환할 수 없어 캐스팅 에러가 남 
		CommentSendDto commentSendDto = CommentSendDto.builder()
				.type("ranking")
				.comment((Comment)commentList)
				.build();
		
		System.out.println("commentSendDto = "+commentSendDto);
	}

}
