package com.haruhan.content.service;

import com.haruhan.common.error.CustomException;
import com.haruhan.common.error.StatusCode;
import com.haruhan.common.error.entity.Content;
import com.haruhan.common.error.repository.ContentRepository;
import com.haruhan.content.dto.ContentResDto;
import com.haruhan.content.entity.UserReceivedContent;
import com.haruhan.content.repository.UserReceivedContentRepository;
import com.haruhan.user.entity.User;
import com.haruhan.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ContentServiceImpl implements ContentService {

    private final UserReceivedContentRepository userReceivedContentRepository;
    private final UserRepository userRepository;
    private final ContentRepository contentRepository;

    @Override
    public List<ContentResDto> getUserReceivedContent(String email) {

        // 사용자가 존재하는지 확인
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException(StatusCode.NOT_FOUND_USER));

        // 사용자가 받은 컨텐츠 조회
        List<UserReceivedContent> receivedContentList = userReceivedContentRepository.findByUser(user);

        // DTO 변환 후 반환
        return receivedContentList.stream()
                .map(receivedContent -> new ContentResDto(
                        receivedContent.getContent().getContent_id(),
                        receivedContent.getContent().getTitle(),
                        receivedContent.getContent().getSummary(),
                        receivedContent.getContent().getBackground(),
                        receivedContent.getContent().getImportance(),
                        receivedContent.getContent().getTip(),
                        receivedContent.getContent().getAdditional_resources()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public List<ContentResDto> getTop5BookmarkedContent() {
        List<Content> top5Content = contentRepository.findTop5ByBookmarkCount();

        return top5Content.stream()
                .map(content -> new ContentResDto(
                        content.getContent_id(),
                        content.getTitle(),
                        content.getSummary(),
                        content.getBackground(),
                        content.getImportance(),
                        content.getTip(),
                        content.getAdditional_resources()
                ))
                .collect(Collectors.toList());
    }
}