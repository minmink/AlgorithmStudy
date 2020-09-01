package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ16916String {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] text = br.readLine().toCharArray();
		char[] pattern = br.readLine().toCharArray();
		int tLen = text.length, pLen = pattern.length;
		
		int[] fail = new int[pLen];
		for (int i=1, j=0; i < pLen; i++) {
			while(j>0 && pattern[i] != pattern[j])
				j = fail[j-1];
			if(pattern[i] == pattern[j])
				fail[i] = ++j;
		}
		
		for (int i=0, j=0; i < tLen; i++) {
			while(j>0 && text[i] != pattern[j])
				j = fail[j-1];
			
			if(text[i] == pattern[j]) {
				if(j == pLen-1) {
					System.out.println(1);
					return;
				} else
					j++;
			}
		}
		
		System.out.println(0);
	}

}
