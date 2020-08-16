package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ15683CCTV {

	static int[][] cctv; // cctv : {행, 열, 번호}
	static int N, M, cctvCnt = 0, min;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][M];
		cctv = new int[8][3];
		min = N*M;
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] != 6 && map[i][j] != 0) {
					cctv[cctvCnt++] = new int[] {i, j, map[i][j]};
				}
			}
		}
		
		dfs(0, map);
		System.out.println(min);
	}

	private static void dfs(int index, int[][] map) {
		if (index == cctvCnt) {
			int cnt = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if(map[i][j] == 0) cnt++;
				}
			}
			if(min>cnt) {
				min = cnt;
			}
			return;
		}
		int[] x = cctv[index];
		int[][] tempMap = new int[N][M];
		switch (x[2]) {
		case 1:
			for (int i = 0; i < N; i++) {
				System.arraycopy(map[i], 0, tempMap[i], 0, M);
			}
			dfs(index+1, play(x[0], x[1], 1, tempMap));
			for (int i = 0; i < N; i++) {
				System.arraycopy(map[i], 0, tempMap[i], 0, M);
			}
			dfs(index+1, play(x[0], x[1], 2, tempMap));
			for (int i = 0; i < N; i++) {
				System.arraycopy(map[i], 0, tempMap[i], 0, M);
			}
			dfs(index+1, play(x[0], x[1], 3, tempMap));
			for (int i = 0; i < N; i++) {
				System.arraycopy(map[i], 0, tempMap[i], 0, M);
			}
			dfs(index+1, play(x[0], x[1], 4, tempMap));
			break;
		case 2:
			for (int i = 0; i < N; i++) {
				System.arraycopy(map[i], 0, tempMap[i], 0, M);
			}
			dfs(index+1, play(x[0], x[1], 1,
					play(x[0], x[1], 2, tempMap)));
			for (int i = 0; i < N; i++) {
				System.arraycopy(map[i], 0, tempMap[i], 0, M);
			}
			dfs(index+1, play(x[0], x[1], 3,
					play(x[0], x[1], 4, tempMap)));
			break;
		case 3:
			for (int i = 0; i < N; i++) {
				System.arraycopy(map[i], 0, tempMap[i], 0, M);
			}
			dfs(index+1, play(x[0], x[1], 1,
					play(x[0], x[1], 3, tempMap)));
			for (int i = 0; i < N; i++) {
				System.arraycopy(map[i], 0, tempMap[i], 0, M);
			}
			dfs(index+1, play(x[0], x[1], 1,
					play(x[0], x[1], 4, tempMap)));
			for (int i = 0; i < N; i++) {
				System.arraycopy(map[i], 0, tempMap[i], 0, M);
			}
			dfs(index+1, play(x[0], x[1], 2,
					play(x[0], x[1], 3, tempMap)));
			for (int i = 0; i < N; i++) {
				System.arraycopy(map[i], 0, tempMap[i], 0, M);
			}
			dfs(index+1, play(x[0], x[1], 2,
					play(x[0], x[1], 4, tempMap)));
			break;
		case 4:
			for (int i = 0; i < N; i++) {
				System.arraycopy(map[i], 0, tempMap[i], 0, M);
			}
			dfs(index+1, play(x[0], x[1], 1,
					play(x[0], x[1], 2,
								play(x[0], x[1], 3, tempMap))));
			for (int i = 0; i < N; i++) {
				System.arraycopy(map[i], 0, tempMap[i], 0, M);
			}
			dfs(index+1, play(x[0], x[1], 1,
					play(x[0], x[1], 2,
								play(x[0], x[1], 4, tempMap))));
			for (int i = 0; i < N; i++) {
				System.arraycopy(map[i], 0, tempMap[i], 0, M);
			}
			dfs(index+1, play(x[0], x[1], 1,
					play(x[0], x[1], 3,
								play(x[0], x[1], 4, tempMap))));
			for (int i = 0; i < N; i++) {
				System.arraycopy(map[i], 0, tempMap[i], 0, M);
			}
			dfs(index+1, play(x[0], x[1], 2,
					play(x[0], x[1], 3,
								play(x[0], x[1], 4, tempMap))));
			break;
		case 5:
			for (int i = 0; i < N; i++) {
				System.arraycopy(map[i], 0, tempMap[i], 0, M);
			}
			dfs(index+1, play(x[0], x[1], 1,
					play(x[0], x[1], 2,
							play(x[0], x[1], 3,
									play(x[0], x[1], 4, tempMap)))));
			break;
		}
	}
	
	// 1: 상, 2: 하, 3: 좌, 4: 우
	private static int[][] play(int row, int col, int dir , int[][] map) {
		switch(dir) {
		case 1:
			while (--row>=0) {
				if(1<=map[row][col] && map[row][col]<=5) continue;
				if(map[row][col] == 6) break;
				map[row][col] = -1;
			}
			break;
		case 2:
			while (++row<N) {
				if(1<=map[row][col] && map[row][col]<=5) continue;
				if(map[row][col] == 6) break;
				map[row][col] = -1;
			}
			break;
		case 3:
			while (--col>=0) {
				if(1<=map[row][col] && map[row][col]<=5) continue;
				if(map[row][col] == 6) break;
				map[row][col] = -1;
			}
			break;
		case 4:
			while (++col<M) {
				if(1<=map[row][col] && map[row][col]<=5) continue;
				if(map[row][col] == 6) break;
				map[row][col] = -1;
			}
			break;
		}
		return map;
	}

}
