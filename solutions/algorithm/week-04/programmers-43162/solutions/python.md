# Python 해설

방문하지 않은 컴퓨터를 찾을 때마다 네트워크 수를 하나 늘리고 DFS로 그 컴퓨터와 연결된 모든 컴퓨터를 방문 처리합니다.

DFS 한 번은 시작점의 연결 요소 전체와 정확히 일치합니다. 이후 같은 요소에서는 새 탐색이 시작되지 않으므로 시작 횟수가 연결 요소 수입니다. 인접 행렬 기준 시간 O(n^2), 공간 O(n)입니다.

```python
def solution(n, computers):
    visited = [False] * n

    def dfs(node):
        visited[node] = True
        for neighbor, connected in enumerate(computers[node]):
            if connected and not visited[neighbor]:
                dfs(neighbor)

    networks = 0
    for node in range(n):
        if not visited[node]:
            networks += 1
            dfs(node)
    return networks
```
