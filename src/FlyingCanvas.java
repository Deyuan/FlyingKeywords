/* Copyright (C) 2014 Deyuan Guo & Dawei Fan. All Rights Reserved. */

import java.awt.Color;
import java.util.List;

import javax.swing.JPanel;

/**
 * FlyingCanvas, a JPanel containing all the flying words.
 * @author Dawei Fan, Deyuan Guo
 */
public class FlyingCanvas extends JPanel {

	private static final long serialVersionUID = 1L;

	private List<Term> termList;
	private Keyword[] aliveKeywords;
	final int step = 5;

	FlyingCanvas(List<Term> termList) {
		this.setLayout(null);
		this.termList = termList;
		initKeywords();

		this.setBackground(Color.BLACK);
		this.setLocation(5, 5);
		this.resize();
		this.setOpaque(true);

		this.setVisible(true);
		flyingThread.start();
	}

	public void resize() {
		this.setSize(Config.getWidth() - 10, Config.getHeight() - 10);
	}

	/**
	 * Initialize all the keyword slots.
	 */
	private void initKeywords() {
		if (termList.size() == 0) return;
		aliveKeywords = new Keyword[Config.getNumOfWords()];
		for (int i = 0; i < aliveKeywords.length; i++) {
			newRandKeyword(i);
		}
	}

	/**
	 * Add one new random keyword into the slot, and add to screen.
	 * @param slot
	 */
	private void newRandKeyword(int slot) {
		int termId = Config.getRandInt(termList.size());
		Term term = termList.get(termId);
		//TODO: deal with empty string
		Keyword kw = new Keyword(term);
		aliveKeywords[slot] = kw;
		add(kw);
	}

	/**
	 * A thread keeping updating all the keywords.
	 */
	private Thread flyingThread = new Thread() {
		@Override
		public void run() {
			while (aliveKeywords != null) {
				for (int i = 0; i < aliveKeywords.length; i++) {
					Keyword kw = aliveKeywords[i];
					kw.updateLocation();
					if (kw.getX() > Config.getWidth()) {
						remove(kw);
						newRandKeyword(i);
					}
				}

				try {
					Thread.sleep(step);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	};

}
