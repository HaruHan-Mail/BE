# HaruHan

하루한은 일상에 영감을 더하는 지식과 컨텐츠를 하루에 하나씩 전달합니다. 성장을 위한 작은 습관을 시작해보세요.

## ✨ 주요 기능

- **사용자 관리**: 회원가입, 로그인, 사용자 정보 설정을 할 수 있습니다.
- **콘텐츠 제공**: 매일 새로운 질문(콘텐츠)을 받습니다.
- **이메일링 서비스**: 사용자가 설정한 시간에 질문을 이메일로 발송합니다.
- **북마크**: 원하는 질문을 북마크하여 다시 볼 수 있습니다.
- **피드백**: 서비스에 대한 피드백을 제출할 수 있습니다.
- **대시보드**: 관리자가 여러 리소스를 확인할 수 있습니다.

## 🛠️ 기술 스택

- **Backend**: Spring Boot 3.1.7, Java 17
- **Database**: MySQL, Redis
- **ORM**: Spring Data JPA
- **Email**: AWS SES
- **Template Engine**: Thymeleaf
- **Build Tool**: Gradle

## 🚀 시작하기

1.  **저장소 복제:**
    ```bash
    git clone https://github.com/your-username/Haruhan.git
    ```
2.  **프로젝트 빌드:**
    ```bash
    ./gradlew build
    ```
3.  **애플리케이션 실행:**
    ```bash
    java -jar build/libs/Haruhan-0.0.1-SNAPSHOT.jar
    ```

## ✍️ 커밋 컨벤션

- `feat` : 새로운 기능 추가
- `fix` : 버그 수정
- `docs` : 문서 수정
- `style` : 코드 포맷팅, 세미콜론 누락, 코드 변경이 없는 경우
- `refactor` : 코드 리펙토링
- `test` : 테스트 코드, 리펙토링 테스트 코드 추가
- `chore` : 빌드 업무 수정, 패키지 매니저 수정
- `merge` : 브랜치 병합
