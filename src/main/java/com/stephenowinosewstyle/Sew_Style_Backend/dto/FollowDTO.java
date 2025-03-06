package com.stephenowinosewstyle.Sew_Style_Backend.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FollowDTO {
        private Long id;
        private Long followerId; // Reference to User
        private Long tailorId; // Reference to Tailor
}
