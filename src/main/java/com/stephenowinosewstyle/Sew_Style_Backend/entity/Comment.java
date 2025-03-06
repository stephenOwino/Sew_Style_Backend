package com.stephenowinosewstyle.Sew_Style_Backend.entity;



import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comment {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String text; // Comment content

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "user_id", nullable = false)
        private User user; // Who made the comment

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "image_id", nullable = false)
        private Image image; // The image being commented on
}

