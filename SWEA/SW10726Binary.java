package algorithm;

import java.io.*;
import java.util.*;

public class SW10726Binary {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			if(M == 0) {
				sb.append("OFF\n");
				continue;
			}
			
			int answer = 1;
			for (int i = 1; i < N; i++) {
				answer = (answer + (1 << i));
			}
			
			if((answer & M) == answer)
				sb.append("ON\n");
			else
				sb.append("OFF\n");
		}

		System.out.println(sb.toString());
	}
}
