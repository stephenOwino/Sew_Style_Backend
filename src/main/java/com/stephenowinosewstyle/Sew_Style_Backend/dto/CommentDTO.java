package com.stephenowinosewstyle.Sew_Style_Backend.dto;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {
        private Long id;
        private String text;
        private Long userId; // Reference to User
        private Long imageId; // Reference to Image
}
