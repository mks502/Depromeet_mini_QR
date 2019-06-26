package com.depromeet.mini_QR.domain.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.depromeet.mini_QR.domain.dto.SeminarRoomDto;
import com.depromeet.mini_QR.domain.entity.Comment;
import com.depromeet.mini_QR.domain.entity.SeminarRoom;
import com.depromeet.mini_QR.domain.repository.CommentRepository;
import com.depromeet.mini_QR.domain.service.SeminarRoomService;

/**
 * SeminarRoom 채팅방 생성과 입장을 위한 컨트롤러 
 * @author Makyuseok, Kimshinje 
 *
 */
@Controller
public class SeminarRoomController {
	@Autowired
	SeminarRoomService seminarRoomService;
	@Autowired
	CommentRepository commentRepository;

	/**
	 * SeminarRoom 채팅방 생성
	 * 
	 * @param seminarTitle
	 * @return
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	@PostMapping(value = "/api/seminar/create") // FormData 방식
	public String setSeminarName(SeminarRoom seminarTitle) throws MalformedURLException, IOException {
		SeminarRoomDto newSeminarRoom = seminarRoomService.createSeminar(seminarTitle);
		String chatRoom = "redirect:/seminar/" + newSeminarRoom.getSeminarId().toString();

		return chatRoom;
	}

	/**
	 * SeminarRoom 채팅방 입장
	 * 
	 * @param seminarId
	 * @return
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	@RequestMapping(value = "/seminar/{seminarId}")
	public ModelAndView EnterSeminarRoom(@PathVariable String seminarId) throws MalformedURLException, IOException {
		ModelAndView modelAndView = new ModelAndView();
		SeminarRoomDto seminarRoom = seminarRoomService.findSeminar(Long.parseLong(seminarId));

		if (seminarRoom == null)
			return null;

		List<Comment> allComments = new ArrayList<Comment>();
		allComments = commentRepository.findAllBySeminarRoom(seminarRoom.toEntity());

		List<Comment> rankingList = commentRepository.findTop3BySeminarRoomOrderByLikeCountDesc(seminarRoom.toEntity());

		modelAndView.addObject("SeminarRoomDto", seminarRoom);
		modelAndView.addObject("allComments", allComments);
		modelAndView.addObject("rankingList", rankingList);
		modelAndView.setViewName("main");

		return modelAndView;
	}
}
