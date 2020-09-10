import java.util.ArrayList;
import java.util.List;

public class Budget {
	
	protected List<Purchase> purchases;
	private double expenses;
	private double income;

	public Budget() {
		this.purchases = new ArrayList<Purchase>();
		this.expenses = 0.0;
		this.income = 0.0;
	}

	public void addPurchase(String type, String item, double price) {
		this.purchases.add(new Purchase(type, item, price));
	}

	public void addIncome(double income) {
		this.income += income;
	}

	public void addExpenses(double expenses) {
		this.expenses += expenses;
	}
	
	public void setIncome(double income) {
		this.income = income;
	}

	public void setExpenses(double expenses) {
		this.expenses = expenses;
	}

	public double getIncome() {
		return this.income;
	}

	public double getExpenses() {
		return this.expenses;
	}

	public double getBalance() {
		double balance = this.income - this.expenses;
		return balance < 0 ? 0 : balance;
	}
	
	public boolean isEmpty() {
		return this.purchases.isEmpty();
	}

	public List<String> getAllPurchasesForFile() {
		List<String> list = new ArrayList<>();
		for (Purchase item : this.purchases) {
			list.add(item.purchaseToStringForFile());
		}
		return list;
	}

	public void printOutChosenPurchaseCategory(String choice) {
		Boolean gotProduct = false;
		double total = 0.0;
		System.out.println("\n" + choice + ":");
		if (choice == "All" && !this.purchases.isEmpty()) {
			gotProduct = true;
			for (Purchase item : this.purchases) {
				System.out.println(item.purchaseToString());
			}
			total = this.getExpenses();
		} else {
			for (Purchase item : this.purchases) {
				if (item.getType().equals(choice)) {
					System.out.println(item.purchaseToString());
					total += item.getPrice();
					gotProduct = true;
				}
			}
		}

		if (gotProduct) {
			System.out.println("Total sum: $" + BudgetUtil.format(total));
		} else {
			System.out.println("Purchase list is empty!");
		}
		System.out.println();
	}
}