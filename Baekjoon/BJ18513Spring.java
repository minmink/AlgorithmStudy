package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ18513Spring {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		StringTokenizer st = new StringTokenizer(s, " ");
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		long[] spring = new long[N];
		Set<Integer> set = new HashSet<>();
		Queue<Integer> queue = new LinkedList<>();
		s = br.readLine();
		st = new StringTokenizer(s, " ");
		for (int i = 0; i < N; i++) {
			int idx = Integer.parseInt(st.nextToken());
			spring[i] = idx;
			set.add(idx);
			queue.offer(idx);
		}
		
		long min = 0;
		long d = 1;
		loop:
		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int n = 0; n < size; n++) {
				int cur = queue.poll();
				int left = cur - 1;
				int right = cur + 1;
				if (!set.contains(left)) {
					set.add(left);
					queue.offer(left);
					min += d;
					if (--K == 0)
						break loop;
				}
				if (!set.contains(right)) {
					set.add(right);
					queue.offer(right);
					min += d;
					if (--K == 0)
						break loop;
				}
			}
			d++;
		}
		
		System.out.println(min);
	}
}
