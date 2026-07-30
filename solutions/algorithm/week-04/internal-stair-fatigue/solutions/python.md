# 피로도를 고려한 계단 학습 - Python 해설

`walk[i]`는 i번 계단에 한 칸 이동으로 도착한 최소 피로도, `jump[i]`는 두 칸 점프로 도착한 최소 피로도입니다. 한 칸 이동은 두 이전 상태에서 가능하지만, 두 칸 점프는 연속 점프를 막기 위해 `walk[i-2]`에서만 가능합니다.

각 상태는 가능한 마지막 이동을 모두 비교한 최솟값이므로 귀납적으로 최적입니다. 시간 O(n), 공간 O(n)이며 두 단계만 보관하면 O(1)로 줄일 수 있습니다.

```python
def minimum_stair_fatigue(fatigue):
    n = len(fatigue)
    inf = float('inf')
    walk = [inf] * (n + 1)
    jump = [inf] * (n + 1)
    walk[0] = 0

    for i in range(1, n + 1):
        cost = fatigue[i - 1]
        walk[i] = min(walk[i - 1], jump[i - 1]) + cost
        if i >= 2:
            jump[i] = walk[i - 2] + cost
    return min(walk[n], jump[n])
```
