# Python 해설

x를 0부터 d까지 k씩 증가시킵니다. 각 x에서 원 안의 y 최댓값은 `floor(sqrt(d^2 - x^2))`입니다. 0부터 그 값까지 k의 배수는 `y_max // k + 1`개입니다.

모든 점은 자신의 x에서 정확히 한 번 계산됩니다. 시간 O(d/k), 공간 O(1)입니다.

```python
from math import isqrt

def solution(k, d):
    answer = 0
    squared = d * d
    for x in range(0, d + 1, k):
        y_max = isqrt(squared - x * x)
        answer += y_max // k + 1
    return answer
```
