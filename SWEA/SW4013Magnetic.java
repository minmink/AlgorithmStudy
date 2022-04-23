package algorithm;

import java.io.*;
import java.util.*;

public class SW4013Magnetic {
	static int[][] magnetic;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tc = 0; tc < testCase; tc++) {
			sb.append("#").append(tc + 1).append(" ");
			magnetic = new int[4][8];
			int K = Integer.parseInt(br.readLine());
			for (int i = 0; i < 4; i++) {
				String s = br.readLine();
				for (int j = 0; j < 8; j++) {
					magnetic[i][j] = s.charAt(j * 2) - '0';
				}
			}
			for (int i = 0; i < K; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				spin(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()));
			}
			int answer = 0;
			for (int i = 0; i < 4; i++) {
				if(magnetic[i][0] == 1)
					answer += Math.pow(2, i);
			}
			sb.append(answer).append("\n");
		}
		System.out.println(sb.toString());
	}

	public static void spin(int num, int dir) {
		int[] spinDir = new int[4];
		Arrays.fill(spinDir, 0);
		spinDir[num] = dir;
		for (int i = 1; i <= 3; i++) {
			if(num - i >= 0 && magnetic[num - i + 1][6] != magnetic[num - i][2] && spinDir[num - i + 1] != 0)
				spinDir[num - i] = spinDir[num - i + 1] * -1;
			if(num + i < 4 && magnetic[num + i - 1][2] != magnetic[num + i][6] && spinDir[num + i - 1] != 0)
				spinDir[num + i] = spinDir[num + i - 1] * -1;
		}
		
		for (int i = 0; i < 4; i++) {
			if(spinDir[i] == 0)
				continue;
			else if(spinDir[i] == -1) {
				int temp = magnetic[i][0];
				for (int j = 1; j < 8; j++) {
					magnetic[i][j - 1] = magnetic[i][j];
				}
				magnetic[i][7] = temp;
			} else {
				int temp = magnetic[i][7];
				for (int j = 6; j >= 0; j--) {
					magnetic[i][j + 1] = magnetic[i][j];
				}
				magnetic[i][0] = temp;
			}
		}
	}
}
