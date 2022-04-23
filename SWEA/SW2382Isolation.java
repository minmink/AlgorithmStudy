package algorithm;

import java.io.*;
import java.util.*;

public class SW2382Isolation {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tc = 0; tc < testCase; tc++) {
			sb.append("#").append(tc + 1).append(" ");
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			Cell[][] map = new Cell[N][N];
			Queue<Node> queue = new LinkedList<>();
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				queue.offer(new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
			}
			
			int[] dr = {0, -1, 1, 0, 0};
			int[] dc = {0, 0, 0, -1, 1};
			
			int time = 0;
			while(time < M) {
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						map[i][j] = new Cell(0, 0, 0);
					}
				}
				
				while (!queue.isEmpty()) {
					Node node = queue.poll();
					node.row += dr[node.dir];
					node.col += dc[node.dir];
					if(node.row == 0) {
						node.num /= 2;
						node.dir = 2;
					} else if(node.row == N - 1) {
						node.num /= 2;
						node.dir = 1;
					} else if(node.col == 0) {
						node.num /= 2;
						node.dir = 4;
					} else if(node.col == N - 1) {
						node.num /= 2;
						node.dir = 3;
					}
					
					Cell cell = map[node.row][node.col];
					cell.total += node.num;
					if(cell.max < node.num) {
						cell.max = node.num;
						cell.dir = node.dir;
					}
				}
				
				if(++time < M) {
					for (int i = 0; i < N; i++) {
						for (int j = 0; j < N; j++) {
							Cell cell = map[i][j];
							queue.offer(new Node(i, j, cell.total, cell.dir));
						}
					}
				}
			}
			
			int result = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					result += map[i][j].total;
				}
			}
			
			sb.append(result).append("\n");
		}
		System.out.println(sb.toString());
	}

	public static class Cell {
		int total;
		int dir;
		int max;
		public Cell(int total, int dir, int max) {
			this.total = total;
			this.dir = dir;
			this.max = max;
		}
	}
	
	public static class Node {
		int row;
		int col;
		int num;
		int dir;
		public Node(int row, int col, int num, int dir) {
			this.row = row;
			this.col = col;
			this.num = num;
			this.dir = dir;
		}
	}
}
