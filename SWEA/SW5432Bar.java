package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SW5432Bar {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int testCase = 1; testCase <= T; testCase++) {
			int total = 0, temp = 0;
			char[] exp = br.readLine().toCharArray();
			for (int i = 0; i < exp.length; i++) {
				if(i != exp.length-1 && exp[i] == '(' && exp[i+1] == ')') {
					total += temp;
					i++;
				}
				else if(exp[i] == '(') {
					total++;
					temp++;
				}
				else {
					temp--;
				}
			}
			sb.append("#").append(testCase).append(" ").append(total).append("\n");
		}
		System.out.println(sb.toString());
	}
}
