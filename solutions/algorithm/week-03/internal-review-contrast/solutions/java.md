# 리뷰 순서의 대비 최대화 - Java 해설

충돌 관계를 불리언 행렬로 만들고 인덱스마다 선택/건너뛰기를 재귀 탐색합니다. 남은 수로 k개를 채울 수 없는 경로는 제거합니다.

모든 부분집합이 고유한 재귀 경로를 가지며 충돌 검사를 통과한 집합만 점수를 갱신합니다. 최악 시간 O(2^n * n), 공간 O(n^2 + n)입니다.

```java
import java.util.Arrays;

class ChallengeSolution {
    private int[] scores;
    private boolean[][] conflict;
    private boolean[] selected;
    private int k;
    private int best;

    public int maxReviewInsight(int[] scores, int[][] conflicts, int k) {
        int n = scores.length;
        this.scores = scores;
        this.k = k;
        this.best = -1;
        this.conflict = new boolean[n][n];
        this.selected = new boolean[n];
        for (int[] pair : conflicts) {
            conflict[pair[0]][pair[1]] = true;
            conflict[pair[1]][pair[0]] = true;
        }
        search(0, 0, 0);
        return best;
    }

    private void search(int index, int count, int total) {
        if (count == k) {
            best = Math.max(best, total);
            return;
        }
        if (index == scores.length || count + scores.length - index < k) return;

        search(index + 1, count, total);
        boolean safe = true;
        for (int other = 0; other < index; other++) {
            if (selected[other] && conflict[index][other]) safe = false;
        }
        if (safe) {
            selected[index] = true;
            search(index + 1, count + 1, total + scores[index]);
            selected[index] = false;
        }
    }
}
```
