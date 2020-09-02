package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ17471Gerrymandering {
	
	static int N, min = Integer.MAX_VALUE;
	static int[] population, district;
	static boolean[][] tree;
	static boolean[] visited;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		tree = new boolean[N+1][N+1];
		population = new int[N+1];
		district = new int[N+1];
		int cnt0 = 0;
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		for (int i = 1; i <= N; i++) {
			population[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int num = Integer.parseInt(st.nextToken());
			if(num == 0) {
				cnt0++;
				continue;
			}
			for (int j = 0; j < num; j++) {
				tree[i][Integer.parseInt(st.nextToken())] = true;
			}
		}
		
		if(cnt0 == 2 && N == 2) {
			System.out.println(Math.abs(population[1] - population[2]));
			return;
		}
		if(cnt0>1) {
			System.out.println(-1);
			return;
		}
		
		dfs(1);
		
		System.out.println(min == Integer.MAX_VALUE? -1:min);
	}


	private static void dfs(int index) {
		if(index > N) {
			int sum1 = 0;
			int sum2 = 0;
			visited = new boolean[N+1];
			
			for (int i = 1; i <= N; i++) {
				if(district[i] == 1) sum1 += population[i];
				else sum2 += population[i];
			}
			
			int cnt = 0;
			for (int i = 1; i <= N; i++) {
				if(!visited[i]) {
					connect(i);
					cnt++;
				}
			}
			
			if(cnt==2) {
				if(min>Math.abs(sum1-sum2)) min = Math.abs(sum1-sum2);
			}
			
			return;
		}
		
		district[index] = 1;
		dfs(index+1);
		
		district[index] = 2;
		dfs(index+1);
		
	}


	private static void connect(int i) {
		visited[i] = true;
		for (int j = 1; j <= N; j++) {
			
			if(tree[i][j] && !visited[j] && district[j] == district[i]) connect(j);
		}
		
	}

}
