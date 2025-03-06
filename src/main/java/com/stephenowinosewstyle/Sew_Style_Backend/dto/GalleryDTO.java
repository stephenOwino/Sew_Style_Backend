package com.stephenowinosewstyle.Sew_Style_Backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GalleryDTO {
        private Long id;
        private Long tailorId;
        private String title;
        private String description;
        private LocalDateTime createdAt;
        private List<ImageDTO> images;
}