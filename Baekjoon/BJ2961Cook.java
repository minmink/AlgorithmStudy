package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ2961Cook {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int n = (int) Math.pow(2, N) - 1;
		int[][] flavors = new int[n][2];
		StringTokenizer st;
		int min = Integer.MAX_VALUE;
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			flavors[i][0] = Integer.parseInt(st.nextToken());
			flavors[i][1] = Integer.parseInt(st.nextToken());
			if(Math.abs(flavors[i][0] - flavors[i][1]) < min) {
				min = Math.abs(flavors[i][0] - flavors[i][1]);
			}
			int index = i;
			
			for (int j = 0; j < index; j++) {
				flavors[++i][0] = flavors[j][0] * flavors[index][0];
				flavors[i][1] = flavors[j][1] + flavors[index][1];
				if(Math.abs(flavors[i][0] - flavors[i][1]) < min) {
					min = Math.abs(flavors[i][0] - flavors[i][1]);
				}
			}
		}
		
		System.out.println(min);
	}

}
