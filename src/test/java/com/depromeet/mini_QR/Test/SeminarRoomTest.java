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
import com.depromeet.mini_QR.domain.dto.RankingSendDto;
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

	@Test
	public void test() {

		SeminarRoom seminarRoom = SeminarRoom.builder()
				.seminarId((long) 13)
				.build();

		List<Comment> commentList = cr
				.findTop3BySeminarRoomOrderByLikeCountDesc(seminarRoom);

		RankingSendDto rankingSendDto = RankingSendDto.builder()
				.type("ranking")
				.commentList(commentList)
				.build();
		
		System.out.println("rankingSendDto = "+rankingSendDto);
	}

}
