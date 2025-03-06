package com.stephenowinosewstyle.Sew_Style_Backend.repository;


import com.stephenowinosewstyle.Sew_Style_Backend.entity.Gallery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GalleryRepository extends JpaRepository<Gallery, Long> {
        List<Gallery> findByTailorId(Long tailorId); // Fetch all galleries of a specific tailor
}
