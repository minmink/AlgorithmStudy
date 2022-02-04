package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ17142Lab {
	static int sec = Integer.MAX_VALUE, N, M, map[][], remain = 0, queue[][], rear = -1, front = -1;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	static ArrayList<int[]> virus = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		StringTokenizer st = new StringTokenizer(s, " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			s = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = s.charAt(j * 2) - '0';
				if (map[i][j] == 0)
					remain++;
				else if (map[i][j] == 2)
					virus.add(new int[] {i, j});
			}
		}
		
		if (remain == 0) {
			System.out.println(0);
			return;
		}
		
		for (int i = 0; i <= virus.size() - M; i++) {
			int[] cur = virus.get(i);
			map[cur[0]][cur[1]] = 100;
			comb(i, 1);
			map[cur[0]][cur[1]] = 2;
		}
		
		if (sec == Integer.MAX_VALUE)
			System.out.println(-1);
		else
			System.out.println(sec);
	}

	public static void comb(int idx, int cnt) {
		if (cnt == M) {
			virus();
			return;
		}
		for (int i = idx + 1; i <= virus.size() - M + cnt; i++) {
			int[] cur = virus.get(i);
			map[cur[0]][cur[1]] = 100;
			comb(i, cnt + 1);
			map[cur[0]][cur[1]] = 2;
		}
	}
	
	public static void virus() {
		int[][] tempMap = new int[N][N];
		for (int i = 0; i < N; i++) {
			tempMap[i] = Arrays.copyOf(map[i], N);
		}
		int tempRemain = remain;
		boolean isMoved = true;
		int cnt = 0, nr, nc;
		rear = front;
		while (isMoved) {
			isMoved = false;
			Queue<int[]> queue = new LinkedList<>();
			for (int row = 0; row < N; row++) {
				for (int col = 0; col < N; col++) {
					if (tempMap[row][col] == 100)
						queue.offer(new int[] {row, col});
				}
			}
			
			while(!queue.isEmpty() && cnt < sec) {
				int[] cur = queue.poll();
				for (int d = 0; d < 4; d++) {
					nr = cur[0] + dr[d];
					nc = cur[1] + dc[d];
					if (nr < 0 || nr >= N || nc < 0 || nc >= N || tempMap[nr][nc] == 1 || tempMap[nr][nc] == 100)
						continue;
					if (tempMap[nr][nc] == 0) {
						tempMap[nr][nc] = 100;
						tempRemain--;
					} else {
						tempMap[nr][nc] = 100;
					}
					isMoved = true;
				}
			}
			
			++cnt;
			if (tempRemain == 0) {
				sec = Math.min(sec, cnt);
				return;
			}
		}
	}
}
