import java.util.*;

class Solution {
    public int solution(int[][] board, int[][] skill) {
        int rows = board.length;
        int cols = board[0].length;
        
        int[][] affected = new int[rows][cols];
        
        for(int[] s: skill){
            // 적 공격
            if(s[0] == 1){      
                affected[s[1]][s[2]] -= s[5];       // 시작 칸 데미지
                
                if(s[4]+1 < cols)
                    affected[s[1]][s[4]+1] += s[5];
                
                if(s[3]+1 < rows){
                    affected[s[3]+1][s[2]] += s[5];
                    if(s[4]+1 < cols)
                        affected[s[3]+1][s[4]+1] -= s[5];
                }
            }
            else{                               // 아군 힐
                affected[s[1]][s[2]] += s[5];   // 시작 칸 힐
                
                if(s[4]+1 < cols)
                    affected[s[1]][s[4]+1] -= s[5];
                
                if(s[3]+1 < rows){
                    affected[s[3]+1][s[2]] -= s[5];
                    if(s[4]+1 < cols)
                        affected[s[3]+1][s[4]+1] += s[5];
                }
            }
        }
        
        // r-1, c 값을 r, c에 복사하기
        for(int r=1; r<rows; r++){
            for(int c=0; c<cols; c++){
                affected[r][c] += affected[r-1][c];
            }
        }
        
        // r, c-1값을 r, c에 복사하기
        for(int r=0; r<rows; r++){
            for(int c=1; c<cols; c++){
                affected[r][c] += affected[r][c-1];
            }
        }
        
        int ans = 0;
        for(int r=0; r<rows; r++){
            for(int c=0; c<cols; c++){
                if(affected[r][c] > -board[r][c])
                    ans ++;
            }
        }
        
        return ans;
    }
}