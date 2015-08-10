#include <iostream>
using namespace std;

int main()
{
	int choice;
	int num1;
	int num2;
	double answer;
	cout << "Enter 1 to add, 2 to subtract, 3 to mulitiply and 4 to divide" << endl;
	cin >> choice;
	cout << "Enter the 1st number" << endl;
	cin >> num1;
	cout << "Enter the 2nd number" << endl;
	cin >> num2;
	if (choice == 1) {
		answer = num1 + num2;
		cout << answer << endl;
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
	if ((int)answer % 2 == 0)
		cout << "The result is even" << endl;

	else
		cout << "The result is odd" << endl;
	int N;
	cout << "Enter how many bottles of ginger ale are on the wall:" << endl;
	cin >> N;

	for (int i = N; i >= 0; i--)
	{
		cout << i << " bottles of ginger ale on the wall." << endl;
	}
	return 0;
}