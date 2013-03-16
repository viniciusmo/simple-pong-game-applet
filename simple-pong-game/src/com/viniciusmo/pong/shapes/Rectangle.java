package com.viniciusmo.pong.shapes;
import java.awt.Color;
import java.awt.Graphics;


public class Rectangle extends Shape {

	public Rectangle(int x, int y, int width, int height) {
		super(x, y, width, height);
	}

	@Override
	public void draw(Graphics graphics) {
		graphics.setColor(Color.white);
		graphics.drawRect(getX(), getY(), getWidth(), getHeight());
		graphics.fillRect(getX(), getY(), getWidth(), getHeight());
	}

}
