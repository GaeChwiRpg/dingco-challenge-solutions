# 피로도를 고려한 계단 학습 - Java 해설

마지막 이동 종류를 DP 상태에 포함합니다. `walk[i]`는 한 칸 이동 도착, `jump[i]`는 두 칸 점프 도착의 최소 피로도입니다. 점프 전 상태를 `walk[i-2]`로 제한하면 연속 점프가 불가능합니다.

시간 O(n), 공간 O(n)입니다.

```java
import java.util.Arrays;

class ChallengeSolution {
    public long minimumStairFatigue(int[] fatigue) {
        int n = fatigue.length;
        long inf = Long.MAX_VALUE / 4;
        long[] walk = new long[n + 1];
        long[] jump = new long[n + 1];
        Arrays.fill(walk, inf);
        Arrays.fill(jump, inf);
        walk[0] = 0;

        for (int i = 1; i <= n; i++) {
            long cost = fatigue[i - 1];
            walk[i] = Math.min(walk[i - 1], jump[i - 1]) + cost;
            if (i >= 2) jump[i] = walk[i - 2] + cost;
        }
        return Math.min(walk[n], jump[n]);
    }
}
```
