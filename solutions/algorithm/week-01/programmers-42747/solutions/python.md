# Python 해설

인용 횟수를 오름차순 정렬합니다. 인덱스 `i`에서 현재 값 이상 인용된 논문은 현재 논문부터 끝까지 `n - i`편입니다. `citations[i] >= n - i`를 처음 만족하면 그 값이 최대 H입니다.

앞선 위치는 필요한 논문 수가 더 많아 조건을 만족하지 않았고, 이후 위치는 후보 H가 더 작습니다. 따라서 첫 경계가 최대입니다. 시간 O(n log n), 정렬 외 공간은 O(1)입니다.

```python
def solution(citations):
    citations.sort()
    n = len(citations)
    for i, citation in enumerate(citations):
        papers = n - i
        if citation >= papers:
            return papers
    return 0
```
