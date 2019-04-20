package com.depromeet.mini_QR.domain.dto;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.depromeet.mini_QR.domain.entity.SeminarRoom;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SeminarRoomDto {
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	Long seminarId;
	
	@Column(nullable = false)
	String seminarTitle;
	
	String fullURL;
	String shortURL;
	
	public SeminarRoom toEntity(){
		return SeminarRoom.builder()
				.seminarId(seminarId)
				.seminarTitle(seminarTitle)
				.fullURL(fullURL)
				.shortURL(shortURL)
				.build();
	}
}
