#include <iostream>
#include <string>

using namespace std;

int main()
{
	int tic[3][3];
	int v = 0;
	for (int z = 0; z < 3; z++) {
		for (int v = 0; v < 3; v++) {
			tic[z][v] = 0;
		}
		tic[z][v] = 0;
	}
	int c = 0;
	bool notRight = true;
	int player = 0;
	int player1Win = 0;
	int player2Win = 0;
	int player1WinDown = 0;
	int player2WinDown = 0;
	bool win = false;
	int i = 0;

	cout << "Welcome to Tic Tac Toe! Player 1 is X's, Player 2 is O's" << endl;

	while (win==false) {
			i++;
			int player1Win = 0;
			int player2Win = 0;

			if (i % 2 == 1) {
				player = 1;
			}
			else {
				player = 2;
			}

			//Creates Game Board
			for (int a = 0; a < 3; a++) {
				for (int b = 0; b < 3; b++) {
					if (tic[b][a] == 1) {
						cout << "X";
					}
					else if (tic[b][a] == 2) {
						cout << "O";
					}
					else {
						cout << " ";
					}
					if (b < 2) {
						cout << "|";
					}
				}
				cout << endl;
				if (a < 2) {
					cout << "-----" << endl;
				}
			}

			//Player Turn
			notRight = true;
			while (notRight == true) {
				int x = 0;
				int y = 0;
				cout << "Player " << player << " select the column 1 - 3" << endl;
				cin >> x;
				cout << "Player " << player << " select the row 1 - 3" << endl;
				cin >> y;
				if (tic[x - 1][y - 1] == 0) {
					tic[x - 1][y - 1] = player;
					notRight = false;
				}
				else {
					cout << "Invalid Input or space already taken" << endl;
				}
			}


			//Win condition
			for (int i = 0; i < 3; i++) {
				if (tic[i][0] == tic[i][1] && tic[i][1] == tic[i][2]) {
					if (tic[i][0] == 1) {
						cout << "Player 1 has won!";
						win = true;
					}
					else if (tic[i][0] == 2){
						cout << "Player 2 has won!";
						win = true;
					}
				}
			}

			for (int i = 0; i < 3; i++) {
				if (tic[0][i] == tic[1][i] && tic[1][i] == tic[2][i]) {
					if (tic[0][i] == 1) {
						cout << "Player 1 has won!";
						win = true;
					}
					else if (tic[0][i] == 2){
						cout << "Player 2 has won!";
						win = true;
					}
				}
			}

			if (tic[0][0] == tic[1][1] && tic[1][1] == tic[2][2] || tic[0][2] == tic[1][1] && tic[1][1] == tic[2][0]) {
				if (tic[1][1] == 1) {
					cout << "Player 1 has won!";
					win = true;
				}
				else if (tic[1][1] == 2){
					cout << "Player 2 has won!";
					win = true;
				}
			}

			cin.ignore();
		}
		
}