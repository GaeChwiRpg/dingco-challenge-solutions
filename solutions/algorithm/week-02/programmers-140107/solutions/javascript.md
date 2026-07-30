# JavaScript 해설

x를 k씩 움직이며 가능한 y 최댓값을 제곱근으로 구합니다. 제한 안의 제곱은 JavaScript 안전 정수 범위에 있으므로 `Number`로 정확한 정수 비교가 가능합니다.

시간 O(d/k), 공간 O(1)입니다.

```javascript
function solution(k, d) {
  let answer = 0;
  const squared = d * d;
  for (let x = 0; x <= d; x += k) {
    const yMax = Math.floor(Math.sqrt(squared - x * x));
    answer += Math.floor(yMax / k) + 1;
  }
  return answer;
}
```
