package algorithm;

import java.io.*;
import java.util.*;

public class BJ1039Exchange {
	private static int max = 0, K, num[], M = 6, total = 0;
	private static Set<Node> set = new HashSet<Node>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		while(true) {
			if ((int) (N / Math.pow(10, M - 1)) > 0)
				break;
			M--;
		}
		num = new int[M];
		for (int i = 0; i < M; i++) {
			num[i] = (int) (N / Math.pow(10, M - i - 1));
			N %= Math.pow(10, M - i - 1);
		}
		K = Integer.parseInt(st.nextToken());
		calc(0);
		if (total == 0)
			System.out.println(-1);
		else
			System.out.println(max);
	}

	private static void calc(int cnt) {
		if (cnt == K) {
			max = Math.max(max, toNumber());
			total++;
			return;
		}
		for (int i = 0; i < M - 1; i++) {
			for (int j = i + 1; j < M; j++) {
				if (i == 0 && num[j] == 0)
					continue;
				int temp = num[i];
				num[i] = num[j];
				num[j] = temp;
				if (set.add(new Node(cnt + 1, toNumber())))
						calc(cnt + 1);
				temp = num[i];
				num[i] = num[j];
				num[j] = temp;
			}
		}
	}
	
	private static int toNumber() {
		String res = "";
		for (int i = 0; i < M; i++) {
			res += num[i];
		}
		return Integer.parseInt(res);
	}
	
	private static class Node {
		int cnt;
		int number;
		public Node(int cnt, int number) {
			this.cnt = cnt;
			this.number = number;
		}
		@Override
		public boolean equals(Object obj) {
			Node temp = (Node) obj;
			if (temp.cnt == this.cnt && temp.number == this.number)
				return true;
			return false;
		}
		@Override
		public int hashCode() {
		    return Objects.hash(cnt, number);
		}
	}
}
