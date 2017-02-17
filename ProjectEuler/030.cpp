#include <iostream>
#include <cstdio>

using namespace std;

int fifth(int n) {
	int potega = 1;

	for(int i=0; i<5; i++) {
		potega *= n;
	}

	return potega;
}

int main() {
	int liczba, suma;
	long long int wynik;

	wynik = 0;

	for(int x_0 = 9; x_0 >= 0; x_0--){
		for(int x_1 = 9; x_1 >= 0; x_1--){ 
			for(int x_2 = 9; x_2 >= 0; x_2--){ 
				for(int x_3 = 9; x_3 >= 0; x_3--){ 
					for(int x_4 = 9; x_4 >= 0; x_4--){ 
						for(int x_5 = 9; x_5 >= 0; x_5--){ 
							liczba = x_0 *100000 + x_1 * 10000 + x_2 * 1000 + x_3 * 100 + x_4 * 10 + x_5;

							suma = fifth(x_0) + fifth(x_1) + fifth(x_2) + fifth(x_3) + fifth(x_4) + fifth(x_5);

							if(suma == liczba)
								wynik += liczba;
						}
					}
				}
			}
		}
	}

	cout << wynik-1 << endl;

	return 0;
}