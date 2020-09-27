package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW3307LIS {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for (int testCase = 1; testCase <= T; testCase++) {
			int N = Integer.parseInt(br.readLine());
			int[] arr = new int[N];
			int[] lis = new int[N];
			
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			int max = 0;
			lis[0] = 1;
			for (int i = 1; i < N; i++) {
				lis[i] = 1;
				for (int j = 0; j < i; j++) {
					if(arr[i] > arr[j] && lis[i] < lis[j]+1) {
						lis[i] = lis[j] + 1;
					}
				}
				if(max < lis[i]) max = lis[i];
			}
			
			sb.append("#").append(testCase).append(" ").append(max).append("\n");
		}
		
		System.out.println(sb);
	}

}
