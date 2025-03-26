package com.haruhan.user.service;


import com.haruhan.user.dto.UserConfirmRequestDto;
import com.haruhan.user.dto.UserRequestDto;
import com.haruhan.user.dto.UserSettingRequestDto;
import com.haruhan.user.dto.UserUnsubscribeRequestDto;

public interface UserService {
    //구독하기 인증 메일 보내기
    void subscribe(UserRequestDto requestDto);
    //구독 해제하기
    void unsubscribe(UserUnsubscribeRequestDto userUnsubscribeRequestDto);
    //수신 빈도 설정하기
    void updateUserSettings(UserSettingRequestDto requestDto);
    //이메일로 받은 인증번호 확인하기
    void confirmSubscription(UserConfirmRequestDto userConfirmRequestDto);
    //관리자 코드 확인하기
    void confirmAdmin(String code);
}
