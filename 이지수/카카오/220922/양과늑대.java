package Programmers.level3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2022. 9. 20.
@see https://school.programmers.co.kr/learn/courses/30/lessons/92343
@performance
@difficulty 
@category #
@note */
public class 양과늑대 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;
	static ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
	static int[] info;
	static boolean[][][] visited;
	static int max=-1;
	
	public static void main(String[] args) throws IOException {
//		int[] map = {0,0,1,1,1,0,1,0,1,0,1,1};
//		int[][] edges = {{0,1},{1,2},{1,4},{0,8},{8,7},{9,10},{9,11},{4,3},{6,5},{4,6},{8,9}};
		
		int[] map = {0,1,0,1,1,0,1,0,0,1,0};
		int[][] edges = {{0,1},{0,2},{1,3},{1,4},{2,5},{2,6},{3,7},{4,8},{6,9},{9,10}};
		
		info = map;
		
		solution(map, edges);
	}
	
	public static int solution(int[] info, int[][] edges) {
        int answer = 0;
        
        visited = new boolean[info.length][18][18];
        for(int i=0; i<info.length; i++) {
        	graph.add(new ArrayList<Integer>());
        }//그래프 초기화
        
        for(int i=0; i<edges.length; i++) {
        	graph.get(edges[i][0]).add(edges[i][1]);
        	graph.get(edges[i][1]).add(edges[i][0]); //양방향
        }
        
        
        dfs(new Node(0,0,0));
        System.out.println(max);
        
        return answer;
    }
	
	//0은 양 1은 늑대
	static void dfs(Node node) { 

		if(info[node.idx] == 0) {
			node.sheep++;
		}else if(info[node.idx] ==1) {
			node.wolf++;
		}
		
//		System.out.println(node.idx +" : "+node.sheep+" : "+node.wolf);
		if(node.wolf<node.sheep)
			max = Math.max(node.sheep, max);
		else if(node.wolf>=node.sheep) {//늑대의 수가 초기화 되는 건 아니기 때문에 양의수가 많은 경우가 나올 리가 없다.
			return;
		}
		
		for(int i=0; i<graph.get(node.idx).size(); i++) {
			int a = graph.get(node.idx).get(i); //연결되어 있는 가야할 노드 찾기
			int temp = info[node.idx];
			if(!visited[node.idx][node.sheep][node.wolf]) {
				visited[node.idx][node.sheep][node.wolf] = true;
				info[node.idx] = -1;//해당 노드 방문 후 사용처리
				dfs(new Node(a, node.sheep, node.wolf));
				visited[node.idx][node.sheep][node.wolf] = false;
				info[node.idx] = temp;
						
 			}
		}
	}
	
	static class Node{
		int idx, sheep, wolf;

		public Node(int idx, int sheep, int wolf) {
			super();
			this.idx = idx;
			this.sheep = sheep;
			this.wolf = wolf;
		}
		
	}
	
}