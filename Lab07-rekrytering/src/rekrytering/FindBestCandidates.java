package rekrytering;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class FindBestCandidates {
	/*
	 * Plockar ut de bästa kandidaterna baserat på betyg. Betygsgränsen kan sättas
	 * genom att ändra MIN_AVG_GRADE
	 */
	private static final double MIN_AVG_GRADE = 5.0;

	public static void main(String[] args) {
		/*
		 * Läser in från en fil, sorterar och plockar ut bäst kandidater Printar även ut
		 * felinmatade kandidater, och markerar dessa. ArrayList för de felmarkerade pga
		 * är lat och vill inte behöva skriva flera forloopar och vektorer
		 */

		Applicant[] app = FileReader.readFromFile("applications_all.txt", 700);
		Applicant[] bestCand = findBestCandidates(app);
		Arrays.sort(bestCand, Collections.reverseOrder());
		ArrayList<Applicant> errorList = new ArrayList<Applicant>();

		// Printar ut kandidaterna
		for (int i = 0; i < bestCand.length; i++) {
			if (bestCand[i].hasError() == false) {
				System.out.println(bestCand[i].toString());
			} else {
				errorList.add(bestCand[i]);
			}
		}

		// Printar ut felkandidaterna
		if(errorList.size() > 0) 
			System.out.println("The following entries had errors in their application and should be reviewed manually");
		
		for (int i = 0; i < errorList.size(); i++) {
			System.out.println(errorList.get(i).toString());
		}

	}

	public static Applicant[] findBestCandidates(Applicant[] applicants) {
		/*
		 * Hittar alla kandidater som har betyg över eller likamed gränsen Returnerar
		 * dessa som en vektor
		 */
		int nbrCandidates = 0;
		for (int i = 0; i < applicants.length; i++) {
			if (applicants[i].getAvgGrade() >= MIN_AVG_GRADE) {
				nbrCandidates++;
			}
		}
		
		// Fixar vektor med rätt längd
		Applicant[] bCand = new Applicant[nbrCandidates];
		int addedCand = 0;

		for (int i = 0; i < applicants.length; i++) {
			if (applicants[i].getAvgGrade() >= MIN_AVG_GRADE) {
				bCand[addedCand] = applicants[i];
				addedCand++;
			}
		}
		return bCand;
	}
}
