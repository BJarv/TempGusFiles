#include <iostream>
#include <string>
using namespace std;

bool blastOff();
void showMenu(string rocketDestination, int rocketFuel);
string setDestination();
int setFuel();

int main()
{
	string rocketDestination = "None";
	int rocketFuel = 0;
	bool isLiftedOff = false;
	int userSelection;
	while (isLiftedOff == false)
	{
		showMenu(rocketDestination, rocketFuel);
		cin >> userSelection;
		switch (userSelection)
		{
		case 1:
			rocketDestination = setDestination();
			break;
		case 2:
			rocketFuel = setFuel();
			break;
		case 3:
			if (rocketDestination != "None" && rocketFuel != 0)
				isLiftedOff = blastOff();
			else
				cout << "Please enter both fuel and destination." << endl;
		}
	}
	return 0;
}

bool blastOff()
{
	char confirm;
	cout << "Enter 'y' to confirm the launch, and 'n' to cancel" << endl;
	cin >> confirm;

	if (confirm == 'n')
		return false;

	for (int i = 10; i > 0; i--)
	{
		cout << i << endl;
	}

	cout << "Blastoff!" << endl;

	return true;
}

void showMenu(string rocketDestination, int rocketFuel)
{
	cout << "Current Destination: " << rocketDestination << endl;
	cout << "Current Fuel %: " << rocketFuel << endl << endl;

	cout << "Enter 1 to set destination" << endl;
	cout << "Enter 2 to fuel up" << endl;
	cout << "Enter 3 to blast off" << endl;
}

int setFuel()
{
	return 100;
}
string setDestination()
{
	string rocketDestination;
	cout << "Enter your destination: " << endl;
	getline(cin, rocketDestination);
	return rocketDestination;
}