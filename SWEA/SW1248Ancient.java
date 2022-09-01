package algorithm;

import java.io.*;
import java.util.*;

public class SW1248Ancient {
	static int[] tree, parents;
	static ArrayList<Integer>[] children;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int V = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			int num1 = Integer.parseInt(st.nextToken());
			int num2 = Integer.parseInt(st.nextToken());
			children = new ArrayList[V + 1];
			parents = new int[V + 1];
			for (int i = 1; i <= V; i++) {
				children[i] = new ArrayList<>();
			}
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < E; i++) {
				int parent = Integer.parseInt(st.nextToken());
				int child = Integer.parseInt(st.nextToken());
				children[parent].add(child);
				parents[child] = parent;
			}
			
			HashSet<Integer> set = new HashSet<>();
			int cur = num1;
			set.add(num1);
			while(true) {
				if(parents[cur] == 0)
					break;
				cur = parents[cur];
				set.add(cur);
			}
			
			cur = num2;
			while(true) {
				if(set.contains(cur))
					break;
				cur = parents[cur];
			}
			
			sb.append(cur).append(" ").append(traverse(cur)).append("\n");
		}
		
		System.out.println(sb.toString());
	}

	private static int traverse(int node) {
		if(children[node].size() == 0)
			return 1;
		
		int val = 1;
		if(children[node].size() >= 1)
			val += traverse(children[node].get(0));
		if(children[node].size() == 2)
			val += traverse(children[node].get(1));
		
		return val;
	}
}
