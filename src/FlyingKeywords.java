/* Copyright (C) 2014 Deyuan Guo & Dawei Fan. All Rights Reserved. */

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * FlyingKeywords Main Entry.
 * @author Dawei Fan, Deyuan Guo
 */
public class FlyingKeywords extends JPanel {

	private static final long serialVersionUID = 1L;

	private List<Term> termList;
	private FlyingThread flyingThread;

	FlyingKeywords() {
		this.setLayout(null);
		this.setBackground(Color.BLACK);
		termList = FileIO.readTermList("text/google-10000-english-fix.txt");

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

		this.setVisible(true);
	}

	public void flyAWord() {
		flyingThread = new FlyingThread(this, termList);
		flyingThread.start();
	}

	private static void createAndShowGUI() {
		// Configuration
		Config.setNumOfWords(10);
		Config.setForward(true);
		Config.setWidth(800);
		Config.setHeight(600);

		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBackground(Color.BLACK);
		frame.setResizable(false);
		frame.setSize(Config.getWidth(), Config.getHeight());
		frame.setLocationRelativeTo(null); //center on screen

		// Remove title bar and set to full screen.
		// Todo: Should have a button to switch normal/full screen
		//frame.setUndecorated(true);
		//frame.getGraphicsConfiguration().getDevice().setFullScreenWindow(frame);

		FlyingKeywords pane = new FlyingKeywords();
		frame.setContentPane(pane);

		frame.setVisible(true);

		pane.flyAWord();
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
