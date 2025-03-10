package com.haruhan.user.service;


import com.haruhan.user.dto.UserConfirmRequestDto;
import com.haruhan.user.dto.UserRequestDto;
import com.haruhan.user.dto.UserSettingRequestDto;
import com.haruhan.user.dto.UserUnsubscribeRequestDto;

public interface UserService {
    void subscribe(UserRequestDto requestDto);

    void unsubscribe(UserUnsubscribeRequestDto userUnsubscribeRequestDto);

    void updateUserSettings(UserSettingRequestDto requestDto);

    void confirmSubscription(UserConfirmRequestDto userConfirmRequestDto);
}
