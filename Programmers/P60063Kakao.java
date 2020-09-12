package algorithm;

import java.util.ArrayDeque;
import java.util.Queue;

public class P60063Kakao {

	static int min = Integer.MAX_VALUE, N, map[][];
	static boolean[][] visitedH, visitedV;
	
	public static void main(String[] args) {
//		int[][] board = {{0,0,0,1,1},{0,0,0,1,0},{0,1,0,1,1},{1,1,0,0,1},{0,0,0,0,0}};
		int[][] board = {{0,0,0,0,0,0,1}, {1,1,1,1,0,0,1},{0,0,0,0,0,0,0},{0,0,1,1,1,1,0},{0,1,1,1,1,1,0},{0,0,0,0,0,1,1},{0,0,1,0,0,0,0}};
//		int[][] board = {{0,0,0,0,0,0,1}, {1,1,1,1,0,0,1}, {0,0,0,0,0,0,0},{0, 0, 1, 1, 1, 0, 0},{0, 1, 1, 1, 1, 1, 0},{0, 0, 0, 0, 0, 1, 0},{0, 0, 1, 0, 0, 0, 0}};
		System.out.println(solution(board));
	}

	public static int solution(int[][] board) {
        N = board.length;
        // 가로 visited
        visitedH = new boolean[N][N];
        // 세로 visited
        visitedV = new boolean[N][N];
        map = board;
        
        visitedH[0][0] = true;
        Queue<int[]> q = new ArrayDeque<int[]>(); // r, c, dir, time
        q.offer(new int[] {0, 0, 0, 0});
        while(!q.isEmpty()) {
        	int[] cur = q.poll();
        	// (N-1, N-1)에 도착했다면 끝
        	if((cur[2] == 0 && cur[0] == N-1 && cur[1] == N-2) || (cur[2] == 1 && cur[0] == N-2 && cur[1] == N-1)) {
        		min = cur[3];
        		break;
        	}
        	
        	if(cur[2] == 0) { // 가로면 rotate(0,1,2,3)
        		// 회전 안하고 이동
        		// 오른쪽
        		if(cur[1]+2 < N && !visitedH[cur[0]][cur[1]+1] && map[cur[0]][cur[1]+2] == 0) {
    				visitedH[cur[0]][cur[1]+1] = true;
    				q.offer(new int[] {cur[0], cur[1]+1, 0, cur[3]+1});
    			}
        		// 왼쪽
        		if(cur[1]-1 >= 0 && !visitedH[cur[0]][cur[1]-1] && map[cur[0]][cur[1]-1] == 0) {
    				visitedH[cur[0]][cur[1]-1] = true;
    				q.offer(new int[] {cur[0], cur[1]-1, 0, cur[3]+1});
    			}
        		//위
    			if(cur[0]-1 >= 0 && !visitedH[cur[0]-1][cur[1]] && map[cur[0]-1][cur[1]] == 0 && map[cur[0]-1][cur[1]+1] == 0) {
    				visitedH[cur[0]-1][cur[1]] = true;
    				q.offer(new int[] {cur[0]-1, cur[1], 0, cur[3]+1});
    			}
    			//아래
    			if(cur[0]+1 < N && !visitedH[cur[0]+1][cur[1]] && map[cur[0]+1][cur[1]] == 0 && map[cur[0]+1][cur[1]+1] == 0) {
    				visitedH[cur[0]+1][cur[1]] = true;
    				q.offer(new int[] {cur[0]+1, cur[1], 0, cur[3]+1});
    			}
    			int[] temp;
    			for (int i = 0; i < 4; i++) {
    				temp = rotate(i, new int[] {cur[0], cur[1]});
    				// 이동 가능하다면
    				if(temp[0] != -1) {
    					visitedV[temp[0]][temp[1]] = true;
    					q.offer(new int[] {temp[0], temp[1], 1, cur[3]+1});
    				}
    			}
        	} else { // 세로면 rotate(4,5,6,7)
        		// 위
        		if(cur[0]-1 >= 0 && !visitedV[cur[0]-1][cur[1]] && map[cur[0]-1][cur[1]] == 0) {
    				visitedV[cur[0]-1][cur[1]] = true;
    				q.offer(new int[] {cur[0]-1, cur[1], 1, cur[3]+1});
    			}
        		// 아래
    			if(cur[0]+2 < N && !visitedV[cur[0]+1][cur[1]] && map[cur[0]+2][cur[1]] == 0) {
    				visitedV[cur[0]+1][cur[1]] = true;
    				q.offer(new int[] {cur[0]+1, cur[1], 1, cur[3]+1});
    			}
    			// 왼
    			if(cur[1]-1 >= 0 && !visitedV[cur[0]][cur[1]-1] && map[cur[0]][cur[1]-1] == 0 && map[cur[0]+1][cur[1]-1] == 0) {
    				visitedV[cur[0]][cur[1]-1] = true;
    				q.offer(new int[] {cur[0], cur[1]-1, 1, cur[3]+1});
    			}
    			// 오른
    			if(cur[1]+1 < N && !visitedV[cur[0]][cur[1]+1] && map[cur[0]][cur[1]+1] == 0 && map[cur[0]+1][cur[1]+1] == 0) {
    				visitedV[cur[0]][cur[1]+1] = true;
    				q.offer(new int[] {cur[0], cur[1]+1, 1, cur[3]+1});
    			}
    			int[] temp;
    			for (int i = 4; i < 8; i++) {
    				temp = rotate(i, new int[] {cur[0], cur[1]});
    				if(temp[0] != -1) {
    					visitedH[temp[0]][temp[1]] = true;
    					q.offer(new int[] {temp[0], temp[1], 0, cur[3]+1});
    				}
    			}
        	}
        }
        
        return min;
    }
	
	public static int[] rotate(int dir, int[] robot) {
		switch(dir) {
		case 0:
			if(robot[0]+1 < N && !visitedV[robot[0]][robot[1]+1] && map[robot[0]+1][robot[1]+1] == 0 && map[robot[0]+1][robot[1]] == 0)
				return new int[] {robot[0], robot[1]+1};
			return new int[] {-1};
		case 1:
			if(robot[0]-1 >= 0 && !visitedV[robot[0]-1][robot[1]+1] && map[robot[0]-1][robot[1]+1] == 0 && map[robot[0]-1][robot[1]] == 0)
				return new int[] {robot[0]-1, robot[1]+1};
			return new int[] {-1};
		case 2:
			if(robot[0]+1 < N && !visitedV[robot[0]][robot[1]] && map[robot[0]+1][robot[1]] == 0 && map[robot[0]+1][robot[1]+1] == 0)
				return new int[] {robot[0], robot[1]};
			return new int[] {-1};
		case 3:
			if(robot[0]-1 >= 0 && !visitedV[robot[0]-1][robot[1]] && map[robot[0]-1][robot[1]] == 0 && map[robot[0]-1][robot[1]+1] == 0)
				return new int[] {robot[0]-1, robot[1]};
			return new int[] {-1};
		case 4:
			if(robot[1]-1 >= 0 && !visitedH[robot[0]][robot[1]-1] && map[robot[0]][robot[1]-1] == 0 && map[robot[0]+1][robot[1]-1] == 0)
				return new int[] {robot[0], robot[1]-1};
			return new int[] {-1};
		case 5:
			if(robot[1]-1 >= 0 && !visitedH[robot[0]+1][robot[1]-1] && map[robot[0]+1][robot[1]-1] == 0 && map[robot[0]][robot[1]-1] == 0)
				return new int[] {robot[0]+1, robot[1]-1};
			return new int[] {-1};
		case 6:
			if(robot[1]+1 < N && !visitedH[robot[0]][robot[1]] && map[robot[0]][robot[1]+1] == 0 && map[robot[0]+1][robot[1]+1] == 0)
				return new int[] {robot[0], robot[1]};
			return new int[] {-1};
		default:
			if(robot[1]+1 < N && !visitedH[robot[0]+1][robot[1]] && map[robot[0]+1][robot[1]+1] == 0 && map[robot[0]][robot[1]+1] == 0)
				return new int[] {robot[0]+1, robot[1]};
			return new int[] {-1};
		}
	}
}
