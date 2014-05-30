package com.teamcriminals.Game;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import com.teamcriminals.GameState.GameStateManager;


public class GamePanel extends JPanel implements Runnable, KeyListener {
	// ����~
	private static final long serialVersionUID = 1L;

	// have to change the display resolution
	// ȭ�� ������ ����
	public static final int WIDTH = 1024;
	public static final int HEIGHT = 768;
	Dimension dimension = new Dimension(WIDTH, HEIGHT);

	// ������, FPS ����
	private Thread thread;
	private boolean isRunning = false;
	private int FPS = 60;
	private long targetTime = 1000 / FPS;

	// image
	private BufferedImage image;
	private Graphics2D g;

	// ���ӽ�����Ʈ�Ŵ��� ���� ���� ����
	private GameStateManager gsm;

	public GamePanel() {
		setPreferredSize(dimension);

		setFocusable(true);
		requestFocus();

		// run(); --> this occurs exception!
	}

	public void addNotify() {
		super.addNotify();
		if (thread == null) {
			thread = new Thread(this);
			addKeyListener(this);
			thread.start();
		}
	}

	public void init() {
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D) image.getGraphics();

		isRunning = true;

		gsm = new GameStateManager();
	}

	public void run() {

		init();

		long start;
		long elapsed;
		long wait;

		// ���� ���� ����
		while (isRunning) {

			start = System.nanoTime();

			update();
			draw();
			drawToScreen();

			elapsed = System.nanoTime() - start;
			wait = targetTime - elapsed / 1000000;

			if (wait < 0) {
				wait = 5;
			}

			try {
				Thread.sleep(wait);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void update() {
		gsm.update();
	}

	private void draw() {
		gsm.draw(g);
	}

	private void drawToScreen() {
		Graphics g2 = getGraphics();
		g2.drawImage(image, 0, 0, WIDTH, HEIGHT, null);
		g2.dispose();
	}

	public void keyPressed(KeyEvent e) {
		gsm.KeyPressed(e.getKeyCode());
	}

	public void keyReleased(KeyEvent e) {
		gsm.KeyReleased(e.getKeyCode());
	}

	public void keyTyped(KeyEvent e) {
	}
}
