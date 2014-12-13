import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.JLabel;

/**
 * A class to describe a keyword instance.
 *
 * @author Davwei
 * @version 0.1
 *
 */
public class Keyword extends JLabel{

	private static final long serialVersionUID = 1L;
	public static final int MAX_FONT = 100;
	public static final int MIN_FONT = 30;
	public static final int MAX_SPEED = 20;
	public static final int MIN_SPEED = 10;

	/** Background color. */
	public static final Color bgColor = Color.BLACK;
	/** Foreground color. */
	public static final Color fgColor = Color.DARK_GRAY;
	/** Highlight color. */
	public static final Color hlColor = Color.GREEN;

	private String name;
	private int y;
	private int speed;
	private int fontSize;

	/** Size of parent dimension, {width, height}. */
	private int dimension[];


	public Keyword(String s, int d[]){
		super(s);
		Random r = new Random();
		dimension = new int[2];

		dimension[0] = d[0];
		dimension[1] = d[1];
		this.name = s;

		setSpeed(MIN_SPEED + r.nextInt(MAX_SPEED-MIN_SPEED+1));
		fontSize = MIN_FONT + r.nextInt(MAX_FONT-MIN_FONT+1);
		y = r.nextInt(dimension[1]-fontSize-15);

		this.setBackground(bgColor);
		this.setForeground(fgColor);
		this.setFont(new Font("Lucida Grande", Font.PLAIN, fontSize));
		this.setSize(new Dimension(s.length()*fontSize, fontSize+5));
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


	public int[] getDimension() {
		return dimension;
	}


	public void setDimension(int dimension[]) {
		this.dimension = dimension;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getFontSize() {
		return fontSize;
	}

	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
	}

	@Override
	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
}
