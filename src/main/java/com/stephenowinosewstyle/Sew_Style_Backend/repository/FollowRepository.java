package com.stephenowinosewstyle.Sew_Style_Backend.repository;

import com.stephenowinosewstyle.Sew_Style_Backend.entity.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Long> {
        List<Follow> findByTailorId(Long tailorId); // Fetch all followers of a tailor
        List<Follow> findByFollowerId(Long followerId); // Fetch all tailors followed by a user
}

