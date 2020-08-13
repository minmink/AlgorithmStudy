package algorithm;

import java.util.Scanner;

public class JO1021Toy3 {

	private static int[][] map;
	private static int[][] result; // 메모이제이션 중복연산을 줄이기 위해, 연산한 결과를 저장
	
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
		result = new int[N+1][N+1];
		search(N); // 작업결과는 전역변수 result에 저장
		for (int i = 1; i < result.length; i++) {
			if(result[N][i] != 0) {
				System.out.println(i + " " + result[N][i]);
			}
		}
	}
	
	/** 제품을 만들기 위해 필요한 기본 부품의 배열을 계산해서 전역변수에 저장하는 메서드 
	 * 0번 0 : 계산 안 한 상태, -1 : 계산함(중간부품), -2 : 계산함(기본부품) */
	public static void search(int n) {
		if(result[n][0] < 0) return; // 계산을 이미 했다면
		
		result[n][0] = -2; // 계산함 표시(기본부품)
		for (int i = 1; i < map.length; i++) {
			if(map[n][i] != 0) { // 사용한 부품이 있다면, 기본부품은 아님
				search(i); // 재귀함수 호출, 하위 부품 탐색, result[i] 행에는 값이 완성되어 있음
				if(result[i][0] == -2) { // 기본부품이면
					result[n][i] += map[n][i];
				} else { // 중간부품이면
					for (int j = 0; j < map.length; j++) {
						result[n][j] += map[n][i] * result[i][j];
					}
				}
				result[n][0] = -1; // 계산함 표시(중간부품)
			}
		}
		return;
	}

}
