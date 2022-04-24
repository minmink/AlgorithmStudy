package algorithm;

import java.io.*;
import java.util.*;

public class SW5653Cell3 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tc = 0; tc < testCase; tc++) {
			sb.append("#").append(tc + 1).append(" ");
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			Queue<Cell> queue = new LinkedList<Cell>();
			PriorityQueue<Cell> pq = new PriorityQueue<Cell>();
			HashSet<ArrayList<Integer>> locs = new HashSet<>();
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < M; j++) {
					int X = Integer.parseInt(st.nextToken());
					if(X == 0)
						continue;
					pq.offer(new Cell(i, j, X));
					ArrayList<Integer> loc = new ArrayList<>();
					loc.add(i);
					loc.add(j);
					locs.add(loc);
				}
			}
			int time = 0;
			int[] dr = {-1, 0, 1, 0};
			int[] dc = {0, 1, 0, -1};
			while(++time <= K) {
				int size = pq.size();
				for (int i = 0; i < size; i++) {
					queue.offer(pq.poll());
				}
				for (int i = 0; i < size; i++) {
					Cell cell = queue.poll();
					cell.nonActive--;
					cell.active--;
					if(cell.nonActive == 0) {
						cell.active = cell.X;
					}
					if(cell.active == cell.X - 1) {
						for (int dir = 0; dir < 4; dir++) {
							int nr = cell.row + dr[dir];
							int nc = cell.col + dc[dir];
							ArrayList<Integer> loc = new ArrayList<Integer>();
							loc.add(nr);
							loc.add(nc);
							if(!locs.contains(loc)) {
								locs.add(loc);
								pq.add(new Cell(nr, nc, cell.X));
							}
						}
					}
					if(cell.active != 0)
						pq.add(cell);
				}
			}
			sb.append(pq.size()).append("\n");
		}
		System.out.println(sb.toString());
	}

	public static class Cell implements Comparable<Cell> {
		int row;
		int col;
		int X;
		int nonActive;
		int active = 0;
		public Cell(int row, int col, int X) {
			this.row = row;
			this.col = col;
			this.X = X;
			nonActive = X;
		}
		@Override
		public int compareTo(Cell o) {
			return o.X - this.X;
		}
	}
}
