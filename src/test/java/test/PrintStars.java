package test;

public class PrintStars {
	public static void main(String[] args) {
		int n = 5;
		int blankNum = n / 2;
		int starNum = 1;
		for (int i = 0; i <n; i++) {
			print(" ", blankNum);
			print("*", starNum);
			System.out.println();
			if (i < n / 2) {
				blankNum--;
				starNum += 2;
			} else {
				blankNum++;
				starNum -= 2;
			}
		}

	}

	static void print(String str, int num) {
		for (int i = 0; i < num; i++) {
			System.out.print(str);
		}
	}
}
