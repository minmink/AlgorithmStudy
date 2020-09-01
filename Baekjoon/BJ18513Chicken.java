package algorithm;
import java.io.BufferedReader; // import BufferedReader
import java.io.IOException; // import IOException
import java.io.InputStreamReader; // import InputStreamReader
import java.util.HashSet; // import HashSet
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer; // import StringTokenizer

public class BJ18513Chicken { // start of class
	public static void main(String[] args) throws IOException { // start of main
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력값을 받기 위한 BufferedReader
		StringTokenizer st = new StringTokenizer(br.readLine(), " "); // StringTokenizer로 입력값을 공백으로 쪼개기
		int N = Integer.parseInt(st.nextToken()); // 치킨집의 개수
		int K = Integer.parseInt(st.nextToken()); // 집의 개수
		st = new StringTokenizer(br.readLine(), " "); // StringTokenizer로 입력값을 공백으로 쪼개기
		HashSet<Integer> homes = new HashSet<>(); // 집의 위치를 저장할 HashSet
		Queue<int[]> q = new LinkedList<int[]>();
		for (int i = 0; i < N; i++) { // 치킨집의 개수만큼 for
			int chicken = Integer.parseInt(st.nextToken());
			homes.add(chicken); // 집의 위치에다가도 저장(치킨집의 위치와 중복되면 안되므로)
			q.offer(new int[] {chicken, 0, 0});
			q.offer(new int[] {chicken, 0, 1});
		} // end of for
		long sum = 0; // 치킨지수 합
		int cnt = 0;
		while (cnt < K) { // 집의 개수만큼 for
			int[] cur = q.poll(); // 좌표, 거리, 방향 (0:좌 1:우)
			if(cur[2]==0 && homes.add(cur[0]-1) && cur[0]-1>=-100000000) {
				q.add(new int[] {cur[0]-1, cur[1]+1, 0});
				cnt++;
				sum += cur[1]+1;
			}
			if(cur[2]==1 && homes.add(cur[0]+1) && cur[0]+1<=100000000) {
				q.add(new int[] {cur[0]+1, cur[1]+1, 1});
				cnt++;
				sum += cur[1]+1;
			}
		} // end of for
		System.out.println(sum); // 정답 출력
	} // end of main
} // end of class
