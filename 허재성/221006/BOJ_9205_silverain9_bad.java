// 14520KB	132ms

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
 
class Point {
    int x;
    int y;
 
    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
 
public class Main {
 
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    public static void main(String[] args) throws NumberFormatException, IOException {
        
 
        int T = Integer.parseInt(input.readLine());
 
        ArrayList<Point> a; // 집, 편의점, 페스티벌에 위치를 저장하는 배열
        ArrayList<ArrayList<Integer>> graph;
        
        StringBuilder output = new StringBuilder();
        while (T-- > 0) {
            int N = Integer.parseInt(input.readLine());
 
            a = new ArrayList<>();
            
            // 집, 편의점, 페스티벌에 위치를 저장함.
            for (int i = 0; i < N + 2; i++) {
                tokens = new StringTokenizer(input.readLine());
                int x = Integer.parseInt(tokens.nextToken());
                int y = Integer.parseInt(tokens.nextToken());
 
                a.add(new Point(x, y));
            }
 
            graph = new ArrayList<>();
            for (int i = 0; i < N + 2; i++) {
                graph.add(new ArrayList<>());
            }
            
            // 맨해튼 거리 1000m 이하를 만족하는 두 정점을 찾음.
            // 그리고 양방향 그래프로 서로 이어 줌.
            for (int i = 0; i < N + 2; i++) {
                for (int j = i + 1; j < N + 2; j++) {
                    if (calDistance(a.get(i), a.get(j)) <= 1000) {
                        graph.get(i).add(j);
                        graph.get(j).add(i);
                    }
                }
            }
            
            // BFS를 이용하여 집에서 페스티벌까지 갈 수 있는지 탐색.
            output.append((BFS(graph, N) ? "happy" : "sad") + '\n');
        }
        
        System.out.println(output);
    }
    
    // 맨해튼 거리
    public static int calDistance(Point p1, Point p2) {
        return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
    }
    
    // 전형적인 BFS 코드
    public static boolean BFS(ArrayList<ArrayList<Integer>> graph, int N) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(0);
 
        boolean[] visited = new boolean[N + 2];
        visited[0] = true;
 
        while (!q.isEmpty()) {
            int now = q.poll();
 
            if (now == N + 1) {
                return true;
            }
 
            for (int next : graph.get(now)) {
                if (!visited[next]) {
                    visited[next] = true;
                    q.offer(next);
                }
            }
        }
 
        return false;
    }
 
}