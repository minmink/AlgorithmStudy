package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;

public class BJ1406Editor {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		int M = Integer.parseInt(br.readLine()); // 명령어 개수
		
		LinkedList<Character> list = new LinkedList<>();
		for (int i = 0; i < s.length(); i++) {
			list.add(s.charAt(i));
		}
		
		int index = s.length(); // 커서의 위치, 문장의 맨 뒤에 위치
		for (int i = 0; i < M; i++) {
			String cmd = br.readLine();
			switch(cmd.charAt(0)) {
			case 'L': // 커서를 왼쪽으로 한 칸 이동 (커서가 문장의 맨 앞이면 무시)
				if(index > 0) index--;
				break;
			case 'D': // 커서를 오른쪽으로 한 칸 이동 (커서가 문장의 맨 뒤면 무시)
				if(index < list.size()) index++;
				break;
			case 'B': // 커서 왼쪽 문자 삭제 (커서가 문장의 맨 앞이면 무시)
				if(index != 0) {
					list.remove(--index);
				}
				break;
			case 'P': // 다음 글자를 커서 왼쪽에 추가
				list.add(index++, cmd.charAt(2));
				break;
			}
		}
		
		StringBuilder sb = new StringBuilder(list.size());

//		for (int i = 0; i < list.size(); i++) {
//			sb.append(list.get(i));
//		}
		
		Iterator<Character> iter = list.iterator(); // 순차적으로 접근하는 방법
		while(iter.hasNext()) {
			sb.append(iter.next());
		}

		System.out.println(sb.toString());
	}
}
