package algorithm;

import java.io.*;
import java.util.*;

public class SW9280ParkingTower {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int tc = Integer.parseInt(br.readLine());
		for (int testCase = 1; testCase <= tc; testCase++) {
			sb.append("#").append(testCase).append(" ");
			int answer = 0;
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int[] prices = new int[n + 1];
			int[] weights = new int[m + 1];
			boolean[] isFull = new boolean[n + 1];
			int[] cars = new int[m + 1];
			Queue<Integer> waitingList = new LinkedList<Integer>();
			int emptyNum = n;
			
			for (int i = 1; i <= n; i++) {
				prices[i] = Integer.parseInt(br.readLine());
			}
			for (int i = 1; i <= m; i++) {
				weights[i] = Integer.parseInt(br.readLine());
			}
			for (int i = 0; i < 2 * m; i++) {
				int car = Integer.parseInt(br.readLine());
				if(car > 0) {
					// 주차해야 될 때
					if(emptyNum == 0)
						waitingList.offer(car);
					else {
						emptyNum--;
						int idx = 0;
						for (idx = 1; idx <= n; idx++) {
							if(!isFull[idx])
								break;
						}
						isFull[idx] = true;
						cars[car] = idx;
						answer += weights[car] * prices[idx];
					}
				} else {
					// 빠져나갈 때
					car *= -1;
					emptyNum++;
					isFull[cars[car]] = false;
					if(waitingList.size() != 0) {
						emptyNum--;
						cars[waitingList.peek()] = cars[car];
						car = waitingList.poll();
						isFull[cars[car]] = true;
						answer += prices[cars[car]] * weights[car];
					}
				}
			}
			
			sb.append(answer).append("\n");
		}
		
		System.out.print(sb.toString());
	}
}
