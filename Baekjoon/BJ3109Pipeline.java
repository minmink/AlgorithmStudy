package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ3109Pipeline {

	static char[][] map;
	static int N, M, max = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		for (int i = 0; i < N; i++) {			
			if(make(i, 0)) max++;
		}
		
		System.out.println(max);
	}

	private static boolean make(int row, int col) {
		if(col == M-1) return true;
		if(row-1>=0 && col+1<M && map[row-1][col+1]!='x') {
			map[row-1][col+1] = 'x';
			if(make(row-1, col+1)) return true;
		}
		if(col+1<M && map[row][col+1]!='x') {
			map[row][col+1] = 'x';
			if(make(row, col+1)) return true;
		}
		if(row+1<N && col+1<M && map[row+1][col+1]!='x') {
			map[row+1][col+1] = 'x';
			if(make(row+1, col+1)) return true;
		}
		return false;
	}
}
