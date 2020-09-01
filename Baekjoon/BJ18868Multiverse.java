package algorithm;
import java.io.BufferedReader; // import BufferedReader
import java.io.IOException; // import IOException
import java.io.InputStreamReader; // import InputStreamReader
import java.util.StringTokenizer; // import StringTokenizer
public class BJ18868Multiverse { // start of class
	public static void main(String[] args) throws IOException { // start of main
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력값을 받기 위한 BufferedReader
		StringTokenizer st = new StringTokenizer(br.readLine(), " "); // StringTokenizer로 입력값을 공백으로 쪼개기
		int M = Integer.parseInt(st.nextToken()); // 반의 개수
		int N = Integer.parseInt(st.nextToken()); // 학생 수
		int[][] scores = new int[M][N]; // 전체 점수들
		for (int i = 0; i < M; i++) { // 반의 개수만큼 for
			st = new StringTokenizer(br.readLine(), " "); // 한 반의 전체 성적
			for (int j = 0; j < N; j++) { // 학생 수만큼 for
				scores[i][j] = Integer.parseInt(st.nextToken()); // 한 학생의 점수
			} // end of for
		} // end of for
		int[] classes = new int[M]; // mC2 조합을 위한 배열
		classes[M-1] = 1; // 마지막 것을 1로 (뽑을 2개 중 하나)
		classes[M-2] = 1; // 마지막에서 두번째 것을 1로 (뽑을 2개 중 나머지)
		int cnt = 0; // 비슷한 쌍의 개수 (정답)
		do { // start of while
			int class1 = -1, class2 = -1; // class1과 class2의 선언 및 초기화
			for (int i = 0; i < M; i++) { // 반의 개수만큼 for
				if(classes[i] == 1) { // 만약 1이라면 그 반을 선택함
					if(class1 == -1) class1 = i; // class1을 뽑음
					else class2 = i; // class2를 뽑음
				} // end of if
			} // end of for
			int[] students = new int[N]; // nC2 조합을 위한 배열
			students[N-1] = 1; // 마지막 것을 1로 (뽑을 2개 중 하나)
			students[N-2] = 1; // 마지막에서 두번째 것을 1로 (뽑을 2개 중 나머지)
			boolean flag = true; // 두 학생을 비교하다가 다르면 그 쌍은 정답에 표시가 안되므로 그것을 체크할 변수
			do { // start of while
				int stu1 = -1, stu2 = -1; // stu1과 stu2의 선언 및 초기화
				for (int i = 0; i < N; i++) { // 학생 수만큼 for
					if(students[i] == 1) { // 만약 1이라면 그 학생을 선택함
						if(stu1 == -1) stu1 = i; // stu1을 뽑음
						else stu2 = i; // stu2를 뽑음
					} // end of if
				} // end of for
				if(scores[class1][stu1]>scores[class1][stu2] && scores[class2][stu1]>scores[class2][stu2]) continue; // 두 반 모두 stu1 점수가 stu2 점수보다 큰 것
				else if(scores[class1][stu1]==scores[class1][stu2] && scores[class2][stu1]==scores[class2][stu2]) continue; // 두 반 모두 stu1 점수가 stu2 점수와 같은 것
				else if(scores[class1][stu1]<scores[class1][stu2] && scores[class2][stu1]<scores[class2][stu2]) continue; // 두 반 모두 stu1 점수가 stu2 점수보다 작은 것
				flag = false; // 두 학생의 비교값이 다르게 나옴
				break; // 더이상 비교할 필요가 없다
			} while(nextPermutation(students)); // 더이상의 조합이 없으면 끝내기
			if(flag) cnt++; // 만약 모두 비교한 값이 같다면 이 쌍은 비슷한 것
		} while(nextPermutation(classes)); // 더이상의 조합이 없으면 끝내기
		System.out.println(cnt); // 정답을 출력
	} // end of main
	private static boolean nextPermutation(int[] order) { // 조합을 위해 순열을 만드는 NextPermutation 메서드
		int i = order.length-1; // 기준이 되는 index
		while(i-1>=0 && order[i-1]>=order[i]) i--; // 이전 index의 값보다 현재 index값이 크다면 이전 index(index-1)는 바꿔야 하는 값이 된다
		if(i==0) return false; // 만약 바꿀 값이 없다면 더이상 순열을 만들 수 없다
		int j = order.length-1; // 바꿀 값을 찾기 위한 index
		while(order[i-1]>=order[j]) j--; // i-1 index의 값보다 큰 값을 찾는다
		swap(order, i-1, j); // 두 값을 바꿔준다
		int k = order.length-1; // 오름차순으로 바꿔주기 위한 index
		while(i<k) swap(order, i++, k--); // 차례차례 모두 바꿔준다
		return true; // 순열 만들기를 성공했다
	} // end of nextPermutation method
	private static void swap(int[] order, int i, int j) { // 두 값을 바꾸기 위한 메서드
		int temp = order[i]; // 바뀌기 전의 값을 저장
		order[i] = order[j]; // 바꿔준다
		order[j] = temp; // 바뀌기 전의 값으로 바꿔준다
	} // end of swap method
} // end of class
