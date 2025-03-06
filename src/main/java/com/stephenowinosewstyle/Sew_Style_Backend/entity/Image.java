package com.stephenowinosewstyle.Sew_Style_Backend.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Image {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String imageUrl; // URL or path to the image file

        private int likeCount; // Number of likes for this image

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "gallery_id", nullable = false)
        private Gallery gallery; // Linking to the gallery
}

