#include <iostream>
#include <cstdio>

using namespace std;

const unsigned long long int modulo = 10000000000;

int main() {
	unsigned long long int temp, wynik;
	wynik = 0;

	for(int i=1; i<1000; i++) {
		temp = 1;
		for(int j=1; j<=i; j++) {
			temp *= i;
			temp %= modulo;
		}
		wynik += temp;
		wynik %= modulo;
	}

	cout << wynik << endl;

	return 0;
}