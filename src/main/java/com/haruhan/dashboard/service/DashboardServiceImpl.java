package com.haruhan.dashboard.service;

import com.haruhan.content.entity.Content;
import com.haruhan.content.repository.ContentRepository;
import com.haruhan.dashboard.dto.DashboardContentDto;
import com.haruhan.dashboard.dto.DashboardResDto;
import com.haruhan.dashboard.dto.DashboardUserDto;
import com.haruhan.user.entity.User;
import com.haruhan.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {

    private final UserRepository userRepository;
    private final ContentRepository contentRepository;

    @Override
    public DashboardResDto getDashboardInfo(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        // 사용자 정보 조회
        Page<User> userPage = userRepository.findAll(pageable);
        Page<DashboardUserDto> users = userPage.map(user -> new DashboardUserDto(user.getUserId(), user.getEmail()));

        // 컨텐츠 정보 조회
        Page<Content> contentPage = contentRepository.findAll(pageable);
        Page<DashboardContentDto> contents = contentPage.map(content -> new DashboardContentDto(content.getContentId(), content.getTitle()));

        return new DashboardResDto(users, contents);
    }
}