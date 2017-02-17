#include <iostream>
#include <cstdio>

using namespace std;

int main()
{
	long double n = 1.0;
	
	for(int i=1; i<=20; i++)
	{
		n = n * (20 + i) / i;
	}
	
	cout << n << endl;
}
