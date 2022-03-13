package algorithm;

import java.io.*;
import java.util.*;

public class BJ15591MooTube {
	private static class Edge {
		int end;
		int usado;
		public Edge(int end, int usado) {
			this.end = end;
			this.usado = usado;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		ArrayList<Edge>[] edges = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			edges[i] = new ArrayList<>();
		}
		for (int i = 1; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int first = Integer.parseInt(st.nextToken()) - 1;
			int second = Integer.parseInt(st.nextToken()) - 1;
			int usado = Integer.parseInt(st.nextToken());
			edges[first].add(new Edge(second, usado));
			edges[second].add(new Edge(first, usado));
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int K = Integer.parseInt(st.nextToken());
			int V = Integer.parseInt(st.nextToken()) - 1;
			boolean[] visited = new boolean[N];
			Queue<Edge> queue = new LinkedList<>();
			int cnt = 0;
			visited[V] = true;
			queue.offer(new Edge(V, Integer.MAX_VALUE));
			while(!queue.isEmpty()) {
				Edge cur = queue.poll();
				for (Edge edge : edges[cur.end]) {
					if (visited[edge.end] || edge.usado < K)
						continue;
					cnt++;
					queue.offer(new Edge(edge.end, Math.min(cur.usado, edge.usado)));
					visited[edge.end] = true;
				}
			}
			sb.append(cnt + "\n");
		}
		System.out.print(sb.toString());
	}
}
