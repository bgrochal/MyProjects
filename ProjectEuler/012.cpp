#include <iostream>
#include <cstdio>

using namespace std;

int generuj_liczbe(int n)
{
	return n*(n+1)/2;
}

int sprawdz_liczbe(int n)
{
	int wartosc = 1, wykladnik;
	
	for(int i = 2; i*i <= n; i++)
	{
		if(n%i == 0)
		{
			wykladnik = 0;
			while(n%i == 0)
			{
				wykladnik++;
				n /= i;
			}
			wartosc *= (wykladnik + 1);
			i = 2;
		}
	}
	
	if(n > 1) // n jest pewną liczbą pierwszą w potędze pierwszej
		wartosc *= 2;
	
	return wartosc;
}

int main()
{
	int k;
    
	//generuj_liczbe(k);
	//if(sprawdz_liczbe(k) > 500)
	
	for(int n = 2; ; n++)
	{
		k = generuj_liczbe(n);
		if(sprawdz_liczbe(k) > 500)
		{
			cout << k << endl;
			break;
		}
		
	}
	
	system("pause");
	return 0;
}
