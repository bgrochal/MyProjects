#include <iostream>
#include <cstdio>
#include <string>

using namespace std;

int main() {
	int dane[100][50];
	int wynik[55];
	int przeniesienie = 0;
	string z;
	
	for(int i = 0; i < 100; i++) {
		cin >> z;
		for(int j = 0; j < 50; j++) {
			dane[i][j] = (int)(z[j]) - 48;
		}
	}
	
	for(int i = 49; i >= 0; i--) {
		wynik[i] = przeniesienie;
		for(int j = 0; j < 100; j++) {
			wynik[i] += dane[j][i];
		}
		przeniesienie = wynik[i]/10;
	}
	
	cout << wynik[0];
	for(int i = 1; i < 50; i++)
		cout << (wynik[i]%10);
	cout << endl;
	
	return 0;
}
