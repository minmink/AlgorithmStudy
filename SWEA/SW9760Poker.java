package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SW9760Poker {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for (int testCase = 1; testCase <= T; testCase++) {
			sb.append("#").append(testCase).append(" ");
			
			int[] suit = new int[4]; // S D H C
			int[] rank = new int[13]; // A 2 3 4 5 6 7 8 9 T J Q K
			char[] s = br.readLine().toCharArray();
			
			boolean sameSuit = true, sameRank = true;
			char preS = s[0], preR = s[1];
			for (int i = 0; i <= 12; i = i+3) {
				switch (s[i]) {
				case 'S':
					suit[0]++;
					if(sameSuit && preS != s[i]) sameSuit = false;
					break;
				case 'D':
					suit[1]++;
					if(sameSuit && preS != s[i]) sameSuit = false;
					break;
				case 'H':
					suit[2]++;
					if(sameSuit && preS != s[i]) sameSuit = false;
					break;
				case 'C':
					suit[3]++;
					if(sameSuit && preS != s[i]) sameSuit = false;
					break;
				}
				switch (s[i+1]) {
				case 'A':
					rank[0]++;
					if(sameRank && preR != s[i+1]) sameRank = false;
					break;
				case 'T':
					rank[9]++;
					if(sameRank && preR != s[i+1]) sameRank = false;
					break;
				case 'J':
					rank[10]++;
					if(sameRank && preR != s[i+1]) sameRank = false;
					break;
				case 'Q':
					rank[11]++;
					if(sameRank && preR != s[i+1]) sameRank = false;
					break;
				case 'K':
					rank[12]++;
					if(sameRank && preR != s[i+1]) sameRank = false;
					break;
				default:
					rank[s[i+1]-'1']++;
					if(sameRank && preR != s[i+1]) sameRank = false;
					break;
				}
			}
			
			boolean continuous = false;
			for (int i = 0; i < rank.length; i++) {
				if(i == 0 && rank[12] == 1) continue;
				if(rank[i] == 1) {
					continuous = true;
					for (int j = 0; j < 5; j++) {
						if(rank[(i+j) % 13] != 1) {
							continuous = false;
							break;
						}
					}
					break;
				}
			}
			
			if(sameSuit && continuous) {
				sb.append("Straight Flush\n");
				continue;
			}
			
			boolean four = false, three = false, two1 = false, two2 = false;
			for (int i = 0; i < 13; i++) {
				if(rank[i] == 4) {
					four = true;
					break;
				}
				if(rank[i] == 3) {
					three = true;
				}
				if(rank[i] == 2) {
					if(!two1) two1 = true;
					else two2 = true;
				}
			}
			if(four) {
				sb.append("Four of a Kind\n");
				continue;
			}
			
			if(three && two1) {
				sb.append("Full House\n");
				continue;
			}
			
			if(sameSuit) {
				sb.append("Flush\n");
				continue;
			}
			
			if(continuous) {
				sb.append("Straight\n");
				continue;
			}
			
			if(three) {
				sb.append("Three of a kind\n");
				continue;
			}
			
			if(two1 && two2) {
				sb.append("Two pair\n");
				continue;
			}
			
			if(two1) {
				sb.append("One pair\n");
				continue;
			}
			
			sb.append("High card\n");
		}
		
		System.out.println(sb);
	}

}
