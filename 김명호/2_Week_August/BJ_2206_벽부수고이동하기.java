import java.io.*;
import java.lang.*;
import java.util.*;

public class BJ_2206_벽부수고이동하기 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static int N, M, min = Integer.MAX_VALUE;
    public static int[][] deltas = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
    public static boolean[][][] visited;
    public static int[][] arr;

    public static void main(String[] args) throws IOException {
        String[] temp = br.readLine().split(" ");
        N = Integer.parseInt(temp[0]);
        M = Integer.parseInt(temp[1]);
        arr = new int[N][M];
        visited = new boolean[N][M][2];

        for (int i = 0; i < N; i++) {
            temp = br.readLine().split("");
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(temp[j]);
            }
        }

        bfs();

        if (min == Integer.MAX_VALUE) {
            bw.write(-1 + "\n");
        } else {
            bw.write(min + "\n");
        }

        bw.flush();
        bw.close();

    }

    public static void bfs() {//오른쪽이랑 왼쪽으로만 이동하자.
        Queue<Integer[]> queue = new LinkedList<>();
        queue.add(new Integer[]{0, 0, 1, 0});
        visited[0][0][0] = true;
        while (!queue.isEmpty()) {
            int r = queue.peek()[0];
            int c = queue.peek()[1];
            int cnt = queue.peek()[2];
            int breakWall = queue.peek()[3];
            queue.remove();
            if (r == N - 1 && c == M - 1) {//도착하면
                min = Math.min(cnt, min);
                return;
            }
            for (int d = 0; d < deltas.length; d++) {
                int lr = r + deltas[d][0];
                int lc = c + deltas[d][1];
                if (isIn(lr, lc)) {//범위안에 속하면
                    if (arr[lr][lc] == 0) {//갈수 있으면 지금까지 부신벽이 있었는지 체크한다.
                        if (breakWall == 0 && !visited[lr][lc][0]) {//부신벽이 없는 상태에서 지나간다면
                            queue.add(new Integer[]{lr, lc, cnt + 1, 0});
                            visited[lr][lc][0] = true;
                        } else if (breakWall == 1 && !visited[lr][lc][1]) {//부신벽이 있는 상태에서 지나간다면
                            queue.add(new Integer[]{lr, lc, cnt + 1, 1});
                            visited[lr][lc][1] = true;
                        }

                    } else if (arr[lr][lc] == 1 && breakWall == 0) {//1인데 벽을 아직 깬적이 없다면 부수고 가본다.
                        queue.add(new Integer[]{lr, lc, cnt + 1, 1});
                        visited[lr][lc][1] = true;
                    }

                }
            }
        }
    }

    public static boolean isIn(int r, int c) {
        return r >= 0 && c >= 0 && r < N && c < M;
    }

}