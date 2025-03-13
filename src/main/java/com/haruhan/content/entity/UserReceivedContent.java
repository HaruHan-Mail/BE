package com.haruhan.content.entity;

import com.haruhan.common.error.entity.Content;
import com.haruhan.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "user_received_content")
public class UserReceivedContent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // 사용자를 참조

    @ManyToOne
    @JoinColumn(name = "content_id", nullable = false)
    private Content content; // 받은 컨텐츠를 참조

    @Column(name = "received_at", nullable = false)
    private LocalDateTime receivedAt; // 받은 날짜

    public UserReceivedContent(User user, Content content) {
        this.user = user;
        this.content = content;
        this.receivedAt = LocalDateTime.now();
    }
}

//id를 pk로 할지, 복합키 쓸지 고민중