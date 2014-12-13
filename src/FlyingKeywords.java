/* Copyright (C) 2014 Deyuan Guo & Dawei Fan. All Rights Reserved. */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * FlyingKeywords.
 * @author Dawei Fan, Deyuan Guo
 */
public class FlyingKeywords extends JPanel {

	private static final long serialVersionUID = 1L;

	// TODO: font size and speed should be a function of window size.
	public static final int MAX_FONT = 100;
	public static final int MIN_FONT = 30;
	public static final int MAX_SPEED = 20;
	public static final int MIN_SPEED = 10;
	public int width;
	public int height;
	public List<String> words;

	FlyingKeywords() {
		this.setLayout(null);
		this.setBackground(Color.BLACK);
		this.setVisible(true);
		this.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if (e.getClickCount() == 2 && !e.isConsumed())
					System.exit(0);
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

		});

		/** Initialize words. */
		initializeWords();
	}

	public void initializeWords() {
		words = new ArrayList<String>();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(
					"text/google-10000-english.txt"));
		} catch (FileNotFoundException e) {
			System.err.println("File Not Found!");
			e.printStackTrace();
		}

		try {
			while (br.ready())
				words.add(br.readLine());
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	class FlyingThread extends Thread {
		String s;
		int speed;
		int font;
		int y;
		final int step = 5;
		FlyingKeywords fk;

		FlyingThread(FlyingKeywords fk, String s) {
			this.fk = fk;
			this.s = s;
			Random r = new Random();
			speed = MIN_SPEED + r.nextInt(MAX_SPEED - MIN_SPEED + 1);
			font = MIN_FONT + r.nextInt(MAX_FONT - MIN_FONT + 1);
			y = r.nextInt(fk.height - font - 15);
		}

		@Override
		public void run() {
			final JLabel lw = new JLabel(s);
			lw.setBackground(Color.BLACK);
			lw.setFont(new Font("Lucida Grande", Font.PLAIN, font));
			lw.setForeground(Color.DARK_GRAY);
			lw.setSize(new Dimension(s.length() * font, font + 5));
			lw.setLocation(-20, y);
			lw.setVisible(true);
			lw.addMouseListener(new MouseListener() {

				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub
					lw.setForeground(Color.GREEN);
				}

				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub
					lw.setForeground(Color.DARK_GRAY);
				}

				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub

				}

			});
			fk.add(lw);
			for (int i = 0; i < (fk.width + 2 * step) / step; i++) {
				lw.setLocation(step * i, y);
				try {
					Thread.sleep(speed);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				repaint();
			}
		}
	}

	public void flyAWord() {
		Random r = new Random();
		FlyingThread ft = new FlyingThread(this, words.get(r.nextInt(words
				.size())));
		ft.start();
	}

	public void flyWords() {

		new Thread(new Runnable() {

			@Override
			public void run() {
				Random r = new Random();
				while (true) {
					flyAWord();
					try {
						Thread.sleep(r.nextInt(500) + 200);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}

		}).start();

	}

	private static void createAndShowGUI() {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setSize(800, 600); //default window size
		frame.setLocationRelativeTo(null); //center on screen

		// Remove title bar and set to full screen.
		// Todo: Should have a button to switch normal/full screen
		//frame.setUndecorated(true);
		//frame.getGraphicsConfiguration().getDevice().setFullScreenWindow(frame);

		FlyingKeywords fk = new FlyingKeywords();
		fk.width = frame.getWidth();
		fk.height = frame.getHeight();
		frame.setContentPane(fk);

		frame.setVisible(true);
		fk.flyWords();
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				createAndShowGUI();
			}
		});
	}
}
