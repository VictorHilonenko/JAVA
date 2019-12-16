package ua.training;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class JavaTime {
	public static void localDateExamples() {
		LocalDate date = LocalDate.now(); // получаем текущую дату
		int year = date.getYear();
        int month = date.getMonthValue();
        int dayOfMonth = date.getDayOfMonth();
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        System.out.println(date);
        System.out.println(dayOfWeek);
        System.out.printf("%d.%d.%d \n", dayOfMonth, month, year);	
        
        LocalDateTime localDateTime = LocalDateTime.now();
        //localDateTime.atZone(zone)
        System.out.println(localDateTime);
        System.out.println(localDateTime.getHour());
        
        System.out.println(localDateTime.minusWeeks(1));
        
        date = LocalDate.of(1914, 1, 30);
        System.out.println(date);
        
        date = date.plusYears(4);
        System.out.println(date);
        
        date = date.plusMonths(1);
        System.out.println(date);
        
        date = date.plusMonths(3);
        date = date.plusDays(14);
        System.out.println(date);   // 1918-11-11
                 
        date = date.minusMonths(10);
        date = date.minusDays(3);
        System.out.println(date);   // 1918-01-08        
        
	}
	
}
