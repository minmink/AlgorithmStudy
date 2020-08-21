package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ17281Baseball {

	static int N;
	static char[][] results;
	static int max = 0;
	static int[] order = new int[9];
	static boolean[] isSelected = new boolean[10];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		results = new char[N][10];
		order[3] = 1;
		isSelected[1] = true;
		
		String s;
		for (int i = 0; i < N; i++) {
			s = br.readLine();
			for (int j = 1; j <= 9; j++) {
				results[i][j] = s.charAt((j-1)*2);
			}
		}
		
		makeOrder(0);
		System.out.println(max);
	}

	private static void makeOrder(int cnt) {
		if(cnt == 3) makeOrder(cnt+1);
		else if(cnt == 9) game();
		else {
			for (int i = 1; i <= 9; i++) {
				if(!isSelected[i]) {
					isSelected[i] = true;
					order[cnt] = i;
					makeOrder(cnt+1);
					isSelected[i] = false;
				}
			}
		}
	}

	private static void game() {
		int out, index = 0, score = 0;
		char result;
		for (int inning = 0; inning < N; inning++) {
			boolean[] ru = new boolean[3];
			out = 0;
			while(out<3) {
				result = results[inning][order[index]];
				switch(result) {
				case '0':
					out++;
					index = (index+1)%9;
					break;
				case '1':
					if(ru[2]) {
						ru[2] = false;
						score++;
					}
					if(ru[1]) {
						ru[1] = false;
						ru[2] = true;
					}
					if(ru[0]) {
						ru[0] = false;
						ru[1] = true;
					}
					ru[0] = true;
					index = (index+1)%9;
					break;
				case '2':
					if(ru[2]) {
						ru[2] = false;
						score++;
					}
					if(ru[1]) {
						ru[1] = false;
						score++;
					}
					if(ru[0]) {
						ru[0] = false;
						ru[2] = true;
					}
					ru[1] = true;
					index = (index+1)%9;
					break;
				case '3':
					if(ru[2]) {
						ru[2] = false;
						score++;
					}
					if(ru[1]) {
						ru[1] = false;
						score++;
					}
					if(ru[0]) {
						ru[0] = false;
						score++;
					}
					ru[2] = true;
					index = (index+1)%9;
					break;
				case '4':
					if(ru[2]) {
						ru[2] = false;
						score++;
					}
					if(ru[1]) {
						ru[1] = false;
						score++;
					}
					if(ru[0]) {
						ru[0] = false;
						score++;
					}
					score++;
					index = (index+1)%9;
					break;
				}
			}
		}
		if(max<score) max = score;
	}

}
