#include <iostream>
using namespace std;

int main()
{
	int userSelection;
	
	while (true)
	{
		cout << "Enter a number to choose a selection:" << endl;
		cout << "1) My Favorite Movie Protagonists" << endl;
		cout << "2) My Favorite Movie Villians" << endl;
		cout << "3) My Favorite Movie Side Characters" << endl;
		cout << "4) Quit" << endl;

		cin >> userSelection;

		if (userSelection == 1)
		{
			cout << "Favorite Protagonists:" << endl;
			cout << "1) Aragorn - Lord Of The Rings" << endl;
			cout << "2) James Tiberius Kirk - Star Trek Original Series Movies" << endl;
			cout << "3) Fa Mulan - Mulan" << endl;

			cout << "Enter 0 to go to the main menu, or a number to see that character's quote" << endl;
			cin >> userSelection;

			if (userSelection == 0)
			{
				continue;
			}
			if (userSelection == 1)
			{
				cout << "Aragorn: played by Viggo Mortensen" << endl;
				cout << "Famous Quote: 'I do not know what strength is in my blood, but I swear to you I will not let the White City fall, nor our people fail.'" << endl << endl;
			}
			if (userSelection == 2)
			{
				cout << "James Tiberius Kirk: Played by William Shatner" << endl;
				cout << "Famous Quote: 'KHAAAAAAAAAAAAAAAAANNNNNNNNNNNNN!!!!!!!!!!!'" << endl << endl;
			}
			if (userSelection == 3)
			{
				cout << "Fa Mulan: Voiced by Ming-Na Wen" << endl;
				cout << "Famous Quote: 'Just because I look like a man doesn't mean I have to smell like one.'" << endl << endl;
			}
		}
		if (userSelection == 2)
		{
			cout << "Favorite Villains:" << endl;
			cout << "1) The Operative - Serentiy" << endl;
			cout << "2) Lucas Lee - Scott Pilgrim Vs. The World" << endl;
			cout << "3) Saruman -  Lord Of The Rings" << endl;

			cout << "Enter 0 to go to the main menu, or a number to see that character's quote" << endl;
			cin >> userSelection;

			if (userSelection == 0)
			{
				continue;
			}

			if (userSelection == 1)
			{
				cout << "The Operative: Played by Chiwetel Ejiofor" << endl;
				cout << "Famous Quote: 'We're making a better world. All of them.'" << endl << endl;
			}
			if (userSelection == 2)
			{
				cout << "Lucas Lee: Played by Chris Evans" << endl;
				cout << "Famous Quote: 'The only thing seperating me and her is the two minutes its going to take me to beat you up.'" << endl << endl;
			}
			if (userSelection == 3)
			{
				cout << "Saruman: Played by Christopher Lee" << endl;
				cout << "Famous Quote: 'Your love of the Halfings' leaf has clearly slowed your mind.'" << endl << endl;
			}
		}
		if (userSelection == 3)
		{
			cout << "Favorite Side Characters:" << endl;
			cout << "1) Wallace Wells - Scott Pilgrim Vs. The World" << endl;
			cout << "2) Boromir - LOTR: Fellowship Of THe Ring" << endl;
			cout << "3) Scotty - Star Trek" << endl;

			cout << "Enter 0 to go to the main menu, or a number to see that character's quote" << endl;
			cin >> userSelection;

			if (userSelection == 0)
			{
				continue;
			}

			if (userSelection == 1)
			{
				cout << "Wallace Wells: Played by Kieran Culkin" << endl;
				cout << "Famous Quote: 'It's not a race guys!'" << endl << endl;
			}
			if (userSelection == 2)
			{
				cout << "Boromir: Played by Sean Bean" << endl;
				cout << "Famous Quote: 'It is a strange fate that we should suffer so much fear and doubt over so small a thing.'" << endl << endl;
			}
			if (userSelection == 3)
			{
				cout << "Scotty: Played by James Doohan" << endl;
				cout << "Famous Quote: 'The only thing we have left is impulse power.'" << endl << endl;
			}
		}
		if (userSelection == 4)
		{
			break;
		}
	}

	return 0;
}