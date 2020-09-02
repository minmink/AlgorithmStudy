package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ1956Exercise {

	static ArrayList<Vertex>[] edges;
	static int INF = Integer.MAX_VALUE, V, E, min = INF, start;
	static int[] root;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		edges = new ArrayList[V+1];
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int num = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int len = Integer.parseInt(st.nextToken());
			if(edges[num]==null) {
				edges[num] = new ArrayList<>();
			}
			edges[num].add(new Vertex(to, len));
		}
		for (int v = 1; v <= V; v++) {
			int[] d = new int[V+1];
			boolean[] visited = new boolean[V+1];
			Arrays.fill(d, INF);
			PriorityQueue<Vertex> pq = new PriorityQueue<>();
			pq.offer(new Vertex(v, 0));
			Vertex cur = null;
			
			while(!pq.isEmpty()) {
				cur = pq.poll();
				if(visited[cur.no]) continue;
				
				if(cur.no != v) visited[cur.no] = true;
				
				if(edges[cur.no]!=null) {
					for (Vertex e : edges[cur.no]) {
						if(e.no == v) {
							if(d[e.no] > cur.len + e.len)
								d[e.no] = cur.len + e.len;
						} else if(!visited[e.no] && d[e.no] > cur.len + e.len) {
							d[e.no] = cur.len + e.len;
							pq.offer(new Vertex(e.no, d[e.no]));
						}
					}
				}
			}
			
			if(min>d[v]) min = d[v];
		}
		System.out.println(min==Integer.MAX_VALUE?-1:min);
	}
	
	static class Vertex implements Comparable<Vertex> {
		int no;
		int len;
		public Vertex(int to, int len) {
			super();
			this.no = to;
			this.len = len;
		}
		@Override
		public int compareTo(Vertex o) {
			return this.len - o.len;
		}
	}
}
