package bj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Bj_6593_상범빌딩 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder output = new StringBuilder();
	static StringTokenizer tokens;
	static int L, R, C, result=0;
	static char[][][] map;
	static boolean[][][] visited;
	static Position[] deltas = {new Position(0,-1,0),new Position(0,1,0),new Position(0,0,-1),new Position(0,0,1),new Position(1,0,0),new Position(-1,0,0)}; //상 하 좌 우 위 아래
	static Queue<Position> q;
	static boolean check; // 탈출 여부
	
	static class Position{ // 각 좌표
		int l, r, c;

		public Position(int l, int r, int c) {
			super();
			this.l = l;
			this.r = r;
			this.c = c;
		}
		
	}
	private static void bfs() {
		while(q.size()>0) {
			int size=q.size();
			result++;
			for(int i=0;i<size;i++) {
				Position p = q.poll();
				for(int d=0;d<deltas.length;d++) {
					int nl=p.l+deltas[d].l;
					int nr=p.r+deltas[d].r;
					int nc=p.c+deltas[d].c;
					
					if(nl<0||nl>=L||nr<0||nr>=R||nc<0||nc>=C) continue;
					if(map[nl][nr][nc]!='#'&&!visited[nl][nr][nc]) { // 다음 갈 곳 정하기
						q.add(new Position(nl,nr,nc));
						visited[nl][nr][nc]=true;
					}
					if(map[nl][nr][nc]=='E') { // 탈출할 경우
						check=true;
						return;
					}
				}				
			}
		}
	}
	public static void main(String[] args) throws Exception {
		
		while(true) {
			tokens=new StringTokenizer(input.readLine());
			L=Integer.parseInt(tokens.nextToken());
			R=Integer.parseInt(tokens.nextToken());
			C=Integer.parseInt(tokens.nextToken());
			
			if(L==0&&R==0&&C==0) break;
			map=new char[L][R][C];
			result=0;
			q = new LinkedList<Position>();
			visited=new boolean[L][R][C];
			check=false;
			
			for(int l=0;l<L;l++) {
				for(int r=0;r<=R;r++) {
					if(r==R) {
						input.readLine();
						continue;
					}
					map[l][r]= input.readLine().toCharArray();
					for(int c=0;c<C;c++) {
						if(map[l][r][c]=='S') { // 첫 위치 넣어주기
							q.add(new Position(l,r,c));
							visited[l][r][c]=true;
						}
					}
				}
			}// for:L
			
			bfs();
			if(!check)output.append("Trapped!\n"); // 통과할 경우
			else output.append(String.format("Escaped in %d minute(s).\n", result)); //통과 못 할 경우
		}
		System.out.println(output);
	}
}
