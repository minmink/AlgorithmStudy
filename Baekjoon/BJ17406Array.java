package algorithm;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ17406Array {

	// index 1부터 시작하기
	
	static int[][] map, rotates;
	static boolean[] flag; // rotates 순열 위한 flag
	static int N, M, K, min = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N+1][M+1];
		rotates = new int[K][3];
		flag = new boolean[K];
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 1; j <= M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			rotates[i] = new int[] {Integer.parseInt(st.nextToken()),
									Integer.parseInt(st.nextToken()),
									Integer.parseInt(st.nextToken())};
		}
		
		for (int i = 0; i < K; i++) {
			flag[i] = true;
			rotate(i);
			rerotate(i);
			flag[i] = false;
		}
		
		System.out.println(min);
	}

	private static void rotate(int i) {
		int rowStart = rotates[i][0] - rotates[i][2];
		int rowEnd = rotates[i][0] + rotates[i][2];
		int colStart = rotates[i][1] - rotates[i][2];
		int colEnd = rotates[i][1] + rotates[i][2];
		
		int past;
		int temp;
		while(rowStart != rowEnd) {
			past = map[rowStart+1][colStart];
			for (int c = colStart; c < colEnd; c++) {
				temp = map[rowStart][c];
				map[rowStart][c] = past;
				past = temp;
			}
			for (int r = rowStart; r < rowEnd; r++) {
				temp = map[r][colEnd];
				map[r][colEnd] = past;
				past = temp;
			}
			for (int c = colEnd; c > colStart; c--) {
				temp = map[rowEnd][c];
				map[rowEnd][c] = past;
				past = temp;
			}
			for (int r = rowEnd; r > rowStart; r--) {
				temp = map[r][colStart];
				map[r][colStart] = past;
				past = temp;
			}
			
			rowStart++; colStart++;
			rowEnd--; colEnd--;
		}
		
		boolean isChanged = true;
		for (int k = 0; k < K; k++) {
			if(!flag[k]) {
				isChanged = false;
				flag[k] = true;
				rotate(k);
				rerotate(k);
				flag[k] = false;
			}
		}
		if(isChanged) sum();
	}
	
	private static void rerotate(int i) {
		int rowStart = rotates[i][0] - rotates[i][2];
		int rowEnd = rotates[i][0] + rotates[i][2];
		int colStart = rotates[i][1] - rotates[i][2];
		int colEnd = rotates[i][1] + rotates[i][2];
		
		int past;
		int temp;
		while(rowStart != rowEnd) {
			past = map[rowStart][colStart+1];
			for (int r = rowStart; r < rowEnd; r++) {
				temp = map[r][colStart];
				map[r][colStart] = past;
				past = temp;
			}
			for (int c = colStart; c < colEnd; c++) {
				temp = map[rowEnd][c];
				map[rowEnd][c] = past;
				past = temp;
			}
			for (int r = rowEnd; r > rowStart; r--) {
				temp = map[r][colEnd];
				map[r][colEnd] = past;
				past = temp;
			}
			for (int c = colEnd; c > colStart; c--) {
				temp = map[rowStart][c];
				map[rowStart][c] = past;
				past = temp;
			}
			
			rowStart++; colStart++;
			rowEnd--; colEnd--;
		}
	}
	
	private static void sum() {
		for (int row = 1; row <= N; row++) {
			int sum = 0;
			for (int col = 1; col <= M; col++) {
				sum += map[row][col];
			}
			if(min > sum) min = sum;
		}
	}
}