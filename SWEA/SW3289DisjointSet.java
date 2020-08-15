package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW3289DisjointSet {

	static int[] parents;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		for (int testCase = 1; testCase <= T; testCase++) {
			sb.append("#").append(testCase).append(" ");
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			parents = new int[n+1];
			for (int i = 1; i <= n; i++) {
				parents[i] = i;
			}
			int a, b;
			
			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				switch(st.nextToken()) {				
				case "0": // union
					a = findSet(Integer.parseInt(st.nextToken()));
					b = findSet(Integer.parseInt(st.nextToken()));
					if(a == b) break;
					parents[b] = a;
					break;
				case "1": // 같은 집합인지 확인
					a = findSet(Integer.parseInt(st.nextToken()));
					b = findSet(Integer.parseInt(st.nextToken()));
					if(a == b) sb.append(1);
					else sb.append(0);
					break;
				}
			}
			sb.append("\n");
		}
		
		System.out.print(sb.toString());
	}

	public static int findSet(int x) {
		if(parents[x] != x) {
			parents[x] = findSet(parents[x]);
		}
		return parents[x];
	}
	
}
