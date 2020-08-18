package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BJ2493Top {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
//		int[][] map = new int[N+1][2];
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		Stack<Integer> building = new Stack<>();
		Stack<Integer> index = new Stack<>();
		int num = Integer.parseInt(st.nextToken());
		building.push(num);
		index.push(1);
		sb.append('0').append(' ');
		
//		for (int i = 1; i <= N; i++) {
//			map[i][0] = Integer.parseInt(st.nextToken());
//			for (int j = i-1; j >= index; j--) {
//				if(map[i][0] < map[j][0]) {
//					map[i][1] = j;
//					index = j;
//					break;
//				}
//			}
//			sb.append(map[i][1]).append(' ');
//		}
		boolean flag;
		for (int i = 2; i <= N; i++) {
			flag = false;
			num = Integer.parseInt(st.nextToken());
			while (!building.isEmpty()) {
				if (building.peek() <= num) {
					building.pop();
					index.pop();
				} else {
					sb.append(index.peek()).append(' ');
					building.push(num);
					index.push(i);
					flag = true;
					break;
				}
			}
			if(!flag) {
				building.push(num);
				index.push(i);
				sb.append('0').append(' ');
			}
		}
		
		System.out.println(sb.toString());
	}

}
