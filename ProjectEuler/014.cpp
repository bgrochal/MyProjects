#include <iostream>
#include <cstdio>

using namespace std;

int tab[10000001] = {0};

int main() {
	long long int n, temp, outcome, max = 0;
	tab[1] = 1;
	
	for(long long int i = 2; i < 1000000; i++)
	{
		n = i; temp = 0;
		
		while(n > 1 && n >= i)
		{
			if(n%2 == 0)
				n /= 2;
			else
				n = 3*n + 1;
				
			temp++;
		}
		
		tab[i] = tab[n] + temp;
		
		if(tab[i] > max)
		{
			max = tab[i];
			outcome = i;
		}
		
	}
	
	cout << max << "  " << outcome << endl;
	
	system("pause");
	return 0;
}
