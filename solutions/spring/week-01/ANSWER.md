# 요청 흐름이 보이는 Todo API — 기준 답안

> 이 문서는 복사할 “모범 문장”이 아니라 통과 가능한 **검증 밀도**를 보여주는 기준 PR입니다. 본인 프로젝트의 코드·로그·수치가 다르면 결론도 달라져야 합니다.

## 한 문장 결론

HTTP 계약을 먼저 고정하고, MVC가 요청을 객체로 바꾸고 다시 JSON으로 내보내는 경계를 통합 테스트로 증명한다.

## 기준 미션

## Week 1. 웹 요청에서 첫 API까지

HTTP와 JSON을 관찰하고 직접 실행되는 Spring Boot API로 연결합니다.

### 이번 PR

**요청 흐름이 보이는 Todo API와 근거형 답변**

1. POST /todos와 GET /todos/{id}의 요청·응답 계약을 정의하고 구현합니다.
2. 정상 생성, 잘못된 입력, 존재하지 않는 Todo 조회를 통합 테스트로 고정합니다.
3. 요청이 컨트롤러에 도착해 JSON 응답이 되기까지의 흐름을 본인 코드와 연결해 설명합니다.

### 필수 제출 증거

- 실행 가능한 API 코드
- 성공·검증 실패·404 통합 테스트
- 요청 흐름 설명
- 근거형 질문 1~5 답변

### 근거형 질문

1. HTTP 요청은 어떤 과정을 거쳐 컨트롤러 메서드의 인자가 되나요?
2. 자바 객체는 누가 JSON 응답으로 변환하며, 그 사실을 어떻게 확인했나요?
3. 입력 검증 실패를 컨트롤러 코드에서 직접 분기하지 않은 이유는 무엇인가요?
4. 단위 테스트가 아니라 통합 테스트로 반드시 확인해야 한 경계는 무엇이었나요?
5. 현재 API 계약에서 가장 먼저 깨질 가능성이 높은 부분과 그 검증 방법은 무엇인가요?

## 선택과 근거

- 컨트롤러는 HTTP 계약만 담당하고 생성·조회 판단은 서비스로 이동한다.
- Bean Validation과 `@RestControllerAdvice`로 검증 실패와 404 오류 형식을 한곳에서 고정한다.
- `@SpringBootTest` + `MockMvc`를 사용해 필터–디스패처–변환기–컨트롤러 경계를 함께 검증한다.

## 제출 증거의 최소 완성선

- [x] `POST /todos` 201과 저장된 응답 본문
- [x] 빈 제목 400과 공통 오류 코드
- [x] 없는 ID 404와 JSON content type
- [x] 요청 DTO → 컨트롤러 → 서비스 → 응답 DTO 흐름도

## 기준 구현·기록 예시

```java
@PostMapping
ResponseEntity<TodoResponse> create(@Valid @RequestBody CreateTodoRequest request) {
  return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
}

@ExceptionHandler(TodoNotFoundException.class)
ResponseEntity<ApiError> notFound(TodoNotFoundException error) {
  return ResponseEntity.status(404).body(new ApiError("TODO_NOT_FOUND", error.getMessage()));
}
```

```java
mockMvc.perform(post("/todos").contentType(APPLICATION_JSON)
    .content("""{"title":""}"""))
  .andExpect(status().isBadRequest())
  .andExpect(jsonPath("$.code").value("VALIDATION_FAILED"));
```

## 근거형 질문 답변

1. DispatcherServlet이 HandlerMapping으로 메서드를 찾고, HandlerAdapter와 argument resolver가 `@RequestBody`를 처리한다. 이 경계는 MockMvc 통합 테스트에서 JSON 요청이 DTO 검증까지 도달하는 것으로 확인한다.
2. Jackson 기반 `HttpMessageConverter`가 객체를 JSON으로 직렬화한다. 응답의 content type과 필드 구조를 테스트해 컨트롤러가 문자열 JSON을 직접 만들지 않았음을 확인한다.
3. 검증 규칙은 DTO에 선언하고 예외 변환은 advice에 둬 정상 흐름과 실패 표현을 분리한다. 컨트롤러 if 분기는 누락과 응답 불일치를 늘린다.
4. JSON 역직렬화, Bean Validation, 예외 매핑과 HTTP 상태는 단위 테스트로 고정할 수 없는 프레임워크 경계라 통합 테스트가 필요하다.
5. 가장 먼저 깨질 경계는 오류 응답 계약이다. 오류 코드·필드·상태를 소비자 관점의 JSON assertion으로 고정한다.

## 다른 답도 통과할 수 있는 조건

- 다른 프레임워크·도구·전략을 골라도 요구한 실패 경계와 결과 정합성을 같은 수준으로 재현하면 통과할 수 있습니다.
- 결론이 반대여도 입력 조건, 비교 기준, 실행 로그와 재검토 조건이 연결되면 유효합니다.
- 이 기준 PR의 예시 수치나 문장을 본인 성과처럼 옮기면 증거로 인정하지 않습니다.

## 공개 후 비교 체크

- [ ] 결론만 읽어도 선택 기준과 검증 결과가 연결되는가?
- [ ] 성공 경로뿐 아니라 실패·경계 조건을 재현했는가?
- [ ] 측정하지 않은 값을 성과처럼 쓰지 않았는가?
