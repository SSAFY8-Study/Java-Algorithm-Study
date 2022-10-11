import java.util.*;
import java.io.*;
import java.lang.*;
//21212	240
public class BJ_6593_상범빌딩 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N, M, L, min;
    static int[][] deltas = new int[][]{{0, 1, 0}, {0, -1, 0}, {0, 0, 1}, {0, 0, -1}, {1, 0, 0}, {-1, 0, 0}};
    static StringTokenizer st;
    static boolean[][][] visited;
    static String[][][] arr;
    static String input = "3 4 5\n" +
            "S....\n" +
            ".###.\n" +
            ".##..\n" +
            "###.#\n" +
            "\n" +
            "#####\n" +
            "#####\n" +
            "##.##\n" +
            "##...\n" +
            "\n" +
            "#####\n" +
            "#####\n" +
            "#.###\n" +
            "####E\n" +
            "\n" +
            "1 3 3\n" +
            "S##\n" +
            "#E#\n" +
            "###\n" +
            "\n" +
            "0 0 0";

    public static void main(String[] args) throws IOException {
        while (true) {//0 0 0을 받을 때까지 반복한다.
            min = Integer.MAX_VALUE;
            //br = new BufferedReader(new StringReader(input));
            st = new StringTokenizer(br.readLine());
            L = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            if (L == 0 && M == 0 & N == 0) {//종료조건 체크
                bw.flush();
                bw.close();
                return;
            }
            arr = new String[L][N][M];//3차원 배열 생성
            visited = new boolean[L][N][M];//3차원 배열 생성
            String[] temp;
            int loc = 0, start1 = 0, start2 = 0;
            for (int i = 0; i < L; i++) {
                for (int j = 0; j < N; j++) {
                    temp = br.readLine().split("");
                    for (int k = 0; k < M; k++) {
                        if (temp[k].equals("S")) {//시작 위치를 찾으면, 따로 저장해준다.
                            loc = i;
                            start1 = j;
                            start2 = k;
                        }
                        arr[i][j][k] = temp[k];
                    }
                }
                br.readLine();
            }
            bfs(loc, start1, start2, 0);//시작 위치부터 dfs 수행한다.
            if (min == Integer.MAX_VALUE) {//도착할수 없으면
                bw.write("Trapped!" + "\n");
            } else {
                bw.write("Escaped in " + min + " minute(s)." + "\n");
            }

        }


    }

    static void bfs(int l, int r, int c, int cnt) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{l, r, c, cnt});
        visited[l][r][c] = true;
        while (!queue.isEmpty()) {
            int[] escape = queue.poll();
            if (arr[escape[0]][escape[1]][escape[2]].equals("E")) {//도착했다면 그동안의 cnt를 min에 저장한다.
                min = escape[3];
                return;
            }
            for (int d = 0, size = deltas.length; d < size; d++) {//사방 + 위아래 탐색!
                int cl = escape[0] + deltas[d][0];
                int cr = escape[1] + deltas[d][1];
                int cc = escape[2] + deltas[d][2];
                int count = escape[3];

                if (isIn(cl, cr, cc) && !visited[cl][cr][cc]) {
                    visited[cl][cr][cc] = true;
                    queue.offer(new int[]{cl, cr, cc, count + 1});
                }
            }
        }
    }


    static boolean isIn(int l, int r, int c) {
        return r >= 0 && c >= 0 && l >= 0 && r < N && c < M && l < L && !arr[l][r][c].equals("#");
    }


}