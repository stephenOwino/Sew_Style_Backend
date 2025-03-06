package com.stephenowinosewstyle.Sew_Style_Backend.repository;

import com.stephenowinosewstyle.Sew_Style_Backend.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
        List<Image> findByGalleryId(Long galleryId); // Fetch all images in a gallery
}
