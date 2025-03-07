package com.stephenowinosewstyle.Sew_Style_Backend.response;

public class AuthenticationResponse {
        private String token;
        private String firstName;
        private String role;
        private Long id; // Add user ID

        public AuthenticationResponse(String message) {
                this.token = message;
        }

        public AuthenticationResponse(String token, String firstName, String role, Long id) {
                this.token = token;
                this.firstName = firstName;
                this.role = role;
                this.id = id;
        }

        public String getToken() { return token; }
        public String getFirstName() { return firstName; }
        public String getRole() { return role; }
        public Long getId() { return id; }
}