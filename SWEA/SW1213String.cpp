#include<iostream>

using namespace std;

int main(int argc, char** argv) {
	for (int i = 0; i < 10; i++)
	{
		int tc;
		cin >> tc;
		cout << "#" << tc << " ";
		string pattern;
		string text;
		cin >> pattern >> text;
		int answer = 0;
		int textHash = 0;
		int patternHash = 0;
		int power = 1;
		int textSize = text.size();
		int patternSize = pattern.size();

		for (int i = 0; i <= textSize - patternSize; i++)
		{
			if (i == 0) {
				for (int j = 0; j < patternSize; j++)
				{
					textHash += text[patternSize - 1 - j] * power;
					patternHash += pattern[patternSize - 1 - j] * power;
					if (j < patternSize - 1)
						power *= patternSize;
				}
			}
			else {
				textHash = patternSize * (textHash - text[i - 1] * power) + text[patternSize - 1 + i];
			}

			if (textHash == patternHash) {
				bool finish = true;

				for (int j = 0; j < patternSize; j++)
				{
					if (text[i + j] != pattern[j]) {
						finish = false;
						break;
					}
				}

				if (finish)
					answer++;
			}
		}

		cout << answer << endl;
	}


}