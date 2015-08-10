#include <iostream>
using namespace std;

int main()
{
	int choice;
	int num1;
	int num2;
	int answer;
	cout << "Enter 1 to add, 2 to subtract, 3 to mulitiply and 4 to divide"<<endl;
	cin >> choice;
	cout << "Enter the 1st number" << endl;
	cin >> num1;
	cout << "Enter the 2nd number" << endl;
	cin >> num2;
	if (choice == 1) {
		answer = num1 + num2;
		cout <<answer << endl;
	}
	else if (choice == 2) {
		answer = num1 - num2;
		cout << answer << endl;
	}
	else if (choice == 3) {
		answer = num1 * num2;
		cout << answer << endl;
	}
	else if (choice == 4) {
		answer = num1 / num2;
		cout << answer << endl;
	}

	return 0;
}