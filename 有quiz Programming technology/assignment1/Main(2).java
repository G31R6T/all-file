import java.util.Scanner;

class plant {
	String name;
	String type;
	int nutrients;
	boolean living;
	// Radiation type£¬0-alpha,1-delta,2-no radiation
	int radiation;

	plant(String name, String type, int nutrients, boolean living, int radiation) {
		this.name = name;
		this.type = type;
		this.nutrients = nutrients;
		this.living = living;
		this.radiation = radiation;
	}
}

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		plant[] arr = new plant[n];
		for (int i = 0; i < n; i++) {
			String name = sc.next();
			String type = sc.next();
			int nutrients = sc.nextInt();
			boolean living = true;
			int radiation = 2;
			plant p = new plant(name, type, nutrients, living, radiation);
			arr[i] = p;
		}
		int day = sc.nextInt();
		// Radiation corresponding operation
		int[] ParrNutrients = { 2, -2, -1 };
		int[] DarrNutrients = { -3, 4, -1 };
		int[] BarrNutrients = { 1, 1, -1 };
		// The simulation process
		for (int i = 0; i < day; i++) {
			// Updated nutrient value
			for (int j = 0; j < n; j++) {
				if (arr[j].living == false)
					continue;
				else {
					if (arr[j].type.equals("p")) {
						arr[j].nutrients += ParrNutrients[arr[j].radiation];
					} else if (arr[j].type.equals("d")) {
						arr[j].nutrients += DarrNutrients[arr[j].radiation];
					} else if (arr[j].type.equals("b")) {
						arr[j].nutrients += BarrNutrients[arr[j].radiation];
					}
				}

			}
			// Type of radiation for the next day
			for (int j = 0; j < n; j++) {
				if (arr[j].living == false)
					continue;
				else {
					int N = arr[j].nutrients;
					int alpha = 10 - N;
					int delta = N;
					if (N < 5) {
						delta += 4;
					} else if (5 <= N && N <= 10) {
						delta += 1;
					}
					if (10 < N) {
						arr[j].living = false;
					}
					if (alpha == 3 || alpha > delta) {
						arr[j].radiation = 0;
					} else if (delta == 3 || delta > alpha) {
						arr[j].radiation = 1;
					} else {
						arr[j].radiation = 2;
					}
				}
			}
			// Print out
			System.out.println("Day " + (i + 1));
			for (int j = 0; j < n; j++) {
				String radiation;
				if (arr[j].living == false) {
					radiation = "dies";
				} else {
					radiation = "living";
				}
				System.out.println(arr[j].name + " " + arr[j].type + " "
						+ arr[j].nutrients + " " + radiation + " ");
			}
		}
		String dies = "";
		String living = "";
		for (int i = 0; i < n; i++) {
			if (arr[i].living == false) {
				dies += arr[i].name + " ";
			} else {
				living += arr[i].name + " ";
			}
		}
		System.out.println("----------------");
		System.out.println("dies: ");
		System.out.println(dies);
		System.out.println("----------------");
		System.out.println("living:");
		System.out.println(living);
	}
}
