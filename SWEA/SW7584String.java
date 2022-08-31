package algorithm;

import java.io.*;
import java.util.*;

public class SW7584String {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int tc = Integer.parseInt(br.readLine());
		for (int testCase = 1; testCase <= tc; testCase++) {
			sb.append("#").append(testCase).append(" ");
			long K = Long.parseLong(br.readLine()) - 1;
			int answer = 0;
			
			while(K > 0) {
				if(K % 4 == 0)
					break;
				else if(K % 2 == 0) {
					answer = 1;
					break;
				} else
					K = (K - 1) / 2;
			}
			
			sb.append(answer).append("\n");
		}
		
		System.out.print(sb.toString());
	}
}
