#include <iostream>
#include <cstdio>

using namespace std;

int main()
{
	int prod, temp;
	int liczba[8];
	bool palindrom;
	
	for(int i = 901; i <= 999; i++)
	{
		for(int j = 901; j <= 999; j++)
		{
			prod = i * j;
			temp = prod;
			palindrom = true;
			
			for(int i = 6; i >= 1; i--)
			{
				liczba[i] = prod%10;
				prod /= 10;
			}
			
			for(int i = 1; i <= 3; i++)
			{
				if(liczba[i] != liczba[7-i])
					palindrom = false;
			}
			
			if(palindrom == true)
				cout << temp << "  ";
		}
	}
	
	system("pause");
	return 0;
}
