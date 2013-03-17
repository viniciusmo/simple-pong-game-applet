package com.viniciusmo.pong.shapes;

import java.awt.Color;
import java.awt.Graphics;

import com.viniciusmo.pong.game.GameArea;
import com.viniciusmo.pong.game.GameOverException;
import com.viniciusmo.pong.game.Moveable;

public class Circle extends Shape implements Moveable {
	private int xdirection;
	private int ydirection;
	private static final int X_SPEED = 16;
	private static final int Y_SPEED = 24;
	private GameArea area;

	public Circle(GameArea area, int x, int y, int width, int height) {
		super(x, y, width, height);
		this.xdirection = 1;
		this.ydirection = -1;
		this.area = area;
	}

	@Override
	public void draw(Graphics graphics) {
		graphics.setColor(Color.RED);
		graphics.drawOval(getX(), getY(), getWidth(), getHeight());
		graphics.fillOval(getX(), getY(), getWidth(), getHeight());
	}

	public void updatePosition() {
		int x = getX();
		int y = getY();
		x = x + (X_SPEED * xdirection);
		y = y + (Y_SPEED * ydirection);
		updateDirections(x, y);
	}

	private void updateDirections(int x, int y) {
		if (x < 0) {
			throw new GameOverException();
		}
		xdirection = (x > area.getWidth() - getWidth()) ? -1 : xdirection;
		ydirection = (y < 0) ? 1 : ydirection;
		ydirection = (y > area.getHeight() - getHeight()) ? -1 : ydirection;
		setX(x);
		setY(y);
	}

	@Override
	public void changeDirectionX() {
		xdirection = xdirection * -1;
	}

	@Override
	public void changeDirectionY() {
		ydirection = ydirection * -1;
	}
}
