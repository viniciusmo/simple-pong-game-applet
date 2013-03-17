package com.viniciusmo.pong;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;

import com.viniciusmo.pong.audio.PlayerMP3;
import com.viniciusmo.pong.game.GameArea;
import com.viniciusmo.pong.game.GameOverException;
import com.viniciusmo.pong.game.Moveable;
import com.viniciusmo.pong.listeners.BarControllerMovement;
import com.viniciusmo.pong.shapes.Circle;
import com.viniciusmo.pong.shapes.Rectangle;
import com.viniciusmo.pong.shapes.Shape;

public class PongGame extends Applet implements Runnable {

	private static final long serialVersionUID = 1L;
	private Shape bar;
	private Shape ball;
	private GameArea area;
	private boolean gamePlay;
	private int score;

	public void init() {
		area = new GameArea(800, 600);
		bar = new Rectangle(100, 0, 10, 150);
		ball = new Circle(area, 500, 100, 15, 15);
		setSize(area.getWidth(), area.getHeight());
		setBackground(Color.black);
		addKeyListener(new BarControllerMovement(bar));
		new Thread(this).start();
		startGame();
	}

	public void startGame() {
		gamePlay = true;
	}

	public void stopGame() {
		gamePlay = false;
	}

	public void paint(Graphics g) {
		bar.draw(g);
		ball.draw(g);
		g.setColor(Color.WHITE);
		g.drawString("Score: " + score, 20, 20);
		g.drawString("by viniciusmo", getWidth() - 100, 20);
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
