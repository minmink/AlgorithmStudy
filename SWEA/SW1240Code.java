package algorithm;

import java.io.*;
import java.util.*;

public class SW1240Code {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		int answer = 0;
		String s = "";
		
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			sb.append("#").append(tc).append(" ");
			
			boolean flag = false;
			for (int cd = 0; cd < N; cd++) {
				if(flag)
				{
					br.readLine();
					continue;
				}
				else
					s = br.readLine();
				
				for (int i = 0; i < M; i++) {
					if(s.charAt(i) - '0' == 1) {
						flag = true;
						break;
					}
					
				}
			}
			
			int[] code = null;
			check:
			for (int start = 0; start <= M - 55; start++) {
				code = new int[8];
				
				for (int i = start; i < start + 56; i += 7) {
					int[] num = new int[4];
					int idx = -1;
					int pre = -1;
					for (int j = i; j < i + 7; j++) {
						int cur = s.charAt(j) - '0';
						if(cur == pre)
							num[idx]++;
						else {
							pre = cur;
							idx++;
							if(idx == 4)
								continue check;
							num[idx]++;
						}
						if(num[idx] > 4)
							continue check;
					}
					
					if(num[0] == 1)
						if(num[1] == 1)
							if(num[2] == 1)
								code[(i - start) / 7] = 6;
							else
								code[(i - start) / 7] = 4;
						else if(num[1] == 2)
							if(num[2] == 1)
								code[(i - start) / 7] = 8;
							else
								code[(i - start) / 7] = 5;
						else if(num[1] == 3)
							code[(i - start) / 7] = 7;
						else
							code[(i - start) / 7] = 3;
					else if(num[0] == 2)
						if(num[1] == 1)
							code[(i - start) / 7] = 2;
						else
							code[(i - start) / 7] = 1;
					else
						if(num[1] == 1)
							code[(i - start) / 7] = 9;
						else
							code[(i - start) / 7] = 0;
				}
				
				break check;
			}
			
			int sum = 0;
			int sumOdd = 0;
			int sumEven = 0;
			for (int i = 0; i < 8; i++) {
				sum += code[i];
				if(i % 2 == 0)
					sumOdd += code[i];
				else
					sumEven += code[i];
			}
			if((sumOdd * 3 + sumEven) % 10 == 0)
				answer = sum;
			else
				answer = 0;
			
			sb.append(answer).append("\n");
		}
		
		System.out.print(sb.toString());
	}

}
