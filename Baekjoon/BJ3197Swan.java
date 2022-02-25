package algorithm;

import java.io.*;
import java.util.*;

public class BJ3197Swan {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		StringTokenizer st = new StringTokenizer(s, " ");
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		char[][] map = new char[R][C];
		int[][] swan = new int[2][2];
		int swanIdx = 0;
		int[] dr = {-1, 0, 1, 0};
		int[] dc = {0, 1, 0, -1};
		Queue<int[]> water = new LinkedList<int[]>();
		for (int i = 0; i < R; i++) {
			s = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = s.charAt(j);
				if(map[i][j] != 'X')
					water.offer(new int[] {i, j});
				if(map[i][j] == 'L')
					swan[swanIdx++] = new int[] {i, j};
			}
		}
		int cnt = 0;
		Queue<int[]> queue = new LinkedList<int[]>();
		boolean[][] visited = new boolean[R][C];
		queue.offer(swan[0]);
		visited[swan[0][0]][swan[0][1]] = true;
		while(true) {
			Queue<int[]> next = new LinkedList<int[]>();
			// 백조 확인
			while(!queue.isEmpty()) {
				int[] cur = queue.poll();
				if (cur[0] == swan[1][0] && cur[1] == swan[1][1]) {
					System.out.println(cnt);
					return;
				}
				for (int i = 0; i < 4; i++) {
					int nr = cur[0] + dr[i];
					int nc = cur[1] + dc[i];
					if (nr < 0 || nr >= R || nc < 0 || nc >= C || visited[nr][nc])
						continue;
					visited[nr][nc] = true;
					if (map[nr][nc] == 'X')
						next.offer(new int[] {nr, nc});
					else
						queue.offer(new int[] {nr, nc});
				}
			}
			queue = next;
			// 얼음 녹이기
			int size = water.size();
			for(int i = 0; i < size; i++) {
				int[] cur = water.poll();
				for (int dir = 0; dir < 4; dir++) {
					int nr = cur[0] + dr[dir];
					int nc = cur[1] + dc[dir];
					if (nr < 0 || nr >= R || nc < 0 || nc >= C)
						continue;
					if (map[nr][nc] == 'X') {
						map[nr][nc] = '.';
						water.offer(new int[] {nr, nc});
					}
				}
			}
			
			cnt++;
		}
	}
}
