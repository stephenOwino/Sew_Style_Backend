package com.stephenowinosewstyle.Sew_Style_Backend.entity;



import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
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
        private Tailor tailor; // Linking to the tailor who owns the gallery

        private String title; // Gallery title, e.g., "Wedding Collection"

        @OneToMany(cascade = CascadeType.ALL, mappedBy = "gallery")
        private List<Image> images; // List of images in the gallery
}


