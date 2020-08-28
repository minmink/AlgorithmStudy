package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SW7793God {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int answer, N, M;
		char[][] map;
		boolean flag;
		boolean[][] visited;
		Queue<int[]> su, devils;
		int[] dr = {-1, 1, 0, 0};
		int[] dc = {0, 0, -1, 1};
		String s;
		for (int testCase = 1; testCase <= T; testCase++) {
			sb.append("#").append(testCase).append(" ");
			answer = 0;
			flag = false;
			su = new LinkedList<>(); devils = new LinkedList<>();
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			map = new char[N][M];
			visited = new boolean[N][M];
			for (int i = 0; i < N; i++) {
				s = br.readLine();
				for (int j = 0; j < M; j++) {
					map[i][j] = s.charAt(j);
					if(map[i][j] == '*') devils.add(new int[] {i, j, 0});
					else if(map[i][j] == 'S') {
						su.add(new int[] {i, j, 0});
						visited[i][j] = true;
						map[i][j] = '.';
					}
				}
			}
			
bfs:		while(true) {
				// devil BFS
				if(!devils.isEmpty()) {
					int level = devils.peek()[2];
					while(!devils.isEmpty()&&devils.peek()[2] == level) {
						int[] cur = devils.poll();
						int nr, nc;
						for (int i = 0; i < 4; i++) {
							nr = cur[0]+dr[i]; nc = cur[1]+dc[i];
							if(0<=nr&&nr<N&&0<=nc&&nc<M&&map[nr][nc]=='.') {
								map[nr][nc]='*';
								devils.add(new int[] {nr, nc, level+1});
							}
						}
					}
				}
				// su BFS
				if(!su.isEmpty()) {
					int level = su.peek()[2];
					while(!su.isEmpty()&&su.peek()[2] == level) {
						int[] cur = su.poll();
						int nr, nc;
						for (int i = 0; i < 4; i++) {
							nr = cur[0]+dr[i]; nc = cur[1]+dc[i];
							if(0<=nr&&nr<N&&0<=nc&&nc<M&&!visited[nr][nc]) {
								if(map[nr][nc] == 'D') {
									flag = true;
									answer = level+1;
									break bfs;
								} else if(map[nr][nc] == '.') {
									visited[nr][nc] = true;
									su.add(new int[] {nr, nc, level+1});
								}
							}
						}
					}
				} else break;
			}
			
			if(flag) sb.append(answer).append("\n");
			else sb.append("GAME OVER").append("\n");
		}
		System.out.println(sb.toString());
	}

}
