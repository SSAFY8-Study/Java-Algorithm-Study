def bin_search(steps:list, k:int, stones:list)->int:
    left = 0
    right = len(steps) -1
    min_idx = -1
    
    while left<= right:
        mid = (left+right)//2
        cnt = 0
        for stone in stones:
            
            if cnt==k:
                break
            
            elif stone - steps[mid]+1<=0:
                cnt+=1
                
            else:
                cnt = 0
        
        
        if cnt<k:
            min_idx = max(min_idx, mid)
            left = mid+1
        else:
            right = mid-1
    
    if min_idx == -1:
        return 0
    
    elif min_idx == len(steps)-1:
        return steps[-1]
    
    return steps[min_idx]

def solution(stones, k):
    steps = sorted(stones)
    answer = bin_search(steps, k, stones)
    
    return answer
