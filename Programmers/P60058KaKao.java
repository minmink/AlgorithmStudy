package algorithm;

public class P60058KaKao {

	public static void main(String[] args) {
		System.out.println(solution("(()())()"));
	}

	public static String solution(String s) {
		boolean isRight = true;
		int flag = 0;
		int idx = 0;
		do {
			if(s.charAt(idx++) == '(')
				flag ++;
			else
				flag --;
			if(flag < 0)
				isRight = false;
		} while (flag != 0 && idx < s.length());
		if(idx != s.length() && isRight)
			return s.substring(0, idx) + solution(s.substring(idx, s.length()));
		if(idx != s.length() && !isRight)
			return "("+solution(s.substring(idx, s.length()))+")"+flip(s.substring(1, idx-1));
		if(!isRight)
			return "()"+flip(s.substring(1, s.length()-1));
		return s;
	}
	
	public static String flip(String s) {
		if(s.length()==0)
			return "";
		char[] result = new char[s.length()];
		for (int i = 0; i < result.length; i++) {
			if(s.charAt(i) == '(')
				result[i] = ')';
			else
				result[i] = '(';
		}
		return String.copyValueOf(result);
	}
}
