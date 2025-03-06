package com.stephenowinosewstyle.Sew_Style_Backend.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "image")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Image {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name = "image_url", nullable = false)
        private String imageUrl;

        @Column(name = "like_count", nullable = false)
        private int likeCount = 0;

        @CreationTimestamp
        @Column(name = "uploaded_at", updatable = false)
        private LocalDateTime uploadedAt;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "gallery_id", nullable = false)
        private Gallery gallery;
}