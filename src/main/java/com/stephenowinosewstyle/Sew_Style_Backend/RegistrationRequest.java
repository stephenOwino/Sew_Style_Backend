package com.stephenowinosewstyle.Sew_Style_Backend;

public record RegistrationRequest(
        String firstName,
        String lastName,
        String email,
        String password,
        String role) {
}
