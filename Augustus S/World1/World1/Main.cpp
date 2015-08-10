#include <SFML/Graphics.hpp>

int main()
{
	sf::RenderWindow window(sf::VideoMode(400, 400), "Camera");
	sf::Texture backText;
	backText.loadFromFile("Textures/grass-texture-background-green.jpg");
	sf::Texture playerText;
	playerText.loadFromFile("Textures/player_ship.png");
	sf::Sprite backSprite;
	backSprite.setTexture(backText);

	sf::Sprite playerSprite;
	playerSprite.setTexture(playerText);
	playerSprite.setOrigin(32, 32);
	

	sf::Clock clock;


	sf::View camera(sf::FloatRect(50, 50, 150, 150));
	camera.setSize(500, 500);
	camera.setCenter(0, 0);


	bool isMovingUp = false;
	bool isMovingDown = false;
	bool isMovingLeft = false;
	bool isMovingRight = false;

	while (window.isOpen())
	{
		sf::Event event;
		while (window.pollEvent(event))
		{
			if (event.type == sf::Event::Closed)
				window.close();

			if (event.type == sf::Event::KeyPressed)
			{
				if (event.key.code == sf::Keyboard::W)
				{
					isMovingUp = true;
				}
				if (event.key.code == sf::Keyboard::S)
				{
					isMovingDown = true;
				}
				if (event.key.code == sf::Keyboard::A)
				{
					isMovingLeft = true;
				}
				if (event.key.code == sf::Keyboard::D)
				{
					isMovingRight = true;
				}
			}
			if (event.type == sf::Event::KeyReleased)
			{
				if (event.key.code == sf::Keyboard::W)
				{
					isMovingUp = false;
				}
				if (event.key.code == sf::Keyboard::S)
				{
					isMovingDown = false;
				}
				if (event.key.code == sf::Keyboard::A)
				{
					isMovingLeft = false;
				}
				if (event.key.code == sf::Keyboard::D)
				{
					isMovingRight = false;
				}
			}
		}

		sf::Vector2f movement(0, 0);
		if (isMovingUp)
			movement.y -= 200;
		if (isMovingDown)
			movement.y += 200;
		if (isMovingLeft)
			movement.x -= 200;
		if (isMovingRight)
			movement.x += 200;

		sf::Time changeInTime = clock.restart();

		playerSprite.move(movement * changeInTime.asSeconds());

		camera.setCenter(playerSprite.getPosition().x, playerSprite.getPosition().y);
		window.setView(camera);

		window.clear();
		window.draw(backSprite);
		window.draw(playerSprite);
		window.display();
	}


	return 0;
}