#include <iostream>
#include <cstdio>
#include <string>

using namespace std;

int main() {
	string liczba = "1";
	int temp, przeniesienie, outcome = 0;
	
	for(int i = 1; i <= 1000; i++) {
		przeniesienie = 0;
        for(int j = liczba.length() - 1; j >= 0 ; j--) {
			temp = (int)(liczba[j]) - 48;
			temp = temp * 2 + przeniesienie;
			liczba[j] = (char)(temp%10 + 48);
			przeniesienie = temp/10;
		}
		if(przeniesienie != 0)
			liczba = (char)(przeniesienie + 48) + liczba;
	}
	
	for(int i = 0; i < liczba.length(); i++)
		outcome += (int)(liczba[i]) - 48;
	
	cout << outcome << endl;

	system("pause");
	return 0;
}
