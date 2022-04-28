package algorithm;

import java.io.*;
import java.util.*;

public class BJ23288Dice2 {
	static int[][] dice = {{0, 2, 0}, {4, 1, 3}, {0, 5, 0}, {0, 6, 0}};
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};
	static int[] loc = {0, 0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][M];
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = s.charAt(j * 2) - '0';
			}
		}
		int score = 0;
		int time = 0;
		int dir = 0;
		while(time++ < K) {
			// 한 칸 이동
			int nr = loc[0] + dr[dir];
			int nc = loc[1] + dc[dir];
			if(nr < 0 || nr >= N || nc < 0 || nc >= M) {
				dir = (dir + 2) % 4;
				nr = loc[0] + dr[dir];
				nc = loc[1] + dc[dir];
			}
			loc = new int[] {nr, nc};
			
			roll(dir);
			
			Queue<int[]> queue = new LinkedList<int[]>();
			boolean[][] visited = new boolean[N][M];
			queue.offer(loc);
			visited[loc[0]][loc[1]] = true;
			int cnt = 1;
			while(!queue.isEmpty()) {
				int[] cur = queue.poll();
				for (int i = 0; i < 4; i++) {
					nr = cur[0] + dr[i];
					nc = cur[1] + dc[i];
					if(nr < 0 || nr >= N || nc < 0 || nc >= M || visited[nr][nc] || map[nr][nc] != map[cur[0]][cur[1]])
						continue;
					cnt++;
					visited[nr][nc] = true;
					queue.offer(new int[] {nr, nc});
				}
			}
			score += cnt * map[loc[0]][loc[1]];
			
			if(dice[3][1] > map[loc[0]][loc[1]])
				dir = (dir + 1) % 4;
			else if(dice[3][1] < map[loc[0]][loc[1]])
				dir = (dir + 3) % 4;
		}
		System.out.println(score);
	}

	public static void roll(int dir) {
		int temp;
		switch(dir) {
		case 0: // 오른쪽
			temp = dice[1][2];
			dice[1][2] = dice[1][1];
			dice[1][1] = dice[1][0];
			dice[1][0] = dice[3][1];
			dice[3][1] = temp;
			break;
		case 1: // 아래쪽
			temp = dice[0][1];
			dice[0][1] = dice[3][1];
			dice[3][1] = dice[2][1];
			dice[2][1] = dice[1][1];
			dice[1][1] = temp;
			break;
		case 2: // 왼쪽
			temp = dice[1][0];
			dice[1][0] = dice[1][1];
			dice[1][1] = dice[1][2];
			dice[1][2] = dice[3][1];
			dice[3][1] = temp;
			break;
		case 3: // 위쪽
			temp = dice[3][1];
			dice[3][1] = dice[0][1];
			dice[0][1] = dice[1][1];
			dice[1][1] = dice[2][1];
			dice[2][1] = temp;
			break;
		}
	}
}
