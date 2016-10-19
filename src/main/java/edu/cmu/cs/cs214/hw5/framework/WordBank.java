package edu.cmu.cs.cs214.hw5.framework;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * The Class WordBank.
 */
public class WordBank {

	/**
	 * Gets the positive words.
	 *
	 * @return the positive words
	 */

	public Set<String> getPositiveWords() {
		Set<String> words = new HashSet<String>();
		Scanner scanner;
		try {
			scanner = new Scanner(new File("assets/positiveWords.txt"));
			while (scanner.hasNextLine()) {
				String word = scanner.nextLine();
				words.add(word.toLowerCase());

			}
			scanner.close();
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
		return words;
	}

	/**
	 * Gets the negative words.
	 *
	 * @return the negative words
	 */

	public Set<String> getNegativeWords() {
		Set<String> words = new HashSet<String>();
		Scanner scanner;
		try {
			scanner = new Scanner(new File("assets/negativeWords.txt"));
			while (scanner.hasNextLine()) {
				String word = scanner.nextLine();
				words.add(word.toLowerCase());
			}
			scanner.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return words;
	}

	/**
	 * Gets the negative words.
	 *
	 * @return the negative words
	 */
	public Set<String> getPrepositionWords() {
		Set<String> words = new HashSet<String>();
		Scanner scanner;
		try {
			scanner = new Scanner(new File("assets/prepositions.txt"));
			while (scanner.hasNextLine()) {
				String word = scanner.nextLine();
				words.add(word.toLowerCase());
			}
			scanner.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return words;
	}
}