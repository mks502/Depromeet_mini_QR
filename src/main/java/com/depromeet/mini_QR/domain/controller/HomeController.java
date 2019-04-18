package com.depromeet.mini_QR.domain.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.depromeet.mini_QR.domain.entity.SeminarRoom;
import com.depromeet.mini_QR.domain.service.SeminarRoomService;
@RestController
public class HomeController {
	
	@GetMapping(value = "/")
    public String index(HttpServletRequest req, HttpServletResponse res) {
        return "home";
    }
	
	@Autowired
	SeminarRoomService seminarRoomService;
	
	@PostMapping(value = "/seminar/{seminarId}")
	public void setSeminarName(@RequestBody SeminarRoom seminarTitle, HttpServletRequest request){
		seminarRoomService.postSeminarRoomTitle(seminarTitle);
	}
}