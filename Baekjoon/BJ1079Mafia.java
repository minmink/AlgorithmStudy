package algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ1079Mafia {

	static int dp[], N, R[][], num, factor[];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		dp = new int[1<<(N-1)+1];
		Arrays.fill(dp, -100);
		factor = new int[N+1];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			factor[i] = Integer.parseInt(st.nextToken());
		}
		factor[N] = -100;
		R = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				R[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		num = Integer.parseInt(br.readLine());
		
		if(N == 1) {
			System.out.println(0);
			return;
		}
		
		if(N%2 == 0)
			System.out.println(func(0, 0));
		else {
			int max = 0;
			for (int i = 1; i < N; i++) {
				if(factor[i] > factor[max]) {
					max = i;
				}
			}
			factor[max] = -100;
			System.out.println(func(1<<max, 1));
		}
	}

	private static int func(int flag, int dead) {
		if(dp[flag] != -100)
			return dp[flag];
		
		dp[flag] = 0;
		
		// 밤
		for (int target = 0; target < N; target++) {
			if((flag&(1<<target))==1 || target == num) continue;
			
			for (int i = 0; i < N; i++) {
				factor[i] += R[target][i];
			}
			
			if(N-dead-1 == 1) {
				for (int i = 0; i < N; i++) {
					factor[i] -= R[target][i];
				}
				return dp[flag] = 1;
			}
			
			// 낮
			int max = N;
			for (int i = 0; i < N; i++) {
				if((flag&(1<<i))==0 && i!=target && factor[i] > factor[max]) {
					max = i;
				}
			}
			if(max == num) {
				for (int i = 0; i < N; i++) {
					factor[i] -= R[target][i];
				}
				continue;
			}
			
			dp[flag] = Math.max(func((flag|(1<<max))|(1<<target), dead+2)+1, dp[flag]);
			
			for (int i = 0; i < N; i++) {
				factor[i] -= R[target][i];
			}
		}
		
		return dp[flag];
	}

}
