#include <iostream>
#include <string>
#include <cstdio>

using namespace std;

bool palindromic_10(int n) {
	int reverse = 0, copy = n;

	while(n>0) {
		reverse *= 10;
		reverse += n%10;

		n /= 10;
	}

	if(reverse == copy)
		return true;

	return false;
}

bool palindromic_2(int n) {
	string dwojkowy = "";
	int dlugosc;

	while(n>0) {
		if(n%2 == 0)
			dwojkowy += "0";
		else
			dwojkowy += "1";

		n /= 2;
	}

	dlugosc = dwojkowy.length();
	for(int i=0; i<dlugosc; i++) {
		if(dwojkowy[i] != dwojkowy[dlugosc-1-i])
			return false;
	}

	return true;
}

int main() {
	long long int suma = 0;

	for(int i=1; i<1000000; i++) {
		if(palindromic_10(i) && palindromic_2(i))
			suma += i;
	}

	cout << suma << endl;

	return 0;
}