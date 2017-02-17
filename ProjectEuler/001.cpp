#include <iostream>
#include <cstdio>

using namespace std;

int main()
{
	int l_trojek, l_piatek, l_pietnastek, suma;
	const int n = 1000;
	
	l_trojek = (n-1)/3;		/** największa trójka: 999 **/
	l_piatek = (n-1)/5;		/** największa piątka: 995 **/
	l_pietnastek = (n-1)/15;	/** największa piętnastka: 990 **/
	
	suma = (3 + 999)*l_trojek + (5 + 995)*l_piatek - (15 + 990)*l_pietnastek;
	suma /= 2;
	
	printf("%d\n", suma);
	system("pause");
}
