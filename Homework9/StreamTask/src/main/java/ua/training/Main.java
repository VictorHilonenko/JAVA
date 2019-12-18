package ua.training;
import java.util.Arrays;
import java.util.Comparator;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Задание 1 Stream.
 *	Сделать массив int. Из него получить IntStream. Для него
 *	1. Найти среднее значение элементов массива (с использованием average и без).
 *	2. Найти минимальный элемент и вернуть значение и индекс (одновременно)
 *	3. Посчитать количество элементов равных нулю
 *	4. Посчитать количество элементов больше нуля
 *	5. Помножить элементы массива на число
 *
 * @author Victor
 *
 */
public class Main {

	public static void main(String[] args) {
		int intArray[] = {0, -100, 200, 300, 500, 50, 700};
		
		//0
		IntStream intStream = Arrays.stream(intArray);
		
		System.out.println("all elements of the stream:");
		intStream.forEach(System.out::println);
		
		//1.1
		intStream = Arrays.stream(intArray);
		
		OptionalDouble avg1 = intStream.average();
		System.out.println("average of the elements of the stream (with average function)...");
		if (avg1.isPresent()) {
			System.out.println(avg1.getAsDouble());
		} else {
			System.out.println("nothing");
		}
	
		//1.2
		intStream = Arrays.stream(intArray);
		
		System.out.println("average of the elements of the stream (w/o average function)...");
		int intSum = intStream.sum();
		long intCount = intArray.length;
		if(intCount != 0) {
			Double avg2 = (double) (intSum/intCount);
			System.out.println(avg2);
		} else {
			System.out.println("nothing");
		}
		
		//2
		intStream = Arrays.stream(intArray);
		OptionalInt intMin = intStream.min();
		System.out.println("min element (w/o it's index yet)");
		
		if (intMin.isPresent()) {
			System.out.println(intMin.getAsInt());
		} else {
			System.out.println("...");
		}
		
		//3
		intStream = Arrays.stream(intArray);
		
		long zeroElementsCount = intStream
			.filter(i -> i == 0)
			.count();
		System.out.println("zeroElementsCount = "+zeroElementsCount);
		
		
		//4
		intStream = Arrays.stream(intArray);
		
		long positiveElementsCount = intStream
			.filter(i -> i > 0)
			.count();
		System.out.println("positiveElementsCount = "+positiveElementsCount);
		
		//5
		intStream = Arrays.stream(intArray);
		
		int multilier = 31 * 17;
        System.out.println("Multiplied by " + multilier + ":");
		intStream
			.map(i -> i * multilier)
			.forEach(System.out::println);
	}

}
