package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BJ15686Chicken {

	static int[] combi;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		char[][] map = new char[N][N];
		int min = Integer.MAX_VALUE;
		ArrayList<int[]> stores = new ArrayList<>();
		ArrayList<int[]> houses = new ArrayList<>();
		
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = s.charAt(j*2);
				if(map[i][j] == '1') houses.add(new int[] {i, j});
				if(map[i][j] == '2') stores.add(new int[] {i, j});
			}
		}
		
		combi = new int[stores.size()];
		for (int i = 1; i <= M; i++) {
			combi[combi.length-i] = 1;
		}
		
		do {
			int[] lengths = new int[houses.size()];
			
			for (int i = 0; i < combi.length; i++) {
				if(combi[i] == 1) {
					// 모든 집에서 이 치킨집과의 거리 구하기
					int[] s = stores.get(i);
					for (int idx = 0; idx < houses.size(); idx++) {
						int[] h = houses.get(idx);
						lengths[idx] = lengths[idx]==0?Math.abs(h[0]-s[0])+Math.abs(h[1]-s[1]):Math.min(lengths[idx], Math.abs(h[0]-s[0]) +Math.abs(h[1]-s[1]));
					}
				}
			}
			// 도시의 치킨거리 구하기
			int sum = 0;
			for (int i = 0; i < lengths.length; i++) {
				sum+=lengths[i];
			}
			if(min>sum) min = sum;
		} while(np());
		System.out.println(min);
	}

	private static boolean np() {
		int i = combi.length-1;
		while(i>0 && combi[i-1]>=combi[i]) --i;
		if(i==0) return false;
		
		int j = combi.length-1;
		while(combi[i-1]>=combi[j]) --j;
		
		int temp = combi[i-1];
		combi[i-1] = combi[j];
		combi[j] = temp;
		
		int k = combi.length-1;
		while(i<k) {
			temp = combi[k];
			combi[k] = combi[i];
			combi[i] = temp;
			i++; k--;
		}
		return true;
	}

}
