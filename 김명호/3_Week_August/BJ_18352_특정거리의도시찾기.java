import java.io.*;
import java.lang.*;
import java.util.*;
//340696	1336
public class BJ_18352_특정거리의도시찾기 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static StringTokenizer st;
    public static int[][] arr;
    public static boolean[] visited;
    public static int N, M, K, X;
    public static List<Integer> list = new ArrayList<>();
    public static List<List<Integer>> datas = new ArrayList<>();

    public static void main(String[] args) throws IOException {//인접리스트를 이용해 시간초과를 해결하였다.
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        visited = new boolean[N + 1];
        for (int i = 0; i <= N; i++) {//인접리스트를 구현하기 위해 리스트를 N개만큼 만들어준다. 인덱스가 노드의 역할을 함.
            datas.add(new ArrayList<>());
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            datas.get(Integer.parseInt(st.nextToken())).add(Integer.parseInt(st.nextToken()));//단방향 간선이므로 출발노드에만 넣어준다.
        }
        bfs();
        if (list.size() == 0) {
            bw.write(-1 + "\n");
        } else {
            Collections.sort(list);
            for (int i = 0; i < list.size(); i++) {
                bw.write(list.get(i) + "\n");
            }
        }
        bw.flush();
        bw.close();
    }

    public static void bfs() {
        Queue<Integer[]> queue = new LinkedList<>();
        queue.offer(new Integer[]{X, 0});//출발점과 cnt를 담는다.
        visited[X] = true;
        while (!queue.isEmpty()) {
            Integer[] node = queue.poll();
            if (node[1] == K)//K에 도달하면 list에 add해준다.
                list.add(node[0]);
            for (int i = 0; i < datas.get(node[0]).size(); i++) {//출발점부터 각 노드마다 갈수있는 노드를 담는다.
                int next = datas.get(node[0]).get(i);
                if (!visited[next]) {
                    visited[next] = true;
                    queue.offer(new Integer[]{next, node[1] + 1});//방문하지 않았으면 방문 표시 이후 큐에 offer
                }


            }
        }
    }


}