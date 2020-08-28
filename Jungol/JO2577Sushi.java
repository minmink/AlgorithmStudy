package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class JO2577Sushi {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken()); // 접시의 수
		int d = Integer.parseInt(st.nextToken()); // 초밥의 가짓수
		int k = Integer.parseInt(st.nextToken()); // 연속해서 먹는 접시의 수
		int c = Integer.parseInt(st.nextToken()); // 쿠폰 번호
		int[] dishes = new int[N];
		HashMap<Integer, Integer> sushies = new HashMap<Integer, Integer>();
		sushies.put(c, 1);
		int cnt = 1;
		int max = 0;
		for (int i = 0; i < N; i++) {
			dishes[i] = Integer.parseInt(br.readLine());
		}
		
		for (int i = 0; i < k; i++) {
			if(sushies.containsKey(dishes[i])) sushies.replace(dishes[i], sushies.get(dishes[i])+1);
			else {
				sushies.put(dishes[i], 1);
				cnt++;
			}
		}
		max = cnt;
		
		if(N == k) {
			System.out.println(cnt);
			return;
		}
		
		for (int i = 0; i < N-1; i++) {
			int next = (i+k)%N;
			if(dishes[i]==dishes[next]) continue;
			if(sushies.get(dishes[i]) == 1) {
				sushies.remove(dishes[i]);
				cnt--;
			} else sushies.replace(dishes[i], sushies.get(dishes[i])-1);
			if(sushies.containsKey(dishes[next])) sushies.replace(dishes[next], sushies.get(dishes[next])+1);
			else {
				sushies.put(dishes[next], 1);
				cnt++;
			}
			if(max<cnt) max=cnt;
			if(max == d+1) break;
		}
		
		System.out.println(max);
	}

}
