package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ3190Snake {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		int N = Integer.parseInt(s);
		s = br.readLine();
		int K = Integer.parseInt(s);
		int[][] apple = new int[N][N];
		StringTokenizer st;
		for (int k = 0; k < K; k++) {
			s = br.readLine();
			st = new StringTokenizer(s, " ");
			apple[Integer.parseInt(st.nextToken()) - 1][Integer.parseInt(st.nextToken()) - 1] = 1;
		}
		apple[0][0] = -1;
		int[] dr = {0, 1, 0, -1};
		int[] dc = {1, 0, -1, 0};
		int dir = 0, sec = 1, row = 0, col = 0;
		s = br.readLine();
		int L = Integer.parseInt(s);
		Queue<int[]> queue = new LinkedList<>();
		queue.offer(new int[] {0, 0});
		for (int l = 0; l < L; l++) {
			s = br.readLine();
			st = new StringTokenizer(s, " ");
			int x = Integer.parseInt(st.nextToken());
			
			while (sec <= x) {
				row += dr[dir];
				col += dc[dir];
				if (row < 0 || row >= N || col < 0 || col >= N || apple[row][col] == -1) {
					System.out.println(sec);
					return;
				}
				if (apple[row][col] == 0) {
					int[] temp = queue.poll();
					apple[temp[0]][temp[1]] = 0;
				}
				apple[row][col] = -1;
				queue.offer(new int[] {row, col});
				sec++;
			}
			
			if (st.nextToken().charAt(0) == 'L')
				dir = (dir + 3) % 4;
			else
				dir = (dir + 1) % 4;
		}
		
		while (true) {
			row += dr[dir];
			col += dc[dir];
			if (row < 0 || row >= N || col < 0 || col >= N || apple[row][col] == -1) {
				System.out.println(sec);
				return;
			}
			if (apple[row][col] == 0) {
				int[] temp = queue.poll();
				apple[temp[0]][temp[1]] = 0;
			}
			apple[row][col] = -1;
			queue.offer(new int[] {row, col});
			sec++;
		}
	}

}
