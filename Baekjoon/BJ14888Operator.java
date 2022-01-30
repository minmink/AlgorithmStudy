package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ14888Operator {
	static int N, arr[], op[]; 
	static long max = Long.MIN_VALUE, min = Long.MAX_VALUE, answer;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		N = Integer.parseInt(s);
		s = br.readLine();
		StringTokenizer st = new StringTokenizer(s, " ");
		arr = new int[N];
		op = new int[4];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		s = br.readLine();
		st = new StringTokenizer(s, " ");
		for (int i = 0; i < 4; i++) {
			op[i] = Integer.parseInt(st.nextToken());
		}
		answer = arr[0];
		operate(0);
		System.out.println(max);
		System.out.println(min);
	}

	public static void operate(int cnt) {
		if (cnt == N - 1) {
			if (answer > max)
				max = answer;
			if (answer < min)
				min = answer;
			return;
		}
		for (int i = 0; i < 4; i++) {
			if (op[i] == 0)
				continue;
			switch(i) {
			case 0:
				answer += arr[cnt + 1];
				op[0]--;
				operate(cnt + 1);
				op[0]++;
				answer -= arr[cnt + 1];
				break;
			case 1:
				answer -= arr[cnt + 1];
				op[1]--;
				operate(cnt + 1);
				op[1]++;
				answer += arr[cnt + 1];
				break;
			case 2:
				answer *= arr[cnt + 1];
				op[2]--;
				operate(cnt + 1);
				op[2]++;
				answer /= arr[cnt + 1];
				break;
			case 3:
				long temp = answer;
				answer /= arr[cnt + 1];
				op[3]--;
				operate(cnt + 1);
				op[3]++;
				answer = temp;
				break;
			}
		}
	}
}
