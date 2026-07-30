# JavaScript 해설

숫자 오름차순으로 정렬합니다. `i` 이후 논문 수는 `n - i`이고, 현재 인용 횟수가 이 수 이상인 첫 지점이 최대 H입니다.

시간 O(n log n), 정렬 구현에 따른 공간 O(n)입니다.

```javascript
function solution(citations) {
  citations.sort((a, b) => a - b);
  const n = citations.length;
  for (let i = 0; i < n; i += 1) {
    const papers = n - i;
    if (citations[i] >= papers) return papers;
  }
  return 0;
}
```
