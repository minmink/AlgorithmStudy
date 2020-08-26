package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ17136Paper {

	static char[][] map;
	static int min = Integer.MAX_VALUE;
	static int[] nums = {0, 5, 5, 5, 5, 5};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new char[10][10];
		for (int i = 0; i < 10; i++) {
			String s = br.readLine();
			for (int j = 0; j < 10; j++) {
				map[i][j] = s.charAt(2*j);
			}
		}
		
		dfs(0, 0);
		
		if(min == Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(min);
	}

	private static void dfs(int index, int cnt) {
		if(index == 100) {
			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 10; j++) {
					if(map[i][j] == '1') return;
				}
			}
			min = cnt;
			return;
		}
		if(cnt >= min) return;
		int r = index/10;
		int c = index%10;
		if(map[r][c]=='1') {
			for (int i = 5; i > 0; i--) {
				if(nums[i] > 0) {
					if(check(r, c, i)) {
						fill(r, c, i, '0');
						nums[i]--;
						dfs(index+1, cnt+1);
						fill(r, c, i, '1');
						nums[i]++;
					}
				}
			}
		} else {
			dfs(index+1, cnt);
		}
	}

	private static void fill(int row, int col, int size, char flag) {
		for (int i = row; i < row+size; i++) {
			for (int j = col; j < col+size; j++) {
				map[i][j] = flag;
			}
		}
	}

	private static boolean check(int row, int col, int size) {
		if(row+size > 10 || col+size > 10) return false;
		for (int i = row; i < row+size; i++) {
			for (int j = col; j < col+size; j++) {
				if(map[i][j]!='1') return false;
			}
		}
		return true;
	}
}
