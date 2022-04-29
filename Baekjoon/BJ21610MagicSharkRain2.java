package algorithm;

import java.io.*;
import java.util.*;

public class BJ21610MagicSharkRain2 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int[] dr = {0, -1, -1, -1, 0, 1, 1, 1};
		int[] dc = {-1, -1, 0, 1, 1, 1, 0, -1};
		Queue<int[]> queue = new LinkedList<int[]>();
		queue.offer(new int[] {N - 1, 0});
		queue.offer(new int[] {N - 1, 1});
		queue.offer(new int[] {N - 2, 0});
		queue.offer(new int[] {N - 2, 1});
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int d = Integer.parseInt(st.nextToken()) - 1;
			int s = Integer.parseInt(st.nextToken());
			boolean[][] increased = new boolean[N][N];
			while(!queue.isEmpty()) {
				int[] cur = queue.poll();
				int nr = (cur[0] + dr[d] * s + N * s) % N;
				int nc = (cur[1] + dc[d] * s + N * s) % N;
				map[nr][nc]++;
				increased[nr][nc] = true;
			}
			
			for (int row = 0; row < N; row++) {
				for (int col = 0; col < N; col++) {
					if(!increased[row][col])
						continue;
					int cnt = 0;
					for (int dir = 1; dir <= 7; dir = dir + 2) {
						int nr = row + dr[dir];
						int nc = col + dc[dir];
						if(nr < 0 || nr >= N || nc < 0 || nc >= N || map[nr][nc] < 1)
							continue;
						cnt++;
					}
					map[row][col] += cnt;
				}
			}
			
			for (int row = 0; row < N; row++) {
				for (int col = 0; col < N; col++) {
					if(map[row][col] >= 2 && !increased[row][col]) {
						queue.offer(new int[] {row, col});
						map[row][col] -= 2;
					}
				}
			}
		}
		
		int answer = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				answer += map[i][j];
			}
		}
		System.out.println(answer);
	}

}
