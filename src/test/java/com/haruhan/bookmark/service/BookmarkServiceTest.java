package com.haruhan.bookmark.service;

import com.haruhan.bookmark.repository.BookmarkRepository;
import com.haruhan.content.repository.ContentRepository;
import com.haruhan.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class BookmarkServiceTest {

    @Mock
    private BookmarkRepository bookmarkRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private ContentRepository contentRepository;

    @InjectMocks
    private BookmarkServiceImpl bookmarkService;

    @BeforeEach
    void setUp() {
    }

//    @Test
//    @DisplayName("정상적으로 찜하기가 작동되는지 확인")
//    void 찜하기_확인() {
//        //Given
//        BookmarkReqDto validDto = new BookmarkReqDto("test4@example.com", 1L);
//        User user = new User("test4@example.com", PreferedTime.MORNING,true);
//        Content content = new Content("test","test","test","test","test","test");
//
//        given(userRepository.findByEmail(validDto.userEmail())).willReturn(java.util.Optional.of(user));
//        given(contentRepository.findById(validDto.contentId())).willReturn(java.util.Optional.of(content));
//
//        //When
//        bookmarkService.addBookmark(validDto);
//
//        //Then
//        verify(bookmarkRepository, times(1)).save(any(Bookmark.class));
//    }
//
//    @Test
//    @DisplayName("이미 찜한 지식을 찜하기 할 경우 발생하는 예외 확인")
//    void 중복된_지식_찜하기_확인() {
//        //Given
//        BookmarkReqDto invalidDto = new BookmarkReqDto("test4@example.com", 1L);
//        User user = new User("test4@example.com", PreferedTime.MORNING,true);
//        Content content = new Content("test","test","test","test","test","test");
//
//        given(userRepository.findByEmail(invalidDto.userEmail())).willReturn(java.util.Optional.of(user));
//        given(contentRepository.findById(invalidDto.contentId())).willReturn(java.util.Optional.of(content));
//        given(bookmarkRepository.existsByBookmarkId(new BookmarkId(user.getUserId(), content.getContent_id()))).willReturn(true);
//
//        //When
//        CustomException exception = assertThrows(CustomException.class, () -> bookmarkService.addBookmark(invalidDto));
//
//        //Then
//        assertEquals(StatusCode.ALREADY_BOOKMARKED, exception.getStatusCode());
//    }
//
//    @Test
//    @DisplayName("존재하지 않는 회원일때 발생하는 예외 확인")
//    void 존재하지_않는_회원_예외처리_확인() {
//        //Given
//        BookmarkReqDto invalidDto = new BookmarkReqDto("test3@example.com", 1L);
//
//        //When
//        CustomException exception = assertThrows(CustomException.class, () -> bookmarkService.addBookmark(invalidDto));
//
//        //Then
//        assertEquals(StatusCode.NOT_FOUND_USER, exception.getStatusCode());
//    }
//
//    @Test
//    @DisplayName("존재하지 않는 지식을 찜할때 발생하는 예외 확인")
//    void 존재하지_않는_지식_찜하기_예외_확인() {
//        //Given
//        BookmarkReqDto invalidDto = new BookmarkReqDto("test4@example.com", 100L);
//        User user = new User("test4@example.com", PreferedTime.MORNING,true);
//        given(userRepository.findByEmail(invalidDto.userEmail())).willReturn(java.util.Optional.of(user));
//
//        //When
//        CustomException exception = assertThrows(CustomException.class, () -> bookmarkService.addBookmark(invalidDto));
//
//        //Then
//        assertEquals(StatusCode.NOT_FOUND, exception.getStatusCode());
//    }
}