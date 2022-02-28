package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ6087Laser {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		StringTokenizer st = new StringTokenizer(s, " ");
		int W = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());
		char[][] map = new char[H][W];
		int min = Integer.MAX_VALUE;
		int[] laser = new int[2];
		for (int i = 0; i < H; i++) {
			s = br.readLine();
			for (int j = 0; j < W; j++) {
				map[i][j] = s.charAt(j);
				if (map[i][j] == 'C')
					laser = new int[] {i, j};
			}
		}
		int[] dr = {0, 1, 0, -1};
		int[] dc = {1, 0, -1, 0};
		Queue<Node> queue = new LinkedList<Node>();
		int[][][] visited = new int[H][W][4];
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				for (int k = 0; k < 4; k++) {
					visited[i][j][k] = Integer.MAX_VALUE;
				}
			}
		}
		queue.offer(new Node(laser[0], laser[1], -1, 0));
		//visited[laser[0]][laser[1]] = true;
		while(!queue.isEmpty()) {
			Node cur = queue.poll();
			if (map[cur.r][cur.c] == 'C' && (cur.r != laser[0] || cur.c != laser[1])) {
				min = Math.min(min, cur.mirror);
				continue;
			}
			if (cur.mirror >= min)
				continue;
			for (int i = 0; i < 4; i++) {
				int nr = cur.r + dr[i];
				int nc = cur.c + dc[i];
				if (nr < 0 || nr >= H || nc < 0 || nc >= W || map[nr][nc] == '*')
					continue;
				int mirror = cur.mirror;
				if (cur.dir != -1 && cur.dir != i)
					mirror++;
				if (visited[nr][nc][i] > mirror) {
					visited[nr][nc][i] = mirror;
					queue.offer(new Node(nr, nc, i, mirror));
				}
			}
		}
		System.out.println(min);
	}

	public static class Node {
		int r;
		int c;
		int dir;
		int mirror;
		public Node(int r, int c, int dir, int mirror) {
			this.r = r;
			this.c = c;
			this.dir = dir;
			this.mirror = mirror;
		}
	}
}
