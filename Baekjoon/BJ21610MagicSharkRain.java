package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ21610MagicSharkRain {
	static int N, M, rear = -1, front = -1;
	static int[][] map, cloud, movement, copy;
	static boolean[][] isCloud;
	static int[] dr = {0, -1, -1, -1, 0, 1, 1, 1};
	static int[] dc = {-1, -1, 0, 1, 1, 1, 0, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		StringTokenizer st = new StringTokenizer(s, " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		cloud = new int[N * N * M][2];
		movement = new int[M][2];
		
		for (int i = 0; i < N; i++) {
			s = br.readLine();
			st = new StringTokenizer(s, " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < M; i++) {
			s = br.readLine();
			st = new StringTokenizer(s, " ");
			movement[i][0] = Integer.parseInt(st.nextToken()) - 1;
			movement[i][1] = Integer.parseInt(st.nextToken());
		}
		
		cloud[++front] = new int[] {N - 1, 0};
		cloud[++front] = new int[] {N - 1, 1};
		cloud[++front] = new int[] {N - 2, 0};
		cloud[++front] = new int[] {N - 2, 1};
		
		for (int i = 0; i < M; i++) {
			isCloud = new boolean[N][N];
			copy = new int[N][N];
			
			// 구름 이동 && 물 주기 && isCloud check && 물복사 버그
			while (front != rear) {
				int[] cur = cloud[++rear];
				int nr = (cur[0] + dr[movement[i][0]] * movement[i][1] + 25 * N) % N;
				int nc = (cur[1] + dc[movement[i][0]] * movement[i][1] + 25 * N) % N;
				isCloud[nr][nc] = true;
				map[nr][nc]++;
				
			}
			
			// 물복사 버그
			for (int row = 0; row < N; row++) {
				for (int col = 0; col < N; col++) {
					if (!isCloud[row][col])
						continue;
					
					int cnt = 0;
					for (int dir = 1; dir < 8; dir += 2) {
						int nr = row + dr[dir];
						int nc = col + dc[dir];
						if (nr < 0 || nr >= N ||
								nc < 0 || nc >= N ||
								map[nr][nc] == 0)
							continue;
						cnt++;
					}
					copy[row][col] = cnt;
				}
			}
			
			for (int row = 0; row < N; row++) {
				for (int col = 0; col < N; col++) {
					map[row][col] += copy[row][col];
				}
			}
			
			// 구름 생기기 && 물의 양-2
			for (int row = 0; row < N; row++) {
				for (int col = 0; col < N; col++) {
					if (!isCloud[row][col] && map[row][col] >= 2) {
						cloud[++front] = new int[] {row, col};
						map[row][col] -= 2;
					}
				}
			}
		}
		
		int sum = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				sum += map[i][j];
			}
		}
		
		System.out.println(sum);
	}

}
