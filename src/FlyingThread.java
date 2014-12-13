/* Copyright (C) 2014 Deyuan Guo & Dawei Fan. All Rights Reserved. */

import java.awt.Dimension;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * FlyingThread.
 * @author Dawei Fan, Deyuan Guo
 */
public class FlyingThread extends Thread {

	private List<Term> termList;
	private List<Keyword> keywordList;
	private FlyingKeywords container;
	private Random rand;
	private Config config;
	final int step = 5;

	FlyingThread(FlyingKeywords container, List<Term> termList, Config c) {
		this.container = container;
		this.config = c;
		this.termList = termList;
		this.rand = new Random();
		initKeywords();
	}

	private void initKeywords() {
		keywordList = new LinkedList<Keyword>();
		for (int i = 0; i < config.getCount(); i++) {
			addAKeyword();
//			System.out.println("Speed: "+keyword.getSpeed());
		}
	}

	/**
	 * Add a new keyword.
	 */
	public void addAKeyword() {
		int idx = rand.nextInt(termList.size());
		Keyword keyword =
				new Keyword(termList.get(idx), new Dimension(1600, 900));
		keywordList.add(keyword);
		container.add(keyword);
	}

	@Override
	public void run(){

		/** Move every keyword. */
		for (int j = 0; j < 1600; j++) {
			for (int i = 0; i < keywordList.size(); i++) {
				if (keywordList.get(i).getX()+keywordList.get(i).getSpeed()>1600){
					container.remove(keywordList.get(i));
					keywordList.remove(i);
					addAKeyword();
					continue;
				}
				keywordList.get(i).setLocation(keywordList.get(i).getX()+keywordList.get(i).getSpeed(), keywordList.get(i).getY());

				try {
					Thread.sleep(step);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

	}
}
