#include <iostream>
#include <cstdio>

using namespace std;

int main() {
	int licznik=0;
	int dzien=2;

	int dni[]={31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

	for(int i=1; i<=100; i++) {
		for(int j=0; j<12; j++) {
			if(dzien%7 == 0)
				licznik++;
			dzien += dni[j];
			dzien %= 7;
		}
	}

	if(dzien%7 == 0)
		licznik++;

	cout << licznik << endl;

	return 0;
}