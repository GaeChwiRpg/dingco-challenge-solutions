# Java 해설

x를 k 간격으로 고정하고 피타고라스 부등식으로 가능한 y의 상한을 구합니다. 제곱은 `long`에서 계산해 32비트 오버플로를 막습니다.

각 x마다 상수 시간 계산이므로 시간 O(d/k), 공간 O(1)입니다.

```java
class Solution {
    public long solution(int k, int d) {
        long answer = 0;
        long squared = (long) d * d;
        for (long x = 0; x <= d; x += k) {
            long yMax = (long) Math.sqrt(squared - x * x);
            while ((yMax + 1) * (yMax + 1) <= squared - x * x) yMax++;
            while (yMax * yMax > squared - x * x) yMax--;
            answer += yMax / k + 1;
        }
        return answer;
    }
}
```
