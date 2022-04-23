package algorithm;

import java.io.*;
import java.util.*;

public class SW5644Charge {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tc = 0; tc < testCase; tc++) {
			sb.append("#").append(tc + 1).append(" ");
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int M = Integer.parseInt(st.nextToken());
			int N = Integer.parseInt(st.nextToken());
			int[] dr = {0, -1, 0, 1, 0};
			int[] dc = {0, 0, 1, 0, -1};
			int[] dirA = new int[M];
			int[] dirB = new int[M];
			String s = br.readLine();
			for (int i = 0; i < M; i++) {
				dirA[i] = s.charAt(i * 2) - '0';
			}
			s = br.readLine();
			for (int i = 0; i < M; i++) {
				dirB[i] = s.charAt(i * 2) - '0';
			}
			
			PriorityQueue<Charger>[][] map = new PriorityQueue[10][10];
			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 10; j++) {
					map[i][j] = new PriorityQueue<>();
				}
			}
			for (int i = 0; i < N; i++) {
				//Charger 정보 및 범위에 저장
				st = new StringTokenizer(br.readLine(), " ");
				int col = Integer.parseInt(st.nextToken()) - 1;
				int row = Integer.parseInt(st.nextToken()) - 1;
				int C = Integer.parseInt(st.nextToken());
				int Q = Integer.parseInt(st.nextToken());
				Charger charger = new Charger(i, Q);
				for (int r = 0; r <= C; r++) {
					for (int c = 0; r + c <= C; c++) {
						if(row + r < 10 && col + c < 10) {
							map[row + r][col + c].offer(charger);
						}
						if(c != 0 && row + r < 10 && col - c >= 0) {
							map[row + r][col - c].offer(charger);
						}
						if(r != 0 && row - r >= 0 && col + c < 10) {
							map[row - r][col + c].offer(charger);
						}
						if(r != 0 && c != 0 && row - r >= 0 && col - c >= 0) {
							map[row - r][col - c].offer(charger);
						}
					}
				}
			}
			
			int[] A = {0, 0};
			int[] B = {9, 9};
			int time = 0;
			int answer = 0;
			while(time <= M) {
				if(map[A[0]][A[1]].size() == 0 || map[B[0]][B[1]].size() == 0) {
					if(map[A[0]][A[1]].size() != 0)
						answer += map[A[0]][A[1]].peek().Q;
					else if(map[B[0]][B[1]].size() != 0)
						answer += map[B[0]][B[1]].peek().Q;
				} else {
					if(A[0] == B[0] && A[1] == B[1]) {
						Charger charger1 = map[A[0]][A[1]].poll();
						Charger charger2 = map[A[0]][A[1]].peek();
						answer += charger1.Q;
						if(charger2 != null)
							answer += charger2.Q;
						map[A[0]][A[1]].offer(charger1);
					} else {
						Charger chargerA = map[A[0]][A[1]].poll();
						Charger chargerB = map[B[0]][B[1]].poll();
						if(chargerA.num != chargerB.num) {
							answer += chargerA.Q;
							answer += chargerB.Q;
						} else {
							Charger chargerA2 = map[A[0]][A[1]].peek();
							Charger chargerB2 = map[B[0]][B[1]].peek();
							if(chargerA2 == null && chargerB2 == null)
								answer += chargerA.Q;
							else if(chargerA2 == null)
								answer += chargerA.Q + chargerB2.Q;
							else if(chargerB2 == null)
								answer += chargerB.Q + chargerA2.Q;
							else if(chargerA2.Q > chargerB2.Q)
								answer += chargerB.Q + chargerA2.Q;
							else
								answer += chargerA.Q + chargerB2.Q;
						}
						map[A[0]][A[1]].offer(chargerA);
						map[B[0]][B[1]].offer(chargerB);					
					}
				}
				
				if(time == M)
					break;
				A[0] += dr[dirA[time]];
				A[1] += dc[dirA[time]];
				B[0] += dr[dirB[time]];
				B[1] += dc[dirB[time]];
				time++;
			}
			
			sb.append(answer).append("\n");
		}
		System.out.println(sb.toString());
	}

	public static class Charger implements Comparable<Charger> {
		int num;
		int Q;
		public Charger(int num, int Q) {
			this.num = num;
			this.Q = Q;
		}
		@Override
		public int compareTo(Charger o) {
			return o.Q - this.Q;
		}
	}
}
