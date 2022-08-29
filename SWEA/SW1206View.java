package algorithm;

import java.io.*;
import java.util.*;

public class SW1206View {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String s = "";
		
		for (int tc = 1; tc <= 10; tc++) {
			int answer = 0;
			int N = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			sb.append("#").append(tc).append(" ");
			int[] buildings = new int[5];
			
			for (int i = 0; i < N; i++) {
				// 5번째에 새로운 빌딩 넣기
				buildings[4] = Integer.parseInt(st.nextToken());
				
				// 가운데 기준으로 세대 수 세기(min -> answer에 더해주기)
				int min = Integer.MAX_VALUE;
				min = Math.min(min, buildings[2] - buildings[0]);
				min = Math.min(min, buildings[2] - buildings[1]);
				min = Math.min(min, buildings[2] - buildings[3]);
				min = Math.min(min, buildings[2] - buildings[4]);
				if(min > 0)
					answer += min;
				
				// 왼쪽으로 한 칸 씩 땡기기
				for (int j = 0; j < 4; j++) {
					buildings[j] = buildings[j + 1];
				}
			}
			
			sb.append(answer).append("\n");
		}
		
		System.out.print(sb.toString());
	}

}
