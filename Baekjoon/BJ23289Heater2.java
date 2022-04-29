package algorithm;

import java.io.*;
import java.util.*;

public class BJ23289Heater2 {
	static int map[][], temp[][], R, C;
	static boolean wall[][][];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		ArrayList<int[]> heater1 = new ArrayList<>();
		ArrayList<int[]> heater2 = new ArrayList<>();
		ArrayList<int[]> heater3 = new ArrayList<>();
		ArrayList<int[]> heater4 = new ArrayList<>();
		ArrayList<int[]> search = new ArrayList<>();
		for (int i = 0; i < R; i++) {
			String s = br.readLine();
			for (int j = 0; j < C; j++) {
				int num = s.charAt(j * 2) - '0';
				if(num == 0)
					continue;
				else if(num == 1)
					heater1.add(new int[] {i, j});
				else if(num == 2)
					heater2.add(new int[] {i, j});
				else if(num == 3)
					heater3.add(new int[] {i, j});
				else if(num == 4)
					heater4.add(new int[] {i, j});
				else
					search.add(new int[] {i, j});
			}
		}
		wall = new boolean[R][C][2];
		int W = Integer.parseInt(br.readLine());
		for (int i = 0; i < W; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			wall[Integer.parseInt(st.nextToken()) - 1][Integer.parseInt(st.nextToken()) - 1][Integer.parseInt(st.nextToken())] = true;
		}
		int cnt = 0;
		while(++cnt <= 100) {
			temp = new int[R][C];
			for (int[] heater : heater1) {
				right(heater);
			}
			for (int[] heater : heater2) {
				left(heater);
			}
			for (int[] heater : heater3) {
				up(heater);
			}
			for (int[] heater : heater4) {
				down(heater);
			}
			
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					map[i][j] += temp[i][j];
				}
			}
			
			
			temp = new int[R][C];
			for (int row = 0; row < R; row++) {
				for (int col = 0; col < C; col++) {
					// 오른쪽
					if(col != C - 1 && !wall[row][col][1]) {
						int diff = (map[row][col] - map[row][col + 1]) / 4;
						temp[row][col] -= diff;
						temp[row][col + 1] += diff;
					}
					
					// 아래쪽
					if(row != R - 1 && !wall[row + 1][col][0]) {
						int diff = (map[row][col] - map[row + 1][col]) / 4;
						temp[row][col] -= diff;
						temp[row + 1][col] += diff;
					}
				}
			}
			
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					map[i][j] += temp[i][j];
				}
			}
			
			for (int i = 0; i < R; i++) {
				if(map[i][0] > 0)
					map[i][0]--;
				if(map[i][C - 1] > 0)
					map[i][C - 1]--;
			}
			for (int i = 1; i < C - 1; i++) {
				if(map[0][i] > 0)
					map[0][i]--;
				if(map[R - 1][i] > 0)
					map[R - 1][i]--;
			}
			
			boolean finish = true;
			for (int[] s : search) {
				if(map[s[0]][s[1]] < K) {
					finish = false;
					break;
				}
			}
			if(finish)
				break;
		}
		System.out.println(cnt);
	}
	
	public static void right(int[] heater) {
		int temperature = 5;
		boolean[][] visited = new boolean[R][C];
		int row = heater[0];
		int col = heater[1] + 1;
		temp[row][col] += temperature;
		visited[row][col] = true;
		Queue<int[]> queue = new LinkedList<int[]>();
		queue.offer(new int[] {row, col});
		while(!queue.isEmpty() && --temperature > 0) {
			int size = queue.size();
			for (int s = 0; s < size; s++) {
				int[] cur = queue.poll();
				int nr = cur[0] - 1;
				int nc = cur[1] + 1;
				if(nr >= 0 && nc < C && !visited[nr][nc] && !wall[cur[0]][cur[1]][0] && !wall[nr][cur[1]][1]) {
					visited[nr][nc] = true;
					queue.offer(new int[] {nr, nc});
					temp[nr][nc] += temperature;
				}
				
				nr = cur[0];
				if(nc < C && !visited[nr][nc] && !wall[cur[0]][cur[1]][1]) {
					visited[nr][nc] = true;
					queue.offer(new int[] {nr, nc});
					temp[nr][nc] += temperature;
				}
				
				nr = cur[0] + 1;
				if(nr < R && nc < C && !visited[nr][nc] && !wall[nr][cur[1]][0] && !wall[nr][cur[1]][1]) {
					visited[nr][nc] = true;
					queue.offer(new int[] {nr, nc});
					temp[nr][nc] += temperature;
				}
			}
		}
	}
	
	public static void left(int[] heater) {
		int temperature = 5;
		boolean[][] visited = new boolean[R][C];
		int row = heater[0];
		int col = heater[1] - 1;
		temp[row][col] += temperature;
		visited[row][col] = true;
		Queue<int[]> queue = new LinkedList<int[]>();
		queue.offer(new int[] {row, col});
		while(!queue.isEmpty() && --temperature > 0) {
			int size = queue.size();
			for (int s = 0; s < size; s++) {
				int[] cur = queue.poll();
				int nr = cur[0] - 1;
				int nc = cur[1] - 1;
				if(nr >= 0 && nc >= 0 && !visited[nr][nc] && !wall[cur[0]][cur[1]][0] && !wall[nr][nc][1]) {
					visited[nr][nc] = true;
					queue.offer(new int[] {nr, nc});
					temp[nr][nc] += temperature;
				}
				
				nr = cur[0];
				if(nc >= 0 && !visited[nr][nc] && !wall[cur[0]][nc][1]) {
					visited[nr][nc] = true;
					queue.offer(new int[] {nr, nc});
					temp[nr][nc] += temperature;
				}
				
				nr = cur[0] + 1;
				if(nr < R && nc >= 0 && !visited[nr][nc] && !wall[nr][cur[1]][0] && !wall[nr][nc][1]) {
					visited[nr][nc] = true;
					queue.offer(new int[] {nr, nc});
					temp[nr][nc] += temperature;
				}
			}
		}
	}
	
	public static void up(int[] heater) {
		int temperature = 5;
		boolean[][] visited = new boolean[R][C];
		int row = heater[0] - 1;
		int col = heater[1];
		temp[row][col] += temperature;
		visited[row][col] = true;
		Queue<int[]> queue = new LinkedList<int[]>();
		queue.offer(new int[] {row, col});
		while(!queue.isEmpty() && --temperature > 0) {
			int size = queue.size();
			for (int s = 0; s < size; s++) {
				int[] cur = queue.poll();
				int nr = cur[0] - 1;
				int nc = cur[1] - 1;
				if(nr >= 0 && nc >= 0 && !visited[nr][nc] && !wall[cur[0]][nc][0] && !wall[cur[0]][nc][1]) {
					visited[nr][nc] = true;
					queue.offer(new int[] {nr, nc});
					temp[nr][nc] += temperature;
				}
				
				nc = cur[1];
				if(nr >= 0 && !visited[nr][nc] && !wall[cur[0]][cur[1]][0]) {
					visited[nr][nc] = true;
					queue.offer(new int[] {nr, nc});
					temp[nr][nc] += temperature;
				}
				
				nc = cur[1] + 1;
				if(nr >= 0 && nc < C && !visited[nr][nc] && !wall[cur[0]][nc][0] && !wall[cur[0]][cur[1]][1]) {
					visited[nr][nc] = true;
					queue.offer(new int[] {nr, nc});
					temp[nr][nc] += temperature;
				}
			}
		}
	}
	
	public static void down(int[] heater) {
		int temperature = 5;
		boolean[][] visited = new boolean[R][C];
		int row = heater[0] + 1;
		int col = heater[1];
		temp[row][col] += temperature;
		visited[row][col] = true;
		Queue<int[]> queue = new LinkedList<int[]>();
		queue.offer(new int[] {row, col});
		while(!queue.isEmpty() && --temperature > 0) {
			int size = queue.size();
			for (int s = 0; s < size; s++) {
				int[] cur = queue.poll();
				int nr = cur[0] + 1;
				int nc = cur[1] - 1;
				if(nr < R && nc >= 0 && !visited[nr][nc] && !wall[nr][nc][0] && !wall[cur[0]][nc][1]) {
					visited[nr][nc] = true;
					queue.offer(new int[] {nr, nc});
					temp[nr][nc] += temperature;
				}
				
				nc = cur[1];
				if(nr < R && !visited[nr][nc] && !wall[nr][cur[1]][0]) {
					visited[nr][nc] = true;
					queue.offer(new int[] {nr, nc});
					temp[nr][nc] += temperature;
				}
				
				nc = cur[1] + 1;
				if(nr < R && nc < C && !visited[nr][nc] && !wall[nr][nc][0] && !wall[cur[0]][cur[1]][1]) {
					visited[nr][nc] = true;
					queue.offer(new int[] {nr, nc});
					temp[nr][nc] += temperature;
				}
			}
		}
	}
}
