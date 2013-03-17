package com.viniciusmo.pong;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import com.viniciusmo.pong.audio.PlayerMP3;
import com.viniciusmo.pong.game.GameArea;
import com.viniciusmo.pong.game.GameOverException;
import com.viniciusmo.pong.game.Moveable;
import com.viniciusmo.pong.listeners.BarControllerMovement;
import com.viniciusmo.pong.listeners.GameControllerKeys;
import com.viniciusmo.pong.listeners.GameKeyListener;
import com.viniciusmo.pong.shapes.Circle;
import com.viniciusmo.pong.shapes.Rectangle;
import com.viniciusmo.pong.shapes.Shape;

public class PongGame extends Applet implements Runnable {

	private static final long serialVersionUID = 1L;
	private Shape bar;
	private Shape ball;
	private GameArea area;
	private GameKeyListener gameKeyListeners;
	private boolean gamePlay;
	private int score;

	public void init() {
		area = new GameArea(800, 600);
		setSize(area.getWidth(), area.getHeight());
		setBackground(Color.black);
		addKeyListeners();
	}

	private void addKeyListeners() {
		List<KeyListener> keyListeners = new ArrayList<KeyListener>();
		keyListeners.add(new GameControllerKeys(this));
		gameKeyListeners = new GameKeyListener(keyListeners);
		addKeyListener(gameKeyListeners);
	}

	public void startGame() {
		bar = new Rectangle(100, 0, 10, 150);
		ball = new Circle(area, 500, 100, 15, 15);
		gamePlay = true;
		gameKeyListeners.addKeyListener(new BarControllerMovement(bar));
		new Thread(this).start();
	}

	public void stopGame() {
		gamePlay = false;
	}

	public boolean isPlayingGame() {
		return gamePlay;
	}

	public void paint(Graphics g) {
		if (gamePlay) {
			bar.draw(g);
			ball.draw(g);
			g.setColor(Color.WHITE);
			g.drawString("Score: " + score, 20, 20);
			g.drawString("by viniciusmo", getWidth() - 100, 20);
		} else {
			g.setColor(Color.WHITE);
			g.drawString("Pressione a tecla espa�o para iniciar o jogo", 20, 20);
		}
	}

	private void sleep() {
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void verifyCollidesWithBallAndPlaySound() {
		if (bar.collides(ball)) {
			Moveable mv = (Moveable) ball;
			mv.changeDirectionX();
			PlayerMP3 player = new PlayerMP3("../res/pong.mp3");
			player.play();
			score += 10;
		}
	}

	@Override
	public void run() {
		while (gamePlay) {
			try {
				ball.updatePosition();
			} catch (GameOverException e) {
				PlayerMP3 player = new PlayerMP3("../res/game_over.mp3");
				player.play();
				stopGame();
			}
			sleep();
			verifyCollidesWithBallAndPlaySound();
			repaint();
		}
	}
}
