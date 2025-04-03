package com.haruhan.content.entity;

import com.haruhan.bookmark.entity.Bookmark;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@Table(name = "content")
@NoArgsConstructor
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

    @Column(name = "bookmark_count", nullable = false)
    private int bookmark_count = 0;

    @OneToMany(mappedBy = "content", cascade = CascadeType.ALL, orphanRemoval = true)
    //orphanRemoval : 연결이 끊어진(null이 된) 북마크 엔티티를 자동 삭제
    private List<Bookmark> bookmarks;

    public Content(String title, String summary, String background, String importance, String tip, String additional_resources) {
        this.title = title;
        this.summary = summary;
        this.background = background;
        this.importance = importance;
        this.tip = tip;
        this.additional_resources = additional_resources;
    }

    public void increaseBookmarkCount() {
        this.bookmark_count++;
    }

    public void decreaseBookmarkCount() {
        if(this.bookmark_count > 0){
            this.bookmark_count--;
        }
    }
}
