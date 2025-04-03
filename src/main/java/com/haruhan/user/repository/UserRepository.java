package com.haruhan.user.repository;

import com.haruhan.user.entity.PreferedTime;
import com.haruhan.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByToken(String token);
    List<User> findAllByPreferedTime(PreferedTime time);
}
