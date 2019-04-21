package com.depromeet.mini_QR.domain.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.depromeet.mini_QR.domain.dto.SeminarRoomDto;
import com.depromeet.mini_QR.domain.entity.SeminarRoom;
import com.depromeet.mini_QR.domain.repository.SeminarRoomRepository;
import com.rosaloves.bitlyj.Url; 
import static com.rosaloves.bitlyj.Bitly.*;

@Service
public class SeminarRoomService {
	@Autowired
	SeminarRoomRepository seminarRoomRepository;
	
	public SeminarRoomDto createSeminar(SeminarRoom seminarRoom) throws MalformedURLException, IOException {
		seminarRoomRepository.save(seminarRoom);
		String title= seminarRoom.getSeminarTitle();
		SeminarRoom newSeminar =seminarRoomRepository.findBySeminarTitle(title);
		String full= "/seminar/";
		String seminarid= Long.toString(newSeminar.getSeminarId());
		String longURL = "http://lvh.me/mini_QR/";
		
		full= full.concat(seminarid);
		newSeminar.setFullURL(full);
		longURL = longURL.concat(full);
		newSeminar.setShortURL(createShortUrl(longURL));
		seminarRoomRepository.save(newSeminar);
		
		return newSeminar.toDto();
	}
	
	// bit.ly api를 사용하여 fullUrl을 shortUrl로 변환 
	public String createShortUrl(String fullUrl) throws MalformedURLException, IOException{
		
		Url url = as("o_1duq7b20be", "R_01bd87bf046e49cd93816dd681925740").call(shorten(fullUrl));
		String shortUrl = url.getShortUrl();
		
		return shortUrl;
	}
}
