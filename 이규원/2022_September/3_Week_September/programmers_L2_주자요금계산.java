import java.util.*;
import java.io.*;


class Solution {
    static class Status{
        int culInTime;
        int culOutTime;
        int total;
        
        public Status(int culInTime, int culOutTime){
            this.culInTime = culInTime;
            this.culOutTime = culOutTime;
        }
        
        public void culTime(){
            total += culOutTime - culInTime;
        }
        
        public String toString(){
            return " In : " + culInTime + " Out : " + culOutTime;
        }
        
        public int culPrice(int basic, int price, int extra, int exPrice){
            if(culOutTime == 0){
                culOutTime = 23 * 60 + 59;
            }
            culTime();

            int result = price;
            if (total  > basic){
                result += Math.ceil((total - basic) * 1.0 / extra) * exPrice;
            }
            return result;
        }
    }
    public int[] solution(int[] fees, String[] records) {
        int cnt = 0;
        // record parse
        TreeMap<Integer, Status> map = new TreeMap<>();
        for (String record : records){
            StringTokenizer tokens = new StringTokenizer(record); // 토큰으로 잘라서
            String[] times = tokens.nextToken().split(":"); // 시간으로 잘라서
            int numbers = Integer.parseInt(tokens.nextToken()); // 번호를 받고
            String op = tokens.nextToken(); // 동작
                        
            // 분으로 계산 : 시간 * 60 + 분
            int cultime = Integer.parseInt(times[0]) * 60 + Integer.parseInt(times[1]);

            // 출입이라면
            if (op.equals("IN")){
                if(map.containsKey(numbers)){ // 기존에 차가 있었다면 들어왔다가 나갔다가 다시 들어오는 경우
                    Status status = map.get(numbers);
                    status.culTime();
                    status.culInTime = cultime;
                    status.culOutTime = 0;
                }else{ // 처음 들어오는 차라면 계산한 시간으로 생성
                    cnt++;
                    map.put(numbers, new Status(cultime, 0));
                }
            }else{
                Status status = map.get(numbers);
                status.culOutTime = cultime;
            }
        }
        int[] answer = new int[cnt];
        int i = 0;
        for(int key : map.keySet()){
            System.out.println(key);
            answer[i++] = map.get(key).culPrice(fees[0], fees[1], fees[2], fees[3]);
        }
        return answer;
    }
}

// TreeMap을 통한 정렬된 값을 얻어서 처리