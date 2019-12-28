import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Analyze extends Budget {

	public void SortAllPurchases(Budget budget) {
		List<Purchase> list = new ArrayList<>(budget.purchases);
		Purchase tmp = new Purchase("tmp", "tmp", 0.0);
		int size = budget.purchases.size();
		System.out.println("All:");
		for (int i = 0; i < size; i++) {
			for (int j = 1; j < size - i; j++) {
				if (list.get(j - 1).getPrice() < list.get(j).getPrice()) {
					tmp = list.get(j - 1);	
					list.set(j - 1, list.get(j));
					list.set(j, tmp);
				}
			}
		}

		for (Purchase item : list) {
			System.out.println(item.purchaseToString());
		}
		System.out.println("Total sum: $" + BudgetUtil.format(budget.getExpenses()));
	}

	public void SortCertainType(Budget budget) {
		List<Purchase> list = new ArrayList<>(budget.purchases);
		Map<String, Double> map = new LinkedHashMap<String, Double>();
		int size = budget.purchases.size();
		map.put("Food", 0.0);
		map.put("Entertainment", 0.0);
		map.put("Clothes", 0.0);
		map.put("Other", 0.0);
		System.out.println("Types:");
		for (int i = 0; i < size; i++) {
			double value = 0.0;			
			if(list.get(i).getType().equals("Food")) {
				value = map.get("Food");
				map.put("Food", (value + list.get(i).getPrice()));
			} else if(list.get(i).getType().equals("Entertainment")) {
				value = map.get("Entertainment");
				map.put("Entertainment", (value + list.get(i).getPrice()));
			} else if(list.get(i).getType().equals("Clothes")) {
				value = map.get("Clothes");
				map.put("Clothes", (value + list.get(i).getPrice()));
			} else {
				value = map.get("Other");
				map.put("Other", (value + list.get(i).getPrice()));
			} 
		}

		for (Map.Entry<String, Double> entry : map.entrySet()) {
			System.out.println(entry.getKey() + " - $" + BudgetUtil.format(entry.getValue()));
		}
		
		System.out.println("Total sum: $" + BudgetUtil.format(budget.getExpenses()));
	}

	public void SortByType(Budget budget, String type) {
		List<Purchase> list = getSortedList(budget);
		int size = budget.purchases.size();
		double total = 0.0;	
		boolean gotProduct = false;
		System.out.println("\n" + type + ":");
		for (int i = 0; i < size; i++) {
			if(list.get(i).getType().equals(type)) {
				total += list.get(i).getPrice();
				System.out.println(list.get(i).purchaseToString());
				gotProduct = true;
			} 
		}
		if (gotProduct) {
			System.out.println("Total sum: $" + BudgetUtil.format(total));
		} else {
			System.out.println("Purchase list is empty!");
		}
		
	}
	
	public List<Purchase> getSortedList(Budget budget) {
		List<Purchase> list = new ArrayList<>(budget.purchases);
		Purchase tmp = new Purchase("tmp", "tmp", 0.0);
		int size = budget.purchases.size();
		for (int i = 0; i < size; i++) {
			for (int j = 1; j < size - i; j++) {
				if (list.get(j - 1).getPrice() < list.get(j).getPrice()) {
					tmp = list.get(j - 1);
					list.set(j - 1, list.get(j));
					list.set(j, tmp);
				}
			}
		}
		return list;
	}
}
