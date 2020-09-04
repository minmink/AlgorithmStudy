package algorithm;

import java.util.Arrays;

public class P60061Kakao2 {

	public static void main(String[] args) {
//		int[][] build_frame = {{1,0,0,1}, {1, 1, 1, 1}, {2,1,0,1}, {2,2,1,1},
//				{5,0,0,1}, {5,1,0,1}, {4,2,1,1}, {3,2,1,1}};
//		int[][] build_frame = {{0,0,0,1},{2,0,0,1},{4,0,0,1},{0,1,1,1},{1,1,1,1},
//				{2,1,1,1},{3,1,1,1},{2,0,0,0},{1,1,1,0},{2,2,0,1}};
		int[][] build_frame = {{1,0,0,1}, {1,1,0,1}, {1,1,1,1}, {1,1,0,0},{0,1,1,1},{1,0,0,0},{2,0,0,1},{2,0,0,0}};
		int[][] result = solution(2, build_frame);
		for (int i = 0; i < result.length; i++) {
			System.out.println(Arrays.toString(result[i]));			
		}
	}

	public static int[][] solution (int n, int[][] build_frame) {
		int[][] map = new int[n*2+1][n*2+1];
		for (int i = 0; i < map.length; i++) {
			Arrays.fill(map[i], -1);			
		}
		int cnt = 0;
		for (int i = 0; i < build_frame.length; i++) {
			int[] cur = build_frame[i];
			// 기둥
			if(cur[2] == 0) {
				// 삭제
				if(cur[3] == 0) {
					// 맨위거나 내 위에 기둥이 없어야 한다
					// 기둥 위에 보가 없거나 있다면 연결되어 있어야한다 
					if((cur[1] == n-1 || map[cur[0]*2][cur[1]*2+2] != 2 || map[cur[0]*2][cur[1]*2+2] != 0) &&
							(map[cur[0]*2][cur[1]*2+2] == -1 || (cur[0] != 0 && map[cur[0]*2-1][cur[1]*2+2] == 1 && cur[0] != n && map[cur[0]*2+2][cur[1]*2+2] >= 1))) {
						cnt--;
						map[cur[0]*2][cur[1]*2] = map[cur[0]*2][cur[1]*2] == 2 ? 1 : -1;
						map[cur[0]*2][cur[1]*2+1] = -1;
					}
				}
				// 설치
				if(cur[3] == 1) {
					// 맨 밑이거나 보의 한쪽 끝부분 위거나 밑에 다른 기둥이 있어야 한다
					if(cur[1] == 0 || map[cur[0]*2][cur[1]*2] == 1 || map[cur[0]*2-1][cur[1]*2] == 1 || map[cur[0]*2][cur[1]*2-1] == 0) {
						cnt++;
						map[cur[0]*2][cur[1]*2] = map[cur[0]*2][cur[1]*2] == 1 ? 2 : 0;
						map[cur[0]*2][cur[1]*2+1] = 0;
					}
				}
			}
			// 보
			else {
				// 삭제
				if(cur[3] == 0) {
					// 보 위에 기둥이 없거나 있다면 밑에 기둥이 있어야 한다
					// 왼쪽 맨 끝이거나 왼쪽에 보가 없거나 왼쪽에 있는 보가 기둥과 연결되어 있어야 한다
					// 오른쪽 맨 끝이거나 오른쪽에 보가 없거나 오른쪽에 있는 보가 기둥과 연결되어 있어야 한다
					if ((map[cur[0]*2][cur[1]*2] == 1 || map[cur[0]*2][cur[1]*2-1] == 0) &&
							((cur[0] == 0 || map[cur[0]*2-1][cur[1]*2] == -1 || map[cur[0]*2-2][cur[1]*2-1] == 0) &&
									(cur[0] == n-1 || map[cur[0]*2+2][cur[1]*2] == -1 || map[cur[0]*2+2][cur[1]*2-1] == 0))) {
						cnt--;
						map[cur[0]*2][cur[1]*2] = map[cur[0]*2][cur[1]*2] == 2 ? 0 : -1;
						map[cur[0]*2+1][cur[1]*2] = -1;
					}
				}
				// 설치
				if(cur[3] == 1) {
					// 왼쪽 밑에 기둥이 있거나 오른쪽 밑에 기둥이 있어야 한다
					// 양쪽이 동시에 다른 보와 연결되어있어야 한다
					if(map[cur[0]*2][cur[1]*2-1] == 0 || map[cur[0]*2+2][cur[1]*2-1] == 0 ||
							(cur[0] != 0 && map[cur[0]*2-1][cur[1]*2] == 1 && cur[0] != n-1 && map[cur[0]*2+2][cur[1]*2] > 0)) {
						cnt++;
						map[cur[0]*2][cur[1]*2] = map[cur[0]*2][cur[1]*2] == 0 ? 2 : 1;
						map[cur[0]*2+1][cur[1]*2] = 1;
					}
				}
			}
		}
		
		int[][] result = new int[cnt][3];
		int idx = 0;
		for (int i = 0; i <= 2*n; i += 2) {
			for (int j = 0; j <= 2*n; j += 2) {
				if(map[i][j] == 0)
					result[idx++] = new int[] {i/2, j/2, 0};
				else if (map[i][j] == 1)
					result[idx++] = new int[] {i/2, j/2, 1};
				else if (map[i][j] == 2) {
					result[idx++] = new int[] {i/2, j/2, 0};
					result[idx++] = new int[] {i/2, j/2, 1};
				}
			}
		}
		
		return result;
	}
	
}
