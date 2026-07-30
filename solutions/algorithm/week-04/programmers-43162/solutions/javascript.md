# JavaScript 해설

미방문 정점 하나를 발견할 때마다 DFS로 연결 요소 전체를 표시하고 답을 증가시킵니다.

인접 행렬 전체를 한 번씩 확인하므로 시간 O(n²), 방문 배열과 호출 스택 공간 O(n)입니다.

```javascript
function solution(n, computers) {
  const visited = Array(n).fill(false);

  function dfs(node) {
    visited[node] = true;
    for (let next = 0; next < n; next += 1) {
      if (computers[node][next] === 1 && !visited[next]) dfs(next);
    }
  }

  let networks = 0;
  for (let node = 0; node < n; node += 1) {
    if (!visited[node]) {
      networks += 1;
      dfs(node);
    }
  }
  return networks;
}
```
