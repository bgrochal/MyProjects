#include <iostream>
#include <cstdio>

using namespace std;

bool prime(int n)
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
	int flag = 1;
	
	for(int i = 3; i <= 1000000; i += 2)
	{
		if(prime(i) == true)
		{
			flag++;
		}
		
		if(flag == 10001)
		{
			cout << i << endl;
			break;
		}
	}
	
	system("pause");
	return 0;
}
