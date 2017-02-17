#include <iostream>
#include <cstdio>
#include <cmath>

using namespace std;

const int N = 100;

int main() {
	int dane[N][N];
	int wynik[N][N];
	
	for(int i=0; i<N; i++) {
		for(int j=0; j<=i; j++) {
			cin >> dane[i][j];
		}
	}
	
	for(int j=0; j<N; j++)
		wynik[N-1][j] = dane[N-1][j];
	
	for(int i=N-2; i>=0; i--) {
		for(int j=0; j<=i; j++) {
			wynik[i][j] = dane[i][j] + max(wynik[i+1][j], wynik[i+1][j+1]);
		}
	}
	
	cout << wynik[0][0] << endl;
	
	system("pause");
	return 0;
}
