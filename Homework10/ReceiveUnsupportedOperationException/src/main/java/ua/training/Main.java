package ua.training;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		
		try {
			List<String> list1 = new ArrayList<>();
			list1.add("12");
			list1.add("15");
			list1.add("19");
			list1.remove(2);
			System.out.println("it works");
		} catch (Exception e) {
			System.out.println(e.getClass() + " received");
		}
		
		try {
			List<String> list2 = Arrays.asList("12", "15", "19");
			list2.remove(2);
		} catch (Exception e) {
			System.out.println("it doesn't, because Arrays.asList returns a fixed-size list");
			System.out.println(e.getClass() + " received");
		}
		
		//

	}

}
