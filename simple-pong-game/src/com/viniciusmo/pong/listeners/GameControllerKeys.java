package com.viniciusmo.pong.listeners;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.viniciusmo.pong.PongGame;

public class GameControllerKeys implements KeyListener {
	private PongGame pong;
	private static final int SPACE_KEY_CODE = 32;

	public GameControllerKeys(PongGame pong) {
		this.pong = pong;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		boolean notPlayingGame = !pong.isPlayingGame();
		if (e.getKeyCode() == SPACE_KEY_CODE && notPlayingGame) {
			pong.startGame();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
