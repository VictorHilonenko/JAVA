package ua.training;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ForEach {
	public static void forEachExamples() {
		Set<Integer> numbers = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5));
	    
		//before Java 8:
	    for (Integer number : numbers) {
	        System.out.println(number);
	    }
	    
	    //now:
	    numbers.forEach(number -> System.out.println(number));
	    //or:
	    numbers.forEach(System.out::println);
	    
	    
	    ////////////////////////////////////////////////////////////////
	    
	    Map<Integer, Integer> numbers2 = new HashMap<>();
	    numbers2.put(1, 100);
	    numbers2.put(2, 200);
	    numbers2.put(3, 300);
	 
	    for (Map.Entry<Integer, Integer> entry : numbers2.entrySet()) {
	        System.out.println("Key: " + entry.getKey() + " Value: " + entry.getValue());
	    }	    
	    
	    //
	    
	    numbers2.forEach((k, v) -> System.out.println("Key: " + k + " Value: " + v));
	    
	    //
	    
	    numbers2.forEach((k, v) -> {
	    	if(v > 100) {
	    		System.out.println("Key: " + k + " Value: " + v);
	    	}
	    });
	    
	    
	}
}
