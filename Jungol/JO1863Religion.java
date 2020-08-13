package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class JO1863Religion {

	static int[] students;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()); // 학생 수
		int m = Integer.parseInt(st.nextToken()); // 쌍 수
		students = new int[n+1];
		int a, b;
		int cnt = n;
		
		for (int i = 1; i <= n; i++) {
			students[i] = i;
		}
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			a = rel(Integer.parseInt(st.nextToken()));
			b = rel(Integer.parseInt(st.nextToken()));
			
			if(a == b) continue;
			students[b] = a;
			cnt--;
		}
		System.out.println(cnt);
	}
	
	public static int rel(int x) {
		if(students[x] != x) {
			students[x] = rel(students[x]);
		}
		return students[x];
	}

}
