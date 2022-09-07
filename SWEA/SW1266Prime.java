package algorithm;

import java.io.*;
import java.util.*;

public class SW1266Prime {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		int[] prime = {2, 3, 5, 7, 11, 13, 17};
		int[][] comb = new int[20][20];
		for (int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			double skillOfMasterA = Double.parseDouble(st.nextToken());
			double skillOfMasterB = Double.parseDouble(st.nextToken());
			
			
			
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
	}
}
