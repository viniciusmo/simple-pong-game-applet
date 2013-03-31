package com.viniciusmo.pong;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

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
		bar = new Rectangle(50, 0, 10, 100);
		ball = new Circle(area, 500, 100, 15, 15);
		gamePlay = true;
		gameKeyListeners.addKeyListener(new BarControllerMovement(bar));
		new Thread(this).start();
	}

	public void stopGame() {
		gamePlay = false;
		score = 0;
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
			g.drawString("Pressione a tecla espaço para iniciar o jogo",
					area.getWidth() - 550, area.getHeight() / 2);
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
			AudioClip audioClip = getAudioClip(getCodeBase(), "../res/pong.wav");
			audioClip.play();
			score += 10;
		}
	}

	@Override
	public void run() {
		while (gamePlay) {
			try {
				ball.updatePosition();
			} catch (GameOverException e) {
				AudioClip audioClip = getAudioClip(getCodeBase(),
						"../res/game_over.wav");
				audioClip.play();
				stopGame();
			}
			sleep();
			verifyCollidesWithBallAndPlaySound();
			repaint();
		}
	}
}
