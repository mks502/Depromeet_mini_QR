package com.depromeet.mini_QR.domain.dto;

import com.depromeet.mini_QR.domain.dto.CommentDto.CommentDtoBuilder;
import com.depromeet.mini_QR.domain.entity.Comment;
import com.depromeet.mini_QR.domain.entity.SeminarRoom;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentSendDto {
	   String type;
	   Comment comment;
}
