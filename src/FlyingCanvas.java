/* Copyright (C) 2014 Deyuan Guo & Dawei Fan. All Rights Reserved. */

import java.awt.Color;
import java.util.List;

import javax.swing.JPanel;

/**
 * FlyingCanvas.
 * @author Dawei Fan, Deyuan Guo
 */
public class FlyingCanvas extends JPanel {

	private static final long serialVersionUID = 1L;

	private List<Term> termList;
	private Keyword[] aliveKeywords;
	final int step = 5;

	FlyingCanvas(List<Term> termList) {
		this.termList = termList;
		initKeywords();

		this.setLayout(null);
		this.setBackground(Color.BLACK);
		this.setSize(Config.getWidth(), Config.getHeight());

		this.setVisible(true);
		flyingThread.start();
	}

	private void initKeywords() {
		aliveKeywords = new Keyword[Config.getNumOfWords()];
		for (int i = 0; i < aliveKeywords.length; i++) {
			Keyword kw = newRandKeyword();
			aliveKeywords[i] = kw;
			add(kw);
		}
	}

	private Keyword newRandKeyword() {
		int idx = Config.getRandInt(termList.size());
		return new Keyword(termList.get(idx));
	}

	private Thread flyingThread = new Thread() {
		@Override
		public void run() {
			while (true) {
				for (int i = 0; i < aliveKeywords.length; i++) {
					Keyword kw = aliveKeywords[i];
					kw.updatePosition();
					if (kw.getX() > Config.getWidth()) {
						remove(kw);
						kw = newRandKeyword();
						aliveKeywords[i] = kw;
						add(kw);
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
