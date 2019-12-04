public class Renderer {
	private int width, height, score1, score2;

	Renderer(int w, int h, int s1, int s2) {
		width = w;
		height = h;
		score1 = s1;
		score2 = s2;
	}

	void moveDown(int n) {
		for (int x = 0; x < n; x++) {
			System.out.println(" ");
		}
	}

	void clearScreen() {
		for (int x = 0; x < height; x++) {
			System.out.println(" ");
		}
	}

	void centerText(String str, String col) {
		int centerPos = (width - str.length());
		for (int i = 0; i < centerPos / 2; i++) {
			System.out.print(" ");
		}
		System.out.print(terminalColors.TEXT_WHITE + col);
		System.out.print(str);
		System.out.print(terminalColors.TEXT_RESET);
		
	}

	void centerTextMid(String str, String col) {
		int centerPos = (width - str.length());
		for (int i = 0; i < centerPos / 2; i++) {
			System.out.print(" ");
		}
		System.out.print(terminalColors.TEXT_WHITE + col);
		System.out.print(str);
		System.out.print(terminalColors.TEXT_RESET);
		moveDown(height / 2);

	}

	void welcomeScreen() {
		clearScreen();
		centerTextMid("Välkommen!", terminalColors.TEXT_RESET);
		centerTextMid("Skriv in namnen på de två spelarna. Bekräfta med enter.", terminalColors.TEXT_RESET);
	}

	void endScreen(String winner) {
		clearScreen();
		centerTextMid("Och vinnaren är....", terminalColors.BACK_BLACK);
		GameLogic.delay(2000);
		for (int i = 0; i < 15; i++) {
			centerTextMid(winner, terminalColors.BACK_BLUE + terminalColors.TEXT_RED);
			GameLogic.delay(500);
			centerTextMid(winner, terminalColors.BACK_RED + terminalColors.TEXT_BLUE);
			GameLogic.delay(500);
		}
		centerTextMid("Vill ni spela igen?", terminalColors.BACK_PURPLE);
	}

	void scoreBar(String name1, String name2) {
		int leftAlign = 3;
		int rightAlign = (width - (leftAlign + name1.length())) - (name2.length() + 4);
		for (int i = 0; i < leftAlign; i++) {
			System.out.print(" ");
		}
		System.out.print(name1 + ": " + score1);
		for (int i = 0; i < rightAlign; i++) {
			System.out.print(" ");
		}
		System.out.print(name2 + ": " + score2);
	}

	void presentFourQuestions(String qst, String str1, String str2, String str3, String str4) {
		clearScreen();
		centerText(qst, terminalColors.BACK_RED);
		moveDown(10);
		int widthStr1 = width - (str1.length() + str2.length() + 3);
		int widthStr2 = width - (str3.length() + str4.length() + 3);
		for (int i = 0; i < widthStr1 / 2; i++) {
			System.out.print(" ");

		}
		System.out.print("1. " + str1 + "   2. " + str2);
		System.out.println(" ");
		System.out.println(" ");
		for (int i = 0; i < widthStr2 / 2; i++) {
			System.out.print(" ");
		}
		System.out.print("3. " + str3 + "   4. " + str4);

		moveDown(5);

	}
}
