#include <iostream>
using namespace std;

int main() {
	int menuSelect = 2;

	cout <<"Enter number 1-3 to choose a class" << endl;
	cout << "1) Paladin" << endl;
	cout << "2) Mage" << endl;
	cout << "3) Horse" << endl;

	cin >> menuSelect;

	cout << "Menu Select: "<< menuSelect << endl;

	return 0;
}