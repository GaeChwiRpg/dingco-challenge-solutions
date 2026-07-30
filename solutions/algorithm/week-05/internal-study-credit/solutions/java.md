# 학습 크레딧 최소 조합 - Java 해설

`dp[x]`는 x학점을 정확히 만드는 최소 모듈 수입니다. 0학점은 모듈 0개이고, 각 합계에서 가능한 마지막 학점을 모두 시도합니다.

시간 O(target * credits.length), 공간 O(target)입니다.

```java
import java.util.Arrays;

class ChallengeSolution {
    public int minimumModules(int[] credits, int target) {
        int inf = target + 1;
        int[] dp = new int[target + 1];
        Arrays.fill(dp, inf);
        dp[0] = 0;

        for (int total = 1; total <= target; total++) {
            for (int credit : credits) {
                if (credit <= total && dp[total - credit] != inf) {
                    dp[total] = Math.min(dp[total], dp[total - credit] + 1);
                }
            }
        }
        return dp[target] == inf ? -1 : dp[target];
    }
}
```
