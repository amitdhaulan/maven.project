package maven.test.project.maven.project;

import java.util.HashMap;
import java.util.Map;

public class App {
	public String reverseString(String src) {
		return new StringBuilder(src).reverse().toString();
	}

	public static void main(String[] args) {
		final String str = "Hello world!";
		App appObject = new App();

		System.out.println(" \"" + str + "\" is \""
				+ appObject.reverseString(str) + "\"");

		System.out.println(appObject.fn("amit kumar singh kashyap", "a"));
	}

	public int fn(String string, String subString) {
		int position = 0;
		
		char[] charr = new char[string.length()];
		for (int i = 0; i < string.length(); i++) {
			charr[i] = string.charAt(i);
			position = i + 1;
		}
		Map<String, Integer> map = new HashMap<String, Integer>();
		for(int j = 0; j<string.length(); j++){
			char compare = string.charAt(j);
			int count = 0;
			for(int i = 0; i<charr.length; i++){
				if(compare == charr[i]){
					count+= 1;
					if (!(compare ==  ' ')) {
						map.put(compare + "", count);	
					}
				}
			}
		}
		
		System.out.println(map);
		System.out.println("No of alphabets in the " + string + " is "
				+ position);
		System.out.print("Position of " + subString + " in " + string + " is ");
		return new StringBuilder(string).indexOf(subString) + 1;
	}
}
