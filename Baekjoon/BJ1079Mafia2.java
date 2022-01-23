package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ1079Mafia2 {
	static int N, R[][], factor[], EJ, max = 0, remain;
	static boolean isDead[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		N = Integer.parseInt(s);
		factor = new int[N];
		R = new int[N][N];
		remain = N - 1;
		isDead = new boolean[N];
		s = br.readLine();
		StringTokenizer st = new StringTokenizer(s, " ");
		for (int i = 0; i < N; i++) {
			factor[i] = Integer.parseInt(st.nextToken());
		}
		for (int i = 0; i < N; i++) {
			s = br.readLine();
			st = new StringTokenizer(s, " ");
			for (int j = 0; j < N; j++) {
				R[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		s = br.readLine();
		EJ = Integer.parseInt(s);
		
		if (N % 2 == 0 && remain != 0) {
			night(1);
		} else if (remain != 0) {
			noon(0);
		}
		
		System.out.println(max);
	}

	public static void night(int cnt) {
		for (int i = 0; i < N; i++) {
			if (isDead[i] || i == EJ)
				continue;
			isDead[i] = true;
			remain--;
			for (int j = 0; j < N; j++) {
				factor[j] += R[i][j];
			}
			if (remain == 0) {
				if (max < cnt)
					max = cnt;
				isDead[i] = false;
				remain++;
				for (int j = 0; j < N; j++) {
					factor[j] -= R[i][j];
				}
				continue;
			}
			noon(cnt);
			isDead[i] = false;
			remain++;
			for (int j = 0; j < N; j++) {
				factor[j] -= R[i][j];
			}
		}
	}
	
	public static void noon(int cnt) {
		int maxFactor = 0;
		int dead = 0;
		for (int i = 0; i < N; i++) {
			if (isDead[i])
				continue;
			if (factor[i] > maxFactor) {
				maxFactor = factor[i];
				dead = i;
			}
		}
		
		if (dead == EJ) {
			if (max < cnt)
				max = cnt;
			return;
		}
		
		isDead[dead] = true;
		night(cnt + 1);
		isDead[dead] = false;
		return;
	}
}
