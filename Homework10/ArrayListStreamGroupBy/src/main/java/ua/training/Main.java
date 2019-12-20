package ua.training;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Main {
	public static void main(String args[]) {
		List<Integer> ints = new ArrayList<>();
		ints.add(500);
		ints.add(1024);
		ints.add(700);
		ints.add(512);
		ints.add(800);
		ints.add(512);
		ints.add(1024);
		
		System.out.println(ints);
		
		ints.stream()
        .collect(Collectors.groupingBy(i -> i, Collectors.counting()))
        .forEach((s, count) -> System.out.println(s + " - " + count));		
	}
}
