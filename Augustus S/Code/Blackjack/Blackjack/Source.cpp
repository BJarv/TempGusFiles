#include "Card.h"
#include <iostream>

int main()
{
	card deckOfCards[52];
	int p1Score = 0;
	int p2Score = 0;

	for (int i = 0; i < 13; i++)
	{
		deckOfCards[i].value = i + 2;
	}
	for (int i = 0; i < 13; i++)
	{
		deckOfCards[i + 13].value = i + 2;
	}
	for (int i = 0; i < 13; i++)
	{
		deckOfCards[i + 26].value = i + 2;
	}
	for (int i = 0; i < 13; i++)
	{
		deckOfCards[i + 39].value = i + 2;
	}
	shuffle(deckOfCards, 52);

	card player1Hand[10];
	card player2Hand[10];
	int topCard = 0;
	bool p1Fold;
	bool p2Fold;

	int p1Size = 0;
	int p2Size = 0;
	bool game = true;

		for (int i = 0; i < 2; i++)
		{
			player1Hand[i] = deckOfCards[topCard];
			topCard++;
			p1Size++;

			player2Hand[i] = deckOfCards[topCard];
			topCard++;
			p2Size++;
		}

		while (game == true) {
		cout << "Player 1's Hand" << endl;
		for (int i = 0; i < p1Size; i++)
		{
			cout << player1Hand[i].displayCard();
		}
		cout << endl << "Player 2's Hand" << endl;
		for (int i = 0; i < p2Size; i++)
		{
			cout << player2Hand[i].displayCard();
		}

		//P1Draw
		char yesNo;
		bool notR = true;
		while (notR == true) {
			cout << "Player 1 do you want to draw? (y/n)";
			cin >> yesNo;
			if (yesNo == 'y') {
				player1Hand[p1Size + 1] = deckOfCards[topCard];
				topCard++;
				p1Size++;
				notR = false;
			}
			else if (yesNo == 'n') {
				notR = false;
				p1Fold = true;
			}
			else {
				cout << "Invalid Input";
			}
		}

		//P2Draw
		bool notR2 = true;
		while (notR2 == true) {
			cout << "Player 2 do you want to draw? (y/n)";
			cin >> yesNo;
			if (yesNo == 'y') {
				player2Hand[p2Size + 1] = deckOfCards[topCard];
				topCard++;
				p2Size++;
				notR2 = false;
			}
			else if (yesNo == 'n') {
				notR2 = false;
				p2Fold = true;
			}
			else {
				cout << "Invalid Input";
			}
		}

		for (int i = 0; i < p1Size; i++)
		{
			p1Score += player1Hand[i].value;
		}

		int p2Score = 0;
		for (int i = 0; i < p2Size; i++)
		{
			p2Score += player2Hand[i].value;
		}
		if(p1Fold == true && p2Fold == true) {
			int close1 = 21 - p1Score;
			int close2 = 21 - p2Score;

			if (p1Score < 22 && close1 > close2) {
				cout << endl << "Player 1 wins!" << endl;
				game = false;
			}
			else if (p2Score < 22 && close2 > close1) {
				cout << endl << "Player 2 wins!" << endl;
				game = false;
			}
			else if (p1Score == p2Score) {
				cout << endl << "Its a tie" << endl;
				game = false;
			}
			else if (p1Score > 21 && p2Score > 21) {
				cout << endl << "Its a tie" << endl;
				game = false;
			}
		}
	}
	cin.ignore();
}