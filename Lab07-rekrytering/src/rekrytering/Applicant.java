package rekrytering;

import java.util.Arrays;

public class Applicant implements Comparable<Applicant> {
	/*
	 * Varje sökande har ett namn och ett antal betyg och även flagga för om det har
	 * uppstått något fel
	 */

	private String name;
	private int[] grades;
	private boolean error;

	public Applicant(String name, String gradesAsString) {
		/*
		 * Applicant består av namn, betyg och errorflagga Grades tolkas i en egen metod
		 */
		this.name = name;
		error = false;
		parseGrades(gradesAsString);
	}

	/*
	 * gradesAsString har formatet x,y,z,q där respektive bokstav är ett betyg Om vi
	 * splittar strängen på komma (",") hamnar varje betyg i en vektor
	 */
	private void parseGrades(String gradesAsString) {
		String[] g = gradesAsString.split(",");

		// Skapa vektorn med heltal
		grades = new int[g.length];

		/*
		 * Iterera över alla betyg för att översätta dessa till ett heltal Kollar även
		 * så att formatet är rätt, annars markeras errorflaggan
		 */

		for (int i = 0; i < g.length; i++) {
			if (g[i].equals("3") || g[i].equals("4") || g[i].equals("5")) {
				grades[i] = Integer.parseInt(g[i]);
				// Om underkänd så räknar vi det som en nolla
			} else if (g[i].equals("U")) {
				grades[i] = 0;
				// Och om det är något annat markeras felflaggan
			} else {
				error = true;
				grades[i] = 0;
			}

		}
	}

	/* Returnerar den privata boolean error */
	public boolean hasError() {
		return error;
	}

	/* Returnerar medelvärdet av betygen */
	public double getAvgGrade() {
		double g = 0;

		/*
		 * Lägger till betygen i en int, delar sedan på antalet rapporterade betyg.
		 * Skulle kunna nolla de som inte uppfyller fem betyg, såsom Sandra.
		 */
		for (int i = 0; i < grades.length; i++) {
			g += grades[i];
		}

		g = g / grades.length;
		return g;
	}

	/* Returnerar namnet, betygen och medelvärdet av betygen */
	public String toString() {
		String fullName = name + Arrays.toString(grades) + " (avg: " + getAvgGrade() + ")";
		return fullName;
	}

	/*
	 * Metod för att jämföra detta Applicant-objekt med ett annat och få ut vilket
	 * som är störst. Retunerar något > 0 om detta objektet är störst. Returnerar
	 * något < 0 om other är störst och returnerar 0 om objekten är lika. Används av
	 * javas inbyggda sorteringsmetoder
	 */
	public int compareTo(Applicant other) {
		// Om exakt samma objekt
		if (other == this) {
			return 0;
		}
		// Annars jämför snittbetyget i första hand
		int gradeRes = (int) Math.round((getAvgGrade() - ((Applicant) other).getAvgGrade()) * 100);
		if (gradeRes == 0) {
			// Om snittbetyget är samma, låt namnet avgöra på namnet
			return name.compareTo(((Applicant) other).name);
		}
		return gradeRes;
	}

	/* Returnerar den privata strängen name */
	public String getName() {
		return name;
	}
}
