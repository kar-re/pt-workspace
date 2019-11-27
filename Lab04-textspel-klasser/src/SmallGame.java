import java.util.Scanner;

public class SmallGame {
	static boolean inputOK = false;
	static void printLnDelay(String str) {
		delay(2000);
		for (int i = 0; i < str.length(); i++) {
			System.out.print(str.charAt(i));	
			delay (20);
			if (i > str.length() - 5) 
				delay(500);
		}
		System.out.println("");
		inputOK = true;
	}
	public static void main(String[] args) {
		String str;
		System.out.println("\uD83D\uDE00");
		printLnDelay(terminalColors.ANSI_WHITE + "Hello world!");
		while (true) {
			Scanner scanner = new Scanner(System.in);
			if (inputOK) {
				str = scanner.next();
				if (str == "hej") {
					printLnDelay("Tjenna!");
				}
			}
			
			
		}
		
	}
	String parseInput(String str) {
		str.toUpperCase();
		return str;
	}
	
	
	
	static void delay(int ms) {
		try
		{
			Thread.sleep(ms);
		}
		catch(InterruptedException ex)
		{
			Thread.currentThread().interrupt();
		}
	}
}
