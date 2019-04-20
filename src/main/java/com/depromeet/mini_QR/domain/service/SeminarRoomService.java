package com.depromeet.mini_QR.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.depromeet.mini_QR.domain.dto.SeminarRoomDto;
import com.depromeet.mini_QR.domain.entity.SeminarRoom;
import com.depromeet.mini_QR.domain.repository.SeminarRoomRepository;

@Service
public class SeminarRoomService {
	@Autowired
	SeminarRoomRepository seminarRoomRepository;
	
	public SeminarRoomDto createSeminar(SeminarRoom seminarRoom) {
		seminarRoomRepository.save(seminarRoom);
		String title= seminarRoom.getSeminarTitle();
		SeminarRoom newSeminar =seminarRoomRepository.findBySeminarTitle(title);
		String full= "/seminar/";
		String seminarid= Long.toString(newSeminar.getSeminarId());
		full.concat(seminarid);
		
		newSeminar.setFullURL(full.concat(seminarid));
		seminarRoomRepository.save(newSeminar);
		return newSeminar.toDto();
	}
}
