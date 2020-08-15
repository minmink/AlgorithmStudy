package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class SW1218괄호 {

	static Stack<String> stack;
	static int result;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		for (int test_case = 1; test_case <= 10; test_case++) {
			result = 1;
			stack = new Stack<>();
			int N = Integer.parseInt(br.readLine());
			String s = br.readLine();
search:		for (int i = 0; i < N; i++) {
				switch(s.charAt(i)) {
				case '(':
					stack.push("(");
					break;
				case '{':
					stack.push("{");
					break;
				case '[':
					stack.push("[");
					break;
				case '<':
					stack.push("<");
					break;
				case ')':
					if(!stack.isEmpty() && stack.peek().equals("(")) {
						stack.pop();
						break;
					} else {
						result = 0;
						break search;
					}
				case '}':
					if(!stack.isEmpty() && stack.peek().equals("{")) {
						stack.pop();
						break;
					} else {
						result = 0;
						break search;
					}
				case ']':
					if(!stack.isEmpty() && stack.peek().equals("[")) {
						stack.pop();
						break;
					} else {
						result = 0;
						break search;
					}
				case '>':
					if(!stack.isEmpty() && stack.peek().equals("<")) {
						stack.pop();
						break;
					} else {
						result = 0;
						break search;
					}
				}
			}
			
			sb.append('#').append(test_case).append(' ').append(result).append("\n");
		}
		System.out.print(sb.toString());
	}

}
