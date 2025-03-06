package com.stephenowinosewstyle.Sew_Style_Backend.repository;


import com.stephenowinosewstyle.Sew_Style_Backend.entity.Tailor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TailorRepository extends JpaRepository<Tailor, Long> {
        Optional<Tailor> findByUserId(Long userId); // To fetch tailor details by user ID
}

