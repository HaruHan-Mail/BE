package com.haruhan.user.controller;

import com.haruhan.common.error.StatusCode;
import com.haruhan.common.error.dto.Message;
import com.haruhan.user.dto.UserRequestDto;
import com.haruhan.user.dto.UserSettingRequestDto;
import com.haruhan.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
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
    public ResponseEntity<Message> unsubscribe(@RequestParam String email) {
        userService.unsubscribe(email);
        return ResponseEntity.ok(new Message(StatusCode.OK));
    }

    @PatchMapping("/settings")
    public ResponseEntity<Message> updateUserSettings(@Valid @RequestBody UserSettingRequestDto requestDto) {
        userService.updateUserSettings(requestDto);
        return ResponseEntity.ok(new Message(StatusCode.OK));
    }
}
