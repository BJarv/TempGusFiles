#include <iostream>
using namespace std;

int main()
{
	int hotdogsDay[7] = { 102, 57, 78, 98, 47, 212, 0 };
	double hotdogAverage = 0;

	for (int i = 0; i < 7; i++)
	{
		cout << "Enter the number of hotdogs you eat on day: " << i + 1 << endl;
		cin >> hotdogsDay[i];
	}
	for (int i = 0; i < 7; i++)
	{
		cout << hotdogsDay[i] << endl;
	}

	for (int i = 0; i < 7; i++)
	{
		hotdogAverage += hotdogsDay[i];
	}
	hotdogAverage /= 7;
	cout << "You eat " << hotdogAverage << " hotdog(s) per day!" << endl;

	return 0;
}