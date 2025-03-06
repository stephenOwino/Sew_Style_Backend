package com.stephenowinosewstyle.Sew_Style_Backend.entity;


import com.stephenowinosewstyle.Sew_Style_Backend.role.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String firstName;
        private String lastName;

        @Column(unique = true, nullable = false)
        private String email;

        private String password;

        @Lob
        private byte[] image; // Profile picture, optional

        @Enumerated(EnumType.STRING)
        private Role role; // Differentiates between USER and TAILOR

        private boolean isActive; // For enabling/disabling accounts

        public User(String john, String doe, String mail, String password123, Role role, byte[] bytes, boolean b) {
        }

        // Add this getter method for isActive
        public boolean isEnabled() {
                return isActive;
        }




}
