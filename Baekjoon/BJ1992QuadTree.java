package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ1992QuadTree {

	static int flag;
	static int[][] map;
	static StringBuilder sb;
	static boolean isSame = true;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			if(i==0) flag = s.charAt(0)-'0';
			for (int j = 0; j < N; j++) {
				map[i][j] = s.charAt(j)-'0';
				if(map[i][j] != flag) isSame = false;
			}
		}
		if(isSame) System.out.println(flag);
		else {
			sb = new StringBuilder();
			sb.append("(");
			compress(0, 0, N/2, N/2);
			compress(0, N/2, N/2, N);
			compress(N/2, 0, N, N/2);
			compress(N/2, N/2, N, N);
			sb.append(")");
			System.out.println(sb.toString());
		}
	}

	private static void compress(int rstart, int cstart, int rend, int cend) {
		if(rstart==rend) {
			sb.append(map[rstart][cstart]);
			return;
		}
		isSame = true;
		flag = map[rstart][cstart];
f:		for (int i = rstart; i < rend; i++) {
			for (int j = cstart; j < cend; j++) {
				if(flag != map[i][j]) {
					isSame = false;
					break f;
				}
			}
		}
		if(isSame) sb.append(flag);
		else {
			sb.append("(");
			compress(rstart, cstart, (rend+rstart)/2, (cend+cstart)/2);
			compress(rstart, (cend+cstart)/2, (rend+rstart)/2, cend);
			compress((rend+rstart)/2, cstart, rend, (cend+cstart)/2);
			compress((rend+rstart)/2, (cend+cstart)/2, rend, cend);
			sb.append(")");
		}
	}

}
