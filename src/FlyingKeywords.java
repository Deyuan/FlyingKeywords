/* Copyright (C) 2014 Deyuan Guo & Dawei Fan. All Rights Reserved. */

import java.awt.Color;
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
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * FlyingKeywords.
 * @author Dawei Fan, Deyuan Guo
 */
public class FlyingKeywords extends JPanel{

	private static final long serialVersionUID = 1L;

	public int width;
	public int height;
	public List<String> words;
	public Config config;
	public FlyingThread flyingThread;

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

		config = new Config();
		config.setCount(10);
		config.setForward(true);

		/** Initialize words. */
		initializeWords();
	}

	public void initializeWords() {
		words = new ArrayList<String>();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader("text/google-10000-english-fix.txt"));
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

	public void flyAWord(){

		flyingThread = new FlyingThread(this, words, config);
		flyingThread.start();
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
 		/** Set it as full screen. */
 		frame.getGraphicsConfiguration().getDevice().setFullScreenWindow(frame);
		FlyingKeywords ft = new FlyingKeywords();
 		ft.width = frame.getWidth();
 		ft.height = frame.getHeight();
		frame.setContentPane(ft);

		ft.flyAWord();
//		ft.flyWords();
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
