package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ17780Game {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[][][] map = new int[N][N][5]; // 색, 말1, 말2, 말3, 말 개수
		int[][] pins = new int[K+1][4]; // r, c, 방향, 순서 (0이 맨 밑)
		int[] dr = {0, 0, 0, -1, 1}; // X 우 좌 상 하
		int[] dc = {0, 1, -1, 0, 0};
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j][0] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 1; i <= K; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			pins[i] = new int[] {Integer.parseInt(st.nextToken())-1,
					Integer.parseInt(st.nextToken())-1,
					Integer.parseInt(st.nextToken()), 0};
			map[pins[i][0]][pins[i][1]][1] = i;
			map[pins[i][0]][pins[i][1]][4] = 1;
		}
		
		int turn = 1;
f:		for (; turn <= 1000; turn++) {
			// pin 번호 순서대로 이동
			for (int i = 1; i <= K; i++) {
				// 맨 밑에 있지 않으면 다음 번호로
				if(pins[i][3] != 0) continue;
				int dir = pins[i][2];
				int r = pins[i][0], c = pins[i][1];
				int nr = r + dr[dir], nc = c + dc[dir];
				// 파란색이거나 밖인 경우
				if(nr<0||nr>=N||nc<0||nc>=N||map[nr][nc][0]==2) {
					if(dir == 1) dir = 2;
					else if (dir == 2) dir = 1;
					else if (dir == 3) dir = 4;
					else dir = 3;
					pins[i][2] = dir;
					nr = r+dr[dir]; nc = c+dc[dir];
					// 파란색이거나 밖인 경우
					if(nr<0||nr>=N||nc<0||nc>=N||map[nr][nc][0]==2)
						continue;
					// 흰색인 경우
					else if (map[nr][nc][0]==0) {
						// 가려고 하는 곳에 있는 말의 개수
						int originalNum = map[nr][nc][4];
						// 움직이는 말들의 개수
						int newNum = map[r][c][4];
						if(originalNum + newNum >= 4)
							break f;
						map[nr][nc][4] = originalNum + newNum;
						// 새로운 곳에 말 옮겨주기
						for (int j = 1; j <= newNum; j++) {
							dir = pins[map[r][c][j]][2];
							int order = pins[map[r][c][j]][3];
							map[nr][nc][j+originalNum] = map[r][c][j];
							pins[map[r][c][j]] = new int[] {nr, nc, dir, order+originalNum};
						}
						int color = map[r][c][0];
						map[r][c] = new int[] {color, 0, 0, 0, 0};
					}
					// 빨간색인 경우
					else {
						// 가려고 하는 곳에 있는 말의 개수
						int originalNum = map[nr][nc][4];
						// 움직이는 말들의 개수
						int newNum = map[r][c][4];
						if(originalNum + newNum >= 4)
							break f;
						map[nr][nc][4] = originalNum + newNum;
						for (int j = 1; j <= newNum; j++) {
							dir = pins[map[r][c][j]][2];
							int order = pins[map[r][c][j]][3];
							map[nr][nc][j+originalNum] = map[r][c][j];
							if(order == 0)							
								pins[map[r][c][j]] = new int[] {nr, nc, dir, order+newNum-1+originalNum};
							else if (order == 1 && newNum == 2)
								pins[map[r][c][j]] = new int[] {nr, nc, dir, order-1+originalNum};
							else if (order == 1)
								pins[map[r][c][j]] = new int[] {nr, nc, dir, order+originalNum};
							else
								pins[map[r][c][j]] = new int[] {nr, nc, dir, order-2+originalNum};
						}
						int color = map[r][c][0];
						map[r][c] = new int[] {color, 0, 0, 0, 0};
					}
				}
				// 흰색인 경우
				else if (map[nr][nc][0]==0) {
					// 가려고 하는 곳에 있는 말의 개수
					int originalNum = map[nr][nc][4];
					// 움직이는 말들의 개수
					int newNum = map[r][c][4];
					if(originalNum + newNum >= 4)
						break f;
					map[nr][nc][4] = originalNum + newNum;
					// 새로운 곳에 말 옮겨주기
					for (int j = 1; j <= newNum; j++) {
						dir = pins[map[r][c][j]][2];
						int order = pins[map[r][c][j]][3];
						map[nr][nc][j+originalNum] = map[r][c][j];
						pins[map[r][c][j]] = new int[] {nr, nc, dir, order+originalNum};
					}
					int color = map[r][c][0];
					map[r][c] = new int[] {color, 0, 0, 0, 0};
				}
				// 빨간색인 경우
				else {
					// 가려고 하는 곳에 있는 말의 개수
					int originalNum = map[nr][nc][4];
					// 움직이는 말들의 개수
					int newNum = map[r][c][4];
					if(originalNum + newNum >= 4)
						break f;
					map[nr][nc][4] = originalNum + newNum;
					for (int j = 1; j <= newNum; j++) {
						dir = pins[map[r][c][j]][2];
						int order = pins[map[r][c][j]][3];
						map[nr][nc][j+originalNum] = map[r][c][j];
						if(order == 0)							
							pins[map[r][c][j]] = new int[] {nr, nc, dir, order+newNum-1+originalNum};
						else if (order == 1 && newNum == 2)
							pins[map[r][c][j]] = new int[] {nr, nc, dir, order-1+originalNum};
						else if (order == 1)
							pins[map[r][c][j]] = new int[] {nr, nc, dir, order+originalNum};
						else
							pins[map[r][c][j]] = new int[] {nr, nc, dir, order-2+originalNum};
					}
					int color = map[r][c][0];
					map[r][c] = new int[] {color, 0, 0, 0, 0};
				}
			}
		}
		
		System.out.println(turn==1001?-1:turn);
	}

}
