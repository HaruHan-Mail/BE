package com.haruhan.email.controller;

import com.haruhan.common.error.StatusCode;
import com.haruhan.common.error.dto.Message;
import com.haruhan.content.entity.Content;
import com.haruhan.content.repository.ContentRepository;
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
    private final ContentRepository contentRepository;

    @PostMapping("/{email}")
    public ResponseEntity<Message> sendEmail(@PathVariable String email) {
        Content sampleContent = contentRepository.findByContentId(6L);
        emailService.sendContentEmail(email, sampleContent);
        return ResponseEntity.ok(new Message(StatusCode.OK));
    }

}
