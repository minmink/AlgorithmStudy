package algorithm;

import java.io.*;
import java.util.*;

public class SW1221GNS {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");
			int[] num = new int[10];
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			st.nextToken();
			int N = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			
			for (int i = 0; i < N; i++) {
				switch(st.nextToken()) {
				case "ZRO":
					num[0]++;
					break;
				case "ONE":
					num[1]++;
					break;
				case "TWO":
					num[2]++;
					break;
				case "THR":
					num[3]++;
					break;
				case "FOR":
					num[4]++;
					break;
				case "FIV":
					num[5]++;
					break;
				case "SIX":
					num[6]++;
					break;
				case "SVN":
					num[7]++;
					break;
				case "EGT":
					num[8]++;
					break;
				case "NIN":
					num[9]++;
					break;
				}
			}
			
			for (int i = 0; i < num[0]; i++) {
				sb.append("ZRO ");
			}
			for (int i = 0; i < num[1]; i++) {
				sb.append("ONE ");
			}
			for (int i = 0; i < num[2]; i++) {
				sb.append("TWO ");
			}
			for (int i = 0; i < num[3]; i++) {
				sb.append("THR ");
			}
			for (int i = 0; i < num[4]; i++) {
				sb.append("FOR ");
			}
			for (int i = 0; i < num[5]; i++) {
				sb.append("FIV ");
			}
			for (int i = 0; i < num[6]; i++) {
				sb.append("SIX ");
			}
			for (int i = 0; i < num[7]; i++) {
				sb.append("SVN ");
			}
			for (int i = 0; i < num[8]; i++) {
				sb.append("EGT ");
			}
			for (int i = 0; i < num[9]; i++) {
				sb.append("NIN ");
			}
			
			sb.append("\n");
		}
		
		System.out.print(sb.toString());
	}

}
