/* Copyright (C) 2014 Deyuan Guo & Dawei Fan. All Rights Reserved. */

import java.util.Random;

/**
 * Configuration.
 * @author Dawei Fan, Deyuan Guo
 */
public class Config {

	private static int numOfWords = 10;
	private static int maxFontSize = 50;
	private static int minFontSize = 10;
	private static int maxSpeed = 10;
	private static int minSpeed = 1;
	private static boolean forward = true;
	private static Random rand = new Random();

	public static int getNumOfWords() { return numOfWords; }
	public static void setNumOfWords(int n) { numOfWords = n; }
	public static int getMaxFontSize() { return maxFontSize; }
	public static void setMaxFontSize(int size) { maxFontSize = size; }
	public static int getMinFontSize() { return minFontSize; }
	public static void setMinFontSize(int size) { minFontSize = size; }
	public static int getMaxSpeed() { return maxSpeed; }
	public static void setMaxSpeed(int size) { maxSpeed = size; }
	public static int getMinSpeed() { return minSpeed; }
	public static void setMinSpeed(int size) { minSpeed = size; }
	public static boolean getForward() { return forward; }
	public static void setForward(boolean b) { forward = b; }

	public static int getRandInt(int n) { return rand.nextInt(n); }
	public static int getRandFontSize() {
		return minFontSize + rand.nextInt(maxFontSize - minFontSize + 1);
	}
	public static int getRandSpeed() {
		return minSpeed + rand.nextInt(maxSpeed - minSpeed + 1);
	}
}
