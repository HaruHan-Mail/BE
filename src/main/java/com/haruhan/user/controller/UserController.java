package com.haruhan.user.controller;

import com.haruhan.common.error.StatusCode;
import com.haruhan.common.error.dto.Message;
import com.haruhan.user.dto.UserConfirmRequestDto;
import com.haruhan.user.dto.UserRequestDto;
import com.haruhan.user.dto.UserSettingRequestDto;
import com.haruhan.user.dto.UserUnsubscribeRequestDto;
import com.haruhan.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@CrossOrigin
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<Message> subscribe(@Valid @RequestBody UserRequestDto requestDto) {
        userService.subscribe(requestDto);
        return ResponseEntity.ok(new Message(StatusCode.OK));
    }

    @DeleteMapping
    public ResponseEntity<Message> unsubscribe(@RequestBody UserUnsubscribeRequestDto userUnsubscribeRequestDto) {
        userService.unsubscribe(userUnsubscribeRequestDto);
        return ResponseEntity.ok(new Message(StatusCode.OK));
    }

    @PatchMapping("/settings")
    public ResponseEntity<Message> updateUserSettings(@Valid @RequestBody UserSettingRequestDto requestDto) {
        userService.updateUserSettings(requestDto);
        return ResponseEntity.ok(new Message(StatusCode.OK));
    }

    // 2. 인증번호 검증 및 구독 완료
    @PostMapping("/confirm-subscribe")
    public ResponseEntity<Message> confirmSubscribe(@RequestBody UserConfirmRequestDto userConfirmRequestDto) {
        userService.confirmSubscription(userConfirmRequestDto);
        return ResponseEntity.ok(new Message(StatusCode.OK, "구독이 완료되었습니다."));
    }
}
