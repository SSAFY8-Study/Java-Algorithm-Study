import java.util.*;
import java.io.*;

class Solution {

    // n진수에서 k진수 변환 함수
    public String convert(int n, int k){
        Deque<Integer> remains = new ArrayDeque<>();
        int remain = 0;
        
        while(n>0){
            remain = n%k;
            remains.addFirst(remain);
            n /= k;
        }
        
        StringBuilder ans = new StringBuilder();
        for(int r: remains){
            ans.append(Integer.toString(r));
        }
        
        return ans.toString();
    }
    
    // 소수 판별 함수 : long 형 중요!
    public boolean isPrime(long prime){
        if(prime == 1)  return false;
        
        for(long i=2; i<=Math.sqrt(prime); i++){
            if(prime%i == 0)
                return false;
        }
        return true;
    }
    
    public int solution(int n, int k) {
        char[] convertedNum = convert(n, k).toCharArray();
        
        int ans = 0;
        StringBuilder word = new StringBuilder();

        for(char cn: convertedNum){     // k진수로 바꾼 값 탐색
            if(cn == '0'){              // 0을 만나면 소수 판별
                String w = word.toString();
                if(w.length() > 0 && isPrime(Long.parseLong(w))){
                    ans++;
                }
                word = new StringBuilder();
            } else{
                word.append(cn);
            }
        }
        
        String lastWord = word.toString();
        if(lastWord.length() > 0 && isPrime(Long.parseLong(lastWord))){
            ans++;
        }
        return ans;
    }
}