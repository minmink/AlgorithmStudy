package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ14503Robot {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 세로
		int M = Integer.parseInt(st.nextToken()); // 가로
		int[][] map = new int[N][M];
		boolean[][] visited = new boolean[N][M];
		st = new StringTokenizer(br.readLine());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken()); // 0위 1오른 2아래 3왼
		visited[r][c] = true;
		int cnt = 1;
		
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = s.charAt(2*j) - '0';
			}
		}
		
		boolean flag = true;
		while(flag) {
			flag = false;
			// 현재 방향에서 왼쪽으로 4번 도는데 벽이 아니고 청소가 안된 부분이라면 break
f:			for (int i = 0; i < 4; i++) {
				switch(d) {
				case 0:
					if(c-1>=0 && !visited[r][c-1] && map[r][c-1] == 0) {
						visited[r][--c] = true;
						d = 3;
						flag = true;
						cnt++;
						break f;
					}
					d = 3;
					break;
				case 1:
					if(r-1>=0 && !visited[r-1][c] && map[r-1][c] == 0) {
						visited[--r][c] = true;
						d = 0;
						flag = true;
						cnt++;
						break f;
					}
					d = 0;
					break;
				case 2:
					if(c+1<M && !visited[r][c+1] && map[r][c+1] == 0) {
						visited[r][++c] = true;
						d = 1;
						flag = true;
						cnt++;
						break f;
					}
					d = 1;
					break;
				case 3:
					if(r+1<N && !visited[r+1][c] && map[r+1][c] == 0) {
						visited[++r][c] = true;
						d = 2;
						flag = true;
						cnt++;
						break f;
					}
					d = 2;
					break;
				}
			}
			// 만약 for 그대로 나오면 flag = false
			if(!flag) {
				switch(d) {
				case 0:
					if(r+1<N && map[r+1][c] == 0) {
						r++;
						flag = true;
					}
					break;
				case 1:
					if(c-1>=0 && map[r][c-1] == 0) {
						c--;
						flag = true;
					}
					break;
				case 2:
					if(r-1>=0 && map[r-1][c] == 0) {
						r--;
						flag = true;
					}
					break;
				case 3:
					if(c+1<M && map[r][c+1] == 0) {
						c++;
						flag = true;
					}
					break;
				}
			}
			// 뒤쪽으로 갈 수 있으면 flag = true
			// 후진 불가라면 flag = false
		}
		
		System.out.println(cnt);
	}

}
