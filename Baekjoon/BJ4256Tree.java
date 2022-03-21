package algorithm;

import java.io.*;
import java.util.*;

public class BJ4256Tree {
	private static int N, idx;
	private static int[] pre, in, idxs;
	private static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int Test = Integer.parseInt(br.readLine());
		for (int testCase = 0; testCase < Test; testCase++) {
			N = Integer.parseInt(br.readLine());
			pre = new int[N];
			in = new int[N];
			idxs = new int[N + 1];
			idx = 1;
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				pre[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				int num = Integer.parseInt(st.nextToken());
				in[i] = num;
				idxs[num] = i;
			}
			find(pre[0], -1, N);
            sb.append("\n");
		}
		System.out.print(sb.toString());
	}

	private static void find(int num, int left, int right) {
		if (idx < N) {
			int node = pre[idx];
			// 다음 노드가 왼쪽  자식일 경우
			if (left < idxs[node] && idxs[node] < idxs[num]) {
				idx++;
				find(node, left, idxs[num]);
			}
		}
		if (idx < N) {
			int node = pre[idx];
			// 다음 노드가 오른쪽 자식일 경우
            if (idxs[num] < idxs[node] && idxs[node] < right) {
                idx++;
                find(node, idxs[num], right);
            }
		}
		sb.append(num + " ");
	}
}
