package com.stephenowinosewstyle.Sew_Style_Backend.dto;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageDTO {
        private Long id;
        private String imageUrl;
        private int likeCount;
        private Long galleryId; // Reference to Gallery
}
