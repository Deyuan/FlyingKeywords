/* Copyright (C) 2014 Deyuan Guo & Dawei Fan. All Rights Reserved. */

/**
 * Term: A data structure for a term.
 * @author Deyuan Guo
 */
public class Term {

	private boolean highlight;
	private String word;
	private String note;
	private String text;

	public Term(String line) {
		parseText(line);
		text = genText();
	}

	public String getWord() { return word; }
	public String getNote() { return note; }
	public String getText() { return text; }

	public boolean getHighlight() { return highlight; }
	public void setHighlight(boolean b) { highlight = b; text = genText(); }

	private void parseText(String str) {
		if (str.charAt(0) == '*') {
			highlight = true;
			str = str.substring(1).trim();
		} else {
			highlight = false;
		}

		String[] parts = str.split("\\|", 2);
		if (parts.length > 0) word = parts[0].trim();
		else word = "";
		if (parts.length > 1) note = parts[1].trim();
		else note = "";
	}

	private String genText() {
		StringBuilder s = new StringBuilder();
		if (highlight) s.append("* ");
		s.append(word);
		if (note.length() > 0) {
			if (word.length() > 0) s.append(" ");
			s.append("| ");
			s.append(note);
		}
		return s.toString();
	}

}
