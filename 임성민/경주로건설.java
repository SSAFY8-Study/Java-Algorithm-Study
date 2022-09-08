import java.util.*;

class Solution {
    
    public static int N;
    public static int answer;

    public static int[] dxs = {1,0,0,-1};
    public static int[] dys = {0,1,-1,0};
    
    public static int[][] map;
    public static boolean[][] visited;
    
    public static boolean isRange(int x, int y){
        return 0<=x && x<N && 0<=y && y<N;
    }
    
    public static void BFS(int dir, int x, int y, int cost){
        Queue<int[]> q = new LinkedList<>();
        //dir,x,y,cost
        q.offer(new int[]{dir,x,y,cost});
        visited[x][y] = true;

        while(!q.isEmpty()){
            int[] cur = q.poll();

             if(cur[1]==N-1 && cur[2]==N-1){
                answer = Math.min(answer, cur[3]);
             }

            for(int d=0; d<dxs.length; d++){
                int nx = cur[1] + dxs[d];
                int ny = cur[2] + dys[d];
                
                if(!isRange(nx,ny) || map[nx][ny]==1) continue;

                int tempCost = cur[3];
                if(cur[0] != d) tempCost+=500;
                tempCost+=100;
                
                // 방문한 적이 있어도 그 지점까지의 최저 비용이면 갱신해야함
                if(!visited[nx][ny] || tempCost<=map[nx][ny]){
                    visited[nx][ny] = true;
                    map[nx][ny] = tempCost;
                    q.offer(new int[]{d,nx,ny,tempCost});
                }
            }
        }
    }
    
    public int solution(int[][] board) {
        map = board;
        N = board.length;
        answer = N*N*500;
        visited = new boolean[N][N];
        //down
        BFS(0,0,0,0);
        //right
        BFS(1,0,0,0);
        
        return answer;
    }
}
