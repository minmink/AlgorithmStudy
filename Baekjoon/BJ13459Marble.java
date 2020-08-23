package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ13459Marble {

	static int N, M;
	static char[][] map;
	static int[] red = new int[2], blue = new int[2];
	static boolean flag = false;
	static int[] dr = {-1, 1, 0, 0}; // 상하좌우
	static int[] dc = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		String s;
		for (int i = 0; i < N; i++) {
			s = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = s.charAt(j);
				if(map[i][j] == 'R') {
					red[0] = i;
					red[1] = j;
				} else if (map[i][j] == 'B') {
					blue[0] = i;
					blue[1] = j;
				}
			}
		}
		
		dfs(0);
		
		if(flag) System.out.println(1);
		else System.out.println(0);
	}

	private static void dfs(int cnt) {
		if(cnt == 10 || flag) return;
		int crr = red[0], crc = red[1], cbr = blue[0], cbc = blue[1];
		int nrr, nrc, nbr, nbc;
		boolean bfin, rmove, bmove;
		for (int i = 0; i < 4; i++) {
			bfin = false; rmove = false; bmove = false;
			nbr = blue[0]+dr[i];
			nbc = blue[1]+dc[i];
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
			
			nrr = red[0]+dr[i];
			nrc = red[1]+dc[i];
			if(map[nrr][nrc] != '#') {
				rmove = true;
				if(map[nrr][nrc] == 'O') {
					flag = true;
					return;
				} else {
					while(map[nrr][nrc]!='#' && map[nrr][nrc]!='O') {
						nrr+=dr[i]; nrc+=dc[i];
					}
					if(map[nrr][nrc] == 'O') {
						flag = true;
						return;
					}
				}
			}
			nrr-=dr[i]; nrc-=dc[i];
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
			if((rmove || bmove) && !flag) {
				red[0] = nrr; red[1] = nrc; blue[0] = nbr; blue[1] = nbc;
				dfs(cnt+1);
			}
			red[0] = crr; red[1] = crc; blue[0] = cbr; blue[1] = cbc;
		}
	}

}
