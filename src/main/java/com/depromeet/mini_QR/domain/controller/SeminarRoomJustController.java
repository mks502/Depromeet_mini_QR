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

import com.depromeet.mini_QR.domain.dto.RankingSendDto;
import com.depromeet.mini_QR.domain.dto.SeminarRoomDto;
import com.depromeet.mini_QR.domain.entity.Comment;
import com.depromeet.mini_QR.domain.entity.SeminarRoom;
import com.depromeet.mini_QR.domain.repository.CommentRepository;
import com.depromeet.mini_QR.domain.service.SeminarRoomService;


////그냥 Controller
@Controller
public class SeminarRoomJustController {
	@Autowired
	SeminarRoomService seminarRoomService;
	@Autowired
	CommentRepository commentRepository;
	
//	@PostMapping(value = "/create")        //기존 ajax 방식 REST
//	public SeminarRoomDto setSeminarName(@RequestBody SeminarRoom seminarTitle, HttpServletRequest request) throws MalformedURLException, IOException{
//		SeminarRoomDto newSeminar = seminarRoomService.createSeminar(seminarTitle);
//		return newSeminar;
//	}
	
//	@PostMapping(value = "/create")      //FormData 방식
//	public SeminarRoomDto setSeminarName(SeminarRoom seminarTitle, HttpServletResponse response) throws MalformedURLException, IOException{
//		response.setContentType("text/html;charset=UTF-8");		
//		System.out.println(seminarTitle);
//		SeminarRoomDto newSeminar = seminarRoomService.createSeminar(seminarTitle);
//		return newSeminar;
//	}
	
//	@PostMapping(value = "/create")      //FormData 방식
//	public ModelAndView setSeminarName(SeminarRoom seminarTitle) throws MalformedURLException, IOException{
//		ModelAndView mv = new ModelAndView();
//		System.out.println(seminarTitle);
//		System.out.println("모델엔 뷰야");
//		SeminarRoomDto newSeminar = seminarRoomService.createSeminar(seminarTitle);
//		mv.addObject("SeminarRoomDto",newSeminar);
//		mv.setViewName("main");
//		System.out.println(newSeminar);
//		return mv;
//	}
	
	@PostMapping(value = "/api/seminar1/create")      //FormData 방식
	public String setSeminarName(SeminarRoom seminarTitle) throws MalformedURLException, IOException{
		System.out.println("seminarTitle = "+seminarTitle);
		System.out.println("모델엔 뷰야");
		SeminarRoomDto newSeminar = seminarRoomService.createSeminar(seminarTitle);
		System.out.println("newSeminar = "+newSeminar);
		String move="redirect:/seminar/"+newSeminar.getSeminarId().toString();
		System.out.println("move = "+move);		
		return move;
	}
	
	@RequestMapping(value = "/seminar/{seminarId}")      //방입장
	public ModelAndView EnterSeminarRoom(@PathVariable String seminarId) throws MalformedURLException, IOException{
		ModelAndView mv = new ModelAndView();
		System.out.println(seminarId);
		Long id= Long.parseLong(seminarId);
		SeminarRoomDto seminarRoom=seminarRoomService.findSeminar(id);
		if(seminarRoom==null) {
			return null;
		}
		List<Comment> allComments= new ArrayList<Comment>();
		allComments = commentRepository.findAllBySeminarRoom(seminarRoom.toEntity());
		
		List<Comment> rankingList = commentRepository.findTop3BySeminarRoomOrderByLikeCountDesc(seminarRoom.toEntity());
//		RankingSendDto rankingSendDto = RankingSendDto.builder()
//        		.type("ranking").commentList(rankingList)
//        		.build();
		
		
		mv.addObject("SeminarRoomDto",seminarRoom);
		mv.addObject("allComments",allComments);
		mv.addObject("rankingList", rankingList);
		mv.setViewName("main");
		System.out.println("seminarRoom = "+seminarRoom);
		System.out.println("모든 댓글"+allComments);
		System.out.println("랭킹 리스트 = "+rankingList);
		return mv;
	}
}
