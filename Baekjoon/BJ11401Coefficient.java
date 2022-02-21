package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ11401Coefficient {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long N = Long.parseLong(st.nextToken());
		long K = Long.parseLong(st.nextToken());
		long A = 1L;
		for (long i = 1; i <= N; i++) {
			A = A * i % 1000000007;
		}
		long B = 1L;
		for (long i = 1; i <= K; i++) {
			B = B * i % 1000000007;
		}
		for (int i = 1; i <= N - K; i++) {
			B = B * i % 1000000007;
		}
		B = calc(B, 1000000005);
		long answer = (A * B) % 1000000007;
		System.out.println(answer);
	}
	
	public static long calc(long l, int num) {
		if (num == 1)
			return l;
		long ret = calc(l, num / 2);
		ret = ret * ret % 1000000007;
		if (num % 2 != 0)
			ret = ret * l % 1000000007;
		return ret;
	}
}
