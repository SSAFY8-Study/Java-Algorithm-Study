'''
생각한 풀이 : 
1. 그리디하게 접근 -> 어피치가 쏜 화살만 보고 그 상황에서의 최적을 n개의 화살에 쓴다.
 - 이 방법은 반례가 많을 것임. 왜 와이?
 - 처음에 어피치가 10점에 9발 쏘고 9점에 1발을 쐈다고 하면 라이언이 10점에 10발쏘고 10점 라이언이 높네?
 - 어떻게 쏘는 것이 가장 점수 차이가 많이 날지 생각하는게 쉽지 않네

2. 중복 조합 -> N이 10이라서 가능할 것
- 화살을 쏘는 모든 경우를 만들어서 일일히 점수 비교
'''

from itertools import combinations_with_replacement
from collections import Counter

def solution(n, info) :
    answer = []
    info = info[::-1]
    max_value = -1
    k = len(info)
    
    for c in combinations_with_replacement(range(0, k), n) :
        ryan = 0
        apeach = 0
        temp_answer = [0 for _ in range(k)]
        
        c = Counter(c)
        for i in range(0, k) :
            if info[i] < c[i] :
                ryan += i
            elif info[i] != 0 :
                apeach += i

            temp_answer[i] = c[i]
        
        if ryan > apeach :
            diff = ryan - apeach
            if max_value < diff :
                max_value = diff
                answer = temp_answer

    if answer :
        return answer[::-1]
    else :
        return [-1]
