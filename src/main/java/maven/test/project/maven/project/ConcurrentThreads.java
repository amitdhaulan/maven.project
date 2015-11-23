package maven.test.project.maven.project;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentThreads {
	static ConcurrentHashMap<String, String> map =new ConcurrentHashMap<String, String>();

	public static void main(String[] args) {

		Thread thread = new Thread(new Runnable() {
			public void run() {
				fillMap();
			}
		});

		Thread thread2 = new Thread(new Runnable() {
			public void run() {
				getMap();
			}
		});
		thread2.start();
		thread.start();
	}

	public static void fillMap() {
		for (int i = 0; i < 5; i++) {
			map.put(i + "", i + "");
		}
		System.out.println(map);
	}

	public static void getMap() {
		System.out.println("filled");
		for (Map.Entry<String, String> entry : map.entrySet()) {
			System.out.println(entry.getKey() + "=>" + entry.getValue());
		}
		

	}
}
