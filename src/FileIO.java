/* Copyright (C) 2014 Deyuan Guo & Dawei Fan. All Rights Reserved. */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * FileIO.
 * @author Deyuan Guo, Dawei Fan
 */
public class FileIO {

	public class Term {
		public String text;
		public boolean highlight;
		public String word;
		public String note;
		public static final char highlighter = '*';
		public static final char splitter = '|';

		Term(String txt) {
			text = txt;
			parseText();
		}

		private void parseText() {
			highlight = false;
			word = "";
			note = "";

			int p = 0;
			while (p < text.length() && Character.isWhitespace(text.charAt(p))) p++;
			if (text.charAt(p) == highlighter) {
				highlight = true;
				p++;
				while (p < text.length() && Character.isWhitespace(text.charAt(p))) p++;
			}
			int i = p, j = 0;
			for (; i < text.length(); i++) {
				if (text.charAt(i) == splitter) {
					j = i + 1;
					while (j < text.length() && Character.isWhitespace(text.charAt(j))) j++;
					i--;
					while (i < text.length() && Character.isWhitespace(text.charAt(i))) i--;
					i++;
					break;
				}
			}
			word = text.substring(p, i);
			note = text.substring(j, text.length());
		}
	}

	public String path;
	public List<Term> termList;

	public FileIO(String filePath) {
		path = filePath;
		termList = new ArrayList<Term>();

		termList = readTermList(path);
	}

	private List<Term> readTermList(String filePath) {
		List<Term> tList = new ArrayList<Term>();

		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(filePath));
		} catch (FileNotFoundException e) {
			System.err.println("File '" + filePath + "'Not Found");
			e.printStackTrace();
		}

		try {
			while (br.ready())
				tList.add(new Term(br.readLine()));
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return tList;
	}

}
