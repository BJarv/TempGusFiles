#include <iostream>
using namespace std;

int main()
{
	int pointsUser = 0;
	int pointsBatman = 0;
	int userInput;
	cout << "Who would win in a fight, you or the Batman?" << endl;

	cout << "On a scale of 1-10, how strong are you?" << endl;
	cin >> userInput;
	if (userInput > 7)    //Ties always go to the BATMAN
	{
		pointsUser = pointsUser + 1;
	}
	else
	{
		pointsBatman = pointsBatman + 1;
	}
	cout << "On a scale of 1-10, how tactical a planner are you?" << endl;
	cin >> userInput;
	if (userInput <= 8)
	{
		pointsBatman = pointsBatman + 1;
	}
	else
	{
		pointsUser = pointsUser + 1;
	}

	char userYN;
	cout << "Would you be scared of a giant bat flying at you? (y/n)" << endl;
	cin >> userYN;
	if (userYN != 'n')
	{
		pointsBatman = pointsBatman + 1;
	}
	else
	{
		pointsUser = pointsUser + 1;
	}

	if (pointsUser <= pointsBatman)
	{
		cout << "Batman would win! There is no shame in that." << endl;
	}
	else
	{
		cout << "You would win in a fight against Batman." << endl;
		cout << "Are you certain that you are not in fact the Batman?" << endl;
	}

	return 0;
}