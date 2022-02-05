package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ17825Dice {
	static int dice[] = new int[10], horse[][] = new int[4][2], max = 0;
	static int[][] map = new int[][]
			{{0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36, 38, 40},
			{0, 13, 16, 19, 25, 30, 35, 40},
			{0, 22, 24, 25, 30, 35, 40},
			{0, 28, 27, 26, 25, 30, 35, 40}};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		for (int i = 0; i < 10; i++) {
			dice[i] = s.charAt(2 * i) - '0';
		}
		move(0, 0);
		System.out.println(max);
	}

	public static void move(int cnt, int sum) {
		if (cnt == 10) {
			max = Math.max(max, sum);
			return;
		}
		boolean is0 = false;
		loop:
		for (int i = 0; i < 4; i++) {
			if (horse[i][0] == -1 || (is0 && horse[i][0] == 0 && horse[i][1] == 0))
				continue;
			if (horse[i][0] == 0 && horse[i][1] == 0)
				is0 = true;
			// 이동
			int[] dest = horse[i].clone();
			if (dest[0] == 0 && dest[1] == 5) {
				dest = new int[] {1, 0};
			} else if (dest[0] == 0 && dest[1] == 10) {
				dest = new int[] {2, 0};
			} else if (dest[0] == 0 && dest[1] == 15) {
				dest = new int[] {3, 0};
			}
			
			dest[1] += dice[cnt];
			
			// 중복이면 패스
			if (map[dest[0]].length > dest[1]) {
				for (int n = 0; n < 4; n++) {
					if (i == n || horse[n][0] == -1)
						continue;
					if (horse[n][0] == dest[0] && horse[n][1] == dest[1])
						continue loop;
					if (horse[n][0] > 0 && dest[0] > 0 && map[horse[n][0]][horse[n][1]] == map[dest[0]][dest[1]])
						continue loop;
					if (map[horse[n][0]][horse[n][1]] == 40 && map[dest[0]][dest[1]] == 40)
						continue loop;
				}
			}
			
			int[] temp = new int[] {horse[i][0], horse[i][1]};
			if (map[dest[0]].length <= dest[1]) {
				horse[i][0] = -1;
				move(cnt + 1, sum);
			}
			else {				
				horse[i] = dest.clone();
				move(cnt + 1, sum + map[horse[i][0]][horse[i][1]]);
			}
			horse[i] = temp.clone();
		}
	}
}
