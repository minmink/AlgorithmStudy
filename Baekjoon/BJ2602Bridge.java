package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ2602Bridge {

	static String order, up, down;
	static int[][][] dp;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		order = br.readLine();
		up = br.readLine();
		down = br.readLine();
		dp = new int[up.length()+1][order.length()][2];
		
		for (int i = 0; i < up.length(); i++) {
			for (int j = 0; j < order.length(); j++) {
				dp[i][j][0] = -1;
				dp[i][j][1] = -1;
			}
		}
		
		System.out.println(dfs(0, 0, 0) + dfs(0, 0, 1));
	}

	private static int dfs(int idxB, int idxO, int dir) {
		if(idxO == order.length()) {
			return 1;
		}
		
		if(dp[idxB][idxO][dir] != -1) {
			return dp[idxB][idxO][dir];
		}
		
		int total = 0;
		if(dir == 0) {
			for (int i = idxB; i < up.length(); i++) {
				if(order.charAt(idxO) == up.charAt(i)) {
					total += dfs(i+1, idxO+1, 1);
				}				
			}
		} else {
			for (int i = idxB; i < up.length(); i++) {
				if(order.charAt(idxO) == down.charAt(i)) {
					total += dfs(i+1, idxO+1, 0);
				}				
			}
		}
		
		return dp[idxB][idxO][dir] = total;
	}

}
