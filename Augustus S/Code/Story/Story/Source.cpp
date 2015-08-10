#include <iostream>
#include <string>
using namespace std;

int main()
{
	string story = "Once upon a time there was a young unicorn named charname. Everyday charname would sneak into mean old farmer Joe's barn and eat all the hay. Until one day farmer Joe was waiting for charname in the barn. They had an epic battle until charname used the unicorn power of hypnosis to turn mean old farmer Joe into nice old farmer Joe. And they lived happily every after!";

	string charName;
	cout << "What is your name?" << endl;
	getline(cin, charName);

	int index = story.find("charname");

	while (index != -1)
	{
		story.replace(index, 8, charName);
		index = story.find("charname");
	}

	cout << story << endl;

	return 0;
}