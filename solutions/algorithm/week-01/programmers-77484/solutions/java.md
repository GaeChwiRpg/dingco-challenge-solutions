# Java 해설

당첨 번호를 `HashSet`에 저장해 membership을 상수 시간에 처리합니다. 확정 일치 수와 0의 수를 세면 가능한 일치 수의 최댓값과 최솟값이 결정됩니다.

각 번호를 정확히 한 상태로 분류하므로 누락이나 중복 계산이 없습니다. 시간 O(n), 공간 O(n)입니다.

```java
import java.util.HashSet;
import java.util.Set;

class Solution {
    public int[] solution(int[] lottos, int[] winNums) {
        Set<Integer> winners = new HashSet<>();
        for (int number : winNums) winners.add(number);

        int match = 0;
        int unknown = 0;
        for (int number : lottos) {
            if (number == 0) unknown++;
            else if (winners.contains(number)) match++;
        }
        return new int[] { rank(match + unknown), rank(match) };
    }

    private int rank(int count) {
        return Math.max(1, 7 - count);
    }
}
```
