/* Copyright (C) 2014 Deyuan Guo & Dawei Fan. All Rights Reserved. */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * A thread to make words fly.
 *
 * @author Dawei
 * @version 0.1
 *
 */
public class FlyingThread extends Thread{

	private List<String> words;
	private List<Keyword> keywordList;
	private FlyingKeywords ft;
	private Config config;
	final int step = 5;

	FlyingThread(FlyingKeywords ft, List<String> w, Config c){
		this.ft = ft;
		this.config = c;
		this.words = new ArrayList<String>(w);
		initializeKeywords();

	}

	private void initializeKeywords(){
		Random r = new Random();
		keywordList = new LinkedList<Keyword>();
		for(int i = 0; i<config.getCount(); i++){
			Keyword keyword = new Keyword(words.get(r.nextInt(words.size())), new int[]{1600, 900});
			keywordList.add(keyword);
			ft.add(keyword);
//			System.out.println("Speed: "+keyword.getSpeed());
		}
	}

	public void addAKeyword(){
		/** Add a new word. */
		Random r = new Random();
		Keyword keyword = new Keyword(words.get(r.nextInt(words.size())), new int[]{1600, 900});
		keywordList.add(keyword);
		ft.add(keyword);
	}

	@Override
	public void run(){

		/** Move every keyword. */
		for(int j = 0; j< 1600; j++){
			for(int i = 0; i<keywordList.size(); i++){
				if(keywordList.get(i).getX()+keywordList.get(i).getSpeed()>1600){
					ft.remove(keywordList.get(i));
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
