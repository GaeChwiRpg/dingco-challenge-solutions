# Python 해설

두 배열을 정렬합니다. B를 작은 수부터 보면서 현재 가장 작은 남은 A를 이길 수 있으면 그 둘을 매칭합니다. 이길 수 없다면 그 B는 어떤 남은 A도 이기지 못하므로 버립니다.

더 큰 B 대신 가장 작은 가능한 B를 써도 현재 승리는 유지되고 큰 B를 미래에 남기므로 승수가 줄지 않습니다. 시간 O(n log n), 정렬 외 공간 O(1)입니다.

```python
def solution(A, B):
    A.sort()
    B.sort()
    a_index = 0
    wins = 0
    for number in B:
        if number > A[a_index]:
            wins += 1
            a_index += 1
            if a_index == len(A):
                break
    return wins
```
