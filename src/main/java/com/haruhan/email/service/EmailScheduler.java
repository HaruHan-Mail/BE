package com.haruhan.email.service;

import com.haruhan.content.entity.Content;
import com.haruhan.content.repository.ContentRepository;
import com.haruhan.user.entity.User;
import com.haruhan.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailScheduler {
    private final UserRepository userRepository;
    private final ContentRepository contentRepository;
    private final EmailService emailService;

//    @Scheduled(cron = "0 0 7 * * MON-FRI") // 오전 7시
//    public void sendMorningEmails() {
//        sendEmailsForTime(PreferedTime.MORNING);
//    }
//
//    @Scheduled(cron = "0 0 12 * * MON-FRI") // 오후 12시
//    public void sendAfternoonEmails() {
//        sendEmailsForTime(PreferedTime.AFTERNOON);
//    }
//
//    @Scheduled(cron = "0 0 18 * * MON-FRI") // 오후 6시
//    public void sendEveningEmails() {
//        sendEmailsForTime(PreferedTime.EVENING);
//    }
//
//    private void sendEmailsForTime(PreferedTime time) {
//        List<User> users = userRepository.findAllByPreferedTime(time); // 시간에 맞는 설정 값을 선택한 유저를 찾음
//        for (User user : users) {
//            List<Content> contents = getNextContents(user);
//            if (!contents.isEmpty()) {
//                emailService.sendContentEmail(user.getEmail());
//                updateUserLastContent(user, contents);
//            }
//        }
//    }

    private List<Content> getNextContents(User user) {
        if (user.getIsDaily()) { // 매일 컨텐츠를 받고 싶은 사람일 경우
            return contentRepository.findNextOneAfter(user.getLastReceivedContentId()); // 컨텐츠 1개 보내기
        } else if (LocalDate.now().getDayOfWeek() == DayOfWeek.MONDAY) { // 월요일에 몰아서 받고싶은 경우에 월요일인지도 체크
            return contentRepository.findNextFiveAfter(user.getLastReceivedContentId()); // 컨텐츠 5개 보내기
        }
        return Collections.emptyList();
    }

    private void updateUserLastContent(User user, List<Content> sentContents) {
        Long lastId = sentContents.get(sentContents.size() - 1).getContent_id();
        user.setLastReceivedContentId(lastId);
        userRepository.save(user);
    }
}
