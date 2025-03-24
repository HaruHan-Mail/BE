package com.haruhan.user.entity;

import com.haruhan.bookmark.entity.Bookmark;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

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

    @Column(nullable = false, unique = true)
    private String token;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    //orphanRemoval : 연결이 끊어진(null이 된) 북마크 엔티티를 자동 삭제
    private List<Bookmark> bookmarks;

    @Column(name = "last_received_content_id")
    private Long lastReceivedContentId = 0L; // 마지막으로 받은 콘텐츠 ID


    public User(String email, PreferedTime preferedTime, Boolean isDaily) {
        this.email = email;
        this.preferedTime = preferedTime;
        this.isDaily = isDaily;
        this.createdAt = LocalDateTime.now();
        this.token = UUID.randomUUID().toString();
    }

    public void updateSettings(PreferedTime preferedTime, boolean isDaily) {
        this.isDaily = isDaily;
        this.preferedTime = preferedTime;
    }

    public boolean isDaily() {
        return isDaily;
    }
}