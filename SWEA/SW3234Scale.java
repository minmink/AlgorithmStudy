package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW3234Scale {

	static int num, N;
	static int[] weights;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int testCase = 1; testCase <= T; testCase++) {
			sb.append("#").append(testCase).append(" ");
			num = 0;
			N = br.readLine().charAt(0) - '0';
			weights = new int[N];
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				weights[i] = Integer.parseInt(st.nextToken());
			}
			
			func(0, 0, 0);
			
			sb.append(num).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static void func(int step, int left, int right) {
		if(step == N) {
			num++;
			return;
		}
		for (int i = step; i < N; i++) {
			// step <-> i
			swap(step, i);
			
			func(step+1, left+weights[step], right);
			if(left>=right+weights[step]) func(step+1, left, right+weights[step]);
			
			// i <-> step
			swap(step, i);
		}
	}

	private static void swap(int i, int j) {
		int temp = weights[j];
		weights[j] = weights[i];
		weights[i] = temp;
	}
	
}
