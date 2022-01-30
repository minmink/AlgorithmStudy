package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ14891Gear {
	static int[][] gear = new int[4][8];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s;
		for (int i = 0; i < 4; i++) {
			s = br.readLine();
			for (int j = 0; j < 8; j++) {
				gear[i][j] = s.charAt(j) - '0';
			}
		}
		s = br.readLine();
		int K = Integer.parseInt(s);
		for (int k = 0; k < K; k++) {
			s = br.readLine();
			StringTokenizer st = new StringTokenizer(s, " ");
			int num = Integer.parseInt(st.nextToken()) - 1;
			int dir = Integer.parseInt(st.nextToken());
			int[] dirs = new int[4];
			dirs[num] = dir;
			// 왼쪽
			for (int i = 1; i < 4; i++) {
				if (num - i < 0 || gear[num - i][2] == gear[num - i + 1][6])
					break;
				dirs[num - i] = dirs[num - i + 1] * -1;
			}
			// 오른쪽
			for (int i = 1; i < 4; i++) {
				if (num + i >= 4 || gear[num + i][6] == gear[num + i - 1][2])
					break;
				dirs[num + i] = dirs[num + i - 1] * -1;
			}
			
			for (int i = 0; i < 4; i++) {
				if (dirs[i] == 0)
					continue;
				if (dirs[i] == -1) {
					// 반시계
					int temp = gear[i][0];
					for (int j = 1; j < 8; j++) {
						gear[i][j - 1] = gear[i][j];
					}
					gear[i][7] = temp;
				} else {
					// 시계
					int temp = gear[i][7];
					for (int j = 6; j >= 0; j--) {
						gear[i][j + 1] = gear[i][j];
					}
					gear[i][0] = temp;
				}
			}
		}
		
		int answer = 0;
		if (gear[0][0] == 1)
			answer += 1;
		if (gear[1][0] == 1)
			answer += 2;
		if (gear[2][0] == 1)
			answer += 4;
		if (gear[3][0] == 1)
			answer += 8;
		
		System.out.println(answer);
	}
}
