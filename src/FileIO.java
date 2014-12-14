/* Copyright (C) 2014 Deyuan Guo & Dawei Fan. All Rights Reserved. */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * FileIO.
 * @author Deyuan Guo, Dawei Fan
 */
public class FileIO {

	/**
	 * Read a file and extract term list
	 * @param filePath
	 * @return A term list
	 */
	public static List<Term> readTermList(String filePath) {
		List<Term> tList = new ArrayList<Term>();
		if (filePath == null) return tList;

		BufferedReader in = null;
		try {
			in = new BufferedReader(new FileReader(filePath));
			while (in.ready()) {
				String str = in.readLine().trim();
				if (str.length() > 0) {
					tList.add(new Term(str));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null) in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return tList;
	}

	/**
	 * Write the term list into a file
	 * @param termList
	 * @param filePath
	 */
	public static void writeTermList(List<Term> termList, String filePath) {
		PrintWriter out = null;
		try {
			out = new PrintWriter(new BufferedWriter(new FileWriter(filePath)));
			for (Term t: termList) {
				out.println(t.getText());
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null) out.close();
		}
	}

	/* Test */
	public static void main(String[] args) {
		String filePath = "text/test.txt";
		List<Term> termList = FileIO.readTermList(filePath);
		for (Term t: termList) {
			System.out.println(t.getText());
			System.out.println("  - " + t.getHighlight());
			System.out.println("  - " + t.getWord());
			System.out.println("  - " + t.getNote());
		}

		//termList.get(0).setHighlight(true);
		//FileIO.writeTermList(termList, "text/test_changed.txt");
	}

}
