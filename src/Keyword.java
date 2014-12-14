/* Copyright (C) 2014 Deyuan Guo & Dawei Fan. All Rights Reserved. */

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

/**
 * Keyword.
 * @author Dawei Fan, Deyuan Guo
 */
public class Keyword extends JLabel {

	private static final long serialVersionUID = 1L;

	private static final Color bgColor = Color.BLACK;
	private static final Color fgColor = Color.GRAY;
	private static final Color hlColor = Color.GREEN;

	private double speedX;
	private double speedY;
	private double posX;
	private double posY;
	private int fontSize;

	public Keyword(Term term) {
		this.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY)); //for debug

		this.setText(term.getWord());
		fontSize = Config.getRandFontSize();
		this.setBackground(bgColor);
		this.setForeground(fgColor);
		this.setFont(new Font("Arial", Font.BOLD, fontSize));
		this.setSize(this.getPreferredSize());

		speedX = Config.getRandSpeed() * ((double)fontSize / Config.getMaxFontSize());
		speedY = 0;

		posX = 0 - this.getWidth();
		posY = Config.getRandInt(Config.getHeight() - fontSize - 10);
		this.setLocation((int)posX, (int)posY);
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

	public double getSpeedX() { return speedX; }
	public void setSpeedX(double speed) { this.speedX = speed; }

	public int getFontSize() { return fontSize; }
	public void setFontSize(int fontSize) { this.fontSize = fontSize; }

	/**
	 * Update the location of the current keyword.
	 */
	public void updateLocation() {
		posX += speedX;
		posY += speedY;
		this.setLocation((int)posX, (int)posY);
	}

}
