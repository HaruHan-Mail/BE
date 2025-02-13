package com.haruhan.common.error.entity;

import com.haruhan.bookmark.entity.Bookmark;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Entity
@Getter
@Table(name = "content")
public class Content {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "content_id", nullable = false)
    private Long content_id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "summary", nullable = false)
    private String summary;

    @Column(name = "background", nullable = false)
    private String background;

    @Column(name = "importance", nullable = false)
    private String importance;

    @Column(name = "tip", nullable = false)
    private String tip;

    @Column(name = "additional_resources", nullable = false)
    private String additional_resources;

    @OneToMany(mappedBy = "content", cascade = CascadeType.ALL, orphanRemoval = true)
    //orphanRemoval : 연결이 끊어진(null이 된) 북마크 엔티티를 자동 삭제
    private List<Bookmark> bookmarks;
}
