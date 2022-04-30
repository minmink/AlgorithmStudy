package algorithm;

import java.io.*;
import java.util.*;

public class BJ19237Shark2 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int[] dr = {-1, 1, 0, 0};
		int[] dc = {0, 0, -1, 1};
		int[][][] dirs = new int[M + 1][4][4];
		int[][][] smells = new int[N][N][2];
		PriorityQueue<Shark> pq = new PriorityQueue<>();
		Queue<Shark> queue = new LinkedList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				int num = Integer.parseInt(st.nextToken());
				if(num == 0)
					continue;
				pq.offer(new Shark(i, j, num, 0));
				smells[i][j] = new int[] {num, k};
			}
		}
		String s = br.readLine();
		for (int i = 0; i < M; i++) {
			Shark shark = pq.poll();
			shark.dir = s.charAt(i * 2) - '1';
			queue.offer(shark);
		}
		while(!queue.isEmpty()) {
			pq.offer(queue.poll());
		}
		for (int i = 1; i <= M; i++) {
			for (int j = 0; j < 4; j++) {
				s = br.readLine();
				for (int d = 0; d < 4; d++) {
					dirs[i][j][d] = s.charAt(d * 2) - '1';
				}
			}
		}
		
		int time = 0;
		while(pq.size() > 1 && ++time <= 1000) {
			boolean[][] kill = new boolean[N][N];
			while(!pq.isEmpty()) {
				Shark shark = pq.poll();
				int row = -1;
				int col = -1;
				int d = -1;
				for (int dir = 0; dir < 4; dir++) {
					int nr = shark.row + dr[dirs[shark.num][shark.dir][dir]];
					int nc = shark.col + dc[dirs[shark.num][shark.dir][dir]];
					if(nr < 0 || nr >= N || nc < 0 || nc >= N || (smells[nr][nc][0] != 0 && smells[nr][nc][0] != shark.num && smells[nr][nc][1] < k + 1))
						continue;
					if(smells[nr][nc][0] != 0 && smells[nr][nc][0] != shark.num && smells[nr][nc][1] == k + 1) {
						if(!kill[nr][nc])
							continue;
						row = -1;
						break;
					}
					
					if(smells[nr][nc][0] == 0) {
						row = nr;
						col = nc;
						d = dirs[shark.num][shark.dir][dir];
						kill[nr][nc] = true;
						break;
					}
					if(row == -1) {
						row = nr;
						col = nc;
						d = dirs[shark.num][shark.dir][dir];
					}
				}
				
				if(row == -1)
					continue;
				
				smells[row][col] = new int[] {shark.num, k + 1};
				shark.dir = d;
				shark.row = row;
				shark.col = col;
				queue.offer(shark);
			}
			
			while(!queue.isEmpty()) {
				pq.offer(queue.poll());
			}
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(smells[i][j][1] == 0)
						continue;
					else if(smells[i][j][1] == 1)
						smells[i][j][0] = 0;
					smells[i][j][1]--;
				}
			}
		}
		System.out.println(time == 1001 ? -1 : time);
	}

	public static class Shark implements Comparable<Shark> {
		int row;
		int col;
		int num;
		int dir;
		public Shark(int row, int col, int num, int dir) {
			this.row = row;
			this.col = col;
			this.num = num;
			this.dir = dir;
		}
		@Override
		public int compareTo(Shark o) {
			return this.num - o.num;
		}
	}
}
