package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ13458Test {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		int N = Integer.parseInt(s);
		int[] place = new int[N];
		s = br.readLine();
		StringTokenizer st = new StringTokenizer(s, " ");
		for (int i = 0; i < N; i++) {
			place[i] = Integer.parseInt(st.nextToken());
		}
		s = br.readLine();
		st = new StringTokenizer(s, " ");
		int manager1 = Integer.parseInt(st.nextToken());
		int manager2 = Integer.parseInt(st.nextToken());
		int total = N;
		for (int i = 0; i < N; i++) {
			int temp = (place[i] - manager1) / manager2;
			if (temp < 0)
				continue;
			total += place[i] - manager1 - manager2 * temp == 0 ? temp : temp + 1;
		}
		System.out.println(total);
	}

}
