#include <iostream>
#include <stdlib.h>
#include <time.h>

using namespace std;

int main()
{
	srand(time(NULL));
	bool game = true;
	int playerHP = 20;
	int enemyHP = 20;

	int attackDamage[3] = { 4, 5, 2 };
	int attackHitChance[3] = { 50, 40, 100 };

	while (game == true) {
		int playerChoice = 0;
		cout << "playerHP: " << playerHP << endl;
		cout << "enemyHP: " << enemyHP << endl;
		cout << "Choose an attack 1-3:" << endl;
		cout << "1) Sword Smash" << endl;
		cout << "2) Fire Exploder" << endl;
		cout << "3) Sneaky Dagger" << endl;

		cin >> playerChoice;
		int enemyChoice = rand() % 3;

		switch (playerChoice) {
		case 1:
			cout << " You swing away with your sword!" << endl;
			break;
		case 2:
			cout << "You shoot a ton of fire out of your hands!" << endl;
			break;
		case 3:
			cout << "You poke at the enemy with your dagger!" << endl;
			break;
		default:
			cout << "You trip over your own feet and skip your turn!" << endl;
		}
		if (rand() % 100 <= attackHitChance[playerChoice-1]){
			enemyHP = enemyHP - attackDamage[playerChoice-1];
			cout << "Hit!";
		}
		else {
			cout << "You miss spectacularly";
		}

		switch (enemyChoice)
		{
		case 0:
			cout << "Your enemy runs at you with his sword" << endl;
			break;
		case 1:
			cout << "Your enemy shoots fireballs at you" << endl;
			break;
		case 2:
			cout << "Your enemy disapears, only to reapear next to you holding a dagger" << endl;
			break;
		}
		if (rand() % 100 <= attackHitChance[enemyChoice-1]){
			playerHP = playerHP - attackDamage[enemyChoice-1];
			cout << "You were hit!" << endl;
		}
		else {
			cout << "He misses spectacularly" << endl;
		}

		if (playerHP < 1){
			cout << "The enemy has won!" << endl;
			game = false;
		} if (enemyHP < 1){
			cout << "The enemy has won!" << endl;
			game = false;
		}

	}
	return 0;
}