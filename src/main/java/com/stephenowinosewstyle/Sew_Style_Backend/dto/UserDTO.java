package com.stephenowinosewstyle.Sew_Style_Backend.dto;



import com.stephenowinosewstyle.Sew_Style_Backend.role.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
        private Long id;
        private String firstName;
        private String lastName;
        private String email;
        private Role role;
        private boolean isActive;


}

