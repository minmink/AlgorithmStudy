package algorithm;

import java.io.*;
import java.util.*;

public class SW1953Arrest {
	static int[][] map;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tc = 0; tc < testCase; tc++) {
			sb.append("#").append(tc + 1).append(" ");
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int R = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			int L = Integer.parseInt(st.nextToken());
			map = new int[N][M];
			for (int i = 0; i < N; i++) {
				String s = br.readLine();
				for (int j = 0; j < M; j++) {
					map[i][j] = s.charAt(j * 2) - '0';
				}
			}
			int cnt = 0;
			Queue<int[]> queue = new LinkedList<int[]>();
			boolean[][] visited = new boolean[N][M];
			queue.offer(new int[] {R, C});
			visited[R][C] = true;
			int time = 0;
			while(!queue.isEmpty() && time++ < L) {
				int size = queue.size();
				for (int i = 0; i < size; i++) {
					int[] cur = queue.poll();
					cnt++;
					for (int dir = 0; dir < 4; dir++) {
						int nr = cur[0] + dr[dir];
						int nc = cur[1] + dc[dir];
						if(nr < 0 || nr >= N || nc < 0 || nc >= M || map[nr][nc] == 0 || visited[nr][nc] || !possible(cur, new int[] {nr, nc}))
							continue;
						visited[nr][nc] = true;
						queue.offer(new int[] {nr, nc});
					}
				}
			}
			
			sb.append(cnt).append("\n");
		}
		System.out.println(sb.toString());
	}
	
	public static boolean possible(int[] cur, int[] next) {
		int diffR = cur[0] - next[0];
		int diffC = cur[1] - next[1];
		int curDir = map[cur[0]][cur[1]];
		int nextDir = map[next[0]][next[1]];
		
		if(diffR == 1 && diffC == 0) {
			if((curDir == 1 || curDir == 2 || curDir == 4 || curDir == 7) &&
					(nextDir == 1 || nextDir == 2 || nextDir == 5 || nextDir == 6))
				return true;
		} else if(diffR == -1 && diffC == 0) {
			if((curDir == 1 || curDir == 2 || curDir == 5 || curDir == 6) &&
					(nextDir == 1 || nextDir == 2 || nextDir == 4 || nextDir == 7))
				return true;
		} else if(diffC == 1) {
			if((curDir == 1 || curDir == 3 || curDir == 6 || curDir == 7) &&
					(nextDir == 1 || nextDir == 3 || nextDir == 4 || nextDir == 5))
				return true;
		} else {
			if((curDir == 1 || curDir == 3 || curDir == 4 || curDir == 5) &&
					(nextDir == 1 || nextDir == 3 || nextDir == 6 || nextDir == 7))
				return true;
		}
		
		return false;
	}
}
