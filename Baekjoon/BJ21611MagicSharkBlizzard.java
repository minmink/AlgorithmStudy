package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ21611MagicSharkBlizzard {
	static int N, M, cnt1, cnt2, cnt3;
	static int[][] map, movement, direction;
	static int[] dr = {0, 0, 1, 0, -1};
	static int[] dc = {0, -1, 0, 1, 0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		StringTokenizer st = new StringTokenizer(str, " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		direction = new int[N][N];
		movement = new int[M][2];
		for (int i = 0; i < N; i++) {
			str = br.readLine();
			st = new StringTokenizer(str, " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int i = 0; i < M; i++) {
			str = br.readLine();
			st = new StringTokenizer(str, " ");
			movement[i][0] = Integer.parseInt(st.nextToken());
			movement[i][1] = Integer.parseInt(st.nextToken());
		}
		
		direction[N / 2][N / 2] = 1;
		for (int i = 3; i <= N; i += 2) {
			getDirection(i);
		}
		
		for (int m = 0; m < M; m++) {
			// 구슬 파괴
			switch (movement[m][0]) {
			case 1:
				for (int d = 1; d <= movement[m][1]; d++) {
					if (N / 2 - d < 0)
						break;
					map[N / 2 - d][N / 2] = 0;
				}
				break;
			case 2:
				for (int d = 1; d <= movement[m][1]; d++) {
					if (N / 2 + d >= N)
						break;
					map[N / 2 + d][N / 2] = 0;
				}
				break;
			case 3:
				for (int d = 1; d <= movement[m][1]; d++) {
					if (N / 2 - d < 0)
						break;
					map[N / 2][N / 2 - d] = 0;
				}
				break;
			case 4:
				for (int d = 1; d <= movement[m][1]; d++) {
					if (N / 2 + d >= N)
						break;
					map[N / 2][N / 2 + d] = 0;
				}
				break;
			}
			
			// 이동 & 폭발
			boolean explosion = true;
			while (explosion) {
				explosion = false;
				int[] count = new int[N * N];
				int idxCnt = 0, row = N / 2, col = N / 2 - 1, pre = 0, rowTemp = N / 2, colTemp = N / 2 - 1, dir = 0;
				int[][] temp = new int[N][N];
				
				while (true) {
					if (map[row][col] == 0) {
						if (row == 0 && col == 0)
							break;
						dir = direction[row][col];
						row += dr[dir];
						col += dc[dir];
						continue;
					}
					
					if (pre == map[row][col]) {
						count[idxCnt]++;
					} else {
						pre = map[row][col];
						count[++idxCnt]++;
					}
					temp[rowTemp][colTemp] = map[row][col];
					
					if (row == 0 && col == 0)
						break;
					dir = direction[row][col];
					row += dr[dir];
					col += dc[dir];
					dir = direction[rowTemp][colTemp];
					rowTemp += dr[dir];
					colTemp += dc[dir];
				}
				map = temp.clone();
				
				idxCnt = 1;
				row = N / 2;
				col = N / 2;
				while (row != 0 || col != 0) {
					dir = direction[row][col];
					row += dr[dir];
					col += dc[dir];
					int cnt = count[idxCnt];
					if (cnt == 0)
						break;
					else if (cnt < 4) {
						idxCnt++;
						for (int i = 1; i < cnt; i++) {
							dir = direction[row][col];
							row += dr[dir];
							col += dc[dir];
						}
						continue;
					}
					
					explosion = true;
					idxCnt++;
					int num = map[row][col];
					switch(num) {
					case 1:
						cnt1 += cnt;
						break;
					case 2:
						cnt2 += cnt;
						break;
					case 3:
						cnt3 += cnt;
						break;
					}
					map[row][col] = 0;
					for (int i = 1; i < cnt; i++) {
						dir = direction[row][col];
						row += dr[dir];
						col += dc[dir];
						map[row][col] = 0;
					}
				}
			}
			
			// 연속 구슬끼리 그룹핑 후 새로 쓰기
			int[][] queue = new int[N * N][2];
			int front = -1, rear = 0, pre = 0, cnt = 1, row = N / 2, col = N / 2;
			do {
				int dir = direction[row][col];
				row += dr[dir];
				col += dc[dir];
				
				if (pre == map[row][col])
					cnt++;
				else {
					queue[++front] = new int[] {cnt, pre};
					pre = map[row][col];
					cnt = 1;
				}
			} while (row != 0 || col != 0);
			
			map = new int[N][N];
			row = N / 2;
			col = N / 2 - 1;
			while (front != rear && row >= 0 && row < N && col >= 0 && col < N) {
				int[] cur = queue[++rear];
				map[row][col] = cur[0];
				
				int dir = direction[row][col];
				row += dr[dir];
				col += dc[dir];
				if (row < 0 || row >= N ||
						col < 0 || col >= N)
					break;
				map[row][col] = cur[1];
				dir = direction[row][col];
				row += dr[dir];
				col += dc[dir];
			}
		}
		
		System.out.println(cnt1 + 2 * cnt2 + 3 * cnt3);
	}
	
	public static void getDirection (int n) {
		for (int col = N / 2 - n / 2; col <= N / 2 + n / 2; col++) {
			int row = N / 2 - n / 2;
			direction[row][col] = 1;
		}
		
		for (int row = N / 2 - n / 2; row <= N / 2 + n / 2; row++) {
			int col = N / 2 + n / 2;
			if (direction[row][col] != 0)
				continue;
			direction[row][col] = 4;
		}
		
		for (int col = N / 2 + n / 2; col >= N / 2 - n / 2; col--) {
			int row = N / 2 + n / 2;
			if (direction[row][col] != 0)
				continue;
			direction[row][col] = 3;
		}
		
		for (int row = N / 2 + n / 2; row >= N / 2 - n / 2; row--) {
			int col = N / 2 - n / 2;
			if (direction[row][col] != 0)
				continue;
			direction[row][col] = 2;
		}
	}
}
