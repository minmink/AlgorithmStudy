package algorithm;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ121002048 {

	static int N;
	static int max = 2;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		if (N == 1) {
			System.out.println(map[0][0]);
			return;
		}
		
		dfs(map, 0);
		
		System.out.println(max);
	}
	
	public static void dfs(int[][] tempMap, int cnt) {
		System.out.println("cnt : " + cnt);
		if (cnt == 5) {
			// 최댓값 검사
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (tempMap[i][j] > max)
						max = tempMap[i][j];
				}
			}
			System.out.println("max : " + max);
			return;
		}
		for (int i = 0; i < 4; i++) {
			int[][] nextMap;
			switch(i) {
			case 0:
				nextMap = left(tempMap);
				dfs(nextMap, cnt + 1);
				break;
			case 1:
				nextMap = right(tempMap);
				dfs(nextMap, cnt + 1);
				break;
			case 2:
				nextMap = up(tempMap);
				dfs(nextMap, cnt + 1);
				break;
			case 3:
				nextMap = down(tempMap);
				dfs(nextMap, cnt + 1);
				break;
			}
			
			
		}
	}
	
	public static int[][] left(int[][] varMap) {
		int[][] resMap = new int[N][N];
		
		// 왼쪽에서부터 이동
		for (int row = 0; row < N; row++) {
			boolean[] isMerged = new boolean[N];
			int idx = 0;
			for (int col = 0; col < N; col++) {
				int cur = varMap[row][col];
				if (cur == 0)
					continue;
				else if (idx == 0 && resMap[row][0] == 0) {
					resMap[row][idx] = cur;
				} else if (resMap[row][idx] == cur && !isMerged[idx]) {
					resMap[row][idx] = cur * 2;
					isMerged[idx] = true;
				} else {
					idx++;
					resMap[row][idx] = cur;
				}
			}
		}
		System.out.println("left");
		for (int i = 0; i < N; i++) {
			System.out.println(Arrays.toString(resMap[i]));
		}
		return resMap;
	}
	
	public static int[][] right(int[][] varMap) {
		int[][] resMap = new int[N][N];
		
		// 오른쪽에서부터 이동
		for (int row = 0; row < N; row++) {
			boolean[] isMerged = new boolean[N];
			int idx = N-1;
			for (int col = N-1; col >= 0; col--) {
				int cur = varMap[row][col];
				if (cur == 0)
					continue;
				else if (resMap[row][N-1] == 0 && idx == N-1) {
					resMap[row][idx] = cur;
				} else if (cur == resMap[row][idx] && !isMerged[idx]) {
					resMap[row][idx] = cur * 2;
					isMerged[idx] = true;
				} else {
					idx--;
					resMap[row][idx] = cur;
				}
			}
		}
		System.out.println("right");
		for (int i = 0; i < N; i++) {
			System.out.println(Arrays.toString(resMap[i]));
		}
		
		return resMap;
	}
	
	public static int[][] up(int[][] varMap) {
		int[][] resMap = new int[N][N];
		
		//위쪽에서부터 이동
		for (int col = 0; col < N; col++) {
			boolean[] isMerged = new boolean[N];
			int idx = 0;
			for (int row = 0; row < N; row++) {
				int cur = varMap[row][col];
				if (cur == 0)
					continue;
				else if (idx == 0 && resMap[idx][col] == 0) {
					resMap[idx][col] = cur;
				} else if (cur == resMap[idx][col] && !isMerged[idx]) {
					resMap[idx][col] = cur * 2;
					isMerged[idx] = true;
				} else {
					idx++;
					resMap[idx][col] = cur;
				}
			}
		}
		System.out.println("up");
		for (int i = 0; i < N; i++) {
			System.out.println(Arrays.toString(resMap[i]));
		}
		
		return resMap;
	}
	
	public static int[][] down(int[][] varMap) {
		int[][] resMap = new int[N][N];
		
		//아래쪽에서부터 이동
		for (int col = 0; col < N; col++) {
			boolean[] isMerged = new boolean[N];
			int idx = N-1;
			for (int row = N-1; row >= 0; row--) {
				int cur = varMap[row][col];
				if (cur == 0)
					continue;
				else if (idx == N-1 && resMap[N-1][col] == 0) {
					resMap[idx][col] = cur;
				} else if (cur == resMap[idx][col] && !isMerged[idx]) {
					resMap[idx][col] = cur * 2;
					isMerged[idx] = true;
				} else {
					idx--;
					resMap[idx][col] = cur;
				}
			}
		}
		System.out.println("down");
		for (int i = 0; i < N; i++) {
			System.out.println(Arrays.toString(resMap[i]));
		}
		
		return resMap;
	}

}
