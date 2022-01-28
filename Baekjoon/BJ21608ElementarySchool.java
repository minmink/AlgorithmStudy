package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ21608ElementarySchool {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		StringTokenizer st;
		int N = Integer.parseInt(s);
		int[][] map = new int[N][N];
		int[][] like = new int[N * N + 1][4];
		int[] dr = {-1, 0, 1, 0};
		int[] dc = {0, 1, 0, -1};
		
		for (int n = 0; n < N * N; n++) {
			int likeMax = 0;
			int emptyMax = 0;
			int[] idx = null;
			s = br.readLine();
			st = new StringTokenizer(s, " ");
			int cur = Integer.parseInt(st.nextToken());
			like[cur][0] = Integer.parseInt(st.nextToken());
			like[cur][1] = Integer.parseInt(st.nextToken());
			like[cur][2] = Integer.parseInt(st.nextToken());
			like[cur][3] = Integer.parseInt(st.nextToken());
			
			for (int row = 0; row < N; row++) {
				for (int col = 0; col < N; col++) {
					if (map[row][col] != 0)
						continue;
					int likeCnt = 0;
					int emptyCnt = 0;
					for (int dir = 0; dir < 4; dir++) {
						int nr = row + dr[dir];
						int nc = col + dc[dir];
						if (nr < 0 || nr >= N || nc < 0 || nc >= N)
							continue;
						if (map[nr][nc] == 0)
							emptyCnt++;
						else if (map[nr][nc] == like[cur][0] || map[nr][nc] == like[cur][1] || map[nr][nc] == like[cur][2] || map[nr][nc] == like[cur][3])
							likeCnt++;
					}
					if (likeMax < likeCnt) {
						likeMax = likeCnt;
						emptyMax = emptyCnt;
						idx = new int[] {row, col};
					} else if (likeMax == likeCnt && emptyMax < emptyCnt) {
						emptyMax = emptyCnt;
						idx = new int[] {row, col};
					} else if (idx == null)
						idx = new int[] {row, col};
				}
			}
			
			map[idx[0]][idx[1]] = cur;
		}
		
		long total = 0;
		for (int row = 0; row < N; row++) {
			for (int col = 0; col < N; col++) {
				int cnt = 0;
				int n = map[row][col];
				for (int dir = 0; dir < 4; dir++) {
					int nr = row + dr[dir];
					int nc = col + dc[dir];
					if (nr < 0 || nr >= N || nc < 0 || nc >= N)
						continue;
					if (map[nr][nc] == like[n][0] || map[nr][nc] == like[n][1] || map[nr][nc] == like[n][2] || map[nr][nc] == like[n][3])
						cnt++;
				}
				
				switch (cnt) {
				case 1:
					total += 1;
					break;
				case 2:
					total += 10;
					break;
				case 3:
					total += 100;
					break;
				case 4:
					total += 1000;
					break;
				}
			}
		}
		
		System.out.println(total);
	}
}
