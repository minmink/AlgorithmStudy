package algorithm;

import java.io.*;
import java.util.*;

public class SW1231Traverse {
	static char[] tree;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int tc = 1; tc <= 10; tc++) {
			sb.append("#").append(tc).append(" ");
			int N = Integer.parseInt(br.readLine());
			tree = new char[202];
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				int node = Integer.parseInt(st.nextToken());
				char value = st.nextToken().charAt(0);
				tree[node] = value;
			}
			
			traverse(1);
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
	}
	
	static void traverse(int node) {
		if(node > 201 || tree[node] == '\0')
			return;
		
		traverse(node * 2);
		sb.append(tree[node]);
		traverse(node * 2 + 1);
	}
}
