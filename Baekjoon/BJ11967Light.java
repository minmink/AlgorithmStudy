package algorithm;

import java.io.*;
import java.util.*;

public class BJ11967Light {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		ArrayList<int[]>[][] arr = new ArrayList[N][N];
		boolean[][] light = new boolean[N][N];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			if(arr[x][y] == null)
				arr[x][y] = new ArrayList<>();
			arr[x][y].add(new int[] {a, b});
		}
		int[] dr = new int[] {-1, 0, 1, 0};
		int[] dc = new int[] {0, 1, 0, -1};
		int cnt = 1;
		Queue<int[]> queue = new LinkedList<int[]>();
		boolean[][] visited = new boolean[N][N];
		light[0][0] = true;
		visited[0][0] = true;
		if(arr[0][0] == null) {
			System.out.println(0);
			return;
		}
		queue.offer(new int[] {0, 0});
		while(true) {
			boolean turnOn = false;
			boolean go = false;
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				int[] cur = queue.poll();
				if(arr[cur[0]][cur[1]] != null) {
					for (int[] node : arr[cur[0]][cur[1]]) {
						if(light[node[0]][node[1]])
							continue;
						light[node[0]][node[1]] = true;
						turnOn = true;
						cnt++;
					}
				}
				boolean remain = false;
				for (int dir = 0; dir < 4; dir++) {
					int nr = cur[0] + dr[dir];
					int nc = cur[1] + dc[dir];
					if(nr < 0 || nr >= N || nc < 0 || nc >= N || visited[nr][nc])
						continue;
					if(!light[nr][nc]) {
						remain = true;
						continue;
					}
					// 가야 되는 곳
					// queue에 넣기, visited true
					visited[nr][nc] = true;
					queue.offer(new int[] {nr, nc});
					go = true;
				}
				if(remain)
					queue.offer(cur);
			}
			if(!turnOn && !go)
				break;
		}
		System.out.println(cnt);
	}
}
