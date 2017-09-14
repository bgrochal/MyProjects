#include <iostream>
#include <string>
#include <cstdio>

using namespace std;

const int N = 1001;

int main() {
	long long int sum = 1;
	long long int add = 2;
	long long int temp = 1;
	bool koniec = false;

	while(!koniec) {
		for(int i=0; i<4; i++) {
			temp += add;
			if(temp > N*N) {
				koniec = true;
				break;
			}
			sum += temp;
		}
		add += 2;
	}

	cout << sum << endl;

	return 0;
}

/** WERSJA REKURENCYJNA - DZIAŁA, ZBYT WIELE INSTANCJI

const int N = 1001;
int tab[N][N];

void go(int counter, int posx, int posy, int available, int jump, int tour, string direction) {
	if(counter > N*N)
		return;
	cout << counter << " ";
	tab[posx][posy] = counter;

	if(available > 0) {
		if(direction=="right")
			go(counter+1, posx+1, posy, available-1, jump, tour, direction);
		else if(direction=="left")
			go(counter+1, posx-1, posy, available-1, jump, tour, direction);
		else if(direction=="up")
			go(counter+1, posx, posy+1, available-1, jump, tour, direction);
		else if(direction=="down")
			go(counter+1, posx, posy-1, available-1, jump, tour, direction);
	}
	else if(available==0 && tour==1) {
		if(direction=="right")
			go(counter+1, posx, posy-1, jump-1, jump, tour+1, "down");
		else if(direction=="left")
			go(counter+1, posx, posy+1, jump-1, jump, tour+1, "up");
		else if(direction=="up")
			go(counter+1, posx+1, posy, jump-1, jump, tour+1, "right");
		else if(direction=="down")
			go(counter+1, posx-1, posy, jump-1, jump, tour+1, "left");
	}
	else if(available==0 && tour==2) {
		if(direction=="up")
			go(counter+1, posx+1, posy, jump, jump+1, 1, "right");
		else if(direction=="down")
			go(counter+1, posx-1, posy, jump, jump+1, 1, "left");
	}
}

int main() {
	long long int suma = 0;

	go(1, N/2, N/2, 1, 1, 1, "right");
	for(int i=0; i<N; i++) {
		suma += tab[i][i];
	}

	cout << suma << endl;

//	for(int i=N-1; i>=0; i--) {
//		for(int j=0; j<N; j++) {
//			printf("%d\t", tab[j][i]);
//		}
//		printf("\n");
//	}

	return 0;
}**/