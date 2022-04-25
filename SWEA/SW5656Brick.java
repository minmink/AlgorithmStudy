package algorithm;

import java.io.*;
import java.util.*;

public class SW5656Brick {
	static int min, N, W, H;
	static Queue<int[]> queue;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tc = 0; tc < testCase; tc++) {
			sb.append("#").append(tc + 1).append(" ");
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			int[][] map = new int[H][W];
			int remain = 0;
			for (int i = 0; i < H; i++) {
				String s = br.readLine();
				for (int j = 0; j < W; j++) {
					map[i][j] = s.charAt(j * 2) - '0';
					if(map[i][j] > 0)
						remain++;
				}
			}
			min = remain;
			queue = new LinkedList<>();
			for (int i = 0; i < W; i++) {
				int h = 0;
				for (int j = 0; j < H; j++) {
					if(map[j][i] != 0) {
						h = j;
						break;
					}
				}
				queue.offer(new int[] {h, i});
				int[][] tempMap = new int[H][W];
				for (int row = 0; row < H; row++) {
					for (int col = 0; col < W; col++) {
						tempMap[row][col] = map[row][col];
					}
				}
				breakBrick(tempMap, 1, remain);
			}
			
			sb.append(min).append("\n");
		}
		System.out.println(sb.toString());
	}
	
	public static void breakBrick(int[][] map, int cnt, int remain) {
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			int num = map[cur[0]][cur[1]];
			if(num == 0)
				continue;
			map[cur[0]][cur[1]] = 0;
			remain--;
			for (int d = 1; d < num; d++) {
				for (int dir = 0; dir < 4; dir++) {
					int nr = cur[0] + dr[dir] * d;
					int nc = cur[1] + dc[dir] * d;
					if(nr < 0 || nr >= H || nc < 0 || nc >= W || map[nr][nc] == 0)
						continue;
					queue.offer(new int[] {nr, nc});
				}
			}
		}
		
		if(cnt == N) {
			min = Math.min(min, remain);
			return;
		}
		
		down(map);
		
		for (int i = 0; i < W; i++) {
			int h = 0;
			for (int j = 0; j < H; j++) {
				if(map[j][i] != 0) {
					h = j;
					break;
				}
			}
			queue.offer(new int[] {h, i});
			int[][] tempMap = new int[H][W];
			for (int row = 0; row < H; row++) {
				for (int col = 0; col < W; col++) {
					tempMap[row][col] = map[row][col];
				}
			}
			breakBrick(tempMap, cnt + 1, remain);
		}
	}

	public static void down(int[][] map) {
		for (int col = 0; col < W; col++) {
			int idx = H - 1;
			for (int row = H - 1; row >= 0; row--) {
				if(map[row][col] > 0) {
					if(map[idx][col] > 0)
						continue;
					else {
						map[idx][col] = map[row][col];
						map[row][col] = 0;
						for (int i = idx - 1; i >= 0; i--) {
							if(map[i][col] == 0) {
								idx = i;
								break;
							}
						}
					}
				} else if(map[idx][col] > 0)
					idx = row;
			}
		}
	}
}
