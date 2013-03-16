package com.viniciusmo.pong.game;
public class GameArea {

	private int height;
	private int width;

	public GameArea(int widht, int height) {
		this.height = height;
		this.width = widht;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
}
