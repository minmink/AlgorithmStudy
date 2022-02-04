package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ20055Robot {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		StringTokenizer st = new StringTokenizer(s, " ");
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int cnt = 0;
		s = br.readLine();
		st = new StringTokenizer(s, " ");
		int[] arr = new int[2 * N];
		for (int i = 0; i < N * 2; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		boolean[] belt = new boolean[N * 2];
		int step = 1;
		while(true) {
			// 벨트 이동
			int temp = arr[2 * N - 1];
			boolean temp2 = belt[2 * N - 1];
			for (int i = 2 * N - 2; i >= 0; i--) {
				arr[i + 1] = arr[i];
				belt[i + 1] = belt[i];
			}
			if (belt[N - 1]) {
				belt[N - 1] = false;
			}
			arr[0] = temp;
			belt[0] = temp2;
			
			// 로봇 이동
			for (int i = 2 * N - 1; i >= 0; i--) {
				if (belt[i] && arr[(i + 1) % (2 * N)] > 0 && !belt[(i + 1) % (2 * N)]) {
					belt[i] = false;
					belt[(i + 1) % (2 * N)] = true;
					arr[(i + 1) % (2 * N)]--;
					if (arr[(i + 1) % (2 * N)] == 0)
						cnt++;
				}
			}
			if (belt[N - 1]) {
				belt[N - 1] = false;
			}
			
			// 로봇 올리기
			if (arr[0] > 0 && !belt[0]) {
				belt[0] = true;
				arr[0]--;
				if (arr[0] == 0)
					cnt++;
			}
			
			if (cnt >= K) {
				System.out.println(step);
				return;
			}
			
			step++;
		}
	}
}
