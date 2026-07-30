# Java 해설

정렬한 B를 순회하며 가장 작은 남은 A를 이길 때만 A 포인터를 이동합니다. 이기지 못하는 B는 더 큰 A도 이길 수 없습니다.

정렬 O(n log n), 매칭 O(n), 추가 공간 O(1)입니다.

```java
import java.util.Arrays;

class Solution {
    public int solution(int[] A, int[] B) {
        Arrays.sort(A);
        Arrays.sort(B);
        int aIndex = 0;
        int wins = 0;
        for (int number : B) {
            if (number > A[aIndex]) {
                wins++;
                aIndex++;
                if (aIndex == A.length) break;
            }
        }
        return wins;
    }
}
```
