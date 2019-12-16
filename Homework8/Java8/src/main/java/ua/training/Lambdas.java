package ua.training;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Lambdas {
	
	public static void Sortings() {
		List<User> users = Arrays.asList(
	            new User("John", 28),
	            new User("Jane", 28),
	            new User("Alex", 21));

	    System.out.println("Before sort:");
	    for (User user : users) {
	        System.out.println(user);
	    }

	    Collections.sort(users, new Comparator<User>() {
	        public int compare(User o1, User o2) {
	            return o1.getAge() - o2.getAge();
	        }
	    });

	    System.out.println("\nAfter sort:");
	    users.forEach(u -> System.out.println(u.toString()));
	    
	    ////////////////////////////////////////////////////////////////
	    
	    Collections.sort(users, (o1, o2) -> o1.getAge() - o2.getAge());
	    
	    System.out.println("\nAfter sort:");
	    users.forEach(System.out::println);
	    
	    ////////////////////////////////////////////////////////////////
	    
	    users.sort((o1, o2) -> o1.getAge() - o2.getAge());
	    //or
	    users.sort(Comparator.comparing(User::getAge));
	    
	    System.out.println("\n!After sort:");
	    users.forEach(System.out::println);
	    
	    ////////////////////////////////////////////////////////////////
	    
	    Comparator<User> comparator = (o1, o2) -> o1.getAge() - o2.getAge();
	    users.sort(comparator.reversed());
	    //or)
	    //users.sort((o1, o2) -> o2.getAge() - o1.getAge());
	    
	    System.out.println("\nAfter sort:");
	    users.forEach(System.out::println);
	    
	    ////////////////////////////////////////////////////////////////
	    
	    Collections.sort(users, new Comparator<User>() {
	        @Override
	        public int compare(User o1, User o2) {
	            if (o1.getAge() == o2.getAge())
	                return o1.getName().compareTo(o2.getName());
	            else return o1.getAge() - o2.getAge();
	        }
	    });
	    
	    System.out.println("\nAfter sort:");
	    users.forEach(System.out::println);
	    
	    ////////////////////////////////////////////////////////////////
	    
	    Comparator<User> comparator2 = ((o1, o2) -> {
	    	if(o1.getAge() == o2.getAge()) {
	    		return o1.getName().compareToIgnoreCase(o2.getName());
	    	} else {
	    		return o1.getAge() - o2.getAge();
	    	}
	    });
	    
	    users.sort(comparator2.reversed());
	    
	    System.out.println("\nAfter sort:");
	    users.forEach(System.out::println);
	    
	    ////////////////////////////////////////////////////////////////
	}
	
	
}
