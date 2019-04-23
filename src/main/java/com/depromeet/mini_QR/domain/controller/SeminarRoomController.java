package com.depromeet.mini_QR.domain.controller;

import java.io.IOException;
import java.net.MalformedURLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.depromeet.mini_QR.domain.dto.SeminarRoomDto;
import com.depromeet.mini_QR.domain.entity.SeminarRoom;
import com.depromeet.mini_QR.domain.service.SeminarRoomService;

@RestController
@RequestMapping(value = "/api/seminar")
public class SeminarRoomController {
	@Autowired
	SeminarRoomService seminarRoomService;
	
//	@PostMapping(value = "/create")        //기존 ajax 방식 REST
//	public SeminarRoomDto setSeminarName(@RequestBody SeminarRoom seminarTitle, HttpServletRequest request) throws MalformedURLException, IOException{
//		SeminarRoomDto newSeminar = seminarRoomService.createSeminar(seminarTitle);
//		return newSeminar;
//	}
	
	@PostMapping(value = "/create")      //FormData 방식
	public SeminarRoomDto setSeminarName(SeminarRoom seminarTitle) throws MalformedURLException, IOException{
		System.out.println(seminarTitle);
		SeminarRoomDto newSeminar = seminarRoomService.createSeminar(seminarTitle);
		return newSeminar;
	}
}
