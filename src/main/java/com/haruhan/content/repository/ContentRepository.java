package com.haruhan.content.repository;

import com.haruhan.content.entity.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContentRepository extends JpaRepository<Content, Long> {
    Content findByContent_id(Long content_id);

    @Query("SELECT c FROM Content c ORDER BY c.bookmark_count DESC LIMIT 3")
    List<Content> findTop5ByBookmarkCount();

    @Query("SELECT c FROM Content c WHERE c.content_id <= :end ORDER BY c.content_id ASC")
    List<Content> findUpToLastContent(@Param("end") Long lastContentId);

    @Query("SELECT c FROM Content c WHERE c.content_id > :lastId ORDER BY c.content_id ASC LIMIT 1")
    List<Content> findNextOneAfter(@Param("lastId") Long lastId);

    @Query("SELECT c FROM Content c WHERE c.content_id > :lastId ORDER BY c.content_id ASC LIMIT 5")
    List<Content> findNextFiveAfter(@Param("lastId") Long lastId);

}