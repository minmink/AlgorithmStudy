package algorithm;

import java.io.*;
import java.util.*;

public class BJ21608ElementarySchool2 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int powN = (int) Math.pow(N, 2);
		int[][] map = new int[N][N];
		int[][] friends = new int[powN + 1][4];
		int[] dr = {-1, 0, 1, 0};
		int[] dc = {0, 1, 0, -1};
		for (int i = 0; i < powN; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int num = Integer.parseInt(st.nextToken());
			friends[num] = new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
			int maxEmpty = -1;
			int maxCnt = -1;
			int[] max = new int[2];
			for (int row = 0; row < N; row++) {
				for (int col = 0; col < N; col++) {
					if(map[row][col] != 0)
						continue;
					int empty = 0;
					int cnt = 0;
					for (int dir = 0; dir < 4; dir++) {
						int nr = row + dr[dir];
						int nc = col + dc[dir];
						if(nr < 0 || nr >= N || nc < 0 || nc >= N)
							continue;
						if(map[nr][nc] == 0)
							empty++;
						else if(map[nr][nc] == friends[num][0] || map[nr][nc] == friends[num][1] || map[nr][nc] == friends[num][2] || map[nr][nc] == friends[num][3])
							cnt++;
					}
					if(maxCnt < cnt) {
						maxCnt = cnt;
						maxEmpty = empty;
						max = new int[] {row, col};
					} else if(maxCnt == cnt && maxEmpty < empty) {
						maxEmpty = empty;
						max = new int[] {row, col};
					}
				}
			}
			
			map[max[0]][max[1]] = num;
		}
		
		int result = 0;
		for (int row = 0; row < N; row++) {
			for (int col = 0; col < N; col++) {
				int num = map[row][col];
				if(num == 0)
					continue;
				int cnt = 0;
				for (int dir = 0; dir < 4; dir++) {
					int nr = row + dr[dir];
					int nc = col + dc[dir];
					if(nr < 0 || nr >= N || nc < 0 || nc >= N)
						continue;
					if(map[nr][nc] == friends[num][0] || map[nr][nc] == friends[num][1] || map[nr][nc] == friends[num][2] || map[nr][nc] == friends[num][3])
						cnt++;
				}
				
				if(cnt != 0)
					result += Math.pow(10, cnt - 1);
			}
		}
		System.out.println(result);
	}

}
