package com.stephenowinosewstyle.Sew_Style_Backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "gallery")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Gallery {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "tailor_id", nullable = false)
        private Tailor tailor;

        @Column(nullable = false)
        private String title;

        @Column(columnDefinition = "TEXT")
        private String description;

        @CreationTimestamp
        @Column(name = "created_at", updatable = false)
        private LocalDateTime createdAt;

        @OneToMany(cascade = CascadeType.ALL, mappedBy = "gallery", orphanRemoval = true)
        private List<Image> images;
}