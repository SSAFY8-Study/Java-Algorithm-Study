import java.util.*;


/**
 * 
 * @author eunbicho
 * @see https://school.programmers.co.kr/learn/courses/30/lessons/92342
 * @category dfs,중복조합, 완전탐색
 * @memo 해결하지 못한 과정: 완전탐색말고 자꾸 규칙을 찾으려고 했었음 -> 실패
 * 					   dfs함수를 만들려고 했는데 for문 돌릴 때 파라미터 값을 어떻게 써야할지 감이 안왔음 -> 구글링
 * 					       최대차이 같은 리스트 여러개를 비교할 때 뒤에서부터 보면서 큰 개수가 있으면 그걸 선택하게 하려고 했는데 코드로 못씀..
 *  
 */

class Solution {
    public static ArrayList<int[]> list = new ArrayList<int[]>();
    public static int max_diff = -1;
    public static int[] ryan;
    public static int[] apeach;
    
    public int[] solution(int n, int[] info) {
        int[] answer = {};
        ryan = new int[11];
        apeach = info;
        
        dfs(n, 0, 0);
        
        // dfs를 돌고 나왔는데도 최대차이가 -1이라면 -> 답을 구할 수 없는 경우
        if(max_diff == -1) return new int[]{-1};
        
        // dfs를 돌고 나왔을 때 list에는 라이언이 어피치를 최대 점수 차이로 이길 수 있는 경우의 배열들이 들어있음
        // 라이언이 가장 큰 점수 차이로 우승할 수 있는 방법이 여러가지일 경우, 가장 낮은 점수를 더 많이 맞힌 경우를 리턴해야함
        // 그래서 아래 방식으로 하면 어떻게 되는거지..?
        Collections.sort(list, (o1, o2) -> {
           for(int i=10;i>=0;i--){
               if(o1[i] != o2[i]) return o2[i] - o1[i];
           } 
           return 0;
        });
        
        return list.get(0);
    }
    
    // 라이언이 과녁을 맞힌 개수 완전탐색하는 dfs함수 
    public static void dfs(int n, int depth, int start){
        // 라이언이 n개 다 쐈으면 어피치 화살개수랑 라이언 화살개수를 비교해서 누가 점수 가져갈지 계산
    	if(depth == n){
            int a_sum = 0;
            int r_sum = 0;
            for(int i=0;i<10;i++){
                if(apeach[i] == 0 && ryan[i] == 0) continue; // 둘 다 0개 쐈으면 둘 다 0점
                if(apeach[i] >= ryan[i]) a_sum += (10-i); // 어피치가 라이언 이상 쏘면 어치피 득점
                else r_sum += (10-i); // 아니면 라이언 득점
            }
            
            if(r_sum > a_sum){ // 라이언이 이겼을 경우 두 점수의 총합차이 계산 후 최대 차이 갱신
                int diff = r_sum - a_sum;
                if(max_diff < diff){ // 최대 차이가 갱신되는 경우에
                    max_diff = diff;
                    list.clear(); // 기존 리스트 클리어
                    list.add(ryan.clone()); // 리스트에 라이언 배열 추가
                } else if(max_diff == diff){ // 최대차이랑 총합차이랑 같은 경우에
                    list.add(ryan.clone()); // 리스트에 라이언 배열 추가
                }
            }
            
            return;
        }
        
    	// 아직 화살 n개 다 안쐈으면 
        for(int i=start;i<11;i++){
            ryan[i]++;
            dfs(n, depth+1, i);
            ryan[i]--;
        }
    }
}