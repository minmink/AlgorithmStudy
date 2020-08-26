package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ2206Wall {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][M];
		int[][] visited = new int[N][M];
		int[][] visitedWall = new int[N][M];
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = s.charAt(j);
			}
		}
		
		if(N == 1 && M == 1) {
			System.out.println(1);
			return;
		}
		
		int[][] q = new int[N*M*2][4]; // r, c, cnt, flag
		int front = -1, rear = -1;
		int cnt = Integer.MAX_VALUE;
		int[] dr = {-1, 1, 0, 0};
		int[] dc = {0, 0, -1, 1};
		visited[0][0] = 2;
		q[++rear] = new int[] {0, 0, 1, 0};
		while(front!=rear) {
			int[] cur = q[++front];
			int cr = cur[0], cc = cur[1], nr, nc, ccnt = cur[2], flag = cur[3];
			for (int i = 0; i < 4; i++) {
				nr = cr+dr[i]; nc = cc+dc[i];
				if(nr == N-1 && nc == M-1) {
					if(cnt>ccnt+1) cnt = ccnt+1;
				}
				if(0<=nr&&nr<N&&0<=nc&&nc<M) {
					if(flag == 0) { // 안뚫은거 
						if(map[nr][nc]=='0' && visited[nr][nc]==0) {
							visited[nr][nc]++;
							q[++rear] = new int[] {nr, nc, ccnt+1, 0};
						} else if (visited[nr][nc] == 0) {
							visited[nr][nc]++;
							q[++rear] = new int[] {nr, nc, ccnt+1, 1};
						}
					} else { // 이미 한 번 뚫은거
						if(map[nr][nc]=='0' && visitedWall[nr][nc]==0) {
							visitedWall[nr][nc]++;
							q[++rear] = new int[] {nr, nc, ccnt+1, 1};
						}
					}
				}
			}
		}
		
		System.out.println(cnt==Integer.MAX_VALUE?-1:cnt);
	}
	
}
