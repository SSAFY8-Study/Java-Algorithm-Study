import java.io.*;
import java.lang.*;
import java.util.*;

public class BJ_4803_트리 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static List<Integer>[] list;
    public static boolean[] visited;
    public static boolean[] checkCycle;
    public static int N, M, cnt = 0;
    public static int T = 0;
    public static boolean flag = false;

    public static void main(String[] args) throws IOException {
        while (true) {
            T++;
            String[] temp = br.readLine().split(" ");
            N = Integer.parseInt(temp[0]);
            M = Integer.parseInt(temp[1]);
            if (N == 0 && M == 0) {//0 0 이 들어오면 종료
                bw.flush();
                bw.close();
                return;
            }
            list = new List[N + 1];//1부터 쓰기 위해 N+1
            for (int i = 1; i <= N; i++) {//2차원 리스트를 작성하자.
                list[i] = new ArrayList<>();
            }
            visited = new boolean[N + 1];//1부터 N까지 쓰자.
            cnt = 0;
            for (int i = 0; i < M; i++) {
                temp = br.readLine().split(" ");
                list[Integer.parseInt(temp[0])].add(Integer.parseInt(temp[1]));
                list[Integer.parseInt(temp[1])].add(Integer.parseInt(temp[0]));
//M만큼 입력받는데, list의 인덱스를 노드라고 생각하고 각 노드가 가야할 곳을 add한다.
//무방향 그래프이므로 출발인덱스와 도착인덱스 모두에 상대 인덱스를 add해준다.
            }
            for (int i = 1; i <= N; i++) {
                if (!visited[i]) {//방문하지 않았으면
                    if(dfs(i,0))//dfs를 실행하는데, 사이클이 없다면
                        cnt++;//카운트해준다.
                }
            }
            switch (cnt) {
                case 0:
                    bw.write("Case " + T + ": No trees." + "\n");
                    break;
                case 1:
                    bw.write("Case " + T + ": There is one tree." + "\n");
                    break;
                default:
                    bw.write("Case " + T + ": A forest of " + cnt + " trees." + "\n");
            }


        }


    }

    public static boolean dfs(int Node, int Before) {//출발점이 노드이고 이전에 방문했던 노드는 Before에 저장한다.
    												//중복으로 방문하지 않기위해서!
        visited[Node] = true;//출발노드를 방문하고,
        for (int i = 0; i < list[Node].size(); i++) {//그 노드가 가야하는 인덱스를 방문하기 위해 for문을 실행한다.
            if(list[Node].get(i) == Before)
                continue;//들렀던 노드는 들릴 필요는 없다.
            else if (visited[list[Node].get(i)]) {//방문하려고 하는 곳이 이미 방문 했다면 사이클이다.
                return false;
            } else if (!dfs(list[Node].get(i),Node)) {//하나가 false면 이렇게 해야 재귀탈출이 가능하다.
                return false;
            }
        }
        return true;//모든 조건을 통과하면 트리이다!
    }

}
