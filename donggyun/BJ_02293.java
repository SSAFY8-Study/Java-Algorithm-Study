import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**

@author itsme
@since 2022. 8. 25.
@see https://www.acmicpc.net/problem/2293
@performance 11612kb 88ms
@category #dp
@note */
public class BJ_02293{
	
	static BufferedReader input;
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;
	static int N,K;
	static int[] coins,dp;
	
	public static void main(String[] args) throws IOException {
		input = new BufferedReader(new InputStreamReader(System.in));
		
		tokens=new StringTokenizer(input.readLine());
		
		N=Integer.parseInt(tokens.nextToken());
		K=Integer.parseInt(tokens.nextToken());
		
		coins=new int[N];//동전 단위를 저장하기 위한 배열
		dp=new int[K+1];//경우의수 저장하는 배열 (목표 가격을 인덱스로 사용하기 위해 크기를 K+1로 정함)
		
		for(int i=0;i<N;i++) {
			coins[i]=Integer.parseInt(input.readLine());//동전 단위 입력 받기
		}
		
		dp[0]=1;//초기값 경우의 수 설정 위해 1로 초기화 (목표가격과 동전 단위가 같을 때 더해주는 값)
		
		for(int i=0;i<N;i++) {
			for(int k=1;k<=K;k++) { 
				if(k>=coins[i]) {//경우의 수가 증가하려면 동전 단위보다 목표가격이 크거나 같아야 한다
					dp[k]+=dp[k-coins[i]];//목표가격이 동전 단위만큼 증가할 때마다 경우의 수가 바뀐다 (이를 적용하기 위해 누적하면서 경우의 수를 바꿔줌)
				}
			}
		}
		output.append(dp[K]);//목표 가격 경우의 수 append
		
		System.out.println(output);//결과 값 출력
	}
	
}
