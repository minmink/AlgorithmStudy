package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SW1225Password {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		Queue<Integer> nums;
		
		for (int test_case = 1; test_case < 11; test_case++) {
			nums = new LinkedList<Integer>();
			br.readLine();
			int diff = 0;
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 8; i++) {
				nums.offer(Integer.parseInt(st.nextToken()));
			}
			while(true) {
				diff = diff % 5 + 1;
				int num = nums.poll();
				int availableDiff = (num > diff)? diff : num;
				num -= availableDiff;
				nums.offer(num);
				if(num == 0) break;
				
			}
			
			sb.append('#').append(test_case).append(' ');
			for (int i = 0; i < 8; i++) {
				sb.append(nums.poll()).append(' ');
			}
			sb.append("\n");
		}
		System.out.print(sb.toString());
	}

}
