/* Copyright (C) 2014 Deyuan Guo & Dawei Fan. All Rights Reserved. */

import java.util.Random;

/**
 * Configuration.
 * @author Dawei Fan, Deyuan Guo
 */
public class Config {

	private static int width = 1024;
	private static int height = 768;
	private static int numOfWords = 10;
	private static int maxFontSize = 60;
	private static int minFontSize = 20;
	private static int maxSpeed = 3;
	private static int minSpeed = 1;
	private static boolean forward = true;
	private static Random rand = new Random();

	public static int getWidth() { return width; }
	public static void setWidth(int w) { width = w; }
	public static int getHeight() { return height; }
	public static void setHeight(int h) { height = h; }
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

	/** Get a random integer in range [0, n) */
	public static int getRandInt(int n) { return rand.nextInt(n); }

	/** Get a random double in range [0.0, 1.0) */
	public static double getRandDouble() { return rand.nextDouble(); }

	/** Get a random integer in range [min, max] as the font size */
	public static int getRandFontSize() {
		return minFontSize + rand.nextInt(maxFontSize - minFontSize + 1);
	}

	/** Get a random double in range [min, max) as the speed */
	public static double getRandSpeed() {
		return minSpeed + rand.nextDouble() * (maxSpeed - minSpeed + 1);
	}

}
