package ua.training;

import java.util.List;

public class Main {
	
	public static void main(String[] args) {
		
		List<String> ints = new ArrayListUnremovable<>();
		ints.add("ok1");
		ints.add("ok2");
		ints.add("ok3");
		ints.add("ok4");
		ints.add("ok5");
		ints.add("ok6");
		ints.add("ok7");
		
		System.out.println(ints);
		
		try {
			ints.remove(4);
		} catch (Exception e) {
			System.out.println(e.getClass() + ": " + e.getMessage());
		}
		
		try {
			ints.remove("ok6");
		} catch (Exception e) {
			System.out.println(e.getClass() + ": " + e.getMessage());
		}
		
		System.out.println(ints);
	}
}
