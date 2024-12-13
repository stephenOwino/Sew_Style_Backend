package com.stephenowinosewstyle.Sew_Style_Backend.repository;


import com.stephenowinosewstyle.Sew_Style_Backend.entity.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {
        VerificationToken findByToken(String token);
}
