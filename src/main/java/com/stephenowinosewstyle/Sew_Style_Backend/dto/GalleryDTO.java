package com.stephenowinosewstyle.Sew_Style_Backend.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GalleryDTO {
        private Long id;
        private Long tailorId; // Reference to Tailor
        private String title;
        private List<ImageDTO> images; // Nested DTO for Images
}
