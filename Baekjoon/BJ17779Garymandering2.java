package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ17779Garymandering2 {

	static int N, answer = Integer.MAX_VALUE;
	static int[][] populations;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		populations = new int[N+1][N+1];
		StringTokenizer st;
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 1; j <= N; j++) {
				populations[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int x = 2; x < N; x++) {
			for (int y = 2; y < N; y++) {
				for (int d1 = 1; d1 <= y-1; d1++) {
					for (int d2 = 1; d2 <= N-y; d2++) {
						if(d1+d2 > N-x) continue;
						calc(x, y, d1, d2);
					}
				}
			}
		}
		
		System.out.println(answer);
	}

	private static void calc(int x, int y, int d1, int d2) {
		int[] pops = new int[5];
		
		for (int r = 1; r <= N; r++) {
			for (int c = 1; c <= N; c++) {
				//1번 선거구
				if (r < x + d1 && c <= y && !(r >= x && c >= y - (r - x))) {
					pops[0] += populations[r][c];
				}
				//2번 선거구
				else if (r <= x + d2 && c > y && !(r >= x && c <= y + (r - x))) {
					pops[1] += populations[r][c];
				}
				//3번 선거구
				else if (r >= x + d1 && c < y - d1 + d2 && !(r <= x + d1 + d2 && c >= (y - d1 + d2 - (x + d1 + d2 - r)))) {
					pops[2] += populations[r][c];
				}
				//4번 선거구
				else if (r > x + d2 && c >= y - d1 + d2 && !(r <= x + d1 + d2 && c <= (y - d1 + d2 + (x + d1 + d2 - r)))) {
					pops[3] += populations[r][c];
				}
				//5번 선거구
				else {
					pops[4] += populations[r][c];
				}
			}
		}

		
		Arrays.sort(pops);
		if(answer > pops[4] - pops[0]) answer = pops[4] - pops[0];
	}
}
