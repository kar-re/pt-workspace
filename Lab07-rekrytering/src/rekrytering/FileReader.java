package rekrytering;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReader {

	/**
	 * Returnerar max nbrOfRows rader från filen som en vektor av Applicant-objekt.
	 * Läser i filen tills det inte finns fler rader eller tills man läst nbrOfRows
	 * rader (det som inträffar först). Returnerar null om filen inte finns.
	 */
	public static Applicant[] readFromFile(String fileName, int nbrOfRows) {
		Scanner scan;
		// Provar att få in filen som input, annars ErrorException
		try {
			scan = new Scanner(new File(fileName), "utf-8");
		} catch (FileNotFoundException e) {
			System.err.println("File not found" + fileName);
			e.printStackTrace();
			return null;
		}

		// Tempvektor för att få 'rätt' vektor efter i avseende till nbrOfRows, annars
		// blir vektorn för stor
		// Finns det en avvägning mellan minnesåtgång/full med nullpointers eller instruktioner?
		Applicant[] apcTemp = new Applicant[nbrOfRows];

		int i = 0;
		// While sålänge det finns ett nästa input, annars null
		while (scan.hasNext()) {
			String name = scan.next() + " " + scan.next();
			String grades = scan.next();
			Applicant curr = new Applicant(name, grades);
			apcTemp[i] = curr;
			i = i + 1;
		}

		// Rättar till vektorn enligt antalet 'rätta' entries
		Applicant[] apc = new Applicant[i];
		for (int p = 0; p < i; p++) {
			apc[p] = apcTemp[p];

		}

		return apc;
	}

}
