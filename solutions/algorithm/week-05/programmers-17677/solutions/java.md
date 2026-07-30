# Java 해설

대문자로 정규화한 뒤 인접한 두 문자가 모두 A-Z인 경우만 빈도 맵에 추가합니다. 모든 키에 대해 min 빈도의 합과 max 빈도의 합을 구합니다.

시간 O(L), 공간 O(L)입니다.

```java
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Solution {
    public int solution(String str1, String str2) {
        Map<String, Integer> left = bigrams(str1);
        Map<String, Integer> right = bigrams(str2);
        Set<String> keys = new HashSet<>(left.keySet());
        keys.addAll(right.keySet());
        int intersection = 0;
        int union = 0;
        for (String key : keys) {
            int a = left.getOrDefault(key, 0);
            int b = right.getOrDefault(key, 0);
            intersection += Math.min(a, b);
            union += Math.max(a, b);
        }
        return union == 0 ? 65536 : intersection * 65536 / union;
    }

    private Map<String, Integer> bigrams(String source) {
        String text = source.toUpperCase();
        Map<String, Integer> counts = new HashMap<>();
        for (int i = 0; i + 1 < text.length(); i++) {
            char a = text.charAt(i), b = text.charAt(i + 1);
            if (a < 'A' || a > 'Z' || b < 'A' || b > 'Z') continue;
            String key = "" + a + b;
            counts.put(key, counts.getOrDefault(key, 0) + 1);
        }
        return counts;
    }
}
```
