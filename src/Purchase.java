
public class Purchase {
	
	private String type;
	private String item;
	private double price;

	public Purchase(String type, String item, double price) {
		this.type = type;
		this.item = item;
		this.price = price;
	}

	public void addPurchase(String type) {
		this.type = type;
	}

	public void addIncome(String item) {
		this.item = item;
	}

	public void addExpenses(double price) {
		this.price = price;

	}

	public double getPrice() {
		return Double.valueOf(BudgetUtil.format(this.price));
	}

	public String getType() {
		return this.type;
	}

	public String purchaseToString() {
		return this.item + " $" + BudgetUtil.format(this.price);
	}

	public String purchaseToStringForFile() {
		// Build a Purchase of a product with information separated with # for easy file reading
		return this.type + "#" + this.item + "#" + BudgetUtil.format(this.price);
	}
}