# 식별자와 save/merge 함정 — 기준 답안

> 이 문서는 복사할 “모범 문장”이 아니라 통과 가능한 **검증 밀도**를 보여주는 기준 PR입니다. 본인 프로젝트의 코드·로그·수치가 다르면 결론도 달라져야 합니다.

## 한 문장 결론

Spring Data의 신규 판단과 JPA merge의 복사 의미를 엔티티 상태와 반환 객체 동일성으로 구분한다.

## 기준 미션

## Week 2. 엔티티 매핑과 생명주기

키 생성 전략, flush, detached와 save/merge 함정을 실험합니다.

### 이번 PR

**식별자와 save 함정 재현 및 근거형 답변**

1. 신규 엔티티와 분리 상태 엔티티를 각각 저장하고 persist와 merge 경로를 비교합니다.
2. 식별자 전략 또는 직접 할당 여부에 따라 INSERT 발생 시점이 달라지는 사례를 기록합니다.
3. merge 반환 객체와 전달 객체를 혼동했을 때 생기는 회귀 테스트를 작성합니다.

### 필수 제출 증거

- 엔티티 매핑 코드
- persist·merge 쿼리 비교 로그
- save·merge 회귀 테스트
- 근거형 질문 5~8 답변

### 근거형 질문

1. Spring Data JPA의 save는 어떤 기준으로 persist와 merge를 선택하나요?
2. merge에 전달한 객체를 계속 사용하면 위험한 이유는 무엇인가요?
3. IDENTITY와 SEQUENCE 전략은 INSERT 시점에 어떤 차이를 만들 수 있나요?
4. flush와 commit을 구분해야 이번 로그를 정확히 설명할 수 있는 이유는 무엇인가요?

## 선택과 근거

- 직접 할당 ID 엔티티는 `Persistable.isNew()`로 신규 여부를 명시한다.
- merge 결과의 관리 객체만 후속 변경에 사용한다.
- IDENTITY/SEQUENCE 차이는 DB 방언 조건을 기록한 별도 실험으로 분리한다.

## 제출 증거의 최소 완성선

- [x] 신규 save의 persist 경로
- [x] 분리 객체 save의 SELECT+merge 경로
- [x] 전달 객체와 반환 객체 비동일성
- [x] flush/commit 마커 로그

## 기준 구현·기록 예시

```java
DetachedTodo detached = new DetachedTodo(id, "changed");
Todo managed = entityManager.merge(detached);
assertThat(managed).isNotSameAs(detached);
managed.rename("tracked");
```

## 근거형 질문 답변

1. 기본은 버전 값 또는 ID가 null인지로 신규 여부를 판단한다. 직접 ID를 할당하면 잘못 merge될 수 있어 Persistable이나 별도 신규 판정을 쓴다.
2. merge는 전달 객체를 관리 상태로 만드는 게 아니라 상태를 관리 인스턴스에 복사해 반환한다. 전달 객체를 수정하면 변경 감지가 보장되지 않는다.
3. IDENTITY는 키를 얻기 위해 INSERT가 조기에 필요할 수 있고 SEQUENCE는 시퀀스 값을 먼저 받아 INSERT를 flush까지 미룰 수 있다.
4. flush는 SQL 동기화이고 commit은 트랜잭션 확정이다. 중간 flush 뒤 롤백될 수 있으므로 둘을 같은 말로 쓰면 로그 결론이 틀린다.

## 다른 답도 통과할 수 있는 조건

- 다른 프레임워크·도구·전략을 골라도 요구한 실패 경계와 결과 정합성을 같은 수준으로 재현하면 통과할 수 있습니다.
- 결론이 반대여도 입력 조건, 비교 기준, 실행 로그와 재검토 조건이 연결되면 유효합니다.
- 이 기준 PR의 예시 수치나 문장을 본인 성과처럼 옮기면 증거로 인정하지 않습니다.

## 공개 후 비교 체크

- [ ] 결론만 읽어도 선택 기준과 검증 결과가 연결되는가?
- [ ] 성공 경로뿐 아니라 실패·경계 조건을 재현했는가?
- [ ] 측정하지 않은 값을 성과처럼 쓰지 않았는가?
