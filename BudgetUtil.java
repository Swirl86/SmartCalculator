import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;

public class BudgetUtil {
	
	public static String format(double num) {
		// Get correct format for double with two decimals and a dot, used in multiple classes.
		DecimalFormatSymbols decimalSymbols = DecimalFormatSymbols.getInstance();
		decimalSymbols.setDecimalSeparator('.');
		return new DecimalFormat("0.00", decimalSymbols).format(num);
	}
	
	public static boolean saveBudgetToFile(Budget budget) throws FileNotFoundException {
		boolean saved = false;
		try {
			List<String> purchases = budget.getAllPurchasesForFile();
			FileWriter writer = new FileWriter("purchases.txt");
			writer.write(String.valueOf(budget.getIncome())); // Income
			writer.write("\r\n");
			for (String purchase : purchases) { // Purchases
				writer.write(purchase);
				writer.write("\r\n");
			}
			writer.close();
			saved = true;
		} catch (IOException e) {
			e.printStackTrace();
		}

		return saved;
	}
	
	public static boolean loadBudgetFromFile(Budget budget) throws FileNotFoundException {
		boolean loaded = false;
		boolean gotIncome = false;
		double totalExpense = 0.0;
		
		try {
			List<String> purchases = Files.readAllLines(Paths.get("purchases.txt"));
			for (String purchase : purchases) {
				if (gotIncome) {
					String[] product = purchase.split("#"); // Type, name, price that is separated with # on a row
					Double price = Double.parseDouble(product[2]);
					budget.addPurchase(product[0], product[1], price);
					totalExpense += Double.valueOf(product[2]);
				} else {
					budget.setIncome(Double.valueOf(purchase));
					gotIncome = true;
				}
			}
			budget.setExpenses(Double.valueOf(format(totalExpense)));
			loaded = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return loaded;
	}
}
