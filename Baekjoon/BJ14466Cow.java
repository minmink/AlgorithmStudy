package algorithm;

import java.io.*;
import java.util.*;

public class BJ14466Cow {
	static int N, K, R, total;
	static boolean map[][], road[][][];
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		total = K * (K - 1) / 2;
		map = new boolean[N][N];
		road = new boolean[N][N][4];
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int startR = Integer.parseInt(st.nextToken()) - 1;
			int startC = Integer.parseInt(st.nextToken()) - 1;
			int endR = Integer.parseInt(st.nextToken()) - 1;
			int endC = Integer.parseInt(st.nextToken()) - 1;
			if (startR - endR == 1) {
				road[startR][startC][0] = true;
				road[endR][endC][2] = true;
			} else if (startR - endR == -1) {
				road[startR][startC][2] = true;
				road[endR][endC][0] = true;
			} else if (startC - endC == 1) {
				road[startR][startC][3] = true;
				road[endR][endC][1] = true;
			} else {
				road[startR][startC][1] = true;
				road[endR][endC][3] = true;
			}
		}
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			map[Integer.parseInt(st.nextToken()) - 1][Integer.parseInt(st.nextToken()) - 1] = true;
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j]) {
					bfs(i, j);
					map[i][j] = false;
				}
			}
		}
		
		System.out.println(total);
	}

	private static void bfs (int r, int c) {
		boolean[][] visited = new boolean[N][N];
		Queue<int[]> queue = new LinkedList<int[]>();
		visited[r][c] = true;
		queue.offer(new int[] {r, c});
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			if ((cur[0] != r || cur[1] != c) && map[cur[0]][cur[1]])
				total--;
			
			for (int i = 0; i < 4; i++) {
				int nr = cur[0] + dr[i];
				int nc = cur[1] + dc[i];
				if (nr < 0 || nr >= N || nc < 0 || nc >= N || visited[nr][nc] || road[cur[0]][cur[1]][i])
					continue;
				visited[nr][nc] = true;
				queue.offer(new int[] {nr, nc});
			}
		}
	}
}
