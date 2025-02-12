package com.haruhan.user.service;


import com.haruhan.user.dto.UserRequestDto;

public interface UserService {
    void subscribe(UserRequestDto requestDto);

    void unsubscribe(String email);
}
