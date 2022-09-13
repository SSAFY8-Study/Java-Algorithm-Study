package study09_1;

import java.util.LinkedList;
import java.util.Queue;

public class Programmers_두큐합같게만들기 {

	public int solution(int[] queue1, int[] queue2) {
        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();
        
        long sum = 0;
        long tmp1=0;
        long tmp2=0;
        for(int i=0; i<queue1.length; i++) {
        	sum+= queue1[i]+queue2[i];
        	tmp1+= queue1[i];
        	tmp2+= queue2[i];
        	q1.add(queue1[i]);
        	q2.add(queue2[i]);
        }
        if(sum%2==1) return -1;
        sum/=2;
        
        int limit = queue1.length*3;
        int ans = 0;
        while(tmp1!=tmp2) {
        	if(limit==0) return -1;
        	int temp = 0;
        	if(tmp1>sum) {
        		temp = q1.poll();
        		tmp1-=temp;
        		q2.add(temp);
        		tmp2+=temp;
        	} else {
        		temp = q2.poll();
        		tmp2-=temp;
        		q1.add(temp);
        		tmp1+=temp;
        	}
        	limit--;
        	ans++;
        }
        return ans;
	}
}
