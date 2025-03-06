package com.stephenowinosewstyle.Sew_Style_Backend.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Follow {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "follower_id", nullable = false)
        private User follower; // The user who follows

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "tailor_id", nullable = false)
        private Tailor tailor; // The tailor being followed
}

