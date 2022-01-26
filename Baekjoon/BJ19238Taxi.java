package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ19238Taxi {
	static int N, M, map[][], gas, taxi[], guest[][][];
	static boolean isVisited[][];
	static int[] dr = {-1, 0, 0, 1};
	static int[] dc = {0, -1, 1, 0};
	static PriorityQueue<Node> queue;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		StringTokenizer st = new StringTokenizer(s, " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		gas = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		guest = new int[N][N][2];
		
		
		for (int i = 0; i < N; i++) {
			s = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = s.charAt(j * 2) - '0';
				guest[i][j][0] = -1;
			}
		}
		
		s = br.readLine();
		st = new StringTokenizer(s, " ");
		taxi = new int[] {Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1};
		
		for (int i = 0; i < M; i++) {
			s = br.readLine();
			st = new StringTokenizer(s, " ");
			guest[Integer.parseInt(st.nextToken()) - 1][Integer.parseInt(st.nextToken()) - 1] = new int[] {Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1};
		}
		
		for (int testCase = 0; testCase < M; testCase++) {
			// 가장 가까운 손님 찾기
			boolean success = false;
			int d = 0, nr, nc;
			isVisited = new boolean[N][N];
			queue = new PriorityQueue<>();
			queue.offer(new Node(taxi[0], taxi[1], d));
			isVisited[taxi[0]][taxi[1]] = true;
			drive:
			while(!queue.isEmpty()) {
				int size = queue.size();
				for (int qs = 0; qs < size; qs++) {
					Node cur = queue.poll();
					if (guest[cur.r][cur.c][0] != -1) {
						// 손님 태우기
						if (gas < d) {
							System.out.println(-1);
							return;
						}
						gas -= d;
						taxi = new int[] {cur.r, cur.c};
						// 손님 데려다주기
						d = 0;
						isVisited = new boolean[N][N];
						Queue<Node> queueTemp = new LinkedList<>();
						queueTemp.offer(new Node(taxi[0], taxi[1], d));
						isVisited[taxi[0]][taxi[1]] = true;
						while(!queueTemp.isEmpty()) {
							size = queueTemp.size();
							for (qs = 0; qs < size; qs++) {
								cur = queueTemp.poll();
								if (cur.r == guest[taxi[0]][taxi[1]][0] && cur.c == guest[taxi[0]][taxi[1]][1]) {
									if (gas < d) {
										System.out.println(-1);
										return;
									}
									gas += d;
									guest[taxi[0]][taxi[1]][0] = -1;
									taxi = new int[] {cur.r, cur.c};
									success = true;
									break drive;
								}
								for (int i = 0; i < 4; i++) {
									nr = cur.r + dr[i];
									nc = cur.c + dc[i];
									if (nr < 0 || nr >= N || nc < 0 || nc >= N || isVisited[nr][nc] || map[nr][nc] == 1)
										continue;
									isVisited[nr][nc] = true;
									queueTemp.offer(new Node(nr, nc, d + 1));
								}
							}
							d++;
						}
					}
					for (int i = 0; i < 4; i++) {
						nr = cur.r + dr[i];
						nc = cur.c + dc[i];
						if (nr < 0 || nr >= N || nc < 0 || nc >= N || isVisited[nr][nc] || map[nr][nc] == 1)
							continue;
						isVisited[nr][nc] = true;
						queue.offer(new Node(nr, nc, d + 1));
					}
				}
				d++;
			}
			if (!success) {
				System.out.println(-1);
				return;
			}
		}
		
		System.out.println(gas);
	}
	
	public static class Node implements Comparable<Node> {
		int r;
		int c;
		int d;
		Node (int r, int c, int d) {
			this.r = r;
			this.c = c;
			this.d = d;
		}
		@Override
		public int compareTo(Node o) {
			if (this.d == o.d) {
				if (this.r == o.r)
					return Integer.compare(this.c, o.c);
				return Integer.compare(this.r, o.r);
			}
			return Integer.compare(this.d, o.d);
		}
		
	}
}
