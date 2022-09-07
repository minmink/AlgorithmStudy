package algorithm;

import java.io.*;
import java.util.*;

public class SW1267Order {
	static ArrayList<Integer>[] graphTo;
	static ArrayList<Integer>[] graphFrom;
	static StringBuilder sb = new StringBuilder();
	static int cnt;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int tc = 1; tc <= 10; tc++) {
			sb.append("#").append(tc);
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int V = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			graphTo = new ArrayList[V + 1];
			graphFrom = new ArrayList[V + 1];
			for (int i = 1; i <= V; i++) {
				graphTo[i] = new ArrayList<>();
				graphFrom[i] = new ArrayList<>();
			}
			
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < E; i++) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				graphTo[from].add(to);
				graphFrom[to].add(from);
			}
			
			cnt = 0;
			visited = new boolean[V + 1];
			while(cnt != V) {
				for (int i = 1; i <= V; i++) {
					if(!visited[i] && graphFrom[i].size() == 0) {
						sb.append(" ").append(i);
						cnt++;
						visited[i] = true;
						for (int j = graphTo[i].size() - 1; j >= 0; j--) {
							int num = graphTo[i].get(j);
							graphFrom[num].remove(graphFrom[num].indexOf(i));
							if(graphFrom[num].size() == 0) {
								sb.append(" ").append(num);
								cnt++;
								visited[num] = true;
								traverse(num);
							}
						}
					}
				}				
			}
			
			sb.append("\n");
		}
		
		System.out.print(sb.toString());
	}

	private static void traverse(int num) {
		if(graphTo[num].size() == 0)
			return;
		
		for (int i = graphTo[num].size() - 1; i >= 0; i--) {
			int n = graphTo[num].get(i);
			graphFrom[n].remove(graphFrom[n].indexOf(num));
			if(graphFrom[n].size() == 0) {
				sb.append(" ").append(n);
				cnt++;
				visited[n] = true;
				traverse(n);
			}
		}
	}
}
