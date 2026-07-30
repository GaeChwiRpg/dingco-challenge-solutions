# Python 해설

당첨 번호를 집합으로 만들고 로또 번호를 한 번 순회합니다. `match`는 확정 일치 수, `unknown`은 0의 수입니다. 최저 일치는 `match`, 최고 일치는 `match + unknown`입니다. 순위는 `max(1, 7 - count)`로 바꿀 수 있습니다.

순회가 끝나면 모든 번호가 확정 일치·미확정·불일치 중 하나로 분류되므로 두 경계가 정확합니다. 시간은 O(n), 공간은 당첨 번호 집합 O(n)입니다.

```python
def solution(lottos, win_nums):
    winners = set(win_nums)
    match = sum(number in winners for number in lottos if number != 0)
    unknown = lottos.count(0)

    def rank(count):
        return max(1, 7 - count)

    return [rank(match + unknown), rank(match)]
```
