package algorithm;

import java.io.*;
import java.util.*;

public class SW5658Password {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tc = 0; tc < testCase; tc++) {
			sb.append("#").append(tc + 1).append(" ");
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			char[] numbers = br.readLine().toCharArray();
			int num = N / 4;
			ArrayList<Long> results = new ArrayList<>();
			for (int start = 0; start < num; start++) {
				for (int cnt = 0; cnt < 4; cnt++) {
					long result = 0;
					int idx = (start + num * cnt) % N;
					for (int i = 0; i < num; i++) {
						int number = numbers[(idx + i) % N] - '0';
						if(number < 0 || number > 9)
							number = numbers[(idx + i) % N] - 'A' + 10;
						result += Math.pow(16, num - i - 1) * number;
					}
					results.add(result);
				}
			}
			results.sort(Comparator.reverseOrder());
			if(K == 1) {
				sb.append(results.get(0)).append("\n");
				continue;
			}
			int answerIdx = 1;
			long pre = results.get(0);
			for (int i = 1; i < results.size(); i++) {
				if(pre == results.get(i))
					continue;
				answerIdx++;
				pre = results.get(i);
				if(answerIdx == K) {
					sb.append(results.get(i)).append("\n");
					break;
				}
			}
		}
		System.out.println(sb.toString());
	}

}
