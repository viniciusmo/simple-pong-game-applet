package com.viniciusmo.pong.audio;

import java.io.BufferedInputStream;
import java.io.FileInputStream;

import javazoom.jl.player.Player;

public class PlayerMP3 {
	private String filename;
	private Player player;

	public PlayerMP3(String filename) {
		this.filename = filename;
	}

	public void close() {
		if (player != null)
			player.close();
	}

	public void play() {
		try {
			FileInputStream fis = new FileInputStream(filename);
			BufferedInputStream bis = new BufferedInputStream(fis);
			player = new Player(bis);
		} catch (Exception e) {
			e.printStackTrace();
		}

		new Thread() {
			public void run() {
				try {
					player.play();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}.start();

	}
}
