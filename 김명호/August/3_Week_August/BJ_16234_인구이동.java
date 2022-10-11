import java.util.*;
import java.io.*;
import java.lang.*;
//294516	512
public class BJ_16234_인구이동{
    static String input = "3 5 10\n" +
            "10 15 20\n" +
            "20 30 25\n" +
            "40 22 10";
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, L, R, Flag, Days;
    static int[][] arr;
    static int[][] deltas = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static StringTokenizer st;
    static boolean[][] visited;
    static List<int[]> list = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        //br = new BufferedReader(new StringReader(input));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        arr = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        Flag = 1;
        Days = -1;
        while (Flag == 1) {
            Flag = 0;
            Days++;
            visited = new boolean[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j]) {
                        bfs(i, j);
                    }
                }
            }
        }
        System.out.println(Days);
    }

    static void bfs(int r, int c) {
        Queue<int[]> queue = new LinkedList<>();
        list.clear();
        queue.offer(new int[]{r, c, arr[r][c]});
        visited[r][c] = true;
        while (!queue.isEmpty()) {
            int[] world = queue.poll();
            list.add(world);
            for (int d = 0; d < deltas.length; d++) {
                int nr = world[0] + deltas[d][0];
                int nc = world[1] + deltas[d][1];
                if (isIn(nr, nc)) {
                    if (checkRange(world[2], arr[nr][nc]) && !visited[nr][nc]) {//조건 만족하면 list에 add한다.
                        queue.offer(new int[]{nr, nc, arr[nr][nc]});
                        visited[nr][nc] = true;
                    }
                }
            }
        }
        if (list.size() > 1) {//연합이 생긴 경우
            Flag = 1;
            int sum = 0;
            for (int i = 0; i < list.size(); i++) {
                sum+=list.get(i)[2];
            }
            int avg = sum/list.size();
            for (int i = 0; i < list.size(); i++) {
                arr[list.get(i)[0]][list.get(i)[1]] = avg;
            }
        }

    }

    static boolean isIn(int r, int c) {
        return r >= 0 && c >= 0 && r < N && c < N;
    }

    static boolean checkRange(int sum1, int sum2) {
        int sum = Math.abs(sum1 - sum2);
        return sum >= L && sum <= R;
    }
}