package com.haruhan.bookmark.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Embeddable // 복합키를 위한 어노테이션
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BookmarkId implements Serializable { // 엔티티의 복합 기본 키 역할을 하는 클래스

    private Long userId;
    private Long contentId;

    //JPA의 엔티티 비교를 위해 필수적으로 오버라이드 해야 함
    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        BookmarkId that = (BookmarkId) o;
        return Objects.equals(userId, that.userId) && Objects.equals(contentId, that.contentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, contentId);
    }
}
