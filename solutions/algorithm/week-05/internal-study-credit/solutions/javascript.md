# 학습 크레딧 최소 조합 - JavaScript 해설

0부터 target까지 최소 모듈 수를 누적합니다. 각 합계의 마지막 모듈 후보를 모두 검사하므로 반복 사용을 포함한 모든 조합을 고려합니다.

시간 O(target × m), 공간 O(target)입니다.

```javascript
function minimumModules(credits, target) {
  const inf = target + 1;
  const dp = Array(target + 1).fill(inf);
  dp[0] = 0;

  for (let total = 1; total <= target; total += 1) {
    for (const credit of credits) {
      if (credit <= total && dp[total - credit] !== inf) {
        dp[total] = Math.min(dp[total], dp[total - credit] + 1);
      }
    }
  }
  return dp[target] === inf ? -1 : dp[target];
}
```
