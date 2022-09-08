package algorithm;

import java.io.*;
import java.util.*;

public class SW1266Prime {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		int[] prime = {2, 3, 5, 7, 11, 13, 17};
		
		for (int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			double skillOfMasterA = Double.parseDouble(st.nextToken()) / 100;
			double skillOfMasterB = Double.parseDouble(st.nextToken()) / 100;
			
			double A = 0, B = 0;
			
			for (int i = 0; i < prime.length; i++) {
				A += combination(18, prime[i]) * Math.pow(skillOfMasterA, prime[i]) * Math.pow(1 - skillOfMasterA,  18 - prime[i]);
				B += combination(18, prime[i]) * Math.pow(skillOfMasterB, prime[i]) * Math.pow(1 - skillOfMasterB,  18 - prime[i]);
			}
			
			double result = A + B - (A * B);
			sb.append(String.format("%.6f", result)).append("\n");
		}
		
		System.out.println(sb.toString());
	}
	
	private static int combination(int n, int r) {
		if(n == r || r == 0)
			return 1;
		else
			return combination(n - 1, r - 1) + combination(n - 1, r);
	}
}
