#include <iostream>
#include "card.h"


int main()
{
	card myCard(14, 'S');
	
	cout << myCard.displayCard() << endl;

	return 0;
}