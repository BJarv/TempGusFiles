#include <SFML\Graphics.hpp>
#include <iostream>
using namespace std;

int main()
{
	sf::RenderWindow window(sf::VideoMode(400, 400), "Text");

	sf::Text myText;
	sf::Font myFont;

	myFont.loadFromFile("C:/Windows/Fonts/arial.ttf");

	myText.setFont(myFont);
	myText.setString("Rubber Duckies");
	myText.setCharacterSize(24);
	myText.setColor(sf::Color::Red);

	myText.setStyle(sf::Text::Bold);

	cout << "Outputting to the Console" << endl;

	while (window.isOpen())
	{
		sf::Event event;
		while (window.pollEvent(event))
		{
			if (event.type == sf::Event::Closed)
				window.close();
		}

		myText.move(0.01f, 0);
		if (myText.getPosition().x > 400)
			myText.setPosition(-200, 0);

		window.clear();
		window.draw(myText);
		window.display();
	}

	return 0;
}