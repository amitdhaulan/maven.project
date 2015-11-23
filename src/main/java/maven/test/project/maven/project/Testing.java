package maven.test.project.maven.project;

import java.util.HashMap;
import java.util.Map;


public class Testing {
	
	public static void main(String[] args) {

		Map<MyInnerClass, String> myMap = new HashMap<MyInnerClass, String>();

		// ***********************************************
		// Same hashCode same equals
		myMap.put(new MyInnerClass("AB", "CD"), "value");
		myMap.put(new MyInnerClass("AB", "CD"), "First");

		// Same hashCode different equals
		myMap.put(new MyInnerClass("ABC", "D"), "Second");
		// ***********************************************

		System.out.println(myMap);
		System.out.println(myMap.get(new MyInnerClass("ABC", "D")));
		System.out.println(myMap.get(new MyInnerClass("AB", "CD")));
					
	}
}

class MyInnerClass {
	private String StrA = "amit";
	private String StrB = "kumar";

	public MyInnerClass(String strA, String strB) {
		super();
		StrA = strA;
		StrB = strB;
	}

	@Override
	public int hashCode() {
		return (StrA + StrB).length();
	}

	@Override
	public boolean equals(Object obj) {
		if (StrB.length() == StrA.length()) {
			return true;
		}
		/*if (StrB.equals(StrA)) {
			return true;
		}*/
		return false;
	}
}
