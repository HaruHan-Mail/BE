package com.haruhan.bookmark.entity;

import com.haruhan.content.entity.Content;
import com.haruhan.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
//복합키 Unique 보장
@Table(name = "bookmark", uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "content_id"}))
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Bookmark {
    @EmbeddedId // 복합키
    private BookmarkId bookmarkId;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @MapsId("contentId")
    @JoinColumn(name = "content_id", nullable = false)
    private Content content;

    public Bookmark(User user, Content content) {
        this.bookmarkId = new BookmarkId(user.getUserId(), content.getContentId());
        this.user = user;
        this.content = content;
    }
}
