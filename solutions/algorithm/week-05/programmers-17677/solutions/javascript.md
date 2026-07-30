# JavaScript 해설

문자열을 대문자로 바꾸고 `/^[A-Z]{2}$/`를 통과한 두 글자만 Map에 셉니다. 합집합 키를 순회하며 min/max 빈도를 더합니다.

시간 O(L), 공간 O(L)입니다.

```javascript
function solution(str1, str2) {
  function bigrams(source) {
    const text = source.toUpperCase();
    const counts = new Map();
    for (let i = 0; i + 1 < text.length; i += 1) {
      const key = text.slice(i, i + 2);
      if (!/^[A-Z]{2}$/.test(key)) continue;
      counts.set(key, (counts.get(key) || 0) + 1);
    }
    return counts;
  }

  const left = bigrams(str1);
  const right = bigrams(str2);
  const keys = new Set([...left.keys(), ...right.keys()]);
  let intersection = 0;
  let union = 0;
  for (const key of keys) {
    const a = left.get(key) || 0;
    const b = right.get(key) || 0;
    intersection += Math.min(a, b);
    union += Math.max(a, b);
  }
  return union === 0 ? 65536 : Math.floor((intersection * 65536) / union);
}
```
