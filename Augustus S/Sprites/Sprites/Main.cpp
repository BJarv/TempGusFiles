#include <SFML/Graphics.hpp>

int main()
{
	sf::RenderWindow window(sf::VideoMode(200, 200), "Sprites!");

	sf::Sprite mySprite;
	sf::Texture myTexture;
	myTexture.loadFromFile("Textures/player_ship.png");

	mySprite.setTexture(myTexture);
	mySprite.setOrigin(32, 32);
	mySprite.setScale(1.5, 1.5);
	mySprite.setPosition(100, 100);

	float rotation = 0;

	while (window.isOpen())
	{
		mySprite.setRotation(rotation);
		rotation += 0.01;
		if (rotation > 360)
			rotation -= 360;
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