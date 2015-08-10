#include <iostream>
using namespace std;

int main()
{
	int userSelect;
	cout << "Enter what you would like to eat:" << endl;
	cout << "1) Pancakes" << endl << "2) Raw Tuna" << endl << "3) Lemon Rissoto" << endl;
	cin >> userSelect;
	switch (userSelect) {
		case 1:
			cout << "I'll bring the maple syrup!" << endl;
			break;
		case 2:
			cout << "Sushi is tasty!" << endl;
			break;
		case 3:
			cout << "Suoni delizioso!" << endl;
			break;
		default:
			cout << "Invalid input" << endl;
	}
	return 0;
}