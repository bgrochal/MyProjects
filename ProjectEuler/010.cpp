#include <iostream>
#include <cstdio>

using namespace std;

bool pierwsza(int n)
{
	for(int i = 2; i*i <= n; i++)
	{
		if(n%i == 0)
			return false;
	}
	return true;
}

int main()
{
	long long int sum = 0;
	const int k = 2000000;
	
	for(int i = 2; i <= k; i++)
	{
		if(pierwsza(i) == true)
			sum += i;
	}
	
	cout << sum << endl;
	
	system("pause");
	return 0;
}
