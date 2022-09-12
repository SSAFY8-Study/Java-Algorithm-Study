import java.util.*;

public class programmers_성격유형검사 {
	public int solution(int[] queue1, int[] queue2) {
        int answer = -2;
        
        long suml = 0;
        long sumr = 0;
        int size = queue1.length;
        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();
        for (int i = 0; i < size; i++){
            suml += queue1[i];
            q1.add(queue1[i]);
            sumr += queue2[i];
            q2.add(queue2[i]);
        }
        
        if ((suml + sumr)  % 2 == 0){
            int i = 0;
            boolean flag = false;
            for (i = 0; i <= size * 3; i++){
                if (suml > sumr){
                    int num = q1.poll();
                    q2.add(num);
                    sumr += num;
                    suml -= num;
                }else if  (suml < sumr){
                    int num = q2.poll();
                    q1.add(num);
                    sumr -= num;
                    suml += num;
                }else{
                    flag = true;
                    break;
                }
            }
            if (flag)
                answer = i;
            else
                answer = -1;
        }else{
            answer = -1;
        }
        return answer;
    }
}

//하나는 추출을 하고, 하나는 삽입을 하는 방식으로 원소의 합이 같도록 만들려고 한다.
//이때 필요한 작업 최소 횟수를 구하려고 한다. 
//* 제한 사항에 길이가 길고 원소값도 크다. -> DP문제 의심!!
//순조부? x, 백트래킹을 통해서 구할 수 있을 거 같으나, 시간이 가능할까?
//슬라이딩 윈도우?
