# JavaScript 해설

이동 전후 좌표를 문자열로 만든 뒤 정렬해 방향 없는 간선 키로 저장합니다. 범위 밖 명령은 무시합니다.

시간 O(m), 공간 O(m)입니다.

```javascript
function solution(dirs) {
  const delta = { U: [0, 1], D: [0, -1], L: [-1, 0], R: [1, 0] };
  let x = 0;
  let y = 0;
  const edges = new Set();

  for (const command of dirs) {
    const [dx, dy] = delta[command];
    const nx = x + dx;
    const ny = y + dy;
    if (nx < -5 || nx > 5 || ny < -5 || ny > 5) continue;
    const edge = [`${x},${y}`, `${nx},${ny}`].sort().join(':');
    edges.add(edge);
    [x, y] = [nx, ny];
  }
  return edges.size;
}
```
