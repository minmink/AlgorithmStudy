package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW2115Honey {

	static int N, M, C, max, map[][], honey[];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int testCase = 1; testCase <= T; testCase++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			max = 0;
			map = new int[N][N];
			honey = new int[M*2];
			for (int i = 0; i < N; i++) {
				String s = br.readLine();
				for (int j = 0; j < N; j++) {
					map[i][j] = s.charAt(j*2) - '0';
				}
			}
			for (int k = 0; k < N-1; k++) {
				for (int i = 0; i <= N-M; i++) {
					int idx1 = 0;
					for (int j = i; j < i+M; j++) {
						honey[idx1++] = map[k][j];
					}
					for (int k2 = k+1; k2 < N; k2++) {
						for (int i2 = 0; i2 <= N-M; i2++) {
							int idx2 = M;
							for (int j2 = i2; j2 < i2+M; j2++) {
								honey[idx2++] = map[k2][j2];
							}
							dfs(0, 0, 0, 0);
						}
					}
				}
			}
			sb.append("#").append(testCase).append(" ").append(max).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static void dfs(int idx, int total1, int total2, int sum) {
		if(idx == honey.length) {
			if(sum > max) max = sum;
			return;
		}
		
		if(idx < M) {
			if(total1 + honey[idx] <= C)
				dfs(idx+1, total1+honey[idx], total2, sum+honey[idx]*honey[idx]);
			dfs(idx+1, total1, total2, sum);
		} else {
			if(total2 + honey[idx] <= C)
				dfs(idx+1, total1, total2+honey[idx], sum+honey[idx]*honey[idx]);
			dfs(idx+1, total1, total2, sum);
		}
	}

}
