import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Item{
    int w,v;
    Item(int w,int v){
        this.w=w;
        this.v=v;
    }
    @Override
    public String toString() {
        return "Item [w=" + w + ", v=" + v + "]";
    }
}
public class BOJ_G5_12865_평범한배낭 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder output = new StringBuilder();
    static StringTokenizer tokens;
    static int N,K,W,V;
    static int[][] dp;
    static Item[] items;

    public static void main(String[] args) throws IOException {
    	input = new BufferedReader(new FileReader("./input.txt"));
        tokens=new StringTokenizer(input.readLine());
        N=Integer.parseInt(tokens.nextToken());
        K=Integer.parseInt(tokens.nextToken());
        dp=new int[N+1][K+1];
        items=new Item[N+1];
        for(int i=1;i<N+1;i++) {
            tokens=new StringTokenizer(input.readLine());
            items[i]=new Item(Integer.parseInt(tokens.nextToken()),Integer.parseInt(tokens.nextToken()));
        }

        for(int i=1;i<N+1;i++) {
            for(int j=1;j<K+1;j++) {
                if(j-items[i].w>=0) {
                    dp[i][j]=Math.max(dp[i-1][j], dp[i-1][j-items[i].w]+items[i].v);
                }
                else {
                    dp[i][j]=dp[i-1][j];
                }
            }
        }
        System.out.println(dp[N][K]);
    }
}


//dp[3][6] -> dp[2][6]+v[3] or dp[2][6-w[3]]+v[3]
//처음부터 i번째까지의 물건을 살펴보고, 배낭의 용량이 j였을 때 배낭에 들어간 물건의 가치합의 최댓값