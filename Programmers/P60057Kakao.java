package algorithm;

public class P60057Kakao {

	static int min = Integer.MAX_VALUE;
	static String text;
	
	public static void main(String[] args) {
		System.out.println(solution("aaaaaaaaaa"));
	}

	public static int solution(String s) {
		text = s;
		if(s.length()==1)
			func(1);
		for (int i = 1; i <= text.length()/2; i++) {
			func(i);
		}
		return min;
	}

	public static void func(int size) {
		int idx = 0;
		String pre = text.substring(idx, idx+size);
		idx += size;
		int cnt = 1;
		int len = size;
		while(idx+size <= text.length()) {
			String cur = text.substring(idx, idx+size);
			idx += size;
			if(pre.equals(cur)) {
				if(++cnt == 2)
					len++;
				else if(cnt == 10)
					len++;
				else if(cnt == 100)
					len++;
				else if(cnt == 1000)
					len++;
			}
			else {
				cnt = 1;
				len += size;
				pre = cur;
			}
			if(len > min)
				return;
		}
		if(idx != text.length()) 
			len += text.length()-idx;
		if(len < min)
			min = len;
	}

}
