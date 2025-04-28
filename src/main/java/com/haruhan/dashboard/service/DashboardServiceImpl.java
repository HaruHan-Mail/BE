package com.haruhan.dashboard.service;

import com.haruhan.content.repository.ContentRepository;
import com.haruhan.dashboard.dto.DashboardContentDto;
import com.haruhan.dashboard.dto.DashboardResDto;
import com.haruhan.dashboard.dto.DashboardUserDto;
import com.haruhan.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {

    private final UserRepository userRepository;
    private final ContentRepository contentRepository;

    @Override
    public DashboardResDto getDashboardInfo() {
        //사용자 정보 조회
        List<DashboardUserDto> users = userRepository.findAll().stream()
                .map(user -> new DashboardUserDto(user.getUserId(), user.getEmail()))
                .toList();

        //컨텐츠 정보 조회
        List<DashboardContentDto> contents = contentRepository.findAll().stream()
                .map(content -> new DashboardContentDto(content.getContentId(), content.getTitle()))
                .toList();

        return new DashboardResDto(users, contents);
    }
}
