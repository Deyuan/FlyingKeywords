/* Copyright (C) 2014 Deyuan Guo & Dawei Fan. All Rights Reserved. */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;

/**
 * Keyword.
 * @author Dawei Fan, Deyuan Guo
 */
public class Keyword extends JLabel {

	private static final long serialVersionUID = 1L;

	public static final Color bgColor = Color.BLACK;
	public static final Color fgColor = Color.GRAY;
	public static final Color hlColor = Color.GREEN;

	private int speedX;
	private int speedY;
	private int posX;
	private int posY;
	private int fontSize;

	public Keyword(Term term) {
		super(term.getWord());
		speedX = Config.getRandSpeed();
		speedY = 0;
		fontSize = Config.getRandFontSize();

		this.setBackground(bgColor);
		this.setForeground(fgColor);
		this.setFont(new Font("Arial", Font.BOLD, fontSize));
		this.setSize(new Dimension(term.getWord().length()*fontSize, fontSize+5));
		posX = 0 - term.getWord().length() * fontSize;
		posY = Config.getRandInt(Config.getHeight() - fontSize);
		this.setLocation(posX, posY);
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

	public int getSpeedX() { return speedX; }
	public void setSpeedX(int speed) { this.speedX = speed; }

	public int getFontSize() { return fontSize; }
	public void setFontSize(int fontSize) { this.fontSize = fontSize; }

	public void updatePosition() {
		posX += speedX;
		posY += speedY;
		this.setLocation(posX, posY);
	}

}
