package com.haruhan.common.error.repository;

import com.haruhan.common.error.entity.Content;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContentRepository extends JpaRepository<Content, Long> {
}
