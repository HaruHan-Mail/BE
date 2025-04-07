package com.haruhan.email.service;

import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.model.*;
import com.haruhan.common.error.CustomException;
import com.haruhan.common.error.StatusCode;
import com.haruhan.user.entity.User;
import com.haruhan.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {

    private final AmazonSimpleEmailService amazonSimpleEmailService;
    private final TemplateEngine templateEngine;
    private final RedisTemplate<String, String> redisTemplate;
    private final UserRepository userRepository;

    private static final String FROM = "no-reply@haruhan.site"; // SES에 등록된 발신자 이메일
    private static final long EXPIRATION_TIME = 5; // 인증번호 만료 시간 (5분)

    public void sendContentEmail(String email, com.haruhan.content.entity.Content content) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new CustomException(StatusCode.NOT_FOUND_USER));
        Context  context = new Context();
        context.setVariable("title", content.getTitle());
        context.setVariable("content_id", content.getContentId());
        context.setVariable("email", email);
        context.setVariable("token", user.getToken());
        String htmlContent = templateEngine.process("question-email", context);

        SendEmailRequest request = new SendEmailRequest()
                .withDestination(new Destination().withToAddresses(email))
                .withMessage(new Message()
                        .withBody(new Body()
                                .withHtml(new Content()
                                        .withCharset("UTF-8")
                                        .withData(htmlContent)))
                        .withSubject(new Content()
                                .withCharset("UTF-8")
                                .withData(content.getTitle())))
                .withSource(FROM);

        amazonSimpleEmailService.sendEmail(request);
        log.info("Sent question email to: {}", email);
    }

    public void sendVerificationEmail(String email) {
        String verificationCode = generateRandomCode(); // 인증번호 생성
        saveVerificationCode(email, verificationCode); // Redis에 저장

        // Thymeleaf를 이용하여 HTML 이메일 본문 생성
        Context context = new Context();
        context.setVariable("code", verificationCode); // 인증번호 전달
        String htmlContent = templateEngine.process("verification-email", context); // verification-email.html 사용

        // AWS SES 이메일 요청 생성
        SendEmailRequest request = new SendEmailRequest()
                .withDestination(new Destination().withToAddresses(email))
                .withMessage(new Message()
                        .withBody(new Body()
                                .withHtml(new Content()
                                        .withCharset("UTF-8")
                                        .withData(htmlContent)))
                        .withSubject(new Content()
                                .withCharset("UTF-8")
                                .withData("HaruHan메일 인증번호 전송")))
                .withSource(FROM);

        // 이메일 전송
        amazonSimpleEmailService.sendEmail(request);
        log.info("Sent verification code to: {}", email);
    }

    private void saveVerificationCode(String email, String code) {
        String key = "email_verification:" + email;
        redisTemplate.opsForValue().set(key, code, EXPIRATION_TIME, TimeUnit.MINUTES);
    }

    private String generateRandomCode() {
        Random random = new Random();
        return String.format("%04d", random.nextInt(10000)); // 4자리 인증번호 생성
    }

    public boolean verifyCode(String email, String inputCode) {
        String key = "email_verification:" + email;
        String storedCode = redisTemplate.opsForValue().get(key);
        return storedCode != null && storedCode.equals(inputCode);
    }
}
