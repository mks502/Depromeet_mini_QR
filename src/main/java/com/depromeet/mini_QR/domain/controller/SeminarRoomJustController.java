package com.depromeet.mini_QR.domain.controller;

import java.io.IOException;
import java.net.MalformedURLException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.depromeet.mini_QR.domain.dto.SeminarRoomDto;
import com.depromeet.mini_QR.domain.entity.SeminarRoom;
import com.depromeet.mini_QR.domain.service.SeminarRoomService;

@Controller
@RequestMapping(value = "/api/seminar1")
public class SeminarRoomJustController {
	@Autowired
	SeminarRoomService seminarRoomService;
	
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
	
	@PostMapping(value = "/create")      //FormData 방식
	public ModelAndView setSeminarName(SeminarRoom seminarTitle) throws MalformedURLException, IOException{
		ModelAndView mv = new ModelAndView();
		System.out.println(seminarTitle);
		System.out.println("모델엔 뷰야");
		SeminarRoomDto newSeminar = seminarRoomService.createSeminar(seminarTitle);
		mv.addObject(newSeminar);
		mv.setViewName("main");
		System.out.println(newSeminar);
		return mv;
	}
}
