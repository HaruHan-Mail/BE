package com.haruhan.user.service;

import com.haruhan.common.error.CustomException;
import com.haruhan.common.error.StatusCode;
import com.haruhan.user.dto.UserRequestDto;
import com.haruhan.user.dto.UserSettingRequestDto;
import com.haruhan.user.entity.PreferedTime;
import com.haruhan.user.entity.User;
import com.haruhan.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // Mockito 확장 사용
class UserServiceTest {

    @Mock
    private UserRepository userRepository; // Mock 객체

    @InjectMocks
    private UserServiceImpl userService; // 테스트할 서비스

    private UserRequestDto requestDto;
    private User user;

    @BeforeEach
    void setUp() {
        requestDto = new UserRequestDto("test@example.com", PreferedTime.MORNING, true);
    }

    @Test
    void 구독_성공() {
        // Given: 이메일이 없는 상태(Mock 설정)
        when(userRepository.findByEmail(requestDto.email())).thenReturn(Optional.empty());

        // When & Then: 예외가 발생하지 않는지 확인 (void 메서드)
        assertDoesNotThrow(() -> userService.subscribe(requestDto));

        // User 객체가 올바르게 저장되었는지 검증
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void 이미_구독된_이메일이면_예외발생() {
        // Given: 이미 존재하는 이메일(Mock 설정)
        when(userRepository.findByEmail(requestDto.email()))
                .thenReturn(Optional.of(new User(requestDto.email(), requestDto.preferedTime(), requestDto.isDaily())));

        // When & Then: 예외 발생 확인
        CustomException exception = assertThrows(CustomException.class, () -> userService.subscribe(requestDto));

        assertEquals(StatusCode.ALREADY_EXIST, exception.getStatusCode());

        // save()가 호출되지 않았는지 검증
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void 유저_설정_변경_성공() {
        // Given
        User user = new User("test@example.com", PreferedTime.MORNING, true);
        UserSettingRequestDto requestDto = new UserSettingRequestDto("test@example.com", PreferedTime.EVENING, false);

        when(userRepository.findByEmail(requestDto.email())).thenReturn(Optional.of(user));

        // When
        userService.updateUserSettings(requestDto);

        // Then
        assertThat(user.isDaily()).isEqualTo(false);
        assertThat(user.getPreferedTime()).isEqualTo(PreferedTime.EVENING);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void 존재하지_않는_유저_설정_변경_시_예외발생() {
        // Given
        UserSettingRequestDto requestDto = new UserSettingRequestDto("notfound@example.com", PreferedTime.EVENING, false);
        when(userRepository.findByEmail(requestDto.email())).thenReturn(Optional.empty());

        // When & Then
        CustomException exception = assertThrows(CustomException.class, () -> userService.updateUserSettings(requestDto));
        assertThat(exception.getStatusCode()).isEqualTo(StatusCode.NOT_EXIST);

        verify(userRepository, never()).save(any(User.class));
    }
}