/* Copyright (C) 2014 Deyuan Guo & Dawei Fan. All Rights Reserved. */

import java.util.List;

/**
 * FlyingThread.
 * @author Dawei Fan, Deyuan Guo
 */
public class FlyingThread extends Thread {

	private List<Term> termList;
	private Keyword[] aliveKeywords;
	private FlyingKeywords container;
	final int step = 5;

	FlyingThread(FlyingKeywords container, List<Term> termList) {
		this.container = container;
		this.termList = termList;
		initKeywords();
	}

	private void initKeywords() {
		aliveKeywords = new Keyword[Config.getNumOfWords()];
		for (int i = 0; i < aliveKeywords.length; i++) {
			Keyword kw = newRandKeyword();
			aliveKeywords[i] = kw;
			container.add(kw);
		}
	}

	private Keyword newRandKeyword() {
		int idx = Config.getRandInt(termList.size());
		return new Keyword(termList.get(idx));
	}

	@Override
	public void run() {
		while (true) {
			for (int i = 0; i < aliveKeywords.length; i++) {
				Keyword kw = aliveKeywords[i];
				kw.updatePosition();
				if (kw.getX() > Config.getWidth()) {
					container.remove(kw);
					kw = newRandKeyword();
					aliveKeywords[i] = kw;
					container.add(kw);
				}
			}

			try {
				Thread.sleep(step);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
