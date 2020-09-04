package algorithm;

import java.util.Arrays;

public class P60061Kakao {

	static boolean[][] pillars, beams;
	
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
		int cnt = 0;
		pillars = new boolean[n+3][n+3];
		beams = new boolean[n+3][n+3];
		
		for (int[] frame : build_frame) {
			int x = frame[0] + 1;
			int y = frame[1] + 1;
			// 건설
			if(frame[3] == 1) {
				// 기둥
				if(frame[2] == 0 && canBuildPillar(x, y)) {
					pillars[x][y] = true;
					cnt++;
				}
				// 보
				else if (frame[2] == 1 && canBuildBeam(x, y)) {
					beams[x][y] = true;
					cnt++;
				}
			}
			// 삭제
			else {
				// 기둥
				if(frame[2] == 0) {
					pillars[x][y] = false;
					if(canDelete(x,y,0,n)) 
						cnt--;
					else
						pillars[x][y] = true;
				} else {
					beams[x][y] = false;
					if(canDelete(x,y,1,n))
						cnt--;
					else beams[x][y] = true;
				}
			}
		}
		
		int idx = 0;
		int[][] answer = new int[cnt][3];
		for (int i = 1; i <= n+1; i++) {
			for (int j = 1; j <= n+1; j++) {
				if(pillars[i][j]) answer[idx++] = new int[] {i-1, j-1, 0};
				if(beams[i][j]) answer[idx++] = new int[] {i-1, j-1, 1};
			}
		}
		
		return answer;
	}
	
	public static boolean canBuildPillar(int x, int y) {
		// 바닥이거나, 밑에 기둥이 있거나, 보 위거나..
		return y == 1 || pillars[x][y-1] || beams[x][y] || beams[x-1][y];
	}
	
	public static boolean canBuildBeam(int x, int y) {
		// 밑에 기둥이 있거나, 양쪽에 보가 있거나..
		return pillars[x][y-1] || pillars[x+1][y-1] || (beams[x-1][y] && beams[x+1][y]);
	}
	
	public static boolean canDelete(int x, int y, int type, int n) {
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				if(pillars[i][j] && !canBuildPillar(i, j))
					return false;
				if(beams[i][j] && !canBuildBeam(i, j))
					return false;
			}
		}
		return true;
	}
}
