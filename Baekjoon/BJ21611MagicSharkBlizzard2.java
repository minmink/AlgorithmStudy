package algorithm;

import java.io.*;
import java.util.*;

public class BJ21611MagicSharkBlizzard2 {
	static int N, powN, map[], tempMap[][], num1 = 0, num2 = 0, num3 = 0;
	static int[] dr = {0, -1, 1, 0, 0};
	static int[] dc = {0, 0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		powN = (int) Math.pow(N, 2);
		int M = Integer.parseInt(st.nextToken());
		tempMap = new int[N][N];
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < N; j++) {
				tempMap[i][j] = s.charAt(j * 2) - '0';
			}
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int d = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			for (int dis = 1; dis <= s; dis++) {
				int nr = N / 2 + dr[d] * dis;
				int nc = N / 2 + dc[d] * dis;
				if(nr < 0 || nr >= N || nc < 0 || nc >= N)
					break;
				tempMap[nr][nc] = 0;
			}
			tempMapToMap();
			
			move();
			
			explode();
			
			change();
			
			if(i != M - 1) 
				mapToTempMap();
		}
		
		System.out.println(num1 + 2 * num2 + 3 * num3);
	}

	public static boolean move() {
		boolean result = false;
		int idx = 1;
		for (int i = 1; i < powN; i++) {
			if(map[i] > 0) {
				if(map[idx] == 0) {
					map[idx] = map[i];
					map[i] = 0;
					idx++;
				}
			} else if(map[idx] != 0)
				idx = i;
		}
		return result;
	}
	
	public static void change() {
		int[] temp = new int[powN];
		int writeIdx = 0;
		int readIdx = 1;
		int pre = map[1];
		int cnt = 1;
		while(true) {
			if(++readIdx >= powN) {
				if(++writeIdx >= powN)
					break;
				temp[writeIdx] = cnt;
				if(++writeIdx >= powN)
					break;
				temp[writeIdx] = pre;
				break;
			}
			if(map[readIdx] == pre && pre != 0) {
				cnt++;
				continue;
			}
			if(++writeIdx >= powN)
				break;
			temp[writeIdx] = cnt;
			if(++writeIdx >= powN)
				break;
			temp[writeIdx] = pre;
			pre = map[readIdx];
			if(pre == 0)
				cnt = 0;
			else
				cnt = 1;
		}
		map = temp;
	}
	
	public static void explode() {
		boolean success = false;
		int cnt = 1;
		int pre = map[1];
		for (int idx = 2; idx < powN; idx++) {
			if(pre == map[idx]) {
				cnt++;
				if(idx == powN - 1 && cnt >= 4 && pre != 0) {
					if(pre == 1)
						num1 += cnt;
					else if(pre == 2)
						num2 += cnt;
					else if(pre == 3)
						num3 += cnt;
					for (int i = 0; i < cnt; i++) {
						map[idx - i] = 0;
					}
				}
				continue;
			}
			if(cnt >= 4 && pre != 0) {
				success = true;
				if(pre == 1)
					num1 += cnt;
				else if(pre == 2)
					num2 += cnt;
				else if(pre == 3)
					num3 += cnt;
				for (int i = 1; i <= cnt; i++) {
					map[idx - i] = 0;
				}
			}
			cnt = 1;
			pre = map[idx];
		}
		
		if(success) {
			move();
			explode();
		}
	}
	
	public static void tempMapToMap() {
		map = new int[powN];
		int idx = 0;
		int temp = 1;
		int row = N / 2;
		int col = N / 2;
		first:
		while(true) {
			for (int i = 1; i <= temp; i++) {
				col--;
				if(col < 0)
					break first;
				map[++idx] = tempMap[row][col];
			}
			for (int i = 1; i <= temp; i++) {
				row++;
				if(row >= N)
					break first;
				map[++idx] = tempMap[row][col];
			}
			temp++;
			for (int i = 1; i <= temp; i++) {
				col++;
				if(col >= N)
					break first;
				map[++idx] = tempMap[row][col];
			}
			for (int i = 1; i <= temp; i++) {
				row--;
				if(row < 0)
					break first;
				map[++idx] = tempMap[row][col];
			}
			temp++;
		}
	}
	
	public static void mapToTempMap() {
		tempMap = new int[N][N];
		int idx = 0;
		int temp = 1;
		int row = N / 2;
		int col = N / 2;
		first:
		while(true) {
			for (int i = 1; i <= temp; i++) {
				col--;
				if(col < 0)
					break first;
				tempMap[row][col] = map[++idx];
			}
			for (int i = 1; i <= temp; i++) {
				row++;
				if(row >= N)
					break first;
				tempMap[row][col] = map[++idx];
			}
			temp++;
			for (int i = 1; i <= temp; i++) {
				col++;
				if(col >= N)
					break first;
				tempMap[row][col] = map[++idx];
			}
			for (int i = 1; i <= temp; i++) {
				row--;
				if(row < 0)
					break first;
				tempMap[row][col] = map[++idx];
			}
			temp++;
		}
	}
}
