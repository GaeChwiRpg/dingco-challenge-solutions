# 리뷰 순서의 대비 최대화 - JavaScript 해설

충돌 인접 집합을 만든 뒤 각 리뷰의 선택/비선택을 탐색합니다. 선택 수와 남은 수를 이용한 가지치기는 가능한 정답을 제거하지 않습니다.

최악 시간 O(2^n * n), 공간 O(n^2 + n)입니다.

```javascript
function maxReviewInsight(scores, conflicts, k) {
  const n = scores.length;
  const blocked = Array.from({ length: n }, () => new Set());
  for (const [a, b] of conflicts) {
    blocked[a].add(b);
    blocked[b].add(a);
  }

  const selected = Array(n).fill(false);
  let best = -1;

  function search(index, count, total) {
    if (count === k) {
      best = Math.max(best, total);
      return;
    }
    if (index === n || count + (n - index) < k) return;

    search(index + 1, count, total);
    const safe = [...blocked[index]].every((other) => !selected[other]);
    if (safe) {
      selected[index] = true;
      search(index + 1, count + 1, total + scores[index]);
      selected[index] = false;
    }
  }

  search(0, 0, 0);
  return best;
}
```
