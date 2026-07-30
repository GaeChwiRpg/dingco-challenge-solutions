# 학습 크레딧 최소 조합 - Python 해설

`dp[x]`를 정확히 x학점을 만드는 최소 모듈 수로 정의합니다. `dp[0] = 0`에서 시작해 각 x에서 마지막에 선택할 학점을 모두 비교합니다. 같은 학점을 여러 번 쓸 수 있으므로 이미 계산한 `dp[x-credit]`를 제한 없이 사용합니다.

모든 최적 조합에는 마지막 모듈이 하나 존재하고, 전이는 그 모든 후보를 검사하므로 최소값을 놓치지 않습니다. 시간 O(target * m), 공간 O(target)입니다.

```python
def minimum_modules(credits, target):
    inf = target + 1
    dp = [inf] * (target + 1)
    dp[0] = 0

    for total in range(1, target + 1):
        for credit in credits:
            if credit <= total and dp[total - credit] != inf:
                dp[total] = min(dp[total], dp[total - credit] + 1)
    return -1 if dp[target] == inf else dp[target]
```
