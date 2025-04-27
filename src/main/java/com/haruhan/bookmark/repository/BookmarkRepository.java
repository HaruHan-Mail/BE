package com.haruhan.bookmark.repository;

import com.haruhan.bookmark.entity.Bookmark;
import com.haruhan.bookmark.entity.BookmarkId;
import com.haruhan.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
    boolean existsByBookmarkId(BookmarkId bookmarkId);

    @Query("SELECT b FROM Bookmark b JOIN FETCH b.content WHERE b.user = :user")
    List<Bookmark> findByUserFetchContent(@Param("user") User user);
}