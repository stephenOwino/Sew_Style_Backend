package com.stephenowinosewstyle.Sew_Style_Backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tailor {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
        private User user; // Linking to the base User

        private String bio; // Optional bio to introduce the tailor
        private String specialization; // E.g., Wedding gowns, suits
        private int experienceYears; // Tailoring experience in years
}

