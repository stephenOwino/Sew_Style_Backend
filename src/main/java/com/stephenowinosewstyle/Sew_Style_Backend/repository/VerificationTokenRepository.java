package com.stephenowinosewstyle.Sew_Style_Backend.repository;

import com.stephenowinosewstyle.Sew_Style_Backend.token.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {
        VerificationToken findByToken(String token);
}
