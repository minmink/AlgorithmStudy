package algorithm;

import java.io.*;
import java.util.*;

public class SW1949Hiking {
	static int result, N, K, map[][];
	static boolean[][] visited;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int testCase = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= testCase; tc++) {
			sb.append("#").append(tc).append(" ");
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			int max = 0;
			result = 1;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					max = Math.max(max, map[i][j]);
				}
			}
			ArrayList<int[]> nodes = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(map[i][j] == max)
						nodes.add(new int[] {i, j});
				}
			}
			
			visited = new boolean[N][N];
			for (int[] node : nodes) {
				visited[node[0]][node[1]] = true;
				dfs(node, 1, 1);
				visited[node[0]][node[1]] = false;
			}
			
			sb.append(result).append("\n");
		}
		System.out.println(sb.toString());
	}

	public static void dfs(int[] node, int cnt, int len) {
		boolean fin = true;
		
		for (int dir = 0; dir < 4; dir++) {
			int nr = node[0] + dr[dir];
			int nc = node[1] + dc[dir];
			if(nr < 0 || nr >= N || nc < 0 || nc >= N || visited[nr][nc])
				continue;
			if(map[node[0]][node[1]] <= map[nr][nc] && cnt == 1 && map[nr][nc] - map[node[0]][node[1]] + 1 <= K) {
				fin = false;
				int temp = map[nr][nc];
				map[nr][nc] = map[node[0]][node[1]] - 1;
				visited[nr][nc] = true;
				dfs(new int[] {nr, nc}, 0, len + 1);
				visited[nr][nc] = false;
				map[nr][nc] = temp;
				continue;
			} else if (map[node[0]][node[1]] <= map[nr][nc])
				continue;
			fin = false;
			visited[nr][nc] = true;
			dfs(new int[] {nr, nc}, cnt, len + 1);
			visited[nr][nc] = false;
		}
		
		if(fin)
			result = Math.max(result, len);
	}
}
