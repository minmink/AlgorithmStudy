package algorithm;

import java.io.*;
import java.util.*;

public class SW1260Chemical {
	static int[][] multiple, dp;
	static ArrayList<Chem> al;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");
			int n = Integer.parseInt(br.readLine());
			int[][] map = new int[n][n];
			for (int i = 0; i < n; i++) {
				String s = br.readLine();
				for (int j = 0; j < n; j++) {
					map[i][j] = s.charAt(j * 2) - '0';
				}
			}
			
			al = new ArrayList<>();
			boolean[][] visited = new boolean[n][n];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if(map[i][j] == 0 || visited[i][j])
						continue;
					int W = n - j;
					int H = n - i;
					for (int r = i; r < n; r++) {
						if(map[r][j] == 0) {
							H = r - i;
							break;
						}
						for (int c = j; c < n; c++) {
							if(map[r][c] == 0) {
								W = c - j;
								break;
							}
							visited[r][c] = true;
						}
					}
					
					al.add(new Chem(W, H));
				}
			}
			
			multiple = new int[al.size()][2];
			findOrder();
			dp = new int[al.size()][al.size()];
            for(int i = 0; i < al.size(); i++)
                Arrays.fill(dp[i], -1);
			
			sb.append(calMinMult(0, al.size() - 1)).append("\n");
		}

		System.out.println(sb.toString());
	}
	
	private static int calMinMult(int left, int right){
        if(left == right)
        	return 0;
        if(dp[left][right] != -1)
        	return dp[left][right];
        
        int min = Integer.MAX_VALUE;
        for(int i = left; i < right; i++){
            int leftResult = calMinMult(left, i);
            int rightResult = calMinMult(i + 1, right);
            int num = multiple[left][0] * multiple[i][1] * multiple[right][1];
            min = Math.min(leftResult + rightResult + num, min);
        }
        
        return dp[left][right] = min;
    }
	
	private static void findOrder(){
        int len = al.size();
        int pos = 0;
        boolean flag;

        // 수나사의 숫자가 암나사 쪽에 안나오는 나사 찾기
        for(int i=0; i<len; i++){
            flag = false;
            for(int j=0; j<len; j++){
                if(i == j)
                    continue;
                // 수나사와 암나사가 같은 경우
                if(al.get(i).H == al.get(j).W){
                    flag = true;
                    break;
                }
            }
            if(!flag)
                pos = i;
        }
        multiple[0][0] = al.get(pos).H;
        multiple[0][1] = al.get(pos).W;

        // 암나사의 값을 수나사의 값으로 갖는 나사를 찾아가며 쓴다.
        int cur = 0;
        int cnt = 1;
        while(len > 1){
            if(cur == pos){
                cur++;
                continue;
            }
            
            // 연결된 나사중 제일 끝의 암나사의 값과 연결하지 않은 다른 나사의 수나사의 값이 같은 경우 연결
            if(al.get(pos).W == al.get(cur).H){
                multiple[cnt][0] = al.get(cur).H;
                multiple[cnt++][1] = al.get(cur).W;
                len--;
                pos = cur;
                cur = 0;    // 모든 나사 탐색을 위해 다시 index 0으로 초기화
            }
            else
                cur++;
        }
    }
	
	private static class Chem {
		int W, H;
		public Chem(int W, int H) {
			this.W = W;
			this.H = H;
		}
	}
}
