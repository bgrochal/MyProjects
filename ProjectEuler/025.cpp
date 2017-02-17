#include <iostream>
#include <cstdio>
#include <string>

using namespace std;

int main() {
	int temp, przeniesienie, licznik = 2;
	string F1 = "1", F2 = "1", F3 = "1";
	
	while(true) {
		if(F3. length() >= 1000)
			break;
		while(F2.length() > F1.length())
			F1 = "0" + F1;
			
		przeniesienie = 0;
		for(int i = F1.length() - 1; i >= 0; i--) {
			temp = (int)(F1[i]) + (int)(F2[i]) - 96 + przeniesienie;
			F3[i] = (char)(temp%10 + 48);
			przeniesienie = temp/10;
		}
		if(przeniesienie != 0)
			F3 = (char)(przeniesienie + 48) + F3;
		
		licznik++;
		F1 = F2; F2 = F3;
	}
	
	cout << licznik << endl;
	
	system("pause");
	return 0;
}
