package com.viniciusmo.pong.shapes;

import java.awt.Rectangle;

import com.viniciusmo.pong.game.Drawable;
import com.viniciusmo.pong.utils.ConverterUtils;

public abstract class Shape implements Drawable {

	private int x;
	private int y;
	private int width;
	private int height;

	public Shape(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public boolean collides(Shape s) {
		Rectangle firstRec = ConverterUtils.convertShapeToRectangleAWT(this);
		Rectangle secondRec = ConverterUtils.convertShapeToRectangleAWT(s);
		if (firstRec.intersects(secondRec)) {
			return true;
		}
		return false;
	}

	public void updatePosition() {
		this.x = getX();
		this.y = getY();
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

}
