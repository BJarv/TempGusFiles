#include <iostream>
using namespace std;

int main() {
	int menuSelect;

	menuSelect = 2;

	cout << "Enter number 1-3 to pick a genre of music" << endl;
	cout << "1) Symphonic Metal" << endl;
	cout << "2) Hip Hop" << endl;
	cout << "3) Jazz" << endl;

	cin >> menuSelect;

	//Symphonic Metal if statement
	if (menuSelect == 1)
	{
		cout << "You selected: Symphonic Metal";
		cout << endl;
		cout << "Recommended artists:" << endl;
		cout << "Nightwish" << endl;
		cout << "Apocalyptica" << endl;
		cout << "Eluveitie" << endl;
	}
	else if (menuSelect == 2)
	{
		cout << "You selected: Hip Hop";
		cout << endl;
		cout << "Recommended artists:" << endl;
		cout << "Nightwish" << endl;
		cout << "Apocalyptica" << endl;
		cout << "Eluveitie" << endl;
	}
	else if (menuSelect == 3)
	{
		cout << "You selected: Hip Hop";
		cout << endl;
		cout << "Recommended artists:" << endl;
		cout << "Nightwish" << endl;
		cout << "Apocalyptica" << endl;
		cout << "Eluveitie" << endl;
	}
	else {
		cout << "Code cannot handle this input" << endl;
	}

	return 0;
}