package algorithm;

import java.io.*;
import java.util.*;

public class BJ21609MiddleSchool2 {
	static int N, map[][];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[] dr = {-1, 0, 1, 0};
		int[] dc = {0, 1, 0, -1};
		int score = 0;
		while(true) {
			PriorityQueue<Group> pq = new PriorityQueue();
			boolean[][] visited = new boolean[N][N];
			for (int row = 0; row < N; row++) {
				for (int col = 0; col < N; col++) {
					if(visited[row][col] || map[row][col] < 1)
						continue;
					
					Queue<int[]> queue = new LinkedList<int[]>();
					boolean[][] rainbowVisited = new boolean[N][N];
					int rainbow = 0;
					int size = 1;
					visited[row][col] = true;
					queue.offer(new int[] {row, col});
					while(!queue.isEmpty()) {
						int[] cur = queue.poll();
						for (int i = 0; i < 4; i++) {
							int nr = cur[0] + dr[i];
							int nc = cur[1] + dc[i];
							if(nr < 0 || nr >= N || nc < 0 || nc >= N || visited[nr][nc] || rainbowVisited[nr][nc] || map[nr][nc] == -1 || (map[nr][nc] != map[row][col] && map[nr][nc] != 0))
								continue;
							
							if(map[nr][nc] > 0)
								visited[nr][nc] = true;
							else if(map[nr][nc] == 0) {
								rainbow++;
								rainbowVisited[nr][nc] = true;
							}
							size++;
							queue.offer(new int[] {nr, nc});
						}
					}
					
					if(size > 1)
						pq.offer(new Group(row, col, rainbow, size));
				}
			}
			
			if(pq.size() == 0)
				break;
			Group group = pq.poll();
			score += Math.pow(group.size, 2);
			visited = new boolean[N][N];
			Queue<int[]> queue = new LinkedList();
			queue.offer(new int[] {group.row, group.col});
			visited[group.row][group.col] = true;
			int num = map[group.row][group.col];
			while(!queue.isEmpty()) {
				int[] cur = queue.poll();
				map[cur[0]][cur[1]] = -2;
				for (int dir = 0; dir < 4; dir++) {
					int nr = cur[0] + dr[dir];
					int nc = cur[1] + dc[dir];
					if(nr < 0 || nr >= N || nc < 0 || nc >= N || visited[nr][nc] || (map[nr][nc] != num && map[nr][nc] != 0))
						continue;
					visited[nr][nc] = true;
					queue.offer(new int[] {nr, nc});
				}
			}
			
			down();
			
			rotate();
		}
		
		System.out.println(score);
	}

	public static void rotate() {
		int[][] temp = new int[N][N];
		for (int row = 0; row < N; row++) {
			for (int col = 0; col < N; col++) {
				temp[N - 1 - col][row] = map[row][col];
			}
		}
		
		map = temp;
		down();
	}
	
	public static void down() {
		for (int col = 0; col < N; col++) {
			int idx = N - 1;
			for (int row = N - 1; row >= 0; row--) {
				if(map[row][col] == -1)
					idx = row - 1;
				else if(map[row][col] >= 0) {
					if(map[idx][col] == -2) {
						map[idx][col] = map[row][col];
						map[row][col] = -2;
						idx--;
					}
				} else if(map[idx][col] != -2)
					idx = row;
			}
		}
	}
	
	public static class Group implements Comparable<Group> {
		int row;
		int col;
		int rainbow;
		int size;
		public Group(int row, int col, int rainbow, int size) {
			this.row = row;
			this.col = col;
			this.rainbow = rainbow;
			this.size = size;
		}
		@Override
		public int compareTo(Group o) {
			if(o.size != this.size)
				return o.size - this.size;
			else if(o.rainbow != this.rainbow)
				return o.rainbow - this.rainbow;
			else if(o.row != this.row)
				return o.row - this.row;
			else
				return o.col - this.col;
		}
	}
}
