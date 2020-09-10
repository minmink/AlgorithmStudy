package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class SW2383Stairs {

	static int N, map[][], pNum, sNum, min, people[][], stairs[][];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int testCase = 1; testCase <= T; testCase++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			pNum = 0; sNum = 0;
			min = Integer.MAX_VALUE;
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j] == 1) pNum++;
					else if(map[i][j] > 1) sNum++;
				}
			}
			people = new int[pNum][3]; // r, c, 계단번호
			stairs = new int[sNum][4]; // r, c, 남은 시간, 남은 시간, 남은 시간, 기본 시간
			int pidx = 0, sidx = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(map[i][j]==1) people[pidx++] = new int[] {i, j, 0}; 
					else if(map[i][j]>1) stairs[sidx++] = new int[] {i, j, 0, 0, 0, map[i][j]};
				}
			}
			
			dfs(0);
			
			sb.append("#").append(testCase).append(" ").append(min).append("\n");
		}
		
		System.out.println(sb.toString());
	}

	
	private static void dfs(int idx) {
		if(idx == people.length) {
			calc();
			return;
		}
		for (int i = 0; i < sNum; i++) {
			people[idx][2] = i;
			dfs(idx+1);
		}
	}


	private static void calc() {
		int time = 0;
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[1]-o2[1];
			}
		});
		Queue<int[]> q = new ArrayDeque<int[]>();
		
		for (int i = 0; i < people.length; i++) {
			int[] cur = people[i];
			int d = Math.abs(cur[0] - stairs[cur[2]][0]) + Math.abs(cur[1] - stairs[cur[2]][1]);
			pq.offer(new int[] {cur[2], d});
		}
		
		while(time++ < min) {
			for (int i = 0; i < stairs.length; i++) {
				if(stairs[i][2] != 0) stairs[i][2]--;
				if(stairs[i][3] != 0) stairs[i][3]--;
				if(stairs[i][4] != 0) stairs[i][4]--;
			}
			while(!pq.isEmpty()) {
				int[] cur = pq.poll();
				cur[1]--;
				q.offer(cur);
			}
			int size = q.size();
			for (int i = 0; i < size; i++) {
				int[] cur = q.poll();
				if(cur[1] == 0) {
					if(stairs[cur[0]][2] == 0) {
						stairs[cur[0]][2] = stairs[cur[0]][5];
					} else if(stairs[cur[0]][3] == 0) {
						stairs[cur[0]][3] = stairs[cur[0]][5];
					} else if(stairs[cur[0]][4] == 0) {
						stairs[cur[0]][4] = stairs[cur[0]][5];
					} else {
						q.offer(cur);
					}
				} else {
					pq.offer(cur);
				}
			}
			if(pq.isEmpty() && q.isEmpty()) break;
		}
		
		if(pq.isEmpty() && q.isEmpty()) {
			while(true) {
				boolean flag = true;
				for (int i = 0; i < stairs.length; i++) {
					if(stairs[i][2] != 0) {
						flag = false;
						stairs[i][2]--;
					}
					if(stairs[i][3] != 0) {
						flag = false;
						stairs[i][3]--;
					}
					if(stairs[i][4] != 0) {
						flag = false;
						stairs[i][4]--;
					}
				}
				time++;
				if(flag) break;
			}
			if(min > time) min = time;
		}
	}
}
