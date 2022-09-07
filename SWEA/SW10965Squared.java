package algorithm;

import java.io.*;

public class SW10965Squared {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		int MAX = 10000000;
		boolean[] noPrime = new boolean[MAX + 1];
		for (int i = 2; i <= Math.sqrt(MAX); i++) {
			if(noPrime[i])
				continue;
			
			for (int j = i * 2; j <= MAX; j += i) {
				noPrime[j] = true;
			}
		}
		
		for (int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");
			int A = Integer.parseInt(br.readLine());
			int answer = 1;
			int num = 0;
			
			for (int i = 2; i <= MAX; i++) {
				if(noPrime[i])
					continue;
				
				if(!noPrime[A]) {
					if(num % 2 == 0)
						answer *= A;
					else if(num % 2 == 1 && i != A) {
						answer *= i;
						answer *= A;
					}
					break;
				}
				
				if(A % i != 0) {
					if(num % 2 == 1)
						answer *= i;
					num = 0;
				} else if(A % i == 0) {
					num++;
					A /= i;
					i--;
				}
			}
			
			sb.append(answer).append("\n");
		}
		
		System.out.println(sb.toString());
	}
}
