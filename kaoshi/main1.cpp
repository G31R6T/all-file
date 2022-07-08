#include <iostream>
using namespace std;

int main()
{
	int n;
	int k;
	int prices[100];
	cin >> n >> k;
	for (int i = 0; i < n; i++)
	{
		cin >> prices[i];
	}

	int index = -1;
	for (int i = 0; i < n; i++)
	{
		if (prices[i] > k && (index == -1 || prices[i] < prices[index]))
		{
			index = i;
		}
	}

	if (index < 0)
	{
		cout << -1 << endl;
	}
	else
	{
		cout << (index + 1) << " " << prices[index] << endl;
	}
	return 0;
}
