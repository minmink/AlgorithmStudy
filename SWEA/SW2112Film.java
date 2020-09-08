package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SW2112Film {

	static int D, W, K, map[][], list[], min;
	static final int NOT = -1, A = 0, B = 1;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int testCase = 1; testCase <= T; testCase++) {
			sb.append("#").append(testCase).append(" ");
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			map = new int[D][W];
			list = new int[D];
			min = Integer.MAX_VALUE;
			
			for (int i = 0; i < D; i++) {
				String s = br.readLine();
				for (int j = 0; j < W; j++) {
					map[i][j] = s.charAt(j*2) - '0';
				}
			}
			
			Arrays.fill(list, NOT);
			
			if(check()) {
				min = 0;
			} else {
				process(0, 0);
			}
			
			sb.append(min).append("\n");
		}
		
		System.out.println(sb.toString());
	}

	private static void process(int row, int cnt) {
		if(cnt>=min) return;
		if(row == D) {
			if(check()) {
				if(min > cnt) min = cnt;
			}
			return;
		}
		
		list[row] = A;
		process(row+1, cnt+1);
		
		list[row] = B;
		process(row+1, cnt+1);
		
		list[row] = NOT;
		process(row+1, cnt);
	}

	private static boolean check() {
		for (int j = 0; j < W; j++) {
			int before = list[0]==NOT?map[0][j]:list[0];
			int count = 1;
			for (int i = 1; i < D; i++) {
				int current = list[i]==NOT?map[i][j]:list[i];
				if(before!=current) {
					before = current;
					count = 1;
				} else {
					if(++count>=K) break;
				}
			}
			if(count<K) return false;
		}
		return true;
	}

}
