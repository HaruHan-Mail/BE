package com.haruhan.content.service;

import com.haruhan.common.error.CustomException;
import com.haruhan.common.error.StatusCode;
import com.haruhan.common.error.entity.Content;
import com.haruhan.common.error.repository.ContentRepository;
import com.haruhan.content.dto.ContentReqDto;
import com.haruhan.content.dto.ContentResDto;
import com.haruhan.user.entity.User;
import com.haruhan.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ContentServiceImpl implements ContentService {

    private final UserRepository userRepository;
    private final ContentRepository contentRepository;

    @Override
    public List<ContentResDto> getUserReceivedContent(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException(StatusCode.NOT_FOUND_USER));

        Long lastContentId = user.getLastReceivedContentId();

        if (lastContentId == null || lastContentId < 1) {
            throw new CustomException(StatusCode.NOT_FOUND);
        }

        List<Content> contentList = contentRepository.findUpToLastContent(lastContentId);

        return contentList.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }


    @Override
    public List<ContentResDto> getTop5BookmarkedContent() {
        List<Content> top5Content = contentRepository.findTop5ByBookmarkCount();

        return top5Content.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ContentResDto createContent(ContentReqDto dto) {
        Content content = new Content(
                dto.title(),
                dto.summary(),
                String.join("\n", dto.background()),
                String.join("\n", dto.importance()),
                String.join("\n", dto.tip()),
                String.join("\n", dto.additionalResources())
        );

        Content saved = contentRepository.save(content);
        return convertToDto(saved);
    }


    //문자열을 줄바꿈 기준으로 분리하여 리스트로 변환
    private List<String> splitByNewLine(String text) {
        return Arrays.stream(text.split("\n"))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());
    }


    //Content -> ContentResDto 변환
    private ContentResDto convertToDto(Content content) {
        return new ContentResDto(
                content.getContent_id(),
                content.getTitle(),
                content.getSummary(),
                splitByNewLine(content.getBackground()),
                splitByNewLine(content.getImportance()),
                splitByNewLine(content.getTip()),
                splitByNewLine(content.getAdditional_resources())
        );
    }
}