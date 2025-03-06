package com.stephenowinosewstyle.Sew_Style_Backend.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TailorDTO {
        private Long id;
        private UserDTO user; // Nested DTO for User
        private String bio;
        private String specialization;
        private int experienceYears;
}
