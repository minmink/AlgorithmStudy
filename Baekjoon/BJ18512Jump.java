package algorithm;
import java.io.BufferedReader; // import BufferedReader
import java.io.IOException; // import IOException
import java.io.InputStreamReader; // import InputStreamReader
import java.util.StringTokenizer; // import StringTokenizer
public class BJ18512Jump { // start of class
	public static void main(String[] args) throws IOException { // start of main
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력값을 받기 위한 BufferedReader
		StringTokenizer st = new StringTokenizer(br.readLine(), " "); // 입력값을 " "로 쪼개기
		int X = Integer.parseInt(st.nextToken()); // 김싸피의 보폭
		int Y = Integer.parseInt(st.nextToken()); // 박싸피의 보폭
		int H1 = Integer.parseInt(st.nextToken()); // 김싸피의 위치
		int H2 = Integer.parseInt(st.nextToken()); // 박싸피의 위치
		while(H1<=10000 && H2<=10000) { // 10000미터까지만 진행
			if(H1==H2) break;
			if(H1<H2) {
				H1 += X; // H1이 H2보다 뒤에 있다면 H1이 뛰어야 한다
			}
			else {
				H2 += Y; // H2가 H1보다 뒤에 있다면 H2가 뛰어야 한다
			}
		} // end of while
		System.out.println(H1==H2?H1:-1);
	} // end of main
} // end of class
