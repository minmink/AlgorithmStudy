package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class JO1021Toy {

	private static int[][] ingredients;
	private static boolean[] flag; // true : 중간부품, false : 기본부품
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // 완제품 번호
		int M = Integer.parseInt(br.readLine());
		ingredients = new int[N+1][N+1];
		int X, Y, K;
		flag = new boolean[N+1];
		
		for (int i = 0; i < M; i++) {
			// X Y K : 완제품 X를 만드는 데  Y가 K개 필요
			StringTokenizer st = new StringTokenizer(br.readLine());
			X = Integer.parseInt(st.nextToken());
			Y = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			make(X, Y, K);
		}
		
		for (int i = 1; i < N && !flag[i]; i++) {
			System.out.printf("%d %d\n", i, ingredients[i][0]);
		}
	}
	
	public static void make(int X, int Y, int K) {
		flag[X] = true;
		if(!flag[X] || ingredients[X][Y] == 0) {
			ingredients[X][Y] += K;			
		}
		if(flag[Y]) {
			for (int i = 0; i < flag.length; i++) {
				if(ingredients[Y][i] != 0) {
					make(Y, i, K * ingredients[Y][i]);
				}
			}
		} else {
			ingredients[Y][0] += K;
			return;
		}
	}

}

