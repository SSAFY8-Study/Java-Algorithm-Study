import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class n2644_촌수계산 {

	static int[][] arr, rev;
	static boolean findAB;
	static boolean[][] direct;
	static int a, b, p;
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		String str = bf.readLine();
		
		p = Integer.parseInt(str);
		arr = new int[p+1][p+1];
		rev = new int[p+1][p+1];
		
		str = bf.readLine();
		StringTokenizer st = new StringTokenizer(str, " ");
		
		a = Integer.parseInt(st.nextToken());
		b = Integer.parseInt(st.nextToken());
		
		str = bf.readLine();
		int n = Integer.parseInt(str);
		
		for(int i = 0; i < n; i++) {
			str = bf.readLine();
			st = new StringTokenizer(str, " ");
			
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			//BFS 에서 루트부모 -> 자식으로 찾아보자
			arr[x][y] = 1;
			//자식 -> 부모로 저장해서 루트를 찾을 수 있도록한다
			rev[y][x] = 1;
		}
		
		int result = 0;
		//root 찾기, 만약 i에게 부모가 없다면 그 i는 루트다!
		for(int i = 1; i <= p; i++) {
			int sum = 0;
			for(int j = 1; j <= p; j++) {
				sum += rev[i][j] = 0;
			}
			if(sum == 0) {
				result = check(i);
				if(result != 0) break;
			}
		}
		
		if(result == 0) System.out.println(-1);
		else System.out.println(result);
	}
	
	static int check(int root) {
		// 큐에는 리스트를 담아준다
		Queue<List<Integer>> q = new LinkedList<>();
		
		// 시작은 루트번호(최고조상)만 담은 리스트를 넣어준다
		List<Integer> list = new LinkedList<>();
		list.add(root);
		q.add(list);
		
		// 혹시 A or B를 찾았다면 체크해준다
		findAB = false;
		// A or B를 찾은 후 카운트하여 둘의 차이를 알도록 한다
		int count = 0;
		List<Integer> listA = new LinkedList<>();
		while(!q.isEmpty()) {
			int len = q.size();
			
			if(findAB) count++;
			// 트리의 레벨 단위로 진행하기 위해 현재 담겨져 있는 큐만큼 돌리기로 나눠준다
			for(int l = 0; l < len; l++) {
				List<Integer> temp = q.poll();
				
				//현재 템프가 A or B라면 체크 해주고, 혹시 하나를 찾은 상태에서 찾았다면 리턴해준다
				if(temp.get(temp.size()-1) == a || temp.get(temp.size()-1) == b) {
					if(!findAB) {
						findAB = true;
						for(int j = 0; j < temp.size(); j++) listA.add(temp.get(j));
					}
					else if(findAB) {
						int cnt = 0;
						//혹시 직계인지, 아니면 둘이 어디서 갈라졌는지 알아본다
						for(int j = 0; j < listA.size(); j++) {
							if(temp.get(j) != listA.get(j)) break;
							cnt++;
						}
						// 촌수 계산은 직계라면 그냥 카운트만 넘겨주면 되지만, 혹시 직계가 아니라면 그 차이에 2를 곱한만큼을 더해야 한다
						return 2*(listA.size() - cnt) + count;
					}
				}
				for(int i = 1; i <= p; i++) {
					if(arr[temp.get(temp.size()-1)][i] == 1) {
						List<Integer> li = new LinkedList<>();
						for(int j = 0; j < temp.size(); j++) li.add(temp.get(j));
						
						li.add(i);
						q.add(li);
					}
				}
			}
		}
		
		return 0;
	}

}
