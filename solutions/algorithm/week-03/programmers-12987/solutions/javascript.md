# JavaScript 해설

오름차순 정렬 후 B의 현재 수가 A의 가장 작은 미처리 수보다 클 때만 승리로 매칭합니다. 이 선택은 더 큰 B를 미래에 남기는 교환 가능한 최적 선택입니다.

시간 O(n log n), 공간은 정렬 구현에 따라 O(n)입니다.

```javascript
function solution(A, B) {
  A.sort((a, b) => a - b);
  B.sort((a, b) => a - b);
  let aIndex = 0;
  let wins = 0;
  for (const number of B) {
    if (number > A[aIndex]) {
      wins += 1;
      aIndex += 1;
      if (aIndex === A.length) break;
    }
  }
  return wins;
}
```
