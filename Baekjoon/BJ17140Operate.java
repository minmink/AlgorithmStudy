package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ17140Operate {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		StringTokenizer st = new StringTokenizer(s, " ");
		int r = Integer.parseInt(st.nextToken()) - 1;
		int c = Integer.parseInt(st.nextToken()) - 1;
		int rowNum = 3;
		int colNum = 3;
		int[] arr;
		PriorityQueue<Node> queue;
		int K = Integer.parseInt(st.nextToken());
		int[][] map = new int[100][100];
		for (int i = 0; i < 3; i++) {
			s = br.readLine();
			st = new StringTokenizer(s, " ");
			for (int j = 0; j < 3; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int cnt = 0;
		if (map[r][c] == K) {
			System.out.println(cnt);
			return;
		}
		while (cnt < 100) {
			if (rowNum >= colNum) {
				for (int row = 0; row < rowNum; row++) {
					arr = new int[101];
					queue = new PriorityQueue<>();
					for (int col = 0; col < colNum; col++) {
						arr[map[row][col]]++;
					}
					for (int i = 1; i < 101; i++) {
						if (arr[i] == 0)
							continue;
						queue.add(new Node(i, arr[i]));
					}
					if (queue.size() * 2 > 100)
						colNum = 100;
					else
						colNum = Math.max(colNum, queue.size() * 2);
					for (int col = 0; col < colNum; col++) {
						map[row][col] = 0;
						map[row][col + 1] = 0;
						if (queue.size() == 0)
							continue;
						Node cur = queue.poll();
						map[row][col] = cur.num;
						map[row][++col] = cur.cnt;
					}
				}
				cnt++;
				if (map[r][c] == K) {
					System.out.println(cnt);
					return;
				}
			}
			if (colNum > rowNum) {
				for (int col = 0; col < colNum; col++) {
					arr = new int[101];
					queue = new PriorityQueue<>();
					for (int row = 0; row < rowNum; row++) {
						arr[map[row][col]]++;
					}
					for (int i = 1; i < 101; i++) {
						if (arr[i] == 0)
							continue;
						queue.add(new Node(i, arr[i]));
					}
					if (queue.size() * 2 > 100)
						rowNum = 100;
					else
						rowNum = Math.max(rowNum, queue.size() * 2);
					for (int row = 0; row < rowNum; row++) {
						map[row][col] = 0;
						map[row + 1][col] = 0;
						if (queue.size() == 0)
							continue;
						Node cur = queue.poll();
						map[row][col] = cur.num;
						map[++row][col] = cur.cnt;
					}
				}
				cnt++;
				if (map[r][c] == K) {
					System.out.println(cnt);
					return;
				}
			}
		}
		System.out.println(-1);
	}

	public static class Node implements Comparable<Node> {
		int num;
		int cnt;
		public Node (int num, int cnt) {
			this.num = num;
			this.cnt = cnt;
		}
		@Override
		public int compareTo(Node o) {
			if (this.cnt == o.cnt)
				return this.num - o.num;
			return this.cnt - o.cnt;
		}
	}
}
