package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ17144Dust {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		int[][] map = new int[R][C];
		int[][] machine = new int[2][2];
		int[] dir_r = {-1, 1, 0, 0};
		int[] dir_c = {0, 0, -1, 1};
		int[][] diff;
		
		for (int row = 0; row < R; row++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int col = 0; col < C; col++) {
				map[row][col] = Integer.parseInt(st.nextToken());
				if(map[row][col] == -1 && machine[1][0] == 0) {
					machine[0][0] = row;
					machine[0][1] = 0;
					machine[1][0] = row+1;
					machine[1][1] = 0;
				}
			}
		}
		
		for (int i = 0; i < T; i++) {
			diff = new int[R][C];
			int nrow, ncol;
			// 미세먼지 확산
			for (int row = 0; row < R; row++) {
				for (int col = 0; col < C; col++) {
					int cnt = 0;
					for (int k = 0; k < 4; k++) {
						nrow = row + dir_r[k];
						ncol = col + dir_c[k];
						if(0<=nrow && nrow<R && 0<=ncol && ncol<C && map[nrow][ncol]!=-1) {
							cnt++;
							diff[nrow][ncol] += map[row][col]/5;
						}
					}
					map[row][col] -= map[row][col]/5*cnt;
				}
			}
			for (int row = 0; row < R; row++) {
				for (int col = 0; col < C; col++) {
					if(diff[row][col]!=0) {
						map[row][col] += diff[row][col];
					}
				}
			}
			
			// 공기청정기 가동
			// 반시계 순환
			int temp = 0;
			for (int col = 1; col < C-1; col++) {
				int ntemp = map[machine[0][0]][col];
				map[machine[0][0]][col] = temp;
				temp = ntemp;
			}
			for (int row = machine[0][0]; row > 0; row--) {
				int ntemp = map[row][C-1];
				map[row][C-1] = temp;
				temp = ntemp;
			}
			for (int col = C-1; col > 0; col--) {
				int ntemp = map[0][col];
				map[0][col] = temp;
				temp = ntemp;
			}
			for (int row = 0; row < machine[0][0]; row++) {
				int ntemp = map[row][0];
				map[row][0] = temp;
				temp = ntemp;
			}
			// 시계 순환
			temp = 0;
			for (int col = 1; col < C-1; col++) {
				int ntemp = map[machine[1][0]][col];
				map[machine[1][0]][col] = temp;
				temp = ntemp;
			}
			for (int row = machine[1][0]; row < R-1; row++) {
				int ntemp = map[row][C-1];
				map[row][C-1] = temp;
				temp = ntemp;
			}
			for (int col = C-1; col > 0; col--) {
				int ntemp = map[R-1][col];
				map[R-1][col] = temp;
				temp = ntemp;
			}
			for (int row = R-1; row > machine[1][0]; row--) {
				int ntemp = map[row][0];
				map[row][0] = temp;
				temp = ntemp;
			}
		}
		
		int sum = 0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				sum += map[i][j];
			}
		}
		System.out.println(sum+2);
	}

}
