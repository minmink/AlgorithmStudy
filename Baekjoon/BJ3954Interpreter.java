package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ3954Interpreter {
	static int m, c, i, arr[], bracket[], idxC, idxM, idxI;
	static String order, input;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(s);
		for (int testCase = 0; testCase < T; testCase++) {
			s = br.readLine();
			st = new StringTokenizer(s, " ");
			m = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			i = Integer.parseInt(st.nextToken());
			arr = new int[m];
			bracket = new int[c];
			int total = 0;
			order = br.readLine();
			Stack<Integer> stack = new Stack<>();
			for (int i = 0; i < c; i++) {
				if (order.charAt(i) == '[')
					stack.push(i);
				else if (order.charAt(i) == ']') {
					int temp = stack.pop();
					bracket[temp] = i;
					bracket[i] = temp;
				}
			}
			input = br.readLine();
			idxM = 0;
			idxI = 0;
			idxC = 0;
			while (idxC < c && total <= 50000000) {
				total++;
				action();
			}
			if (idxC == c) {
				sb.append("Terminates\n");
				continue;
			}
			int max = idxC;
			int min = idxC;
			while (total-- > 0) {
				action();
				max = Math.max(max, idxC);
				min = Math.min(min, idxC);
			}
			sb.append("Loops " + (min - 1) + " " + max + "\n");
		}
		System.out.println(sb.toString());
	}
	
	public static void action() {
		switch(order.charAt(idxC)) {
		case '-':
			arr[idxM] = arr[idxM] == 0 ? 255 : arr[idxM] - 1;
			break;
		case '+':
			arr[idxM] = arr[idxM] == 255 ? 0 : arr[idxM] + 1;
			break;
		case '<':
			idxM = (idxM - 1 + m) % m;
			break;
		case '>':
			idxM = (idxM + 1) % m;
			break;
		case '[':
			if (arr[idxM] == 0)
				idxC = bracket[idxC];
			break;
		case ']':
			if (arr[idxM] != 0)
				idxC = bracket[idxC];
			break;
		case ',':
			if (idxI < i)
				arr[idxM] = input.charAt(idxI);
			else
				arr[idxM] = 255;
			idxI++;
			break;
		}
		idxC++;
	}
}
