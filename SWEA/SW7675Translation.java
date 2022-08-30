package algorithm;

import java.io.*;
import java.util.*;

public class SW7675Translation {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for (int tc = 1; tc <= testCase; tc++) {
			sb.append("#").append(tc);
			int N = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			
			for (int i = 0; i < N; i++) {
				int answer = 0;
				while(true) {
					String word = st.nextToken();
					boolean isName = true;
					if(word.charAt(0) < 'A' || word.charAt(0) > 'Z') {
						if(isFin(word))
							break;
						
						continue;
					}
					
					for (int j = 1; j < word.length(); j++) {
						if(j == word.length() - 1 && isFin(word))
							break;
						
						if(word.charAt(j) < 'a' || word.charAt(j) > 'z') {
							isName = false;
							break;
						}
					}
					
					if(isName)
						answer++;
					if(isFin(word))
						break;
				}				
				
				sb.append(" ").append(answer);
			}
			
			sb.append("\n");
		}
		
		System.out.print(sb.toString());
	}

	private static boolean isFin(String word) {
		if (word.charAt(word.length() - 1) == '.' || word.charAt(word.length() - 1) == '!' || word.charAt(word.length() - 1) == '?')
			return true;
		
		return false;
	}
}
