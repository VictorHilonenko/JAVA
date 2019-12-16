package ua.training;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class MethodLinks {
	public static void methodLinksExamples() {
		
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        numbers.forEach(number -> System.out.println(number));
        //
        numbers.forEach(System.out::println);
        
        /////////////////////////////////////////////////////////////
        
        Function<String, Integer> toInteger = string -> parse(string);
        Integer integer = toInteger.apply("6");
        System.out.println(integer);
        
        Function<String, Integer> toInteger2 = MethodLinks::parse;
        Integer integer2 = toInteger2.apply("7");
        System.out.println(integer2);
        
        /////////////////////////////////////////////////////////////
        
        Function<String, String> trim = String::trim;
        String str = trim
                .andThen(String::toLowerCase)
                .andThen(StringBuilder::new)
                .andThen(StringBuilder::reverse)
                .andThen(StringBuilder::toString)
                .apply(" !ABCDEFG! ");
        		
        System.out.println(str); // Output !gfedcba!        
        
		
	}
	
	private static Integer parse(String s) {
        return Integer.parseInt(s);
    }
	
}
