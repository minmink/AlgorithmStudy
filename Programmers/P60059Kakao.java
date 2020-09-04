package algorithm;

public class P60059Kakao {

	static int[][] key, lock;
	static int N, M;
	
	public static void main(String[] args) {
		int[][] key = {{0,0,0},{1,0,0},{0,1,1}};
		int [][] lock = {{1,1,1},{1,1,0},{1,0,1}};
		System.out.println(solution(key, lock));
	}

	public static boolean solution(int[][] k, int[][] l) {
		key = k; lock = l;
		N = lock.length; M = key.length;
		int rowStart = -1, rowEnd = -1, colStart = -1, colEnd = -1;
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(lock[i][j] == 0) {
					cnt++;
					if(rowStart == -1) {
						rowStart = i; rowEnd = i; colStart = j; colEnd = j;
					}
					if(colStart>j) colStart = j;
					if(colEnd<j) colEnd = j;
					if(rowEnd<i) rowEnd = i;
					
				}
			}
		}
		if(cnt == 0) return true;
		if(rowEnd - rowStart > M-1 || colEnd - colStart > M-1)
			return false;
		
		// key 시작점
		for (int lockR = rowEnd-M+1; lockR <= rowStart; lockR++) {
			for (int lockC = colEnd-M+1; lockC <= colStart; lockC++) {
				if(func(lockR, lockC)) return true;
			}
		}
		
		return false;
	}
	
	public static boolean func(int lockR, int lockC) {
		boolean flag1 = true, flag2 = true, flag3 = true, flag4 = true;
		// lock의 좌표는 lockR lockC 더해주기
		for (int i = 0; i < M; i++) {
			if(i + lockR < 0) continue;
			if(i + lockR == N) break;
			for (int j = 0; j < M; j++) {
				if(j + lockC < 0) continue;
				if(j + lockC == N) break;
				if(flag1 && lock[i+lockR][j+lockC] == key[i][j]) flag1 = false;
				if(flag2 && lock[i+lockR][j+lockC] == key[j][M-1-i]) flag2 = false;
				if(flag3 && lock[i+lockR][j+lockC] == key[M-1-j][i]) flag3 = false;
				if(flag4 && lock[i+lockR][j+lockC] == key[M-1-i][M-1-j]) flag4 = false;
				if(!flag1 && !flag2 && !flag3 && !flag4) return false;
			}
		}
		
		if(flag1 || flag2 || flag3 || flag4) return true;
		return false;
	}
	
}
