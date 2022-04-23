package algorithm;

import java.io.*;
import java.util.*;

public class SW2383Lunch {
	static int stairs[][], min, select[], stair0Time, stair1Time;
	static ArrayList<int[]> people;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tc = 0; tc < testCase; tc++) {
			sb.append("#").append(tc + 1).append(" ");
			int N = Integer.parseInt(br.readLine());
			stairs = new int[2][2];
			stairs[0] = new int[] {-1, -1};
			people = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					int num = Integer.parseInt(st.nextToken());
					if(num > 1) {
						if(stairs[0][0] == -1) {
							stairs[0] = new int[] {i, j};
							stair0Time = num;
						} else {
							stairs[1] = new int[] {i, j};
							stair1Time = num;
						}
					} else if(num == 1) {
						people.add(new int[] {i, j});
					}
				}
			}
			
			min = Integer.MAX_VALUE;
			select = new int[people.size()];
			calc();
			make(0);
			sb.append(min).append("\n");
		}
		System.out.println(sb.toString());
	}

	public static void make(int idx) {
		for (int i = idx; i < select.length; i++) {
			select[i] = 1;
			calc();
			make(i + 1);
			select[i] = 0;
		}
	}
	
	public static void calc() {
		int time = 0;
		int[] stair0 = {-1, -1, -1};
		int[] stair1 = {-1, -1, -1};
		PriorityQueue<Integer> pq0 = new PriorityQueue<>();
		PriorityQueue<Integer> pq1 = new PriorityQueue<>();
		for (int i = 0; i < select.length; i++) {
			if(select[i] == 0)
				pq0.offer(Math.abs(stairs[0][0] - people.get(i)[0]) + Math.abs(stairs[0][1] - people.get(i)[1]));
			else
				pq1.offer(Math.abs(stairs[1][0] - people.get(i)[0]) + Math.abs(stairs[1][1] - people.get(i)[1]));
		}
		
		while(true) {
			if(++time >= min)
				return;
			
			for (int i = 0; i < 3; i++) {
				if(stair0[i] >= 0) {
					stair0[i]++;
					if(stair0[i] == stair0Time)
						stair0[i] = -1;
				}
				if(stair1[i] >= 0) {
					stair1[i]++;
					if(stair1[i] == stair1Time)
						stair1[i] = -1;
				}
			}
			
			while(!pq0.isEmpty() && pq0.peek() <= time) {
				boolean success = false;
				for (int i = 0; i < 3; i++) {
					if(stair0[i] != -1)
						continue;
					pq0.poll();
					stair0[i] = 0;
					success = true;
					break;
				}
				if(!success)
					break;
			}
			
			while(!pq1.isEmpty() && pq1.peek() <= time) {
				boolean success = false;
				for (int i = 0; i < 3; i++) {
					if(stair1[i] != -1)
						continue;
					pq1.poll();
					stair1[i] = 0;
					success = true;
					break;
				}
				if(!success)
					break;
			}
			
			if(pq0.isEmpty() && pq1.isEmpty() &&
					stair0[0] == -1 && stair0[1] == -1 && stair0[2] == -1 &&
					stair1[0] == -1 && stair1[1] == -1 && stair1[2] == -1)
				break;
		}
		
		min = time + 1;
	}
}
