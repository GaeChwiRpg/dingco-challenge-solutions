# 피로도를 고려한 계단 학습 - JavaScript 해설

현재 계단과 마지막 이동이 점프였는지를 상태로 둡니다. 한 칸 이동은 모든 이전 상태에서, 두 칸 점프는 이전 이동이 한 칸이었던 상태에서만 전이합니다.

시간 O(n), 공간 O(n)입니다.

```javascript
function minimumStairFatigue(fatigue) {
  const n = fatigue.length;
  const walk = Array(n + 1).fill(Infinity);
  const jump = Array(n + 1).fill(Infinity);
  walk[0] = 0;

  for (let i = 1; i <= n; i += 1) {
    const cost = fatigue[i - 1];
    walk[i] = Math.min(walk[i - 1], jump[i - 1]) + cost;
    if (i >= 2) jump[i] = walk[i - 2] + cost;
  }
  return Math.min(walk[n], jump[n]);
}
```
