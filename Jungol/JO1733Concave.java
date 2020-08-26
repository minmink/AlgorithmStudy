package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class JO1733Concave {

	static char[][] map;
	static boolean[][] visited;
	static int win[] = new int[2];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new char[19][19];
		for (int i = 0; i < 19; i++) {
			String s = br.readLine();
			for (int j = 0; j < 19; j++) {
				map[i][j] = s.charAt(2*j);
			}
		}
		
		for (int i = 0; i < 19; i++) {
			for (int j = 0; j < 19; j++) {
				if(map[i][j] != '0') {
					if((j-1>=0 && map[i][j] != map[i][j-1])||j==0) check(i, j, 0);
					if(win[0] == 0 && ((i-1>=0 && j-1>=0 && map[i][j] != map[i-1][j-1])||(i==0 && j==0))) check(i, j, 1);
					if(win[0] == 0 && ((i-1>=0 && map[i][j] != map[i-1][j])||i==0)) check(i, j, 2);
					if(win[0] == 0 && ((i-1>=0 && j+1<19 && map[i][j] != map[i-1][j+1])||(i==0 && j==18))) check(i, j, 3);
					if(win[0] != 0) {
						System.out.println(win[0]);
						if(win[1] == 3) {
							System.out.println((i+5) + " " + (j-3));
						}
						else System.out.println((i+1) + " " + (j+1));
						return;
					}
				}
			}
		}
		
		System.out.println(0);
	}

	// 0오른 1:오른대각 2:아래 3:왼대각
	private static void check(int row, int col, int dir) {
		int cnt = 1;
		char flag = map[row][col];
		switch(dir) {
		case 0:
			while(++col<19) {
				if(map[row][col] != flag) break;
				cnt++;
			}
			if(cnt==5) {
				win[0] = flag-'0';
				win[1] = dir;
			}
			break;
		case 1:
			while(++row<19 && ++col<19) {
				if(map[row][col] != flag) break;
				cnt++;
			}
			if(cnt==5) {
				win[0] = flag-'0';
				win[1] = dir;
			}
			break;
		case 2:
			while(++row<19) {
				if(map[row][col] != flag) break;
				cnt++;
			}
			if(cnt==5) {
				win[0] = flag-'0';
				win[1] = dir;
			}
			break;
		case 3:
			while(++row<19 && --col>=0) {
				if(map[row][col] != flag) break;
				cnt++;
			}
			if(cnt==5) {
				win[0] = flag-'0';
				win[1] = dir;
			}
			break;
		}
	}

}
