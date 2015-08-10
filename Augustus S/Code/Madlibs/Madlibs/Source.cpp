#include <iostream>
#include <string>
using namespace std;

int main()
{
	string word;
	string name;
	int index = 0;
	string story = "Once upon a time there was a insert named charname, and everyday, charname would go to the insert insert to insert. Until one day, a pack of wild insert came and insert all of the insert. This made charname feel insert, so they went to the insert to insert. charname was sucessful, and was named the insert of insert.";
	cout << "Enter the name of your main character:";
	getline(cin, name);
	while (index != -1)
	{
		story.replace(index, 8, name);
		index = story.find("charname");
	}
	for (int i = 0; i < 14; i++) {
		if (i == 1 || i == 3 || i == 11) {
			cout << "Now enter a noun" << endl;
			getline(cin, word);
		}
		else if (i == 2 || i == 8) {
			cout << "Now enter an adjective" << endl;
			getline(cin, word);
		}
		else if (i == 4 || i == 7) {
			cout << "Now enter a verb" << endl;
			getline(cin, word);
		}
		else if (i == 5) {
			cout << "Now enter a plural noun" << endl;
			getline(cin, word);
		}
		else if (i == 6) {
			cout << "Now enter a past time verb" << endl;
			getline(cin, word);
		}
		else if (i == 7) {
			cout << "Now enter a plural occupation" << endl;
			getline(cin, word);
		}
		else if (i == 9) {
			cout << "Now enter a location" << endl;
			getline(cin, word);
		}
		else if (i == 12) {
			cout << "Now enter a town name" << endl;
			getline(cin, word);
		}
		story.replace(story.find("insert"), 6, word);

	}
	cout << story;
	cin.ignore();
}