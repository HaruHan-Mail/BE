package com.haruhan.common.error.repository;

import com.haruhan.common.error.entity.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ContentRepository extends JpaRepository<Content, Long> {

    @Query("SELECT c FROM Content c ORDER BY c.bookmark_count DESC LIMIT 1")
    List<Content> findTop5ByBookmarkCount();
}
