package algorithm;

import java.util.Arrays;
import java.util.Scanner;

public class BJ1463To1 {

	static int dp[], X;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		X = sc.nextInt();
		dp = new int[X+1];
//		Arrays.fill(dp, Integer.MAX_VALUE);
//		dp[X] = 0;
//		for (int i = X; i >= 2; i--) {
//			if(i%3 == 0 && i/3 >= 1 && dp[i/3] > dp[i]+1) {
//				dp[i/3] = dp[i]+1;
//			}
//			if(i%2 == 0 && i/2 >= 1 && dp[i/2] > dp[i]+1) {
//				dp[i/2] = dp[i]+1;
//			}
//			if(dp[i-1] > dp[i]+1) {
//				dp[i-1] = dp[i]+1;
//			}
//		}
//		System.out.println(dp[1]);
		
		for (int i = 2; i <= X; i++) {
			int min = dp[i-1] + 1;
			if(i%2 == 0) min = Math.min(dp[i/2]+1, min);
			if(i%3 == 0) min = Math.min(dp[i/3]+1, min);
			dp[i] = min;
		}
		System.out.println(dp[X]);
	}

}
