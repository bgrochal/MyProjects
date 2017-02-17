#include <iostream>
#include <cstdio>

using namespace std;

const int n = 1000000;
int prime[n+1];
bool is_prime[n+1];


void sieve() {
	for(int i = 3; i <= n; i += 2) {
		if(prime[i] == -1) {
			prime[i] = 1;
			is_prime[i] = true;
			for(int j = 2*i; j <= n; j += i)
				prime[j] = 0;
		}
	}
}

int main() {
	for(int i = 0; i <= n; i++) {
		prime[i] = -1;
		is_prime[i] = false;
	}
	prime[0] = 0; prime[1] = 0; prime[2] = 1;
	is_prime[0] = false; is_prime[1] = false; is_prime[2] = true;

	sieve();
	
	
	
	return 0;
}
