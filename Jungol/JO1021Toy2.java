package algorithm;

import java.util.Scanner;

public class JO1021Toy2 {

	private static int[][] map;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt(); // 3~100, 완제품
		int M = sc.nextInt(); // 3~100, 정보의 개수

		map = new int[N+1][N+1]; // 0번은 안씀
		// row 제품을 만드는데 col 부품이 필요한 개수를 저장할 배열
		for (int i = 0; i < M; i++) {
			int X = sc.nextInt(); // X를 만드는데 부품 Y가 K개 필요하
			int Y = sc.nextInt();
			int K = sc.nextInt();
			map[X][Y] = K;
		}
		int[] result = search(N);
		for (int i = 1; i < result.length; i++) {
			if(result[i] != 0) {
				System.out.println(i + " " + result[i]);
			}
		}
	}
	
	/** 제품을 만들기 위해 필요한 기본 부품의 배열을리턴하는 메서드 
	 * 0번 0 : 계산 안 한 상태, -1 : 계산함(중간부품), -2 : 계산함(기본부품) */
	public static int[] search(int n) {
		int[] result = new int[map.length + 1]; // N번째 제품을 만들기 위해 필요한 기본 부품의 배열
		result[0] = -2; // 계산함 표시(기본부품)
		for (int i = 1; i < map.length; i++) {
			if(map[n][i] != 0) { // 사용한 부품이 있다면, 기본부품은 아님
				int[] val = search(i); // 재귀함수 호출
				if(val[0] == -2) { // 기본부품이면
					result[i] += map[n][i];
				} else { // 중간부품이면
					for (int j = 0; j < val.length; j++) {
						result[j] += map[n][i] * val[j];
					}
				}
				result[0] = -1; // 계산함 표시(중간부품)
			}
		}
		
		
		return result;
	}

}
