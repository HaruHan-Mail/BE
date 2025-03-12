package com.haruhan.email.controller;

import com.haruhan.common.error.StatusCode;
import com.haruhan.common.error.dto.Message;
import com.haruhan.email.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/email")
@RequiredArgsConstructor
@CrossOrigin
public class EmailController {

    private final EmailService emailService;

    @PostMapping("/{email}")
    public ResponseEntity<Message> sendEmail(@PathVariable String email) {
        emailService.sendQuestionEmail(email);
        return ResponseEntity.ok(new Message(StatusCode.OK));
    }

}
