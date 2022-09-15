import java.util.*;
/*
1. 순열을 만든다 -> 8! 이라서 1초안에 가능
2. 만들어진 순열을 집합으로 바꾼다.
3. 조건을 하나씩 돌면서 하나라도 맞지 않는 경우에 취소
4. 조건을 만족하면 답으로 체크
*/
class Solution {
    
    public static int answer;
    
    public static String[] conditions;
    public static char[] kakaofriends = {'A','C','F','J','M','N','R','T'};
    
    public static boolean isAvailable(Map<Character, Integer> hashmap){
        
        for(String condition : conditions){
            char target1 = condition.charAt(0);
            char target2 = condition.charAt(2);
            char op = condition.charAt(3);
            int value = Character.getNumericValue(condition.charAt(4));
            
            int compare = Math.abs(hashmap.get(target1) - hashmap.get(target2))-1;
            
            if(op == '='){
                if(value != compare){
                    return false;
                }
            }
            else if(op == '>'){
                if(value >= compare){
                    return false;
                }
            }
            else if(op == '<'){
                if(value <= compare){
                    return false;
                }
                
            }
        }
        
        return true;
    }
    
    public static void permutations(int depth, int status, Map<Character, Integer> hashmap){
        
        if(depth == kakaofriends.length){
            if(isAvailable(hashmap)){
                answer+=1;
            }
            return;
        }
        
        for(int i=0; i<kakaofriends.length; i++){
            if((status & (1<<i)) == 0){
                //append key value in hashmap
                hashmap.put(kakaofriends[i], depth);
                permutations(depth+1, status|(1<<i), hashmap);
            }
        }
    }
    
    
    public int solution(int n, String[] data) {
        answer = 0;
        conditions = data;
        
        permutations(0,0,new HashMap<Character, Integer>());
        
        return answer;
    }
}
