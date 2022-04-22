package algorithm;

import java.io.*;
import java.util.*;

public class SW2112Film2 {
	static int D, W, K, map[][], flags[], min;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tc = 0; tc < testCase; tc++) {
			sb.append("#").append(tc + 1).append(" ");
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			flags = new int[D];
			for (int i = 0; i < D; i++) {
				flags[i] = -1;
			}
			map = new int[D][W];
			for (int i = 0; i < D; i++) {
				String s = br.readLine();
				for (int j = 0; j < W; j++) {
					map[i][j] = s.charAt(j * 2) - '0';
				}
			}
			
			min = D;
			if(K == 1 || check()) {
				sb.append(0).append("\n");
			} else {
				makeMap(0, 0);
				sb.append(min).append("\n");
			}
		}
		System.out.println(sb.toString());
	}

	public static void makeMap(int row, int cnt) {
		if(cnt >= min)
			return;
		
		if(row == D) {
			if(check())
				min = Math.min(min, cnt);
			return;
		}
		
		flags[row] = -1;
		makeMap(row + 1, cnt);
		flags[row] = 0;
		makeMap(row + 1, cnt + 1);
		flags[row] = 1;
		makeMap(row + 1, cnt + 1);
	}
	
	public static boolean check() {
		for (int col = 0; col < W; col++) {
			int cnt = 1;
			int pre = flags[0] == -1? map[0][col] : flags[0];
			boolean pass = false;
			for (int row = 1; row < D; row++) {
				int temp = flags[row] == -1? map[row][col] : flags[row];
				if(pre == temp) {
					cnt++;
					if(cnt == K){
						pass = true;
						break;
					}
				} else {
					cnt = 1;
					pre = temp;
				}
			}
			if(!pass)
				return false;
		}
		return true;
	}
}
