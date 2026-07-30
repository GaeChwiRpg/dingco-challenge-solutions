# Python 해설

현재 좌표와 다음 좌표를 두 끝점으로 하는 간선을 저장합니다. `tuple(sorted((start, end)))`로 두 끝점을 정렬하면 왕복 이동이 같은 키가 됩니다. 경계 밖 이동은 상태를 바꾸지 않습니다.

각 명령은 집합 연산 한 번이므로 시간 O(m), 저장되는 길은 최대 O(m)입니다.

```python
def solution(dirs):
    delta = {'U': (0, 1), 'D': (0, -1), 'L': (-1, 0), 'R': (1, 0)}
    x = y = 0
    edges = set()

    for command in dirs:
        dx, dy = delta[command]
        nx, ny = x + dx, y + dy
        if not (-5 <= nx <= 5 and -5 <= ny <= 5):
            continue
        edges.add(tuple(sorted(((x, y), (nx, ny)))))
        x, y = nx, ny
    return len(edges)
```
