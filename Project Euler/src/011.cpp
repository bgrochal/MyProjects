#include <iostream>
#include <cstdio>

using namespace std;

int main()
{
	int max = 0, temp;
	int grid[20][20];
	
	for(int i = 0; i <= 19; i++)
	{
		for(int j = 0; j <= 19; j++)
			cin >> grid[i][j];
	}

	for(int i = 3; i <= 19; i++)
	{
		for(int j = 0; j <= 19; j++)
		{
			temp = grid[i][j] * grid[i-1][j] * grid[i-2][j] * grid[i-3][j];
			if(temp > max)
				max = temp;
		}
	}	

	for(int i = 0; i <= 19; i++)
	{
		for(int j = 3; j <= 19; j++)
		{
			temp = grid[i][j] * grid[i][j-1] * grid[i][j-2] * grid[i][j-3];
			if(temp > max)
				max = temp;
		}
	}
	
	for(int i = 0; i <= 16; i++)
	{
		for(int j = 0; j <= 16; j++)
		{
			temp = grid[i][j] * grid[i+1][j+1] * grid[i+2][j+2] * grid[i+3][j+3];
			if(temp > max)
				max = temp;
		}	
	
	}	

	for(int i = 0; i <= 16; i++)
	{
		for(int j = 19; j >= 3; j--)
		{
			temp = grid[i][j] * grid[i+1][j-1] * grid[i+2][j-2] * grid[i+3][j-3];
			if(temp > max)
				max = temp;
		}		
	}
	
	cout << max << endl;
	
	system("pause");
	return 0;
}
