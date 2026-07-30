# JavaScript 해설

당첨 번호 `Set`을 만든 뒤 확정 일치와 0을 셉니다. 최고 상황은 0이 모두 당첨 번호이고, 최저 상황은 모두 당첨 번호가 아닌 경우입니다.

입력 한 번과 고정 크기 집합을 사용하므로 시간 O(n), 공간 O(n)입니다.

```javascript
function solution(lottos, winNums) {
  const winners = new Set(winNums);
  let match = 0;
  let unknown = 0;

  for (const number of lottos) {
    if (number === 0) unknown += 1;
    else if (winners.has(number)) match += 1;
  }

  const rank = (count) => Math.max(1, 7 - count);
  return [rank(match + unknown), rank(match)];
}
```
