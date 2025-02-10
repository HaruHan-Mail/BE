package com.haruhan.user.service;

import com.haruhan.user.dto.UserRequestDto;
import com.haruhan.user.entity.PreferedTime;
import com.haruhan.user.entity.User;
import com.haruhan.user.repository.UserRepository;
import com.haruhan.common.error.CustomException;
import com.haruhan.common.error.StatusCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)  // Mockito 확장 사용
class UserServiceTest {

    @Mock
    private UserRepository userRepository;  // Mock 객체

    @InjectMocks
    private UserServiceImpl userService;  // 테스트할 서비스

    private UserRequestDto requestDto;

    @BeforeEach
    void setUp() {
        requestDto = new UserRequestDto();
        requestDto.setEmail("test@example.com");
        requestDto.setPreferedTime(PreferedTime.MORNING);
        requestDto.setIsDaily(true);
    }

    @Test
    void 구독_성공() {
        // Given: 이메일이 없는 상태(Mock 설정)
        when(userRepository.findByEmail(requestDto.getEmail())).thenReturn(Optional.empty());

        // When: 구독 요청 (void 메서드이므로 반환값 확인 X)
        assertDoesNotThrow(() -> userService.subscribe(requestDto));

        // Then: 저장이 한 번 호출되었는지 검증
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void 이미_구독된_이메일이면_예외발생() {
        // Given: 이미 존재하는 이메일(Mock 설정)
        when(userRepository.findByEmail(requestDto.getEmail()))
                .thenReturn(Optional.of(new User()));

        // When & Then: 예외 발생 확인
        CustomException exception = assertThrows(CustomException.class, () -> {
            userService.subscribe(requestDto);
        });

        assertEquals(StatusCode.ALREADY_EXIST, exception.getStatusCode());

        // save가 호출되지 않았는지 검증
        verify(userRepository, never()).save(any(User.class));
    }
}