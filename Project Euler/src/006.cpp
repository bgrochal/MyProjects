#include <iostream>
#include <cstdio>

using namespace std;

int main()
{
	int squares_sum, square_of_sum, output;
	
	squares_sum = 100*101*201/6;
	square_of_sum = 101 * 50;
	square_of_sum *= square_of_sum;
	
	cout << square_of_sum - squares_sum << endl;

	system("pause");
	return 0;
}
