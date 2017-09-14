#include <iostream>
#include <cstdio>

using namespace std;

int main()
{
	int output = 1;
	long long n;               /** sufit z pierwiastka **/
	
	cin >> n;
	
	for(int i=2; i*i <= n; i++)
	{
		if(n%i == 0)
		{
			while(n%i == 0)
				n /= i;
				
			output = i;
			i = 2;
		}
	}
	
	if(output < n)
		output = n;			/** W pętli wydzieliliśmy przez wszystkie liczby pierwsze, więc n jest największą liczbą pierwszą **/
	
	cout << output << endl;
	
	system("pause");
	return 0;
}
