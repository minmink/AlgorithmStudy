package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ11657TimeMachine {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		Edge[] edges = new Edge[M];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			edges[i] = new Edge(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()));
		}
		
		long[] time = new long[N];
		for (int i = 1; i < N; i++) {
			time[i] = Long.MAX_VALUE;
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (time[edges[j].start] == Long.MAX_VALUE)
					continue;
				if (time[edges[j].end] > edges[j].d + time[edges[j].start]) {
					if (i == N - 1) {
						System.out.println(-1);
						return;
					}
					time[edges[j].end] = edges[j].d + time[edges[j].start];
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i < N; i++) {
			if (time[i] == Long.MAX_VALUE)
				sb.append(-1 + "\n");
			else
				sb.append(time[i] + "\n");
		}
		System.out.print(sb.toString());
	}
	
	private static class Edge {
		int start;
		int end;
		int d;
		public Edge(int start, int end, int d) {
			this.start = start;
			this.end = end;
			this.d = d;
		}
	}
}
