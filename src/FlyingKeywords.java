/* Copyright (C) 2014 Deyuan Guo & Dawei Fan. All Rights Reserved. */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 * FlyingKeywords Main Entry.
 * @author Dawei Fan, Deyuan Guo
 */
public class FlyingKeywords extends JLayeredPane {

	private static final long serialVersionUID = 1L;

	private JFrame parentFrame;
	private List<Term> termList;
	private FlyingCanvas canvas;
	private JButton bExit;
	private JButton bFileChooser;
	private JFileChooser fc;
	private JButton bFullScreen;
	private boolean isFullScreen;

	FlyingKeywords(JFrame frame) {
		parentFrame = frame;
		isFullScreen = false;
		this.setLayout(null);
		this.setBackground(Color.DARK_GRAY);

		addFlyingCanvas(null);
		addExitButton();
		addFileChooserButton();
		addFullScreenButton();

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

	private JButton buttonTemplate(String text, String tip, int x, int y) {
		JButton b = new JButton(text) {
			private static final long serialVersionUID = 1L;

			@Override
			public Point getToolTipLocation(MouseEvent e) {
				return new Point(0, 25);
			}
		};
		b.setToolTipText(tip);
		b.setFocusable(false);
		b.setBackground(Color.BLACK);
		b.setForeground(Color.GRAY);
		b.setFont(new Font("Arial", Font.BOLD, 10));
		b.setMargin(new Insets(0,0,0,1));
		b.setLocation(x, y);
		b.setSize(20, 20);
		b.setOpaque(true);
		return b;
	}

	/**
	 * Add file chooser button to the main pane.
	 */
	private void addExitButton() {
		bExit = buttonTemplate("E", "Exit <Alt+E>", 10, 10);
		bExit.setMnemonic(KeyEvent.VK_E);
		bExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		this.add(bExit, new Integer(2));
	}

	/**
	 * Add file chooser button to the main pane.
	 */
	private void addFileChooserButton() {
		fc = new JFileChooser();
		fc.setCurrentDirectory(new File("./text"));

		bFileChooser = buttonTemplate("R", "Read File... <Alt+R>", 35, 10);
		bExit.setMnemonic(KeyEvent.VK_R);
		bFileChooser.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (fc.showOpenDialog(bFileChooser) == JFileChooser.APPROVE_OPTION) {
					File file = fc.getSelectedFile();
					addFlyingCanvas(file.getAbsolutePath());
				} else {
					return;
				}
			}
		});
		this.add(bFileChooser, new Integer(2));
	}

	/**
	 * Add full screen button to the main pane.
	 */
	private void addFullScreenButton() {
		bFullScreen = buttonTemplate("F", "Full Screen <Alt+F>", 60, 10);
		bExit.setMnemonic(KeyEvent.VK_F);
		bFullScreen.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				switchFullScreen();
			}
		});
		this.add(bFullScreen, new Integer(2));
	}

	/**
	 * Switch between full screen mode and normal mode.
	 */
	private void switchFullScreen() {
		GraphicsDevice device = this.getGraphicsConfiguration().getDevice();
		if (isFullScreen) {
			device.setFullScreenWindow(null);
			Config.setWidth(1024);
			Config.setHeight(768);
			canvas.resize();
			isFullScreen = false;
		} else {
			device.setFullScreenWindow(parentFrame);
			Config.setWidth(parentFrame.getWidth());
			Config.setHeight(parentFrame.getHeight());
			canvas.resize();
			isFullScreen = true;
		}
	}

	/**
	 * Generate termList, and create keyword canvas.
	 * @param filePath
	 */
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

		FlyingKeywords pane = new FlyingKeywords(frame);
		frame.setContentPane(pane);
		frame.pack();
		frame.setLocationRelativeTo(null); //center on screen

		frame.setVisible(true);
	}

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(
					UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				createAndShowGUI();
			}
		});
	}

}
