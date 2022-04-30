package algorithm;

import java.io.*;
import java.util.*;

public class BJ16236Shark2 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] map = new int[N][N];
		int[] shark = new int[2];
		int sharkSize = 2;
		int eat = 0;
		int result = 0;
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = s.charAt(j * 2) - '0';
				if(map[i][j] == 9) {
					shark = new int[] {i, j};
					map[i][j] = 0;
				} else if(map[i][j] > 0)
					cnt++;
			}
		}
		
		int[] dr = {-1, 0, 0, 1};
		int[] dc = {0, -1, 1, 0};
		while(cnt > 0) {
			PriorityQueue<Fish> pq = new PriorityQueue<>();
			Queue<Fish> queue = new LinkedList<>();
			boolean[][] visited = new boolean[N][N];
			pq.add(new Fish(shark[0], shark[1]));
			visited[shark[0]][shark[1]] = true;
			boolean success = false;
			int time = -1;
			
			search:
			while(!pq.isEmpty()) {
				time++;
				int size = pq.size();
				for (int i = 0; i < size; i++) {
					Fish cur = pq.poll();
					if(map[cur.row][cur.col] > 0 && map[cur.row][cur.col] < sharkSize) {
						shark = new int[] {cur.row, cur.col};
						map[cur.row][cur.col] = 0;
						cnt--;
						eat++;
						success = true;
						break search;
					}
					
					for (int dir = 0; dir < 4; dir++) {
						int nr = cur.row + dr[dir];
						int nc = cur.col + dc[dir];
						if(nr < 0 || nr >= N || nc < 0 || nc >= N || visited[nr][nc] || map[nr][nc] > sharkSize)
							continue;
						visited[nr][nc] = true;
						queue.offer(new Fish(nr, nc));
					}
				}
				
				while(!queue.isEmpty()) {
					pq.offer(queue.poll());
				}
			}
			
			if(!success)
				break;
				
			result += time;
			if(eat == sharkSize) {
				sharkSize++;
				eat = 0;
			}
		}
		
		System.out.println(result);
	}
	
	public static class Fish implements Comparable<Fish> {
		int row;
		int col;
		public Fish(int row, int col) {
			this.row = row;
			this.col = col;
		}
		@Override
		public int compareTo(Fish o) {
			if(this.row != o.row)
				return this.row - o.row;
			else
				return this.col - o.col;
		}
	}
}
