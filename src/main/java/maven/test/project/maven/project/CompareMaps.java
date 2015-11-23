package maven.test.project.maven.project;

import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CompareMaps {
	public final static int THREAD_POOL_SIZE = 5;
	public static Map<String, Integer> HashTableObject = null;
	public static Map<String, Integer> SynchronizedMapObject = null;
	public static Map<String, Integer> ConcurrentHashMapObject = null;

	public static void main(String[] args) {
		HashTableObject = new Hashtable<String, Integer>();
		try {
			performTest(HashTableObject);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// Test with synchronizedMap Object
		SynchronizedMapObject = Collections
				.synchronizedMap(new HashMap<String, Integer>());
		try {
			performTest(SynchronizedMapObject);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// Test with ConcurrentHashMap Object
		ConcurrentHashMapObject = new ConcurrentHashMap<String, Integer>();
		try {
			performTest(ConcurrentHashMapObject);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void performTest(
			final Map<String, Integer> threads)
			throws InterruptedException {

		System.out.println("Test started for: " + threads.getClass());
		long averageTime = 0;
		for (int i = 0; i < 5; i++) {

			long startTime = System.nanoTime();
			ExecutorService exServer = Executors
					.newFixedThreadPool(THREAD_POOL_SIZE);

			for (int j = 0; j < THREAD_POOL_SIZE; j++) {
				exServer.execute(new Runnable() {
					@SuppressWarnings("unused")
					public void run() {

						for (int i = 0; i < 500000; i++) {
							Integer randomNumber = (int) Math
									.ceil(Math.random() * 550000);

							// Retrieve value. We are not using it anywhere
							Integer value = threads
									.get(String.valueOf(randomNumber));

							// Put value
							threads.put(
									String.valueOf(randomNumber),
									randomNumber);
						}
					}
				});
			}

			// Make sure executor stops
			exServer.shutdown();

			// Blocks until all tasks have completed execution after a shutdown
			// request
			exServer.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);

			long entTime = System.nanoTime();
			long totalTime = (entTime - startTime) / 1000000L;
			averageTime += totalTime;
			System.out.println("500K entried added/retrieved in " + totalTime
					+ " ms");
		}
		System.out.println("For " + threads.getClass()
				+ " the average time is " + averageTime / 5 + " ms\n");
	}
}
