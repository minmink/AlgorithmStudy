package algorithm;

import java.io.*;
import java.util.*;

public class BJ10021Watering {
	private static class Node implements Comparable<Node> {
		int idx;
		int dis;
		public Node(int idx, int dis) {
			this.idx = idx;
			this.dis = dis;
		}
		@Override
		public int compareTo(Node o) {
			return this.dis - o.dis;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		ArrayList<Node>[] graph = new ArrayList[2000];
		for (int i = 0; i < 2000; i++) {
			graph[i] = new ArrayList<>();
		}
		int[][] nodes = new int[N][2];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			nodes[i] = new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
		}
		for (int i = 0; i < N - 1; i++) {
			for (int j = i + 1; j < N; j++) {
				int dis = (int) (Math.pow(nodes[i][0] - nodes[j][0], 2) + Math.pow(nodes[i][1] - nodes[j][1], 2));
				if (dis < C)
					continue;
				graph[i].add(new Node(j, dis));
				graph[j].add(new Node(i, dis));
			}
		}
		boolean[] visited = new boolean[N];
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(0, 0));
		long answer = 0;
		int cnt = 0;
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			if (visited[cur.idx])
				continue;
			visited[cur.idx] = true;
			answer += cur.dis;
			if (++cnt == N) {
				System.out.println(answer);
				return;
			}
			
			for (Node node : graph[cur.idx]) {
				if (visited[node.idx])
					continue;
				pq.add(new Node(node.idx, node.dis));
			}
		}
		System.out.println(-1);
	}
}
