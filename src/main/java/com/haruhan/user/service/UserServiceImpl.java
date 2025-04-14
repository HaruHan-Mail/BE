package com.haruhan.user.service;

import com.haruhan.common.error.CustomException;
import com.haruhan.common.error.StatusCode;
import com.haruhan.email.service.EmailService;
import com.haruhan.user.dto.UserConfirmRequestDto;
import com.haruhan.user.dto.UserRequestDto;
import com.haruhan.user.dto.UserSettingRequestDto;
import com.haruhan.user.dto.UserUnsubscribeRequestDto;
import com.haruhan.user.entity.User;
import com.haruhan.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final EmailService emailService;
    private final UserRepository userRepository;
    private final RedisTemplate<String, String> redisTemplate;

    @Transactional
    @Override
    public void subscribe(UserRequestDto requestDto) {
        // 이미 가입된 이메일인지 확인
        if (userRepository.findByEmail(requestDto.email()).isPresent()) {
            throw new CustomException(StatusCode.ALREADY_EXIST);
        }

        // 인증번호 이메일 전송
        emailService.sendVerificationEmail(requestDto.email());
    }

    @Transactional
    @Override
    public void unsubscribe(UserUnsubscribeRequestDto userUnsubscribeRequestDto) {
        User user = userRepository.findByToken(userUnsubscribeRequestDto.token())
                .orElseThrow(() -> new CustomException(StatusCode.NOT_EXIST));

        userRepository.delete(user);
    }

    @Override
    @Transactional
    public void updateUserSettings(UserSettingRequestDto requestDto) {
        User user = userRepository.findByToken(requestDto.token())
                .orElseThrow(() -> new CustomException(StatusCode.NOT_EXIST));

        user.updateSettings(requestDto.preferedTime(), requestDto.isDaily());
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void confirmSubscription(UserConfirmRequestDto userConfirmRequestDto) {
        // 인증번호 확인
        if (!emailService.verifyCode(userConfirmRequestDto.email(), userConfirmRequestDto.verificationCode())) {
            throw new CustomException(StatusCode.INVALID_VERIFICATION_CODE);
        }

        // 새 사용자 추가
        User user = new User(userConfirmRequestDto.email(), userConfirmRequestDto.preferedTime(), userConfirmRequestDto.isDaily());
        userRepository.save(user);
        emailService.sendWelcomeEmail(userConfirmRequestDto.email());
    }

    @Override
    public void confirmAdmin(String code) {
        String adminCode = redisTemplate.opsForValue().get("admin");
        if (adminCode == null) {
            throw new CustomException(StatusCode.NOT_EXIST_ADMIN_CODE);
        }
        if (!adminCode.equals(code)) {
            throw new CustomException(StatusCode.INVALID_ADMIN_CODE);
        }
    }
}