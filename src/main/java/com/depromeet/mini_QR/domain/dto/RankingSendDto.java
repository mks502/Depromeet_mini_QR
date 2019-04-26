package com.depromeet.mini_QR.domain.dto;

import java.util.List;

import com.depromeet.mini_QR.domain.entity.Comment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RankingSendDto {
	   String type;
	   List<Comment> commentList;
}
