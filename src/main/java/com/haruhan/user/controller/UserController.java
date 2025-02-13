package com.haruhan.user.controller;

import com.haruhan.common.error.StatusCode;
import com.haruhan.common.error.dto.Message;
import com.haruhan.user.dto.UserRequestDto;
import com.haruhan.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

    private final UserService userService;

    @PostMapping("/subscribe")
    public ResponseEntity<Message> subscribe(@Valid @RequestBody UserRequestDto requestDto) {
        userService.subscribe(requestDto);
        return ResponseEntity.ok(new Message(StatusCode.OK));
    }

    @DeleteMapping("/unsubscribe")
    public ResponseEntity<Message> unsubscribe(@RequestParam String email) {
        userService.unsubscribe(email);
        return ResponseEntity.ok(new Message(StatusCode.OK));
    }
}
