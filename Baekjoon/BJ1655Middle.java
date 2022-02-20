package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class BJ1655Middle {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> small = new PriorityQueue<>(Collections.reverseOrder());
		PriorityQueue<Integer> big = new PriorityQueue<>();
		int num = 0;
		for (int i = 0; i < N; i++) {
			num++;
			int n = Integer.parseInt(br.readLine());
			if (num % 2 == 0) {
				if (small.peek() <= n)
					big.offer(n);
				else {
					big.offer(small.poll());
					small.offer(n);
				}
			} else {
				if (big.size() > 0 && big.peek() < n) {
					small.offer(big.poll());
					big.offer(n);
				} else
					small.offer(n);
			}
			System.out.println(small.peek());
		}
	}
}
