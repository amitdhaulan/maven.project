package maven.test.project.maven.project;

public class TestTr {
	public static void main(String[] args) {
		triangle(5, 9);
		print(null);
	}

	public static void print(Object object) {
		System.out.println("Object");
	}

	public static void print(Integer integer) {
		System.out.println("Integer");
	}

	public static void print(float f) {
		System.out.println("float");
	}

	public static void print(double d) {
		System.out.println("double");
	}

	public static void print(long l) {
		System.out.println("long");
	}

	public static void print(short s) {
		System.out.println("short");
	}

	public static void triangle(int m, int n) {
		for (int i = 0; i < m; i++) {
			for (int j = 0; j <= n; j++) {
				if ((5 - i) <= j && (5 + i) >= j) {
					System.out.print("*");
				} else {
					System.out.print(" ");
				}
			}
			System.out.println("");
		}
	}
}
