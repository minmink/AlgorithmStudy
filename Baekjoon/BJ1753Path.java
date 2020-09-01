package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ1753Path {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		int start = Integer.parseInt(br.readLine());
		ArrayList<Node>[] arr = new ArrayList[V+1];
		for (int i = 1; i <= V; i++) {
			arr[i] = new ArrayList<>();
		}
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			arr[Integer.parseInt(st.nextToken())].add(new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		
		StringBuilder sb = new StringBuilder();
		final int INF = Integer.MAX_VALUE;
		int[] d = new int[V+1];
		Arrays.fill(d, INF);
		d[start] = 0;
		boolean[] visited = new boolean[V+1];
		int cnt = -1;
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(start, 0));
		int current = start;
		while (!pq.isEmpty() && ++cnt<V) {
			Node n = pq.poll();
			if(visited[n.v]) continue;
			
			current = n.v;
			visited[current] = true;
			for (Node node : arr[current]) {
				if(!visited[node.v] && d[node.v] > n.weight + node.weight) {
					d[node.v] = n.weight + node.weight;
					pq.add(new Node(node.v, d[node.v]));
				}
			}
		}
		for (int i = 1; i <= V; i++) {
			sb.append(d[i]==INF?"INF":d[i]).append("\n");
		}
		System.out.println(sb.toString());
		
	}

	public static class Node implements Comparable<Node> {
		int v;
		int weight;
		public Node(int v, int weight) {
			super();
			this.v = v;
			this.weight = weight;
		}
		@Override
		public int compareTo(Node o) {
			return this.weight - o.weight;
		}
		@Override
		public String toString() {
			return "Node [v=" + v + ", weight=" + weight + "]";
		}
	}
}
