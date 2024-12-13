package com.stephenowinosewstyle.Sew_Style_Backend.model;

import lombok.Data;

@Data
public class UserModel {

        private String firstName;
        private String lastName;
        private String email;
        private String password;
        private String confirmPassword;

        public UserModel(String firstName, String lastName, String email, String password, String confirmPassword) {
                this.firstName = firstName;
                this.lastName = lastName;
                this.email = email;
                this.password = password;
                this.confirmPassword = confirmPassword;
        }

}