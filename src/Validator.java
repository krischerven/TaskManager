import java.util.Scanner;

public class Validator {
	public int getChoice(Scanner sc, int min, int max) {
		while (true) {
			if (sc.hasNextInt()) {
				int test = sc.nextInt();
				sc.nextLine(); // garbage
				if (test >= min && test <= max) {
					return test;
				} else {
					System.err.println("Error: out of range.");
				}
			} else {
				System.err.println("Error: not a valid integer.");
				sc.nextLine(); // prevents infinite loops
			}
		}
	}
	
	public int getChoiceZero(Scanner sc, int min, int max) {
		while (true) {
			if (sc.hasNextInt()) {
				int test = sc.nextInt();
				sc.nextLine(); // garbage
				if (test == 0) {
					return 0;
				} else if (test >= min && test <= max) {
					return test;
				} else {
					System.err.println("Error: out of range.");
				}
			} else {
				System.err.println("Error: not a valid integer.");
				sc.nextLine(); // prevents infinite loops
			}
		}
	}
	
	public boolean getYesNo(Scanner sc) {
		while (true) {
			String line = sc.nextLine().toLowerCase().trim();
			if (line.equals("y") || line.equals("yes")) {
				return true;
			} else if (line.equals("n") || line.equals("no")) {
				return false;
			} else {
				System.err.println("Please input y/n to continue.");
			}
		}
	}
}
