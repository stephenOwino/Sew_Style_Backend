package com.stephenowinosewstyle.Sew_Style_Backend.exception;

public class UserAlreadyExistsException extends RuntimeException {
        public UserAlreadyExistsException(String message) {
                super(message);
        }
}
