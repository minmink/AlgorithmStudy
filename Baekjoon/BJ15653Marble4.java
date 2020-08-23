package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ15653Marble4 {

	static int N, M;
	static char[][] map;
	static Queue<int[]> red = new LinkedList<>(), blue = new LinkedList<>();
	static int min = Integer.MAX_VALUE;
	static int[] dr = {-1, 1, 0, 0}; // 상하좌우
	static int[] dc = {0, 0, -1, 1};
	static boolean[][][][] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		visited = new boolean[N][M][N][M];
		String s;
		for (int i = 0; i < N; i++) {
			s = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = s.charAt(j);
				if(map[i][j] == 'R') {
					red.add(new int[] {i, j, 0});
				} else if (map[i][j] == 'B') {
					blue.add(new int[] {i, j});
				}
			}
		}
		
		visited[red.peek()[0]][red.peek()[1]][blue.peek()[0]][blue.peek()[1]] = true;
wh:		while (!red.isEmpty()) {
			int crr = red.peek()[0], crc = red.peek()[1], cbr = blue.peek()[0], cbc = blue.peek()[1], cnt = red.peek()[2];
			red.poll(); blue.poll();
			int nrr, nrc, nbr, nbc;
			boolean rfin, bfin, rmove, bmove;
			for (int i = 0; i < 4; i++) {
				rfin = false; bfin = false; rmove = false; bmove = false;
				nbr = cbr+dr[i];
				nbc = cbc+dc[i];
				if(map[nbr][nbc] != '#') {
					bmove = true;
					if(map[nbr][nbc] == 'O') bfin = true;
					else {
						while(map[nbr][nbc]!='#' && map[nbr][nbc]!='O') {
							nbr+=dr[i]; nbc+=dc[i];
						}
						if(map[nbr][nbc] == 'O') bfin = true;
					}
				}
				nbr-=dr[i]; nbc-=dc[i];
				if(bfin) continue;
				
				nrr = crr+dr[i];
				nrc = crc+dc[i];
				if(map[nrr][nrc] != '#') {
					rmove = true;
					if(map[nrr][nrc] == 'O') rfin = true;
					else {
						while(map[nrr][nrc]!='#' && map[nrr][nrc]!='O') {
							nrr+=dr[i]; nrc+=dc[i];
						}
						if(map[nrr][nrc] == 'O') rfin = true;
					}
				}
				nrr-=dr[i]; nrc-=dc[i];
				if(rfin) {
					if(min>cnt+1) min = cnt+1;
					break wh;
				}
				if(nrr == nbr && nrc == nbc) {
					switch(i) {
					case 0: // 상
						if(crr>cbr) nrr++;
						else nbr++;
						break;
					case 1: // 하
						if(crr>cbr) nbr--;
						else nrr--;
						break;
					case 2: // 좌
						if(crc>cbc) nrc++;
						else nbc++;
						break;
					case 3: // 우
						if(crc>cbc) nbc--;
						else nrc--;
						break;
					}
				}
				if((rmove || bmove) && !visited[nrr][nrc][nbr][nbc]) {
					visited[nrr][nrc][nbr][nbc] = true;
					red.add(new int[] {nrr, nrc, cnt+1});
					blue.add(new int[] {nbr, nbc});
				}
			}
		}
		
		if(min == Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(min);
	}
}
