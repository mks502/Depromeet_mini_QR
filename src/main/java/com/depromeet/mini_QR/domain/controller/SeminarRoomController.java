package com.depromeet.mini_QR.domain.controller;

import java.io.IOException;
import java.net.MalformedURLException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@PostMapping(value = "/create")
	public SeminarRoomDto setSeminarName(@RequestBody SeminarRoom seminarTitle, HttpServletRequest request) throws MalformedURLException, IOException{
		SeminarRoomDto newSeminar = seminarRoomService.createSeminar(seminarTitle);
		return newSeminar;
	}

}
