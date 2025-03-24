package com.haruhan.content.repository;

import com.haruhan.content.entity.UserReceivedContent;
import com.haruhan.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserReceivedContentRepository extends JpaRepository<UserReceivedContent, Long> {

    List<UserReceivedContent> findByUser(User user);
}