package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ2263Tree {

	static int N;
	static int[] in, indexes, post;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		in = new int[N+1]; post = new int[N+1]; indexes = new int[N+1];
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= N; i++) {
			in[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= N; i++) {
			post[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 1; i <= N; i++) {
			indexes[in[i]] = i;
		}
		
		getPreOrder(1, N, 1, N);
		System.out.println(sb.toString());
	}

	private static void getPreOrder(int inStart, int inEnd, int postStart, int postEnd) {
		if(inStart>inEnd || postStart>postEnd) return;
		
		int root = post[postEnd];
		sb.append(root+" ");
		
		int rootIndex = indexes[root];
		int left = rootIndex - inStart;
		
		getPreOrder(inStart, rootIndex-1, postStart, postStart+left-1);
		getPreOrder(rootIndex+1, inEnd, postStart+left, postEnd-1);
	}

}
