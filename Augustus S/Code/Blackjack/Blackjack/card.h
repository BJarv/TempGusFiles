#include <string>
#include <stdlib.h>
#include <time.h>
using namespace std;

class card
{
public:
	//Variables
	int value;

	//constructors
	card()
	{
		value = 11;
	}
	card(int v)
	{
		value = v;
	}

	//functions
	string displayCard()
	{
		string displayValue = "[";
		if (value < 11)
		{
			displayValue += to_string(value);
		}
		if (value == 11)
		{
			displayValue += "J";
			value = 10;
		}
		if (value == 12)
		{
			displayValue += "Q";
			value = 10;
		}
		if (value == 13)
		{
			displayValue += "K";
			value = 10;
		}
		if (value == 14)
		{
			displayValue += "A";
			value = 11;
		}
		displayValue += "]";


		return displayValue;
	}

};

void shuffle(card deck[], int len)
{
	srand(time(NULL));
	for (int i = 0; i < len; i++)
	{
		int r = rand() % (len - i) + i;

		card temp = deck[i];
		deck[i] = deck[r];
		deck[r] = temp;
	}
}