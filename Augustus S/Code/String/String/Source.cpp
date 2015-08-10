#include <string>
#include <iostream>
using namespace std;

int main()
{
	string fullName = "Bobby Shaftoe";
	string nickName = "Lizard";

	cout << "Enter your first and last name:" << endl;
	getline(cin, fullName);
	cout << "Enter your nickname: " << endl;
	getline(cin, nickName);

	for (int i = 0; i < fullName.length(); i++)
	{
		if (fullName[i] == ' ')
		{
			cout << " '";
			cout << nickName;
			cout << "' ";
		}
		else
			cout << fullName[i];
	}

	return 0;
}