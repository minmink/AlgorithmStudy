package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class SW1953Criminal {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		for (int testCase = 1; testCase <= T; testCase++) {
			st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int R = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			int L = Integer.parseInt(st.nextToken());
			boolean[][] visited = new boolean[N][M];
			int[][] map = new int[N][M];
			for (int i = 0; i < N; i++) {
				String s = br.readLine();
				for (int j = 0; j < M; j++) {
					map[i][j] = s.charAt(j*2)-'0';
				}
			}
			int cnt = 1;
			int time = 1;
			Queue<int[]> q = new ArrayDeque<int[]>();
			q.offer(new int[] {R, C});
			visited[R][C] = true;
			while(time++<L && !q.isEmpty()) {
				int size = q.size();
				for (int i = 0; i < size; i++) {
					int[] cur = q.poll();
					switch(map[cur[0]][cur[1]]) {
					case 1:
						if(cur[0]-1>=0 && !visited[cur[0]-1][cur[1]] && (map[cur[0]-1][cur[1]]==1 || map[cur[0]-1][cur[1]]==2 || map[cur[0]-1][cur[1]]==5 || map[cur[0]-1][cur[1]]==6)) {
							visited[cur[0]-1][cur[1]] = true;
							q.offer(new int[] {cur[0]-1, cur[1]});
							cnt++;
						}
						if(cur[0]+1<N && !visited[cur[0]+1][cur[1]] && (map[cur[0]+1][cur[1]]==1 || map[cur[0]+1][cur[1]]==2 || map[cur[0]+1][cur[1]]==4 || map[cur[0]+1][cur[1]]==7)) {
							visited[cur[0]+1][cur[1]] = true;
							q.offer(new int[] {cur[0]+1, cur[1]});
							cnt++;
						}
						if(cur[1]-1>=0 && !visited[cur[0]][cur[1]-1] && (map[cur[0]][cur[1]-1]==1 || map[cur[0]][cur[1]-1]==3 || map[cur[0]][cur[1]-1]==4 || map[cur[0]][cur[1]-1]==5)) {
							visited[cur[0]][cur[1]-1] = true;
							q.offer(new int[] {cur[0], cur[1]-1});
							cnt++;
						}
						if(cur[1]+1<M && !visited[cur[0]][cur[1]+1] && (map[cur[0]][cur[1]+1]==1 || map[cur[0]][cur[1]+1]==3 || map[cur[0]][cur[1]+1]==6 || map[cur[0]][cur[1]+1]==7)) {
							visited[cur[0]][cur[1]+1] = true;
							q.offer(new int[] {cur[0], cur[1]+1});
							cnt++;
						}
						break;
					case 2:
						if(cur[0]-1>=0 && !visited[cur[0]-1][cur[1]] && (map[cur[0]-1][cur[1]]==1 || map[cur[0]-1][cur[1]]==2 || map[cur[0]-1][cur[1]]==5 || map[cur[0]-1][cur[1]]==6)) {
							visited[cur[0]-1][cur[1]] = true;
							q.offer(new int[] {cur[0]-1, cur[1]});
							cnt++;
						}
						if(cur[0]+1<N && !visited[cur[0]+1][cur[1]] && (map[cur[0]+1][cur[1]]==1 || map[cur[0]+1][cur[1]]==2 || map[cur[0]+1][cur[1]]==4 || map[cur[0]+1][cur[1]]==7)) {
							visited[cur[0]+1][cur[1]] = true;
							q.offer(new int[] {cur[0]+1, cur[1]});
							cnt++;
						}
						break;
					case 3:
						if(cur[1]-1>=0 && !visited[cur[0]][cur[1]-1] && (map[cur[0]][cur[1]-1]==1 || map[cur[0]][cur[1]-1]==3 || map[cur[0]][cur[1]-1]==4 || map[cur[0]][cur[1]-1]==5)) {
							visited[cur[0]][cur[1]-1] = true;
							q.offer(new int[] {cur[0], cur[1]-1});
							cnt++;
						}
						if(cur[1]+1<M && !visited[cur[0]][cur[1]+1] && (map[cur[0]][cur[1]+1]==1 || map[cur[0]][cur[1]+1]==3 || map[cur[0]][cur[1]+1]==6 || map[cur[0]][cur[1]+1]==7)) {
							visited[cur[0]][cur[1]+1] = true;
							q.offer(new int[] {cur[0], cur[1]+1});
							cnt++;
						}
						break;
					case 4:
						if(cur[0]-1>=0 && !visited[cur[0]-1][cur[1]] && (map[cur[0]-1][cur[1]]==1 || map[cur[0]-1][cur[1]]==2 || map[cur[0]-1][cur[1]]==5 || map[cur[0]-1][cur[1]]==6)) {
							visited[cur[0]-1][cur[1]] = true;
							q.offer(new int[] {cur[0]-1, cur[1]});
							cnt++;
						}
						if(cur[1]+1<M && !visited[cur[0]][cur[1]+1] && (map[cur[0]][cur[1]+1]==1 || map[cur[0]][cur[1]+1]==3 || map[cur[0]][cur[1]+1]==6 || map[cur[0]][cur[1]+1]==7)) {
							visited[cur[0]][cur[1]+1] = true;
							q.offer(new int[] {cur[0], cur[1]+1});
							cnt++;
						}
						break;
					case 5:
						if(cur[0]+1<N && !visited[cur[0]+1][cur[1]] && (map[cur[0]+1][cur[1]]==1 || map[cur[0]+1][cur[1]]==2 || map[cur[0]+1][cur[1]]==4 || map[cur[0]+1][cur[1]]==7)) {
							visited[cur[0]+1][cur[1]] = true;
							q.offer(new int[] {cur[0]+1, cur[1]});
							cnt++;
						}
						if(cur[1]+1<M && !visited[cur[0]][cur[1]+1] && (map[cur[0]][cur[1]+1]==1 || map[cur[0]][cur[1]+1]==3 || map[cur[0]][cur[1]+1]==6 || map[cur[0]][cur[1]+1]==7)) {
							visited[cur[0]][cur[1]+1] = true;
							q.offer(new int[] {cur[0], cur[1]+1});
							cnt++;
						}
						break;
					case 6:
						if(cur[0]+1<N && !visited[cur[0]+1][cur[1]] && (map[cur[0]+1][cur[1]]==1 || map[cur[0]+1][cur[1]]==2 || map[cur[0]+1][cur[1]]==4 || map[cur[0]+1][cur[1]]==7)) {
							visited[cur[0]+1][cur[1]] = true;
							q.offer(new int[] {cur[0]+1, cur[1]});
							cnt++;
						}
						if(cur[1]-1>=0 && !visited[cur[0]][cur[1]-1] && (map[cur[0]][cur[1]-1]==1 || map[cur[0]][cur[1]-1]==3 || map[cur[0]][cur[1]-1]==4 || map[cur[0]][cur[1]-1]==5)) {
							visited[cur[0]][cur[1]-1] = true;
							q.offer(new int[] {cur[0], cur[1]-1});
							cnt++;
						}
						break;
					case 7:
						if(cur[0]-1>=0 && !visited[cur[0]-1][cur[1]] && (map[cur[0]-1][cur[1]]==1 || map[cur[0]-1][cur[1]]==2 || map[cur[0]-1][cur[1]]==5 || map[cur[0]-1][cur[1]]==6)) {
							visited[cur[0]-1][cur[1]] = true;
							q.offer(new int[] {cur[0]-1, cur[1]});
							cnt++;
						}
						if(cur[1]-1>=0 && !visited[cur[0]][cur[1]-1] && (map[cur[0]][cur[1]-1]==1 || map[cur[0]][cur[1]-1]==3 || map[cur[0]][cur[1]-1]==4 || map[cur[0]][cur[1]-1]==5)) {
							visited[cur[0]][cur[1]-1] = true;
							q.offer(new int[] {cur[0], cur[1]-1});
							cnt++;
						}
						break;
					}
				}
			}
			sb.append("#").append(testCase).append(" ").append(cnt).append("\n");
		}
		System.out.println(sb.toString());
	}

}
