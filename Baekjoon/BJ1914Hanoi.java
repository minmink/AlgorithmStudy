package algorithm;

import java.math.BigInteger;
import java.util.Scanner;

public class BJ1914Hanoi {

	// 최소 이동 횟수
	// from to (N이 20 이하일 때)

	// P(n-1) -> Base -> P(n-1)
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
//		int total = 1;
//		
//		for (int i = 1; i < N; i++) {
//			total = total * 2 + 1;
//		}
//		System.out.println(total);

		BigInteger K = new BigInteger("2");
		K = K.pow(N).subtract(BigInteger.ONE);
		System.out.println(K);
		
		if(N <= 20) {
			move(1, 2, 3, N);			
		}
	}

	public static void move(int from, int x, int to, int n) {
		if(n == 1) {
			System.out.printf("%d %d\n", from, to);
			return;
		}
		move(from, to, x, n-1);
		System.out.printf("%d %d\n", from, to);
		move(x, from, to, n-1);
		return;
	}
}
