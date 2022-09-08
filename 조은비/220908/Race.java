import java.util.*;

/**
 * 
 * @author eunbicho
 * @see https://school.programmers.co.kr/learn/courses/30/lessons/67259
 * @category bfs
 * @memo 해결하지 못한 과정: 클래스 변수로 r, c, 비용까지만 생각했는데 방향까지 넣어서 생각했어야했다..! 
 * 					      방향에 따라서 비용 바뀌는거 생각정리가 좀 헷갈렸다.-> 구글링
 * 					     그리고 이렇게 했을 때 테스트 케이스 1개가 틀렸대  					     
 *  
 */

class Solution {
    private static int n;
    private static int[][] map;
    private static boolean[][] visit;

    private static int[] dx = {-1, 0, 1, 0}; //상우하좌
    private static int[] dy = {0, 1, 0 ,-1};

    private static int cost = Integer.MAX_VALUE;

    public int solution(int[][] board) {
        int answer = 0;

        //자동차 경주로 건설
        //nn
        //0 : 비어있음, 1 : 벽
        //상하좌우
        //벽이있는칸 x
        //직선도로 : 100원
        //코너 : 500원
        //최소비용 : bfs

        //방향
        //상 : 0
        //우 : 1
        //하 : 2
        //좌 : 3

        n = board.length;

        map = board;
        visit = new boolean[n][n];

        bfs(0,0,-1,0);

        answer = cost;

        return answer;
    }

    private static void bfs(int x, int y, int dir, int price) {

        Queue<Road> q = new LinkedList();
        q.add(new Road(x,y,dir,price));
        visit[x][y] = true;

        while (!q.isEmpty()) {
            Road head = q.poll();

            int qx = head.x;
            int qy = head.y;
            int qDir = head.dir;
            int qPrice = head.cost;

            // 뽑은게 도착점이면 최저비용 갱신
            if (qx == n-1 && qy == n-1) {
                cost = Math.min(cost, qPrice);
            }

            // 사방탐색
            for (int i=0; i<dx.length; i++) {
                int nx = qx + dx[i];
                int ny = qy + dy[i];
                int nDir = i;
                int nPrice = qPrice;

                // 새로운 좌표로 갈 수 없는 경우 continue
                if (nx < 0 || ny < 0 || nx >= n || ny >= n || map[nx][ny] == 1) {
                    continue;
                }

                if (qDir == -1) {
                    //처음엔 직선
                    nPrice += 100;
                } else if (qDir == nDir) {
                    //방향 같을 때
                    nPrice += 100;
                } else {
                    //방향 다를 때
                    nPrice += 600;
                }

                if (!visit[nx][ny] || map[nx][ny] >= nPrice) {
                    //방문 x, 이전 값이 더 클 경우 작은값을 넣어준다.
                    visit[nx][ny] = true; // 방문처리
                    map[nx][ny] = nPrice;
                    q.add(new Road(nx, ny, nDir, nPrice));
                }

            }
        }

    }

}

// map 좌표마다 좌표값이랑 방향, 비용 담는 클래스
class Road {
    int x, y, dir, cost;

    Road(int x, int y, int dir, int cost) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.cost = cost;
    }
}