package com.stephenowinosewstyle.Sew_Style_Backend.response;


public class AuthenticationResponse {
        private String token;

        public AuthenticationResponse(String token) {
                this.token = token;
        }

        // Getters and setters
        public String getToken() { return token; }
        public void setToken(String token) { this.token = token; }
}