package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
//import java.util.Scanner;
import java.util.StringTokenizer;

public class SW1210Ladder {

	private static int[][] map;
	private static int tempX; // 이동하는 X
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		Scanner sc = new Scanner(System.in);
		for (int test_case = 1; test_case <= 10; test_case++) {
//			sc.nextInt();
			br.readLine();
			int answer = 0;
			map = new int[100][100];
			
			for (int i = 0; i < 100; i++) {
//				for (int j = 0; j < 100; j++) {
//					map[i][j] = sc.nextInt();
//				}
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < 100; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for (int x = 0; x < 100; x++) {
				if(map[0][x] == 0) continue;
				answer = x;
				tempX = x;
				for (int y = 1; y < 100; y++) {
					// 좌 탐색
					if(tempX-1>=0 && map[y][tempX-1] == 1) {
						goLeft(y);
						continue;
					}
					// 우 탐색
					if(tempX+1<100 && map[y][tempX+1] == 1) {
						goRight(y);
					}
				}
				if(map[99][tempX] == 2) {
					break;
				}
			}
			
			System.out.printf("#%d %d\n", test_case, answer);
		}

	}
	
	private static void goLeft(int y) {
		while(true) {
			if(tempX-1 < 0) break;
			if(map[y][tempX-1] == 1) {
				tempX--;
			} else {
				break;
			}
		}
	}
	
	private static void goRight(int y) {
		while(true) {
			if(tempX+1 >= 100) break;
			if(map[y][tempX+1] == 1) {
				tempX++;
			} else {
				break;
			}
		}
	}

}
