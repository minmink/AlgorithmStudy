package algorithm;

import java.io.*;
import java.util.*;

public class SW1215Palindrome {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		for (int tc = 1; tc <= 10; tc++) {
			sb.append("#").append(tc);
			int N = Integer.parseInt(br.readLine());
			char[][] map = new char[8][];
			for (int i = 0; i < 8; i++) {
				map[i] = br.readLine().toCharArray();
			}
			int answer = 0;
			
			for (int row = 0; row < 8; row++) {
				for (int col = 0; col <= 8 - N; col++) {
					boolean flag = true;
					
					for (int i = 0; i < N / 2; i++) {
						if(map[row][col + i] != map[row][col + N - 1 - i]) {
							flag = false;
							break;
						}
					}
					
					if(flag)
						answer++;
				}
			}
			
			for (int col = 0; col < 8; col++) {
				for (int row = 0; row <= 8 - N; row++) {
					boolean flag = true;
					
					for (int i = 0; i < N / 2; i++) {
						if(map[row + i][col] != map[row + N - 1 - i][col]) {
							flag = false;
							break;
						}
					}
					
					if(flag)
						answer++;
				}
			}
			
			sb.append(" ").append(answer).append("\n");
		}
		
		System.out.print(sb.toString());
	}
}
