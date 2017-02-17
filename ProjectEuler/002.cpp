#include <iostream>
#include <cstdio>

using namespace std;

int main()
{
	int F_1 = 1, F_2 = 2, F_3 = 0, sum = 0;
	const double n = 4e6;
	
	while(F_1 + F_2 <= n)
	{
		F_3 = F_1 + F_2;
		
		if(F_3 % 2 == 0)
			sum += F_3;
		
		F_1 = F_2;
		F_2 = F_3;
		
		cout << F_3 << "  ";
	}
	
	cout << sum+2 << endl;
	
	system("pause");
	return 0;
}
