package algorithm;

import java.io.*;
import java.util.*;

public class SW1232Operation {
	static int[][] tree;
	static String[] value;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= 10; tc++) {
			sb.append("#").append(tc).append(" ");
			int N = Integer.parseInt(br.readLine());
			tree = new int[N + 1][2];
			value = new String[N + 1];
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				int node = Integer.parseInt(st.nextToken());
				value[node] = st.nextToken();
				if(st.hasMoreTokens()) {
					tree[node][0] = Integer.parseInt(st.nextToken());
					tree[node][1] = Integer.parseInt(st.nextToken());
				}
			}
			
			
			sb.append(traverse(1)).append("\n");
		}
		
		System.out.println(sb.toString());
	}
	
	static int traverse(int node) {
		if(tree[node][0] == 0)
			return Integer.parseInt(value[node]);
		
		int val1 = traverse(tree[node][0]);
		int val2 = traverse(tree[node][1]);
		
		switch(value[node]) {
		case "-":
			return val1 - val2;
		case "+":
			return val1 + val2;
		case "/":
			return val1 / val2;
		default:
			return val1 * val2;
		}
	}
}
