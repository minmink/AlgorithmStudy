package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ23288Dice {
	static int N, M, K, answer = 0, cur[] = new int[] {0, 0, 1};
	static int[][] map, dice = new int[][] {{0, 2, 0}, {4, 1, 3}, {0, 5, 0}, {0, 6, 0}};
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		StringTokenizer st = new StringTokenizer(s, " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			s = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = s.charAt(j * 2) - '0';
			}
		}
		int nr, nc, n;
		for (int testCase = 0; testCase < K; testCase++) {
			// 1칸 이동
			nr = cur[0] + dr[cur[2]];
			nc = cur[1] + dc[cur[2]];
			if (nr < 0 || nr >= N || nc < 0 || nc >= M) {
				cur[2] = (cur[2] + 2) % 4;
				nr = cur[0] + dr[cur[2]];
				nc = cur[1] + dc[cur[2]];
			}
			cur[0] = nr;
			cur[1] = nc;
			roll(cur[2]);
			// 점수 획득
			score();
			// 방향 바꾸기
			n = map[cur[0]][cur[1]];
			if (dice[3][1] > n)
				cur[2] = (cur[2] + 1) % 4;
			else if (dice[3][1] < n)
				cur[2] = (cur[2] + 3) % 4;
		}
		System.out.println(answer);
	}

	public static void roll(int dir) {
		int temp;
		switch (dir) {
		// 상
		case 0:
			temp = dice[0][1];
			dice[0][1] = dice[1][1];
			dice[1][1] = dice[2][1];
			dice[2][1] = dice[3][1];
			dice[3][1] = temp;
			break;
		// 우
		case 1:
			temp = dice[3][1];
			dice[3][1] = dice[1][2];
			dice[1][2] = dice[1][1];
			dice[1][1] = dice[1][0];
			dice[1][0] = temp;
			break;
		// 하
		case 2:
			temp = dice[3][1];
			dice[3][1] = dice[2][1];
			dice[2][1] = dice[1][1];
			dice[1][1] = dice[0][1];
			dice[0][1] = temp;
			break;
		// 좌
		case 3:
			temp = dice[3][1];
			dice[3][1] = dice[1][0];
			dice[1][0] = dice[1][1];
			dice[1][1] = dice[1][2];
			dice[1][2] = temp;
			break;
		}
	}
	
	public static void score() {
		Queue<int[]> queue = new LinkedList<int[]>();
		queue.offer(new int[] {cur[0], cur[1]});
		int n = map[cur[0]][cur[1]];
		boolean[][] visit = new boolean[N][M];
		visit[cur[0]][cur[1]] = true;
		answer += n;
		while(!queue.isEmpty()) {
			int[] temp = queue.poll();
			for (int dir = 0; dir < 4; dir++) {
				int nr = temp[0] + dr[dir];
				int nc = temp[1] + dc[dir];
				if (nr < 0 || nr >= N || nc < 0 || nc >= M || map[nr][nc] != n || visit[nr][nc])
					continue;
				answer += n;
				queue.offer(new int[] {nr, nc});
				visit[nr][nc] = true;
			}
		}
	}
}
