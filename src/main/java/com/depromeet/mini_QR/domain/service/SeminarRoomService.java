package com.depromeet.mini_QR.domain.service;

import static com.rosaloves.bitlyj.Bitly.as;
import static com.rosaloves.bitlyj.Bitly.shorten;

import java.io.IOException;
import java.net.MalformedURLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.depromeet.mini_QR.domain.dto.SeminarRoomDto;
import com.depromeet.mini_QR.domain.entity.SeminarRoom;
import com.depromeet.mini_QR.domain.repository.SeminarRoomRepository;
import com.rosaloves.bitlyj.Url;

@Service
public class SeminarRoomService {
	@Autowired
	SeminarRoomRepository seminarRoomRepository;
	
	/**
	 * 새로운 SeminarRoom 채팅방 생성 
	 * @param seminarRoom
	 * @return
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	public SeminarRoomDto createSeminar(SeminarRoom seminarRoom) throws MalformedURLException, IOException {
		seminarRoomRepository.save(seminarRoom);
		String seminarTitle= seminarRoom.getSeminarTitle();
		SeminarRoom newSeminarRoom =seminarRoomRepository.findBySeminarTitle(seminarTitle);
		String fullURL= "/seminar/";
		String seminarId= Long.toString(newSeminarRoom.getSeminarId());
		String longURL = "http://15.164.104.40:8081/mini_QR/";
		
		fullURL = fullURL.concat(seminarId);
		newSeminarRoom.setFullURL(fullURL);
		longURL = longURL.concat(fullURL);
		newSeminarRoom.setShortURL(createShortUrl(longURL));
		seminarRoomRepository.save(newSeminarRoom);
		
		return newSeminarRoom.toDto();
	}
	 
	/**
	 * bit.ly 라이브러리를 사용하여 fullUrl을 shortUrl로 변환
	 * @param fullUrl
	 * @return shortUrl
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	public String createShortUrl(String fullUrl) throws MalformedURLException, IOException{
		Url url = as("o_1duq7b20be", "R_01bd87bf046e49cd93816dd681925740").call(shorten(fullUrl));
		String shortUrl = url.getShortUrl();
		
		return shortUrl;
	}
	
	/**
	 * id로 seminarRoom 조회 
	 * @param seminarId
	 * @return seminarRoomDto 
	 */
	public SeminarRoomDto findSeminar(Long seminarId) {
		SeminarRoom seminarRoom= seminarRoomRepository.findBySeminarId(seminarId);
		return seminarRoom.toDto();
	}
}
