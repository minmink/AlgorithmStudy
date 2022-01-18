package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ23290MagicSharkFireball {
	static int N, M, K;
	static List<FireBall>[][] pre;
	static List<FireBall>[][] next;
	static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};
	
	public static class FireBall {
		int m, d, s;
		public FireBall (int m, int s, int d) {
			this.m = m;
			this.d = d;
			this.s = s;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		String s = br.readLine();
		st = new StringTokenizer(s, " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		pre = new ArrayList[N][N];
		next = new ArrayList[N][N];
		
		for (int i = 0; i < M; i++) {
			s = br.readLine();
			st = new StringTokenizer(s, " ");
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			if (pre[r][c] == null)
				pre[r][c] = new ArrayList<>();
			pre[r][c].add(new FireBall(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		
		for (int cnt = 0; cnt < K; cnt++) {
			// 이동 (pre -> next)
			move();
			
			// 같은 자리 합친 후 나누기
			act();
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					pre[i][j] = next[i][j];
				}
			}
			next = new ArrayList[N][N];
		}
		
		int sum = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (pre[i][j] == null)
					continue;
				
				for (int k = 0; k < pre[i][j].size(); k++) {
					sum += pre[i][j].get(k).m;
				}
			}
		}
		
		System.out.println(sum);
	}

	public static void move() {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (pre[r][c] == null || pre[r][c].size() == 0)
					continue;
				for (int k = 0; k < pre[r][c].size(); k++) {
					FireBall cur = pre[r][c].get(k);
					int nr = (r + dr[cur.d] * cur.s + 250 * N) % N;
					int nc = (c + dc[cur.d] * cur.s + 250 * N) % N;
					if (next[nr][nc] == null)
						next[nr][nc] = new ArrayList<>();
					next[nr][nc].add(cur);
				}
			}
		}
	}
	
	public static void act() {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (next[r][c] == null || next[r][c].size() <= 1)
					continue;
				
				int sumM = 0;
				int sumS = 0;
				int cnt = 0;
				int preD = next[r][c].get(0).d % 2;
				boolean isOdd = false;
				for (int k = 0; k < next[r][c].size(); k++) {
					FireBall cur = next[r][c].get(k);
					sumM += cur.m;
					sumS += cur.s;
					cnt++;
					if (preD != cur.d % 2)
						isOdd = true;
				}
				
				next[r][c] = null;
				
				if (sumM / 5 == 0)
					continue;
				
				next[r][c] = new ArrayList<>();
				
				if (isOdd) {
					next[r][c].add(new FireBall(sumM / 5, sumS / cnt, 1));
					next[r][c].add(new FireBall(sumM / 5, sumS / cnt, 3));
					next[r][c].add(new FireBall(sumM / 5, sumS / cnt, 5));
					next[r][c].add(new FireBall(sumM / 5, sumS / cnt, 7));
				} else {
					next[r][c].add(new FireBall(sumM / 5, sumS / cnt, 0));
					next[r][c].add(new FireBall(sumM / 5, sumS / cnt, 2));
					next[r][c].add(new FireBall(sumM / 5, sumS / cnt, 4));
					next[r][c].add(new FireBall(sumM / 5, sumS / cnt, 6));
				}
			}
		}
	}
}
