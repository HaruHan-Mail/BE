package com.haruhan.user.service;


import com.haruhan.user.dto.UserRequestDto;
import com.haruhan.user.dto.UserSettingRequestDto;

public interface UserService {
    void subscribe(UserRequestDto requestDto);

    void unsubscribe(String email);

    void updateUserSettings(UserSettingRequestDto requestDto);
}
