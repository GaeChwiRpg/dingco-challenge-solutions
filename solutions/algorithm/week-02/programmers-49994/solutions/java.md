# Java 해설

간선의 두 좌표를 사전순으로 정렬한 문자열 키로 저장합니다. 경계 검사를 통과한 이동만 간선 집합과 현재 위치에 반영합니다.

시간 O(m), 공간 O(m)입니다.

```java
import java.util.HashSet;
import java.util.Set;

class Solution {
    public int solution(String dirs) {
        int x = 0, y = 0;
        Set<String> edges = new HashSet<>();
        for (char command : dirs.toCharArray()) {
            int nx = x, ny = y;
            if (command == 'U') ny++;
            if (command == 'D') ny--;
            if (command == 'L') nx--;
            if (command == 'R') nx++;
            if (nx < -5 || nx > 5 || ny < -5 || ny > 5) continue;
            String a = x + "," + y;
            String b = nx + "," + ny;
            edges.add(a.compareTo(b) <= 0 ? a + ":" + b : b + ":" + a);
            x = nx;
            y = ny;
        }
        return edges.size();
    }
}
```
