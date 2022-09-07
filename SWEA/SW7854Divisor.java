package algorithm;

import java.io.*;
import java.util.*;

public class SW7854Divisor {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		TreeSet<Integer> set = new TreeSet<>();
		for (int i = 1; i < 10; i++) {
			int num = (int) Math.pow(10, i);
			for (int j = 1; j <= Math.sqrt(num); j++) {
				if(num % j != 0)
					continue;

				if((num / j) / (num / 10) > 0)
					set.add(num / j);
				if(j / (num / 10) > 0)
					set.add(j);
			}
		}
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");
			int X = Integer.parseInt(br.readLine());
			sb.append(set.headSet(X).size() + (set.contains(X)? 1 : 0)).append("\n");
		}
		
		System.out.println(sb.toString());
	}
}
