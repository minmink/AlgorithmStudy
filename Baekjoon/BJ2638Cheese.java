package algorithm;

import java.io.*;
import java.util.*;

public class BJ2638Cheese {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][M];
		int[] dr = {-1, 0, 1, 0};
		int[] dc = {0, 1, 0, -1};
		int cnt = 0;
		int time = 0;
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = s.charAt(j * 2) - '0';
				if (map[i][j] == 1)
					cnt++;
			}
		}
		if (cnt == 0) {
			System.out.println(0);
			return;
		}
		while(cnt > 0) {
			Queue<int[]> queue = new LinkedList<int[]>();
			boolean[][] visited = new boolean[N][M];
			boolean[][] outAir = new boolean[N][M];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if((i != 0 && j != 0) || visited[i][j] || map[i][j] == 1)
						continue;
					visited[i][j] = true;
					queue.offer(new int[] {i, j});
					while(!queue.isEmpty()) {
						int[] cur = queue.poll();
						outAir[cur[0]][cur[1]] = true;
						for (int dir = 0; dir < 4; dir++) {
							int nr = cur[0] + dr[dir];
							int nc = cur[1] + dc[dir];
							if (nr < 0 || nr >= N || nc < 0 || nc >= M || map[nr][nc] == 1 || visited[nr][nc])
								continue;
							visited[nr][nc] = true;
							queue.offer(new int[] {nr, nc});
						}
					}
				}
			}
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if(map[i][j] == 0)
						continue;
					int airCnt = 0;
					for (int dir = 0; dir < 4; dir++) {
						int nr = i + dr[dir];
						int nc = j + dc[dir];
						if (nr < 0 || nr >= N || nc < 0 || nc >= M || !outAir[nr][nc])
							continue;
						airCnt++;
					}
					if(airCnt >= 2) {
						map[i][j] = 0;
						cnt--;
					}
				}
			}
			time++;
		}
		System.out.println(time);
	}

}
