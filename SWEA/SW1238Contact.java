package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SW1238Contact {
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int N, start, max[];
		ArrayList<Integer>[] list;
		int[] visited;
		Queue<Integer> queue;
		
		for (int testCase = 1; testCase < 11; testCase++) {
			sb.append("#").append(testCase).append(" ");
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			start = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			queue = new LinkedList<Integer>();
			visited = new int[101];
			max = new int[] {1, start}; // level, 번호
			list = new ArrayList[101];
			for (int i = 0; i < 101; i++) {
				list[i] = new ArrayList<Integer>();
			}
			
			for (int i = 0; i < N/2; i++) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				boolean flag = false;
				for (int j = 0; j < list[from].size(); j++) {
					if(list[from].get(j) == to) {
						flag = true;
						break;
					}
				}
				if(!flag) list[from].add(to);
			}
			
			queue.offer(start);
			visited[start] = 1;
			int target;
			while(!queue.isEmpty()) {
				target = queue.poll();
				for (int t : list[target]) {
					if(visited[t] == 0 || visited[t] > visited[target]+1) {
						visited[t] = visited[target]+1;
						if(max[0] < visited[t]) {
							max[0] = visited[t];
							max[1] = t;
						} else if(max[0] == visited[t] && max[1] < t) {
							max[1] = t;
						}
						queue.offer(t);
					}
				}
			}
			
			sb.append(max[1]).append("\n");
			System.out.println(max[1]);
		}
		
		System.out.print(sb.toString());
	}

}
