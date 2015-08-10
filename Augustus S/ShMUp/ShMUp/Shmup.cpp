// Shmup.cpp
#include "Shmup.h"

// ShmupGame Implementations

// ShmupGame constructor
ShmupGame::ShmupGame() : window(sf::VideoMode(WIDTH, HEIGHT), "Shmup"){
	window.setFramerateLimit(60);
	bg.loadFromFile("Assets/Space Sprites/stars_background.png");

	bgSprite.setTexture(bg);

}

void ShmupGame::run()
{
	sf::Clock clock;
	sf::Time timeSinceLastUpdate = sf::Time::Zero;

	playerTexture.loadFromFile("Assets/Space Sprites/player_ship.png");
	enemyTexture.loadFromFile("Assets/Space Sprites/enemy_ship.png");
	playerBulletTexture.loadFromFile("Assets/Space Sprites/player_laser.png");
	enemyBulletTexture.loadFromFile("Assets/Space Sprites/enemy_laser.png");

	player = Player(sf::Vector2f(WIDTH / 2, HEIGHT / 2), this);

	while (window.isOpen())
	{
		processEvents();
		timeSinceLastUpdate = clock.restart();
		update(timeSinceLastUpdate);

		//Drawing goes here
		render();
	}
}

void ShmupGame::update(sf::Time time){

	if (player.enabled)
		player.update(time);

	for (PlayerBullet &b : playerBullets){
		if (b.enabled){
			b.update(time);
		}
	}
	for (EnemyBullet &b : enemyBullets){
		if (b.enabled){
			b.update(time);
		}
	}

	int enemiesLeft = 0;
	for (Enemy &e : enemies){
		if (e.enabled){
			e.update(time);
			enemiesLeft++;
		}
	}

	if (enemiesLeft == 0)
		spawnEnemies(7);

}

void ShmupGame::processEvents(){
	sf::Event event;
	while (window.pollEvent(event))
	{
		if (event.type == sf::Event::Closed)
		{
			window.close();
		}
		if (event.type == sf::Event::KeyPressed)
		{
			if (event.key.code == sf::Keyboard::W)
			{
				p1MovingUp = true;
			}
			if (event.key.code == sf::Keyboard::S)
			{
				p1MovingDown = true;
			}
			if (event.key.code == sf::Keyboard::A)
			{
				p1MovingLeft = true;
			}
			if (event.key.code == sf::Keyboard::D)
			{
				p1MovingRight = true;
			}
			if (event.key.code == sf::Keyboard::Space)
			{
				firing = true;
			}

		}
		if (event.type == sf::Event::KeyReleased)
		{
			if (event.key.code == sf::Keyboard::W)
			{
				p1MovingUp = false;
			}
			if (event.key.code == sf::Keyboard::S)
			{
				p1MovingDown = false;
			}
			if (event.key.code == sf::Keyboard::A)
			{
				p1MovingLeft = false;
			}
			if (event.key.code == sf::Keyboard::D)
			{
				p1MovingRight = false;
			}
			if (event.key.code == sf::Keyboard::Space)
			{
				firing = false;
			}
		}
	}
}

void ShmupGame::render(){
	window.clear();

	window.draw(bgSprite);

	if (player.enabled)
		window.draw(player.sprite);

	for (PlayerBullet bullet : playerBullets){
		if (bullet.enabled){
			window.draw(bullet.sprite);
		}
	}

	for (Enemy e : enemies){
		if (e.enabled){
			window.draw(e.sprite);
		}
	}

	for (EnemyBullet bullet : enemyBullets){
		if (bullet.enabled){
			window.draw(bullet.sprite);
		}
	}

	window.display();
}

void ShmupGame::spawnEnemies(int count){
	if (count > 10)
		count = 10;
	for (int i = 0; i < count; i++){

		if (i < 5){
			enemies[i] = Enemy(sf::Vector2f(i * 100, 50), this);
		}
		else{
			enemies[i] = Enemy(sf::Vector2f(WIDTH - ((i - 5) * 100), 150), this);
			enemies[i].movingRight = false;
		}
		enemies[i].enabled = true;
		enemies[i].nextShotTime = rand() % 6;


	}
}


// GameObject Implementations

GameObject::GameObject() : GameObject::GameObject(sf::Vector2f(0, 0), NULL){
}


GameObject::GameObject(sf::Vector2f position, ShmupGame* game){
	this->game = game;
	this->sprite.setPosition(position);

	// Code here
}

void GameObject::update(sf::Time timeElapsed){

}

bool GameObject::isColliding(GameObject* g){
	return (g->enabled && sprite.getGlobalBounds().intersects(g->sprite.getGlobalBounds()));
}

// Player Implementations

Player::Player() : GameObject(){

}

Player::Player(sf::Vector2f position, ShmupGame* game) : GameObject(position, game){

	enabled = true;
	sprite.setTexture(game->playerTexture);
	sprite.setOrigin(32, 32);

}

void Player::update(sf::Time timeElapsed){
	sf::Vector2f p1Movement(0, 0);

	if (this->game->p1MovingUp)
		p1Movement.y -= PLAYER_SPEED;
	if (this->game->p1MovingDown)
		p1Movement.y += PLAYER_SPEED;
	if (this->game->p1MovingLeft)
		p1Movement.x -= PLAYER_SPEED;
	if (this->game->p1MovingRight)
		p1Movement.x += PLAYER_SPEED;
	if (this->game->firing && shotTimer.getElapsedTime().asSeconds() > 0.25) {
		fire();
		shotTimer.restart();
	}

	sprite.move(p1Movement * timeElapsed.asSeconds());
}

void Player::fire(){

	game->playerBullets[game->currentBullet].sprite.setPosition(this->sprite.getPosition());
	game->playerBullets[game->currentBullet].enabled = true;
	game->playerBullets[game->currentBullet].velocity = sf::Vector2f(0, -BULLET_SPEED);
	if (game->playerBullets[game->currentBullet].game == NULL){
		// If we haven't used this bullet yet set a couple values on it
		game->playerBullets[game->currentBullet].game = game;
		game->playerBullets[game->currentBullet].sprite.setTexture(game->playerBulletTexture);
	}
	game->currentBullet++;
	if (game->currentBullet == 50){
		game->currentBullet = 0;
	}

}

// Enemy Implementations

Enemy::Enemy() : GameObject(){

}

Enemy::Enemy(sf::Vector2f position, ShmupGame* game) : GameObject(position, game){

	sprite.setTexture(game->enemyTexture);
	sprite.setOrigin(32, 32);

}

void Enemy::update(sf::Time timeElapsed){

	sf::Vector2f velocity(0, 0);

	if (movingDown){
		if (moveTimer.getElapsedTime().asSeconds() > 1){
			movingDown = false;
			movingRight = !movingRight;
		}
		else{
			velocity.y = ENEMY_SPEED;
		}

	}
	else if (movingRight){
		if (sprite.getPosition().x > WIDTH - 100){
			movingDown = true;
			moveTimer.restart();
		}
		else {
			velocity.x = ENEMY_SPEED;
		}
	}
	else {
		if (sprite.getPosition().x < 100){
			movingDown = true;
			moveTimer.restart();
		}
		else {
			velocity.x = -ENEMY_SPEED;
		}
	}
    

		if (fireTimer.getElapsedTime().asSeconds() > nextShotTime){
			nextShotTime = (rand() % 100 + 1) / 10.f;
			fireTimer.restart();
			fire();
		}

		sprite.move(velocity * timeElapsed.asSeconds());
}

void Enemy::fire() {

	game->enemyBullets[game->currentEnemyBullet].sprite.setPosition(this->sprite.getPosition());
	game->enemyBullets[game->currentEnemyBullet].enabled = true;
	game->enemyBullets[game->currentEnemyBullet].velocity = sf::Vector2f(0, BULLET_SPEED);
	if (game->enemyBullets[game->currentEnemyBullet].game == NULL){
		// If we haven't used this bullet yet set a couple values on it
		game->enemyBullets[game->currentEnemyBullet].game = game;
		game->enemyBullets[game->currentEnemyBullet].sprite.setTexture(game->enemyBulletTexture);
	}
	game->currentEnemyBullet++;
	if (game->currentEnemyBullet == 50){
		game->currentEnemyBullet = 0;
	}
}

// PlayerBullet Implementations

PlayerBullet::PlayerBullet() : GameObject(){

}

void PlayerBullet::update(sf::Time timeElapsed){
	sprite.move(velocity * timeElapsed.asSeconds());
	
	if (sprite.getPosition().y < 0 || sprite.getPosition().y > HEIGHT ||
		sprite.getPosition().x < 0 || sprite.getPosition().x > WIDTH) {
		enabled = false;
	}

	for (Enemy &e : game->enemies) {
		if (isColliding(&e)) {
			enabled = false;
			e.enabled = false;
		}
	}
}

// EnemyBullet Implementations

EnemyBullet::EnemyBullet() : GameObject(){

}

void EnemyBullet::update(sf::Time timeElapsed){
	sprite.move(velocity * timeElapsed.asSeconds());
	
	if (sprite.getPosition().y < 0 || sprite.getPosition().y > HEIGHT ||
		sprite.getPosition().x < 0 || sprite.getPosition().x > WIDTH) {
		enabled = false;
	}

	if (isColliding(&game->player)){
		enabled = false;
		game->player.enabled = false;
	}
}