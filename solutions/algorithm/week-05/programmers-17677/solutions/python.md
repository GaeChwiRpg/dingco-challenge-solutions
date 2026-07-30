# Python 해설

문자열을 대문자로 통일하고 영문자 두 글자만 `Counter`에 셉니다. 각 원소의 교집합 빈도는 min, 합집합 빈도는 max입니다. 합집합이 0이면 정의에 따라 65536을 반환합니다.

두 문자열 길이 합을 L이라 하면 시간 O(L), 공간 O(L)입니다.

```python
from collections import Counter

def solution(str1, str2):
    def bigrams(text):
        text = text.upper()
        return Counter(
            text[i:i + 2]
            for i in range(len(text) - 1)
            if text[i:i + 2].isalpha() and text[i:i + 2].isascii()
        )

    left = bigrams(str1)
    right = bigrams(str2)
    keys = left.keys() | right.keys()
    intersection = sum(min(left[key], right[key]) for key in keys)
    union = sum(max(left[key], right[key]) for key in keys)
    return 65536 if union == 0 else intersection * 65536 // union
```
