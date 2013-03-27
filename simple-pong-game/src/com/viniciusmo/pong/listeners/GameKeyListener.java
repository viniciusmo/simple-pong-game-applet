package com.viniciusmo.pong.listeners;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class GameKeyListener implements KeyListener {

	private List<KeyListener> keyListeners;

	public GameKeyListener(List<KeyListener> keyListeners) {
		this.keyListeners = keyListeners;
	}

	public void addKeyListener(KeyListener key) {
		keyListeners.add(key);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		List<KeyListener> keys = new ArrayList<KeyListener>(keyListeners);
		for (KeyListener key : keys) {
			key.keyPressed(e);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		for (KeyListener key : keyListeners) {
			key.keyReleased(e);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		for (KeyListener key : keyListeners) {
			key.keyTyped(e);
		}
	}

}
