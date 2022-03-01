package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ10942Palindrome {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		boolean[][] dp = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			int front = i;
			int rear = i;
			while(true) {
				if (front < 0 || rear >= N)
					break;
				if(arr[front] == arr[rear])
					dp[front--][rear++] = true;
				else
					break;
			}
			front = i;
			rear = i + 1;
			while(true) {
				if (front < 0 || rear >= N)
					break;
				if(arr[front] == arr[rear])
					dp[front--][rear++] = true;
				else
					break;
			}
		}
		int M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			if (dp[Integer.parseInt(st.nextToken()) - 1][Integer.parseInt(st.nextToken()) - 1])
				sb.append(1 + "\n");
			else
				sb.append(0 + "\n");
		}
		System.out.print(sb.toString());
	}
}
