package com.haruhan.user.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PreferedTime preferedTime;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(nullable = false)
    private Boolean isDaily;


    public User(String email, PreferedTime preferedTime, Boolean isDaily) {
        this.email = email;
        this.preferedTime = preferedTime;
        this.isDaily = isDaily;
        this.createdAt = LocalDateTime.now();
    }

    public void updateSettings(PreferedTime preferedTime, boolean isDaily) {
        this.isDaily = isDaily;
        this.preferedTime = preferedTime;
    }

    public boolean isDaily() {
        return isDaily;
    }
}