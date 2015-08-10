#include <SFML/Graphics.hpp>

int main()
{
	sf::RenderWindow window(sf::VideoMode(200, 200), "Sprites!");

	sf::Sprite mySprite;
	sf::Texture myTexture;
	myTexture.loadFromFile("Textures/player_ship.png");

	mySprite.setTexture(myTexture);

	float rotation = 0;

	while (window.isOpen())
	{
		sf::Event event;
		while (window.pollEvent(event))
		{
			if (event.type == sf::Event::Closed)
				window.close();
		}

		window.clear();
		window.draw(mySprite);
		window.display();
	}

	return 0;
}