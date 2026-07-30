# Java 해설

오름차순 정렬 후 `n - i`를 H 후보로 봅니다. 현재 인용 수가 후보 이상인 첫 위치가 조건을 만족하는 최대 경계입니다.

정렬 O(n log n), 순회 O(n), 추가 공간 O(1)입니다.

```java
import java.util.Arrays;

class Solution {
    public int solution(int[] citations) {
        Arrays.sort(citations);
        int n = citations.length;
        for (int i = 0; i < n; i++) {
            int papers = n - i;
            if (citations[i] >= papers) return papers;
        }
        return 0;
    }
}
```
