package com.haruhan.user.controller;

import com.haruhan.user.dto.UserRequestDto;
import com.haruhan.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/subscribe")
    public ResponseEntity<String> subscribe(@RequestBody UserRequestDto requestDto) {
        String responseMessage = userService.subscribe(requestDto);
        return ResponseEntity.ok(responseMessage);
    }
}
