package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ17822Circle {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		StringTokenizer st = new StringTokenizer(s, " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[][] circle = new int[N][M];
		for (int i = 0; i < N; i++) {
			s = br.readLine();
			st = new StringTokenizer(s, " ");
			for (int j = 0; j < M; j++) {
				circle[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int rotate = 0; rotate < K; rotate++) {
			s = br.readLine();
			st = new StringTokenizer(s, " ");
			int x = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			ArrayList<int[]> removeArr = new ArrayList<>();
			
			for (int i = x - 1; i < N; i += x) {
				int[] tempCircle = new int[M];
				if (d == 1) { // 반시계방향회전
					for (int c = 0; c < M; c++) {
						tempCircle[(c - k + M) % M] = circle[i][c];
					}
				} else { // 시계방향회전
					for (int c = 0; c < M; c++) {
						tempCircle[(c + k) % M] = circle[i][c];
					}
				}
				circle[i] = Arrays.copyOf(tempCircle, M);
			}
			
			// 인접한 같은 수 제거
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (circle[i][j] != 0 && circle[i][j] == circle[i][(j + 1) % M]) {
						removeArr.add(new int[] {i, j});
						removeArr.add(new int[] {i, (j + 1) % M});
					}
					if (circle[i][j] != 0 && i != N - 1 && circle[i][j] == circle[i + 1][j]) {
						removeArr.add(new int[] {i, j});
						removeArr.add(new int[] {i + 1, j});
					}
				}
			}
			
			for (int[] node : removeArr) {
				circle[node[0]][node[1]] = 0;
			}
			
			// 없으면 원판 안에서 평균 구한 후 더하거나 빼기
			if (removeArr.size() == 0) {
				double sum = 0;
				double cnt = 0;
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < M; j++) {
						if (circle[i][j] == 0)
							continue;
						sum += circle[i][j];
						cnt++;
					}
				}
				
				double avg = sum / cnt;
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < M; j++) {
						if (circle[i][j] != 0 && circle[i][j] > avg)
							circle[i][j]--;
						else if (circle[i][j] != 0 && circle[i][j] < avg)
							circle[i][j]++;
					}
				}
			}
		}
		
		int sum = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				sum += circle[i][j];
			}
		}
		System.out.println(sum);
	}
}
