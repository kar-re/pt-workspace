package memory;

public class MemoryBoard {
	int size;
	MemoryCardImage[][] deck;
	boolean[][] isTurned;

	/**
	 * Skapar ett memorybräde med size * size kort. backFileName är filnamnet för
	 * filen med baksidesbilden. Vektorn frontFileNames innehåller filnamnen för
	 * frontbilderna.
	 */
	public MemoryBoard(int size, String backFileName, String[] frontFileNames) {
		this.size = size;
		deck = new MemoryCardImage[size][size];
		isTurned = new boolean[size][size];
		createCards(backFileName, frontFileNames);
	}

	/*
	 * Skapar size * size / 2 st memorykortbilder. Placerar ut varje kort på två
	 * slumpmässiga ställen på spelplanen.
	 */
	private void createCards(String backFileName, String[] frontFileNames) {
		if (size % 2 != 0 || size > frontFileNames.length / 2) {
			System.out.println("Error! Size must be a multiple of 2 & no more than " + frontFileNames.length / 2);
		}
		for (int i = 0; i < ((size * size) / 2); i++) {
			MemoryCardImage card = new MemoryCardImage(frontFileNames[i], backFileName);
			placeCard(card);
			placeCard(card);
		}
	}

	/*
	 * Placerar korten baserat på om en slumpmässig plats finns tillgänglig Annars
	 * slumpar den fram en ny
	 */
	private void placeCard(MemoryCardImage card) {
		int r = rand(), c = rand();
		while (!isAvailable(r, c)) {
			r = rand();
			c = rand();
		}
		deck[r][c] = card;
		isTurned[r][c] = false;

	}

	/*
	 * Använder Math.random för att slumpa fram ett tal som även är adjustat till
	 * size och korrekt avrundat samt typecastat.
	 */
	private int rand() {
		return (int) Math.round((size - 1) * Math.random());
	}

	/*
	 * Kollar om platsen på row r och col c är tillgänglig
	 */
	private boolean isAvailable(int r, int c) {
		return deck[r][c] == null ? true : false;
	}

	/** Tar reda på brädets storlek. */
	public int getSize() {
		return size;
	}

	/**
	 * Hämtar den tvåsidiga bilden av kortet på rad r, kolonn c. Raderna och
	 * kolonnerna numreras från 0 och uppåt.
	 */
	public MemoryCardImage getCard(int r, int c) {
		return deck[r][c];
	}

	/** Vänder kortet på rad r, kolonn c. */
	public void turnCard(int r, int c) {
		isTurned[r][c] = !isTurned[r][c];
	}

	/** Returnerar true om kortet r, c har framsidan upp. */
	public boolean frontUp(int r, int c) {
		return isTurned[r][c];
	}

	/**
	 * Returnerar true om det är samma kort på rad r1, kolonn c2 som på rad r2,
	 * kolonn c2.
	 */
	public boolean same(int r1, int c1, int r2, int c2) {
		return deck[r1][c1].equals(deck[r2][c2]);
	}

	/** Returnerar true om alla kort har framsidan upp. */
	public boolean hasWon() {
		int count = 0;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (frontUp(i, j)) {
					count += 1;
				}
			}
		}

		return count == size * size ? true : false;
	}
}
