# 리뷰 순서의 대비 최대화 - Python 해설

각 리뷰를 선택하거나 건너뛰는 모든 경우를 탐색합니다. 충돌 목록은 인접 집합으로 전처리해 현재 선택 집합과의 충돌을 빠르게 확인합니다. 남은 리뷰를 모두 골라도 k개가 되지 않으면 즉시 중단합니다.

각 유효한 크기 k 부분집합은 인덱스 순서의 선택/비선택 경로 하나로 정확히 대응됩니다. 선택 전에 충돌을 검사하므로 기록된 후보는 모두 유효합니다. 시간 O(2^n * n), 공간 O(n^2 + n)입니다.

```python
def max_review_insight(scores, conflicts, k):
    n = len(scores)
    blocked = [set() for _ in range(n)]
    for a, b in conflicts:
        blocked[a].add(b)
        blocked[b].add(a)

    selected = [False] * n
    best = -1

    def search(index, count, total):
        nonlocal best
        if count == k:
            best = max(best, total)
            return
        if index == n or count + (n - index) < k:
            return

        search(index + 1, count, total)
        if all(not selected[other] for other in blocked[index]):
            selected[index] = True
            search(index + 1, count + 1, total + scores[index])
            selected[index] = False

    search(0, 0, 0)
    return best
```
