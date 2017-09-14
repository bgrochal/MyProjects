#include <iostream>
#include <cstdio>
#include <vector>

using namespace std;

const int n = 28125;
int P [n];
int K [n];
int sum[n];
int sigma [n];

vector<int> v;

int power(int a, int b)
{
	int outcome = 1;
	for(int i = 1; i <= b; i++)
		outcome *= a;
	
	return outcome;
}

void sieve()
{
	for(int i = 0; i <= n; i++)
		P[i] = -1;
	P[1] = 1;
	
	for(int i = 2; i <= n; i++)
	{
		if(P[i] == -1)
		{
			P[i] = i;
			for(int j = 2*i; j <= n; j += i)
			{
				if(P[j] == -1)
					P[j] = i;
			}
		}
	}
}

void exponent()
{
	K[1] = 1;
	
	for(int i = 2; i <= n; i++)
	{
		int temp = i/P[i];
		if(temp % P[i] == 0)
			K[i] = K[temp] + 1;
		else
			K[i] = 1;
	}
}

void count()
{
	sigma[1] = 1;
	sigma[2] = 3;
	for(int i = 3; i <= n; i++)
	{
		int temp = power(P[i], K[i]);
		sigma[i] = ((temp * P[i] - 1)/(P[i] - 1))*(sigma[i/temp]);

		if(sigma[i] - i > i)
			v.push_back(i);
	}
}

int main()
{
	sieve();
	exponent();
	count();
	
	/**for(int i=0; i<v.size(); i++){ 
		cout << v[i] << " ";
		getchar();
	}
	cout << endl;**/

	long long int out = 0;

	for(int i=0; i<n; i++)
		sum[i] = false;

	for(int i=0; i<v.size(); i++) {
		for(int j=i; j<v.size(); j++){
			if(v[i]+v[j] < n)
				sum[v[i] + v[j]] = true;
		}
	}

	for(int i=0; i<n; i++) {
		if(!sum[i])
			out += i;
	}

	cout << out << endl;

	return 0;
}
