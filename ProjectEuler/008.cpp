#include <iostream>
#include <cstdio>
#include <cstdlib>

using namespace std;

int main()
{
	string number;
	cin >> number;
	
	int temp, max = 0;
	int tab[1001];
	
	for(int i=0; i<=999; i++)
	{
		switch(number[i])
		{
			case '0': tab[i] = 0; break;
			case '1': tab[i] = 1; break;
			case '2': tab[i] = 2; break;
			case '3': tab[i] = 3; break;
			case '4': tab[i] = 4; break;
			case '5': tab[i] = 5; break;
			case '6': tab[i] = 6; break;
			case '7': tab[i] = 7; break;
			case '8': tab[i] = 8; break;
			case '9': tab[i] = 9; break;
		}
	}
	
	for(int i=0; i<=995; i++)
	{
		temp = tab[i]*tab[i+1]*tab[i+2]*tab[i+3]*tab[i+4];
		if(temp > max)
			max = temp;
	}
	
	cout << max << endl;
	
	system("pause");
	return 0;
}
