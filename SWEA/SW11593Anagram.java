package algorithm;

import java.io.*;
import java.util.*;

public class SW11593Anagram {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		long[] factor = new long[20];
		int[] cnt = new int[26];
		factor[0] = 1;
		for (int i = 1; i < 20; i++) {
			factor[i] = factor[i - 1] * i;
		}
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");
			String s = br.readLine();
			cnt = new int[26];
			for (int i = 0; i < s.length(); i++) {
				cnt[s.charAt(i) - 'A']++;
			}
			
			long answer = 0;
			for (int i = 0; i < s.length(); i++) {
				for (int j = 0; j < s.charAt(i) - 'A'; j++) {
					long temp = 0;
					if(cnt[j] != 0) {
						cnt[j]--;
						temp = factor[s.length() - 1 - i];
						for (int k = 0; k < 26; k++) {
							temp /= factor[cnt[k]];
						}
						cnt[j]++;
					}
					answer += temp;
				}
				cnt[s.charAt(i) - 'A']--;
			}
			
			sb.append(answer).append("\n");
		}

		System.out.println(sb.toString());
	}
}
