package com.haruhan.feedback.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "feedback")
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feedback_id", nullable = false)
    private Long feedback_id;

    @Column(name = "feedback_content", nullable = false)
    private String feedback_content;

    @Column(name = "create_at", nullable = false)
    private LocalDateTime create_at = LocalDateTime.now();

    public Feedback(String feedback_content) {
        this.feedback_content = feedback_content;
    }
}
