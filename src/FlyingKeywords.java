/* Copyright (C) 2014 Deyuan Guo & Dawei Fan. All Rights Reserved. */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.SwingUtilities;

/**
 * FlyingKeywords Main Entry.
 * @author Dawei Fan, Deyuan Guo
 */
public class FlyingKeywords extends JLayeredPane {

	private static final long serialVersionUID = 1L;

	private List<Term> termList;
	private FlyingCanvas canvas;
	private JButton fileChooser;
	private JFileChooser fc;

	FlyingKeywords() {
		this.setLayout(null);
		this.setBackground(Color.DARK_GRAY);

		addFileChooser();
		addFlyingCanvas(null);

		this.setPreferredSize(new Dimension(Config.getWidth(), Config.getHeight()));
		this.setOpaque(true);
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
	}

	private void addFileChooser() {
		fc = new JFileChooser();
		fc.setCurrentDirectory(new File("."));

		JButton b = new JButton("File");
		b.setBackground(Color.BLACK);
		b.setForeground(Color.BLACK);
		b.setFont(new Font("Lucida Grande", Font.PLAIN, 7));
		b.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (fc.showOpenDialog(fileChooser) == JFileChooser.APPROVE_OPTION) {
					File file = fc.getSelectedFile();
					addFlyingCanvas(file.getAbsolutePath());
				} else {
					return;
				}
			}
		});
		b.setSize(20, 20);
		b.setLocation(10, 10);
		b.setOpaque(true);
		fileChooser = b;
		this.add(fileChooser, new Integer(2));
	}

	private void addFlyingCanvas(String filePath) {
		if (canvas != null) this.remove(canvas);
		termList = FileIO.readTermList(filePath);
		canvas = new FlyingCanvas(termList);
		this.add(canvas, new Integer(1));
	}

	private static void createAndShowGUI() {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setBackground(Color.BLACK);

		// Remove title bar and set to full screen.
		// TODO: Should have a button to switch normal/full screen
		//frame.setUndecorated(true);
		//frame.getGraphicsConfiguration().getDevice().setFullScreenWindow(frame);

		FlyingKeywords pane = new FlyingKeywords();
		frame.setContentPane(pane);
		frame.pack();
		frame.setLocationRelativeTo(null); //center on screen

		frame.setVisible(true);
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
