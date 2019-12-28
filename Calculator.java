
import java.io.FileNotFoundException;
import java.util.*;

public class Calculator {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner scanner = new Scanner(System.in);
		Budget budget = new Budget();
		Analyze analyze = new Analyze();
		
		int option = -1; // Switch statement option
		int choice = -1; // Choice option when multiple options

		while (option != 0) {
			option = getOptiones(scanner);
			scanner.nextLine(); // Skip the newline
			System.out.println();
			switch (option) {
			case 1:
				System.out.println("Enter income:");
				budget.addIncome(scanner.nextDouble());
				System.out.println("Income was added!\n");
				break;
			case 2:
				choice = -1;
				while (choice != 5) {
					choice = getPurchaseOptions(scanner);
					scanner.nextLine();// Skip the newline
					if (choice != 5) {
						addPurchase(budget, scanner, getTypeOfPurchase(choice));
					}
				}
				System.out.println();
				break;
			case 3:
				if (budget.isEmpty()) {
					System.out.println("Purchase list is empty");
				} else {
					choice = -1;
					while (choice != 6) {
						choice = getPurchaseOptionsPerCategory(scanner);
						if (choice != 6) {
							budget.printOutChosenPurchaseCategory(getTypeOfPurchase(choice));
						}
					}
				}
				System.out.println();
				break;
			case 4:
				System.out.println("Balance: $" + BudgetUtil.format(budget.getBalance()) + "\n");
				break;
			case 5:
				boolean savedBudget = BudgetUtil.saveBudgetToFile(budget);
				System.out.println(savedBudget ? "Purchases were saved!\n" : "Somthing went wrong!\n");
				break;
			case 6:
				boolean loadedBudget = BudgetUtil.loadBudgetFromFile(budget);
				System.out.println(loadedBudget ? "Purchases were loaded!\n" : "Somthing went wrong!\n");
				break;
			case 7:
				int analyz = -1;				
				choice = -1;
				while (choice != 4) {					
					choice = getAnalyzeOptions(scanner);
					scanner.nextLine(); // Skip the newline
					if (choice == 1 && notEmpty(budget)) {
						System.out.println();
						analyze.SortAllPurchases(budget);
					} else if (choice == 2) {	
						System.out.println();
						analyze.SortCertainType(budget);
					} else if (choice == 3) {
						System.out.println();
						analyz = getAnalyzePurchaseOptionsPerCategory(scanner);
						analyze.SortByType(budget, getTypeOfPurchaseForAnalyze(analyz));
					}					
					System.out.println();
				}				
				break;
			case 0:
				System.out.print("Bye!");
				break;
			}
		}
		scanner.close();
	}
	
	public static int getOptiones(Scanner scanner) {
		System.out.println(
				"Choose your action:\n" + "1) Add income\n" + "2) Add purchase\n" + "3) Show list of purchases\n"
						+ "4) Balance\n" + "5) Save\n" + "6) Load\n" + "7) Analyze (Sort)\n" + "0) Exit");

		return scanner.nextInt();
	}
	
	public static int getPurchaseOptions(Scanner scanner) {
		System.out.println("Choose the type of purchase:\n" + "1) Food\n" + "2) Clothes\n" + "3) Entertainment\n"
				+ "4) Other\n" + "5) Back");
		return scanner.nextInt();
	}

	public static int getPurchaseOptionsPerCategory(Scanner scanner) {
		System.out.println("Choose the type of purchase:\n" + "1) Food\n" + "2) Clothes\n" + "3) Entertainment\n"
				+ "4) Other\n" + "5) All\n" + "6) Back");

		return scanner.nextInt();
	}
	
	public static int getAnalyzeOptions(Scanner scanner) {
		System.out.println("How do you want to sort?\n" + "1) Sort all purchases\n" + "2) Sort by type\n"
				+ "3) Sort certain type\n" + "4) Back");
		return scanner.nextInt();
	}
	
	public static int getAnalyzePurchaseOptionsPerCategory(Scanner scanner) {
		System.out.println("Choose the type of purchase\n" + "1) Food\n" + "2) Clothes\n"
				+ "3) Entertainment\n" + "4) Other");
		return scanner.nextInt();
	}
	
	public static boolean notEmpty(Budget budget) {
		if(budget.isEmpty()) {
			System.out.println("\nPurchase list is empty!");
		}
		return !budget.isEmpty(); // Return true if false, isEmpty().
	}

	public static void addPurchase(Budget budget, Scanner scanner, String type) {
		System.out.println("\nEnter purchase name:");
		String item = scanner.nextLine();
		System.out.println("Enter its price:");
		double price = scanner.nextDouble();
		budget.addPurchase(type, item, price);
		budget.addExpenses(price);
		System.out.println("Purchase was added!\n");
	}

	public static String getTypeOfPurchase(int choice) {
		String value = "";
		if (choice == 1) {
			value = "Food";
		} else if (choice == 2) {
			value = "Clothes";
		} else if (choice == 3) {
			value = "Entertainment";
		} else if (choice == 4) {
			value = "Other";
		} else {
			value = "All";
		}
		return value;
	}

	public static String getTypeOfPurchaseForAnalyze(int choice) {
		String value = "";
		if (choice == 1) {
			value = "Food";
		} else if (choice == 2) {
			value = "Clothes";
		} else if (choice == 3) {
			value = "Entertainment";
		} else {
			value = "Other";
		}
		return value;
	}
}
