package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW7699Travel {

	static int R, C, max;
	static char[][] map;
	static boolean[] visited;
	static int[] dr = {-1, 1, 0, 0}; // 상하좌우
	static int[] dc = {0, 0, -1, 1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		for (int t = 1; t <= T; t++) {
			sb.append("#").append(t).append(" ");
			st = new StringTokenizer(br.readLine());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			map = new char[R][C];
			visited = new boolean[26];
			max = 0;
			for (int i = 0; i < R; i++) {
				String s = br.readLine();
				for (int j = 0; j < C; j++) {
					map[i][j] = s.charAt(j);
				}
			}
			
			visited[map[0][0] - 'A'] = true;
			dfs(0, 0, 1);
			sb.append(max).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static void dfs(int row, int col, int cnt) {
		boolean flag = false;
		int nr, nc;
		for (int i = 0; i < 4; i++) {
			nr = row+dr[i];
			nc = col+dc[i];
			if(0<=nr && nr<R && 0<=nc && nc<C && !visited[map[nr][nc] - 'A']) {
				flag = true;
				visited[map[nr][nc]-'A'] = true;
				dfs(nr, nc, cnt+1);
				visited[map[nr][nc]-'A'] = false;
			}
		}
		
		if(!flag) {
			if(max<cnt) max = cnt;
		}
	}
}
