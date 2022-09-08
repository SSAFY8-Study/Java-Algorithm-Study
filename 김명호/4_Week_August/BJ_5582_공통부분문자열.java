import java.io.*;
import java.lang.*;
import java.util.*;

public class BJ_5582_공통부분문자열 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {//직전 문자에 +1한 값을 넣는다.
        String[] datas1 = br.readLine().split("");
        String[] datas2 = br.readLine().split("");
        int[][] dataVisited = new int[datas1.length][datas2.length];
        int max = 0;
        for (int i = 0; i < datas1.length; i++) {
            for (int j = 0; j < datas2.length; j++) {
                if (datas1[i].equals(datas2[j])) {
                    if (i == 0 || j == 0)
                        dataVisited[i][j] = 1;
                    else
                        dataVisited[i][j] = dataVisited[i - 1][j - 1] + 1;
                    max = Math.max(max,dataVisited[i][j]);
                }
            }
        }


        bw.write(max + "\n");
        bw.flush();
        bw.close();
    }

}