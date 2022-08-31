package algorithm;

import java.io.*;
import java.util.*;

public class SW8898Farmer {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int tc = Integer.parseInt(br.readLine());
		for (int testCase = 1; testCase <= tc; testCase++) {
			sb.append("#").append(testCase).append(" ");
			int dis = 0;
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine(), " ");
			int c1 = Integer.parseInt(st.nextToken());
			int c2 = Integer.parseInt(st.nextToken());
			dis += Math.abs(c1 - c2);
			int[] cows = new int[N];
			int[] horses = new int[M];
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				cows[i] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(cows);
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < M; i++) {
				horses[i] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(horses);
			
			int min = Integer.MAX_VALUE;
			int num = 0;
			int idx = 0;
			for (int c = 0; c < N; c++) {
				while(idx < M) {
					int val = Math.abs(cows[c] - horses[idx]);
					if(min > val) {
						min = val;
						num = 1;
					} else if(min == val)
						num++;
					
					if(cows[c] <= horses[idx])
						break;
					
					idx++;
				}
			}
			
			sb.append(dis + min).append(" ").append(num).append("\n");
		}
		
		System.out.print(sb.toString());
	}
}
