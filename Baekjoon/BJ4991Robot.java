package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ4991Robot {
	private static int R, C, dr[] = {-1, 0, 1, 0}, dc[] = {0, 1, 0, -1}, min, edge[][];
	private static char[][] map;
	private static boolean[] permArr;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		while(true) {
			st = new StringTokenizer(br.readLine());
			C = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			if (R == 0 && C == 0)
				break;
			map = new char[R][C];
			int[] robot = new int[2];
			ArrayList<int[]> dust = new ArrayList<>();
			for (int i = 0; i < R; i++) {
				String s = br.readLine();
				for (int j = 0; j < C; j++) {
					map[i][j] = s.charAt(j);
					if (map[i][j] == 'o')
						robot = new int[] {i, j};
					else if (map[i][j] == '*')
						dust.add(new int[] {i, j});
				}
			}
			edge = new int[dust.size() + 1][dust.size() + 1];
			for (int i = 0; i < dust.size(); i++) {
				edge[0][i + 1] = edge[i + 1][0] = getDis(robot, dust.get(i));
			}
			for (int i = 0; i < dust.size() - 1; i++) {
				for (int j = i + 1; j < dust.size(); j++) {
					edge[i + 1][j + 1] = edge[j + 1][i + 1] = getDis(dust.get(i), dust.get(j));
				}
			}
			min = Integer.MAX_VALUE;
			permArr = new boolean[dust.size() + 1];
			perm(0, dust.size(), 0);
			if (min == Integer.MAX_VALUE)
				sb.append(-1 + "\n");
			else
				sb.append(min + "\n");
		}
		System.out.println(sb.toString());
	}
	
	private static int getDis(int[] node1, int[] node2) {
		int dis = 1;
		boolean[][][] visited = new boolean[R][C][4];
		Queue<int[]> queue = new LinkedList<int[]>();
		queue.offer(node1);
		while(!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				int[] cur = queue.poll();
				for (int dir = 0; dir < 4; dir++) {
					int nr = cur[0] + dr[dir];
					int nc = cur[1] + dc[dir];
					if (nr == node2[0] && nc == node2[1])
						return dis;
					if (nr < 0 || nr >= R || nc < 0 || nc >= C || visited[nr][nc][dir] || map[nr][nc] == 'x')
						continue;
					visited[nr][nc][dir] = true;
					queue.offer(new int[] {nr, nc});
				}
			}
			dis++;
		}
		return -1;
	}
	
	private static void perm(int node, int cnt, int sum) {
		if (cnt == 0) {
			min = Math.min(min, sum);
			return;
		}
		if (sum >= min)
			return;
		for (int i = 1; i < permArr.length; i++) {
			if (permArr[i] || edge[node][i] == -1)
				continue;
			permArr[i] = true;
			perm(i, cnt - 1, sum + edge[node][i]);
			permArr[i] = false;
		}
	}
}
