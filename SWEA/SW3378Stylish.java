package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SW3378Stylish {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		for (int testCase = 1; testCase <= T; testCase++) {
			sb.append("#").append(testCase).append(" ");
			st = new StringTokenizer(br.readLine(), " ");
			int p = Integer.parseInt(st.nextToken());
			int q = Integer.parseInt(st.nextToken());
			String[] master = new String[p];
			String[] me = new String[q];
			for (int i = 0; i < p; i++) {
				master[i] = br.readLine().trim();
			}
			for (int i = 0; i < q; i++) {
				me[i] = br.readLine().trim();
			}
			int[] result = new int[q];
			Arrays.fill(result, -2);
			
			for (int r = 1; r <= 20; r++) {
				for (int c = 1; c <= 20; c++) {
					for (int s = 1; s <= 20; s++) {
						if(isOk(master, r, c, s))
							calIndent(me, r, c, s, result);
					}
				}
			}
			for (int i = 0; i < q; i++) {
				sb.append(result[i]).append(" ");
			}
			sb.append("\n");
		}
		System.out.print(sb.toString());
	}

	static void calIndent(String[] text, int r, int c, int s, int[] result) {
		int rCnt = 0, cCnt = 0, sCnt = 0;
		for (int i = 0; i < text.length; i++) {
			if(result[i] == -2)
				result[i] = r*rCnt + c*cCnt + s*sCnt;
			else {
				if(r*rCnt + c*cCnt + s*sCnt != result[i])
					result[i] = -1;
			}
			
			for (char ch : text[i].toCharArray()) {
				switch(ch) {
				case '(':
					rCnt++;
					break;
				case ')':
					rCnt--;
					break;
				case '{':
					cCnt++;
					break;
				case '}':
					cCnt--;
					break;
				case '[':
					sCnt++;
					break;
				case ']':
					sCnt--;
					break;
				}
			}
		}
	}
	
	static boolean isOk(String[] text, int r, int c, int s) {
		int rCnt = 0, cCnt = 0, sCnt = 0;
		for (int i = 0; i < text.length; i++) {
			int cnt = 0;
			for (char ch : text[i].toCharArray()) {
				if(ch == '.')
					cnt++;
				else
					break;
			}
			
			if(cnt != r*rCnt + c*cCnt + s*sCnt)
				return false;
			
			for (char ch : text[i].toCharArray()) {
				switch(ch) {
				case '(':
					rCnt++;
					break;
				case ')':
					rCnt--;
					break;
				case '{':
					cCnt++;
					break;
				case '}':
					cCnt--;
					break;
				case '[':
					sCnt++;
					break;
				case ']':
					sCnt--;
					break;
				}
			}
		}
				
		return true;
	}
	
}
