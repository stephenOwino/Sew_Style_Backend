package com.stephenowinosewstyle.Sew_Style_Backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageDTO {
        private Long id;
        private String imageUrl;
        private int likeCount;
        private LocalDateTime uploadedAt;
        private Long galleryId;
}