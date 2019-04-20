package com.depromeet.mini_QR.domain.dto;

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
public class CommentDto {
   Long commentId;
   String content;
   Integer likeCount = 0;
   SeminarRoom seminarRoom;
   
   public Comment toEntity() {
      return Comment.builder()
            .commentId(commentId)
            .content(content)
            .likeCount(likeCount)
            .seminarRoom(seminarRoom)
            .build();
   }   
}