package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW2117Security {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int testCase = 1; testCase <= T; testCase++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int[][] map = new int[N][N];
			int num = 0;
			for (int i = 0; i < N; i++) {
				String s = br.readLine();
				for (int j = 0; j < N; j++) {
					map[i][j] = s.charAt(j*2)-'0';
					if(map[i][j] == 1) num++;
				}
			}
			
			int max = 0;
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					for (int K = 1; K < 2*N-1; K++) {
						int cost = (K-1)*(K-1)+K*K;
						if(cost>num*M) break;
						int cnt = 0;
						
						for (int i = 0; i < K; i++) {
							for (int j = 0; j+i < K; j++) {
								if(0<=r+i && r+i<N && 0<=c+j && c+j<N && map[r+i][c+j] == 1) cnt++;
								if(i!=0 && 0<=r-i && r-i<N && 0<=c+j && c+j<N && map[r-i][c+j] == 1) cnt++;
								if(j!=0 && 0<=r+i && r+i<N && 0<=c-j && c-j<N && map[r+i][c-j] == 1) cnt++;
								if(i!=0 && j!=0 && 0<=r-i && r-i<N && 0<=c-j && c-j<N && map[r-i][c-j] == 1) cnt++;
							}
						}
						
						if(cost <= cnt*M) {
							if(max<cnt) {
								max = cnt;							
							}
						}
					}
				}
			}
			
			sb.append("#").append(testCase).append(" ").append(max).append("\n");
		}
		
		System.out.println(sb.toString());
	}

}
