#include <iostream>
#include <string>
using namespace std;

int main()
{
	string meals[3][4] = { { "Orange Juice", "Toast", "Jelly", "Oolong Tea" },
	{ "Sandwich", "Chips", "Water", "Cookie" },
	{ "Pasta", "Salad", "Ginger Ale", "A Mint" } };

	for (int i = 0; i < 3; i++)
	{
		for (int j = 0; j < 4; j++)
		{
			getline(cin, meals[i][j]);
		}
	}
	for (int i = 0; i < 3; i++) {
		for (int j = 0; j < 3; j++) {
			cout << meals[i][j] << " ";
		}
		cout << endl; //mvoing on to the next row
	}

	return 0;
}