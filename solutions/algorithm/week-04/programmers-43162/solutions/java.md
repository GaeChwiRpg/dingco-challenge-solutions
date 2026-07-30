# Java 해설

각 미방문 정점에서 DFS를 시작합니다. DFS 전에 방문 표시를 해 순환 그래프에서도 중복 진입을 막습니다.

행렬의 각 행을 최대 한 번 검사하므로 시간 O(n^2), 방문 배열과 재귀 스택 공간 O(n)입니다.

```java
class Solution {
    public int solution(int n, int[][] computers) {
        boolean[] visited = new boolean[n];
        int networks = 0;
        for (int node = 0; node < n; node++) {
            if (!visited[node]) {
                networks++;
                dfs(node, computers, visited);
            }
        }
        return networks;
    }

    private void dfs(int node, int[][] computers, boolean[] visited) {
        visited[node] = true;
        for (int next = 0; next < computers.length; next++) {
            if (computers[node][next] == 1 && !visited[next]) {
                dfs(next, computers, visited);
            }
        }
    }
}
```
