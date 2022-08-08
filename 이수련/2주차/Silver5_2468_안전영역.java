import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Silver5_2468_안전영역 {
	
    static int N;
    static int cnt;
    static int [][]map;
    static int [][]copy_map;
    static boolean [][]visits;
    static int dx[] = {-1,0,0,1};
    static int dy[] = {0,1,-1,0};
 
    public static void main(String args[]) throws NumberFormatException, IOException {
 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
 
        map = new int[N+1][N+1];
        copy_map = new int[N+1][N+1];
 
        int min = 100;
        int max = -1;
 
        for (int i=1; i<=N; i++){
        	StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=1; j<=N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                min = Math.min(min, map[i][j]);
                max = Math.max(max, map[i][j]);
            }
        }
        
        int tmp = -1;
        for(int i=0; i<=max; i++){
            cnt = 0;
            visits = new boolean[N+1][N+1];
            // map 초기화, 복사
            for(int r=1; r<=N; r++){
                for(int c=1; c<=N; c++){
                    copy_map[r][c] = map[r][c];
                }
            }
            
 
            for(int r=1; r<=N; r++){
                for(int c=1; c<=N; c++){
                    if (copy_map[r][c]<=i){
                        copy_map[r][c]=-1;// 침수되는 곳을 -1
                        visits[r][c] = true;
                    }
                }
            } 
 
 
            for(int r=1; r<=N; r++){
                for(int c=1; c<=N; c++){
                    if(!visits[r][c]){
                        dfs(r,c);
                        cnt++; // dfs를 한번 다 돌았으면 cnt를 1씩 증가시켜준다.
                    }
                }
            }
 
            tmp = Math.max(tmp, cnt);
 
        }
 
        System.out.print(tmp);
 
    }
 
 
    public static void dfs(int x, int y){
        visits[x][y] = true;
 
        for(int i=0; i<4; i++){
            int new_x = x+dx[i];
            int new_y = y+dy[i];
 
            if (new_x>0 && new_y>0 && new_x<=N && new_y<=N){
            	if(!visits[new_x][new_y] && map[new_x][new_y] !=-1){
            		dfs(new_x, new_y);
            	}
            }
        }
    }
 

}
