package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class SW1767Processor {

	static ArrayList<int[]> cores;
	static int N, maxCnt, minLen;
	static int[][] map;
	static boolean[] up, down, left, right;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine().trim());
		StringBuilder sb = new StringBuilder();
		for (int testCase = 1; testCase <= T; testCase++) {
			sb.append("#").append(testCase).append(" ");
			N = Integer.parseInt(br.readLine().trim());
			cores = new ArrayList<>();
			map = new int[N][N];
			maxCnt = 0;
			minLen = Integer.MAX_VALUE;
			up = new boolean[N]; down = new boolean[N]; left = new boolean[N]; right = new boolean[N];
			
			for (int i = 0; i < N; i++) {
				String s = br.readLine();
				for (int j = 0; j < N; j++) {
					map[i][j] = s.charAt(2*j)-'0';
					if(map[i][j] == 1)
						cores.add(new int[] {i, j});
				}
			}
			
			dfs(0, 0, 0);
			
			sb.append(minLen).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static void dfs(int idx, int cnt, int len) {
		if (cnt+cores.size()-idx < maxCnt)
			return;
		if (cnt+cores.size()-idx == maxCnt && minLen <= len)
			return;
		
		if(idx == cores.size()) {
			maxCnt = cnt;
			minLen = len;
			return;
		}
		
		int[] cur = cores.get(idx);
		// 위
		boolean flag = true;
		for (int i = 0; i < cur[0]; i++) {
			if(map[i][cur[1]] == 1) {
				flag = false;
				break;
			}
		}
		if(flag) {
			for (int i = 0; i < cur[0]; i++) {
				map[i][cur[1]] = 1;
			}
			dfs(idx+1, cnt+1, len+cur[0]);
			for (int i = 0; i < cur[0]; i++) {
				map[i][cur[1]] = 0;
			}
			if(cur[0] == 0)
				return;
		}
		// 아래
		flag = true;
		for (int i = cur[0]+1; i < N; i++) {
			if(map[i][cur[1]] == 1) {
				flag = false;
				break;
			}
		}
		if(flag) {
			for (int i = cur[0]+1; i < N; i++) {
				map[i][cur[1]] = 1;
			}
			dfs(idx+1, cnt+1, len+N-1-cur[0]);
			for (int i = cur[0]+1; i < N; i++) {
				map[i][cur[1]] = 0;
			}
			if(cur[0] == N-1)
				return;
		}
		// 왼
		flag = true;
		for (int i = 0; i < cur[1]; i++) {
			if(map[cur[0]][i] == 1) {
				flag = false;
				break;
			}
		}
		if(flag) {
			for (int i = 0; i < cur[1]; i++) {
				map[cur[0]][i] = 1;
			}
			dfs(idx+1, cnt+1, len+cur[1]);
			for (int i = 0; i < cur[1]; i++) {
				map[cur[0]][i] = 0;
			}
			if(cur[1] == 0)
				return;
		}
		// 오른
		flag = true;
		for (int i = cur[1]+1; i < N; i++) {
			if(map[cur[0]][i] == 1) {
				flag = false;
				break;
			}
		}
		if(flag) {
			for (int i = cur[1]+1; i < N; i++) {
				map[cur[0]][i] = 1;
			}
			dfs(idx+1, cnt+1, len+N-1-cur[1]);
			for (int i = cur[1]+1; i < N; i++) {
				map[cur[0]][i] = 0;
			}
			if(cur[1] == N-1)
				return;
		}
		
		dfs(idx+1, cnt, len);
	}
}
