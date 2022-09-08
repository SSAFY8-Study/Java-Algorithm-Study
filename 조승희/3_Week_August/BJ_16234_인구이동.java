package study3_08;
/**
 * 296496	712
 */
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class BJ_16234_인구이동 {

	static int N, L, R, sum;
	static int[][] map;	//인구수 지도
	static List<int[]> move;	//인구 이동할 구역
	static boolean[][] visited;	//방문 여부
	static int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};	//탐색 방향
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		L = sc.nextInt();
		R = sc.nextInt();
		
		map = new int[N][N];
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		int ans = 0; //인구이동 발생 일
		while(true) {
			visited = new boolean[N][N];
			boolean checkM = false;		//인구이동 여부
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(!visited[i][j]) {
//						sum = 0;	// 인구 이동 시 총 인구의 합
						check(i, j);	//인구 이동 여부 확인
						int len = move.size();  //인구이동할 도시 개수
						if(len>1) {	//2도시 이상 move리스트에 들어가 있으면
//							System.out.println(ans);
							int avg = sum/len;	//인구 평균 계산
							for(int[] k:move) {  // 각 도시에 인구 분배
								map[k[0]][k[1]] = avg;
//								System.out.println(k[0]+" "+k[1]);
							}
							checkM = true;	//인구 이동 여부 체크
						}
					}
				}
			}
			if(!checkM) {	//더이상 인구이동 하지 않으면 답 출력하고 while문 탈출
				System.out.println(ans);
				break;
			}
			ans++;	// 인구이동 했다는 말이므로 ans+1
		}

	}
	
	static void check(int x, int y) {	//bfs로 인구 이동 여부 확인
		Queue<int[]> q = new LinkedList<>();
		move = new ArrayList<>();
		
		q.add(new int[]{x, y});		// 탐색 시작지점 q에 넣음
		move.add(new int[]{x, y});
		visited[x][y] = true;	//방문 여부 체크
		sum = map[x][y];
		
		while(!q.isEmpty()) {	//q가 빈 상태가 아니면
			int[] cur = q.poll();	//가장 앞에 있는 x, y를 꺼냄
			
			for(int[] d: dir) {	// 4방향 확인
				int nx = cur[0] + d[0];	
				int ny = cur[1] + d[1];
				if(nx<0 || nx>=N || ny<0 || ny>=N) continue; //map 넘어서면 continue
				if(visited[nx][ny]) continue;	//이미 방문 했으면 다음으로
				int dif = Math.abs(map[cur[0]][cur[1]]-map[nx][ny]);  //인구수 차이
				if(dif>=L && dif<=R) {	//L보다 크고 R보다 작으면
					visited[nx][ny] = true;	//방문 여부 체크
					q.add(new int[]{nx, ny}); //다음 방문을 위해 q에 넣음
					move.add(new int[]{nx, ny});  //인구 이동 리스트에 추가
					sum+= map[nx][ny];
				}
			}
		}
	}

}
