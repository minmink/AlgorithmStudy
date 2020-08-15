package algorithm;

import java.util.Arrays;
import java.util.Scanner;

public class SW1208Flatten {

	// 오름차순 정렬
	// [-1] 에서 [0]으로 1개 옮기기
	// 필요시 정렬
	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[] boxes = new int[100];
		
		for (int test_case = 1; test_case <= 10; test_case++) {
			int min = 100;
			int dump = sc.nextInt();	
			int sum = 0;
			for (int i = 0; i < 100; i++) {
				boxes[i] = sc.nextInt();
				sum += boxes[i];
			}
			
			int target = 1;
			if(sum % 100 == 0) {
				target = 0;
			}
			
			Arrays.sort(boxes);
			
			while(dump > 0) {
				if(boxes[99] - boxes[0] <= target) break;
				boxes[0]++;
				boxes[99]--;
				if (boxes[0] > boxes[1] || boxes[99] < boxes[98]) {
					Arrays.sort(boxes);
				}
				dump--;
			}
			
			min = boxes[99] - boxes[0];
			
			System.out.printf("#%d %d\n", test_case, min);
		}
	}

}
