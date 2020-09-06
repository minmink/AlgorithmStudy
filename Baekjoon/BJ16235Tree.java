package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ16235Tree {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[2] - o2[2];
			}
		});
		int[][] map = new int[N][N];
		for (int i = 0; i < N; i++) {
			Arrays.fill(map[i], 5);
		}
		
		int[][] add = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				add[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int cnt = 0;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			pq.offer(new int[] {Integer.parseInt(st.nextToken())-1,
					Integer.parseInt(st.nextToken())-1,
					Integer.parseInt(st.nextToken()), 1});
			cnt++;
		}
		
		int time = 0;
		int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1};
		int[] dc = {-1, 0, 1, -1, 1, -1, 0, 1};
		while(time++ < K && cnt > 0) {			
			ArrayList<int[]> trees = new ArrayList<>();
			while(!pq.isEmpty()) {
				int[] tree = pq.poll();
				if(map[tree[0]][tree[1]] >= tree[2]) {
					map[tree[0]][tree[1]] -= tree[2]++;
					trees.add(tree);
				} else {
					tree[3] = 0;
					trees.add(tree);
				}
			}
			
			for (int[] tree : trees) {
				if(tree[3] == 0) {
					map[tree[0]][tree[1]] += tree[2]/2;
					cnt--;
				} else if(tree[2] % 5 == 0) {
					// 인접한 8칸에 번식
					int nr, nc;
					for (int i = 0; i < 8; i++) {
						nr = tree[0]+dr[i]; nc = tree[1]+dc[i];
						if(0<=nr && nr<N && 0<=nc && nc<N) {
							pq.add(new int[] {nr, nc, 1, 1});
							cnt++;
						}
					}
					pq.add(tree);
				} else {
					pq.add(tree);
				}
			}
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					map[i][j] += add[i][j];
				}
			}
		}
		
		System.out.println(cnt);
	}

}
