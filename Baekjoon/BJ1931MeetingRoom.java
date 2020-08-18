package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class BJ1931MeetingRoom {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		int[][] reservation = new int[N][2];
		int cnt = 1;
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			reservation[i][0] = Integer.parseInt(st.nextToken());
			reservation[i][1] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(reservation, new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				if(o1[1] == o2[1]) return o1[0]-o2[0];
				return o1[1]-o2[1];
			}
		});
		
		int start = reservation[0][1];
		for (int i = 1; i < reservation.length; i++) {
			if(reservation[i][0] >= start) {
				start = reservation[i][1];
				cnt++;
			}
		}
		
		System.out.println(cnt);
	}

}
