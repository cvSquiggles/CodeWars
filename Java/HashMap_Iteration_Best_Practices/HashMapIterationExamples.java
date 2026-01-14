import java.util.*;
import java.util.stream.*;

public class HashMapIterationExamples {
    
    public static void main(String[] args) {
        // Create and populate a sample HashMap
        HashMap<String, Integer> map = new HashMap<>();
        map.put("Apple", 10);
        map.put("Banana", 20);
        map.put("Orange", 15);
        map.put("Grape", 25);
        
        System.out.println("Original HashMap: " + map);
        System.out.println("\n========================================\n");
        
        // Method 1: Using entrySet() with for-each loop (RECOMMENDED for most cases)
        System.out.println("Method 1: Using entrySet() - Best for accessing both keys and values");
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }
        System.out.println("\n========================================\n");
        
        // Method 2: Using keySet() iteration
        System.out.println("Method 2: Using keySet() - Use when you only need keys");
        for (String key : map.keySet()) {
            System.out.println("Key: " + key);
        }
        System.out.println("\n========================================\n");
        
        // Method 3: Using keySet() with get() - LESS EFFICIENT, avoid if possible
        System.out.println("Method 3: Using keySet() with get() - Less efficient, not recommended");
        for (String key : map.keySet()) {
            Integer value = map.get(key);
            System.out.println("Key: " + key + ", Value: " + value);
        }
        System.out.println("\n========================================\n");
        
        // Method 4: Using values() iteration
        System.out.println("Method 4: Using values() - Use when you only need values");
        for (Integer value : map.values()) {
            System.out.println("Value: " + value);
        }
        System.out.println("\n========================================\n");
        
        // Method 5: Using Iterator with entrySet()
        System.out.println("Method 5: Using Iterator - Use when you need to remove entries while iterating");
        Iterator<Map.Entry<String, Integer>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Integer> entry = iterator.next();
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
            // You can safely remove entries here: iterator.remove();
        }
        System.out.println("\n========================================\n");
        
        // Method 6: Using forEach with lambda (Java 8+) - RECOMMENDED for modern code
        System.out.println("Method 6: Using forEach with lambda - Clean and readable");
        map.forEach((key, value) -> {
            System.out.println("Key: " + key + ", Value: " + value);
        });
        System.out.println("\n========================================\n");
        
        // Method 7: Using Stream API (Java 8+) - Best for transformations
        System.out.println("Method 7: Using Stream API - Best for filtering and transformations");
        map.entrySet().stream()
            .forEach(entry -> System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue()));
        System.out.println("\n========================================\n");
        
        // Method 8: Stream API with filter example
        System.out.println("Method 8: Stream API with filtering - Values greater than 15");
        map.entrySet().stream()
            .filter(entry -> entry.getValue() > 15)
            .forEach(entry -> System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue()));
        System.out.println("\n========================================\n");
        
        // Method 9: Stream API with sorting
        System.out.println("Method 9: Stream API with sorting by value");
        map.entrySet().stream()
            .sorted(Map.Entry.comparingByValue())
            .forEach(entry -> System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue()));
        System.out.println("\n========================================\n");
        
        // Performance comparison demonstration
        demonstratePerformance();
    }
    
    public static void demonstratePerformance() {
        System.out.println("Performance Comparison (with 100,000 entries):\n");
        
        // Create a large HashMap
        HashMap<Integer, String> largeMap = new HashMap<>();
        for (int i = 0; i < 100000; i++) {
            largeMap.put(i, "Value" + i);
        }
        
        // Test Method 1: entrySet()
        long startTime = System.nanoTime();
        for (Map.Entry<Integer, String> entry : largeMap.entrySet()) {
            Integer key = entry.getKey();
            String value = entry.getValue();
        }
        long endTime = System.nanoTime();
        System.out.println("entrySet(): " + (endTime - startTime) / 1000000.0 + " ms");
        
        // Test Method 2: keySet() with get()
        startTime = System.nanoTime();
        for (Integer key : largeMap.keySet()) {
            String value = largeMap.get(key);
        }
        endTime = System.nanoTime();
        System.out.println("keySet() with get(): " + (endTime - startTime) / 1000000.0 + " ms");
        
        // Test Method 3: forEach lambda
        startTime = System.nanoTime();
        largeMap.forEach((key, value) -> {
            Integer k = key;
            String v = value;
        });
        endTime = System.nanoTime();
        System.out.println("forEach lambda: " + (endTime - startTime) / 1000000.0 + " ms");
        
        System.out.println("\nConclusion: entrySet() is typically the fastest when you need both keys and values.");
    }
}
