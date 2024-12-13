package com.stephenowinosewstyle.Sew_Style_Backend.repository;

import com.stephenowinosewstyle.Sew_Style_Backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
        Optional<User> findByEmail(String email);
}
