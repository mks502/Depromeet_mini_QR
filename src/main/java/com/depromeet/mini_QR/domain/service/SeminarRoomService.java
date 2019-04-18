package com.depromeet.mini_QR.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.depromeet.mini_QR.domain.entity.SeminarRoom;
import com.depromeet.mini_QR.domain.repository.SeminarRoomRepository;

@Service
public class SeminarRoomService {
	@Autowired
	SeminarRoomRepository seminarRoomRepository;
	
	public void postSeminarRoomTitle(SeminarRoom seminarRoom) {
		seminarRoomRepository.save(seminarRoom);
	}
}
