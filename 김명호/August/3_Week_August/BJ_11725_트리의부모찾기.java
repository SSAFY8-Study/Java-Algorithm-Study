import java.io.*;
import java.lang.*;
import java.util.*;
//73084	652
public class BJ_11725_트리의부모찾기{
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static StringTokenizer st;
    public static int[][] arr;
    public static boolean[] visited;
    public static int N;
    public static int []result;
    public static List<Integer> list = new ArrayList<>();
    public static List<List<Integer>> datas = new ArrayList<>();

    public static void main(String[] args) throws IOException {//인접리스트를 이용해 시간초과를 해결하였다.
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());//노드의 개수
        visited = new boolean[N + 1];
        result = new int[N+1];
        for (int i = 0; i <= N; i++) {//인접리스트를 구현하기 위해 리스트를 N개만큼 만들어준다.
            datas.add(new ArrayList<>());
        }
        for (int i = 0; i < N-1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            datas.get(a).add(b);
            datas.get(b).add(a);
        }
        
        
        bfs();
        for (int i = 2; i <= N; i++) {
			bw.write(result[i]+"\n");
		}
        //2번 노드부터 노드의 조상 프린트
        
        bw.flush();
        bw.close();
    }

    public static void bfs() {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);//루트 노드를 담는다.
        visited[1] = true;
        while (!queue.isEmpty()) {
            int node = queue.poll();
            
            for (int i = 0; i < datas.get(node).size(); i++) {//출발점부터 각 노드마다 갈수있는 노드를 담는다.
                int next = datas.get(node).get(i);
                if (!visited[next]) {
                	result[next] = node;
                    visited[next] = true;
                    queue.offer(next);//방문하지 않았으면 방문 표시 이후 큐에 offer
                }


            }
        }
    }


}