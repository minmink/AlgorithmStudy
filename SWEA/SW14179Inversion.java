package algorithm;

import java.io.*;
import java.util.*;

public class SW14179Inversion {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		final long DIV = 1000000007;
		for (int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			long K = Long.parseLong(st.nextToken());
			
			st = new StringTokenizer(br.readLine(), " ");
			long cnt = 0;
			int[] nums = new int[N];
			for (int j = 0; j < N; j++) {
				nums[j] = Integer.parseInt(st.nextToken());
				for (int i = 0; i < j; i++) {
					if(nums[i] < nums[j])
						cnt = (cnt + (K * (K - 1) / 2) % DIV) % DIV;
					else if(nums[i] > nums[j])
						cnt = (cnt + (K + (K * (K - 1) / 2)) % DIV) % DIV;
				}
			}
			
			sb.append(cnt).append("\n");
		}
		
		System.out.println(sb.toString());
	}
}
