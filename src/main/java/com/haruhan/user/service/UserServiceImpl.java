package com.haruhan.user.service;

import com.haruhan.common.error.CustomException;
import com.haruhan.common.error.StatusCode;
import com.haruhan.user.dto.UserRequestDto;
import com.haruhan.user.entity.User;
import com.haruhan.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Transactional
    @Override
    public String subscribe(UserRequestDto requestDto) {
        // 이미 가입된 이메일인지 확인
        if (userRepository.findByEmail(requestDto.getEmail()).isPresent()) {
            throw new CustomException(StatusCode.ALREADY_EXIST);
        }

        // 새 사용자 추가
        User user = new User();
        user.setEmail(requestDto.getEmail());
        user.setPreferedTime(requestDto.getPreferedTime());
        user.setIsDaily(requestDto.getIsDaily());
        userRepository.save(user);

        return "구독이 완료되었습니다.";
    }
}