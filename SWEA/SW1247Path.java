package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW1247Path {

	static int[] order;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		for (int testCase = 1; testCase <= T; testCase++) {
			sb.append("#").append(testCase).append(" ");
			int N = Integer.parseInt(br.readLine());
			int[] start, end;
			int[][] customers = new int[N][2];
			int min = Integer.MAX_VALUE;
			st = new StringTokenizer(br.readLine());
			start = new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
			end = new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
			order = new int[N];
			for (int i = 0; i < N; i++) {
				customers[i] = new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
				order[i] = i;
			}
			
			do {
				int sum = 0;
				sum += Math.abs(start[0]-customers[order[0]][0]) + Math.abs(start[1]-customers[order[0]][1]);
				for (int i = 0; i < N-1; i++) {
					sum += Math.abs(customers[order[i]][0]-customers[order[i+1]][0]) + Math.abs(customers[order[i]][1]-customers[order[i+1]][1]);
				}
				sum += Math.abs(customers[order[N-1]][0]-end[0]) + Math.abs(customers[order[N-1]][1]-end[1]);
				if(sum<min) min = sum;
			} while(np());
			
			sb.append(min).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static boolean np() {
		int i = order.length-1;
		while(i-1>=0 && order[i-1]>=order[i]) i--;
		if(i == 0) return false;
		
		int j = order.length-1;
		while(order[i-1]>=order[j]) j--;
		int temp = order[i-1];
		order[i-1] = order[j];
		order[j] = temp;
		
		int k = order.length-1;
		while(i<k) {
			temp = order[i];
			order[i++] = order[k];
			order[k--] = temp;
		}
		
		return true;
	}

}
