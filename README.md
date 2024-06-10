<p align = "center">
    <img src = "assets-readme/Sparta_Coding_Club.png" width = 150 />
    <img src = "assets-readme/Kotlin_Spring_2nd.png" width = 150 />
</p>

# T o d o S e r v e r

# 개발 환경

| 기준  | 내용                                                                                                   |
|-----|------------------------------------------------------------------------------------------------------|
| OS  | `Arch Linux x86_64 w/ Linux 6.6.32-1-lts`                                                            |
| IDE | `IntelliJ IDEA 2024.1.2`                                                                             |
| SDK | 개발 언어: `Kotlin 1.9.24`(JVM: `OpenJDK 17.0.11`)<br/>프레임워크: `Spring Boot 3.3.0`<br/>빌드 툴: `Gradle 8.8` |

# 목차
<!-- TOC -->
* [1. 과제 요구사항](#1-과제-요구사항)
* [2. 고민거리(과제 제출 시 같이 써야 하는 질문들)](#2-고민거리과제-제출-시-같이-써야-하는-질문들)
* [3. 데이터베이스](#3-데이터베이스)
    * [3-1. Entity Relationship Diagram (ERD)](#3-1-entity-relationship-diagram-erd)
    * [3-2. 데이터베이스 테이블](#3-2-데이터베이스-테이블)
* [4. API 명세서](#4-api-명세서)
    * [4-1. API call](#4-1-api-call)
    * [4-2. API Call에 이용하는 Data Transfer Object (`DTO`)](#4-2-api-call에-이용하는-data-transfer-object-dto)
<!-- TOC -->

# 1. 과제 요구사항

<details> <summary>1-1. Step 1 (필수)</summary>

- [x] 할 일 카드 작성
  - `제목`, `내용`, `작성일`, `작성자` 저장
  - 저장 성공 시 할 일 정보 반환
- [x] 선택한 할 일 조회
  - 선택한 할 일의 정보(`제목`, `내용`, `작성일`, `작성자`) 조회
- [x] 할 일 카드 목록 조회
  - 등록된 *전체* 할 일 조회
  - *작성일 기준 내림차순* 정렬
- [x] 선택한 할 일 수정
  - `작성일`을 제외한 나머지 정보 수정 가능
  - 수정 성공 시 수정된 할 일 정보 반환
- [x] 선택한 할 일 삭제
  - 선택한 할 일 삭제

</details>

<details> <summary>1-2. Step 2 (필수)</summary>

- [x] 할 일 카드 완료/비완료 표시
  - 할 일 생성 시 기본으로는 `false`로 설정됨
- [x] 댓글 작성
  - 연관된 댓글이 존재하는지 확인 필요
- [x] 댓글 수정 및 삭제
  - 연관된 댓글이 존재하는지 확인 필요
  - 작성한 댓글의 작성자/비밀번호와 요청 시 넘겨주는 작성자/비밀번호의 일치 여부 확인 필요

</details>


# 2. 고민거리(과제 제출 시 같이 써야 하는 질문들)
TBD


# 3. 데이터베이스

## 3-1. Entity Relationship Diagram (ERD)
```mermaid
erDiagram
    Task {
        Long id PK
        String title
        String content
        String author
        Boolean isDone
        ZonedDateTime createdAt
        ZonedDateTime updatedAt
    }
    Comment {
        Long id PK
        Task task FK
        String content
        String author
        String password
        ZonedDateTime createdAt
        ZonedDateTime updatedAt
    }
    
    Task ||--o{ Comment: has
```


# 4. API 명세서

## 4-1. API call

- 4-1-1. 할 일(`task`) 관련

| Feature       |   Method | URL                   | Request                   | Response                     |
|---------------|---------:|-----------------------|---------------------------|------------------------------|
| 할 일 추가        |   `POST` | `/api/tasks`          | body: `CreateTaskRequest` | `TaskResponse`               |
| 할 일 조회 (목록)   |    `GET` | `/api/tasks`          | -                         | `List<TaskDetailedResponse>` |
| 할 일 조회        |    `GET` | `/api/tasks/{taskId}` | -                         | `TaskResponse`               |
| 할 일 완료/미완료 표시 |  `PATCH` | `/api/tasks/{taskId}` | -                         | -                            |
| 할 일 수정        |    `PUT` | `/api/tasks/{taskId}` | body: `UpdateTaskRequest` | `TaskResponse`               |
| 할 일 삭제        | `DELETE` | `/api/tasks/{taskId}` | -                         | -                            |

- 4-1-2. 댓글(`comment`) 관련

| Feature |   Method | URL                                        | Request                      | Response                  |
|---------|---------:|--------------------------------------------|------------------------------|---------------------------|
| 댓글 추가   |   `POST` | `/api/tasks/{taskId}/comments`             | body: `CreateCommentRequest` | `CommentDetailedResponse` |
| 댓글 수정   |    `PUT` | `/api/tasks/{taskId}/comments/{commentId}` | body: `UpdateCommentRequest` | `CommentDetailedResponse` |
| 댓글 삭제   | `DELETE` | `/api/tasks/{taskId}/comments/{commentId}` | -                            | -                         |

## 4-2. API Call에 이용하는 Data Transfer Object (`DTO`)

<details> <summary>4-2-1. 요청(request)</summary>

<details> <summary>4-2-1-1. 할 일(`task`) 관련</summary>

- 4-2-1-1-1. `CreateTaskRequest`

할 일 추가 시(`POST /api/tasks`) `body`에 추가하는 내용
```kotlin
data class CreateTaskRequest(
    val title: String,          // 추가할 할 일의 제목
    val content: String,        // 추가할 할 일의 본문
    val owner: String           // 추가할 할 일의 소유자
)
```

- 4-2-1-1-2. `UpdateTaskRequest`

할 일 수정 시(`PUT /api/tasks`) `body`에 추가하는 내용
```kotlin
data class UpdateTaskRequest(
    val title: String,          // 수정할 할 일의 제목
    val content: String,        // 수정할 할 일의 본문
    val owner: String           // 수정할 할 일의 소유자
)
```
</details>

<details> <summary>4-2-1-2. 댓글(`comment`) 관련</summary>

- 4-2-1-1-1. `CreateCommentRequest`

댓글 추가 시(`POST /api/tasks/{taskId}/comments`) `body`에 추가하는 내용
```kotlin
data class CreateCommentRequest(
    val content: String,        // 추가할 댓글의 내용
    val author: String,         // 추가할 댓글의 작성자
    val password: String        // 추가할 댓글의 작성자가 설정한 비밀번호
)
```

- 4-2-1-1-2. `UpdateTaskRequest`

댓글 수정 시(`PUT /api/tasks/{taskId}/comments/{commentId}`) `body`에 추가하는 내용
```kotlin
data class UpdateCommentRequest(
    val content: String,        // 수정할 댓글의 새 내용
    val author: String,         // 수정할 댓글의 원 작성자
    val password: String        // 수정할 댓글의 원 작성자가 설정한 비밀번호
)
```
</details>

</details>


<details> <summary>4-2-2. 응답(response)</summary>

<details> <summary>4-2-2-1. 할 일(`task`) 관련</summary>

- 4-2-2-1-1. `TaskResponse`

할 일(`task`)에 대해 CRU~~D~~ 진행 시 서버에서 보내는 응답
```kotlin
import java.time.ZonedDateTime

data class TaskResponse(
    val id: Long?,                      // 할 일의 ID
    val title: String,                  // 할 일의 제목
    val content: String,                // 할 일의 본문
    val owner: String,                  // 할 일의 소유자
    val createdAt: ZonedDateTime,       // 할 일의 생성 시각
    val updatedAt: ZonedDateTime        // 할 일의 마지막 수정 시각
)
```

- 4-2-2-1-2. `TaskDetailedResponse`

할 일(`task`) 상세 조회 시 서버에서 보내는 응답(관련된 댓글(`comment`)들을 포함함)
```kotlin
data class TaskDetailedResponse(
    val task: TaskResponse,             // 할 일
    val comments: List<CommentResponse> // 할 일과 연관된 댓글들
)
```

</details>

<details> <summary>4-2-2-2. 댓글(`comment`) 관련</summary>

- 4-2-2-1-1. `CommentResponse`

단일 할 일(`task`)에 대해 상세 조회를 진행할 때 연관된 댓글 목록으로 댓글 `entity` 대신 보내는 응답
```kotlin
import java.time.ZonedDateTime

data class CommentResponse(
    val id: Long,                       // 댓글의 ID
    val content: String,                // 댓글의 내용
    val author: String,                 // 댓글의 작성자
    val createdAt: ZonedDateTime,       // 댓글의 생성 시각
    val updatedAt: ZonedDateTime,       // 댓글의 마지막 수정 시각
)
```

- 4-2-2-1-2. `TaskDetailedResponse`

댓글(`comment`) 생성 및 수정 시 서버에서 보내는 응답(연관된 할 일(`task`)을 포함함)
```kotlin
data class CommentDetailedResponse(
    val comment: CommentResponse,       // 댓글
    val taskRelated: TaskResponse       // 댓글이 달린 할 일
)
```

</details>

</details>