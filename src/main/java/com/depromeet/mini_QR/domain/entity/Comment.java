package com.depromeet.mini_QR.domain.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.depromeet.mini_QR.domain.entity.Member.MemberBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comment {
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	String commentId;
	String content;
	Integer likeCount;
}
