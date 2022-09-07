package algorithm;

import java.io.*;
import java.util.*;

public class SW10966Water {

	public static void main(String[] args) throws NumberFormatException, IOException {
		int[] dr = {-1, 0, 1, 0};
		int[] dc = {0, 1, 0, -1};
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");
			int answer = 0;
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			char[][] map = new char[N][];
			boolean[][] isVisited = new boolean[N][M];
			Queue<int[]> queue = new LinkedList<int[]>();
			for (int i = 0; i < N; i++) {
				map[i] = br.readLine().toCharArray();
				for (int j = 0; j < M; j++) {
					if(map[i][j] == 'W') {
						queue.offer(new int[] {i, j});
						isVisited[i][j] = true;
					}
				}
			}
			
			int[][] memo = new int[N][M];
			int dis = 0;
			while(!queue.isEmpty()) {
				dis++;
				int size = queue.size();
				for (int i = 0; i < size; i++) {
					int[] cur = queue.poll();
					for (int d = 0; d < 4; d++) {
						int nr = cur[0] + dr[d];
						int nc = cur[1] + dc[d];
						if(nr < 0 || nr >= N || nc < 0 || nc >= M || map[nr][nc] == 'W' || isVisited[nr][nc] || (memo[nr][nc] != 0 && memo[nr][nc] <= dis))
							continue;
						queue.offer(new int[] {nr, nc});
						isVisited[nr][nc] = true;
						memo[nr][nc] = dis;
					}
				}
			}
					
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					answer += memo[i][j];
				}
			}
			
			sb.append(answer).append("\n");
		}
		
		System.out.println(sb.toString());
	}
}
