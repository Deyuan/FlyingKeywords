/* Copyright (C) 2014 Deyuan Guo & Dawei Fan. All Rights Reserved. */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.JLabel;

/**
 * Keyword.
 * @author Dawei Fan, Deyuan Guo
 */
public class Keyword extends JLabel {

	private static final long serialVersionUID = 1L;
	public static final int MAX_FONT = 50;
	public static final int MIN_FONT = 10;
	public static final int MAX_SPEED = 20;
	public static final int MIN_SPEED = 10;

	public static final Color bgColor = Color.BLACK;
	public static final Color fgColor = Color.GRAY;
	public static final Color hlColor = Color.GREEN;

	private int y;
	private int speed;
	private int fontSize;

	/** Size of parent dimension, {width, height}. */
	Dimension pDim = null;

	public Keyword(Term term, Dimension dim) {
		super(term.getWord());
		Random r = new Random();
		pDim = dim;

		setSpeed(MIN_SPEED + r.nextInt(MAX_SPEED-MIN_SPEED+1));
		fontSize = MIN_FONT + r.nextInt(MAX_FONT-MIN_FONT+1);
		y = r.nextInt(pDim.height-fontSize-15);

		this.setBackground(bgColor);
		this.setForeground(fgColor);
		this.setFont(new Font("Lucida Grande", Font.PLAIN, fontSize));
		this.setSize(new Dimension(term.getWord().length()*fontSize, fontSize+5));
		this.setLocation(-20, y);
		this.setVisible(true);
		this.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				setForeground(hlColor);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				setForeground(fgColor);
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
	}

	public Dimension getDimension() { return pDim; }
	public void setDimension(Dimension dim) { pDim = dim; }

	public int getSpeed() { return speed; }
	public void setSpeed(int speed) { this.speed = speed; }

	public int getFontSize() { return fontSize; }
	public void setFontSize(int fontSize) { this.fontSize = fontSize; }

	@Override
	public int getY() { return y; }
	public void setY(int y) { this.y = y; }

}
