package algorithm;

import java.io.*;
import java.util.*;

public class SW5648Simulation {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tc = 0; tc < testCase; tc++) {
			sb.append("#").append(tc + 1).append(" ");
			int N = Integer.parseInt(br.readLine());
			Queue<Atom> queue = new LinkedList<Atom>();
			int[][] map = new int[4001][4001];
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				int x = Integer.parseInt(st.nextToken()) * 2 + 2000;
				int y = Integer.parseInt(st.nextToken()) * 2 + 2000;
				int dir = Integer.parseInt(st.nextToken());
				int energy = Integer.parseInt(st.nextToken());
				queue.offer(new Atom(x, y, dir, energy));
				map[x][y] = energy;
			}
			
			int[] dx = {0, 0, -1, 1};
			int[] dy = {1, -1, 0, 0};
			int answer = 0;
			while (!queue.isEmpty()) {
				if(queue.size() <= 1)
					break;
				Atom atom = queue.poll();
				if(map[atom.x][atom.y] != atom.energy) {
					answer += map[atom.x][atom.y];
					map[atom.x][atom.y] = 0;
					continue;
				}
				
				int nx = atom.x + dx[atom.dir];
				int ny = atom.y + dy[atom.dir];
				
				if(nx >= 0 && nx <= 4000 && ny >= 0 && ny <= 4000) {
					if(map[nx][ny] == 0) {
						map[nx][ny] = atom.energy;
						queue.offer(new Atom(nx, ny, atom.dir, atom.energy));
					} else {
						map[nx][ny] += atom.energy;
					}
				}
				map[atom.x][atom.y] = 0;
			}
			sb.append(answer).append("\n");
		}
		System.out.println(sb.toString());
	}

	public static class Atom {
		int x;
		int y;
		int dir;
		int energy;
		public Atom(int x, int y, int dir, int energy) {
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.energy = energy;
		}
	}
}
