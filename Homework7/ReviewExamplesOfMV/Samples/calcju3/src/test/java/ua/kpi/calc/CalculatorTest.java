package ua.kpi.calc;

import static org.junit.Assert.*;

import java.util.Optional;

import org.junit.Test;

public class CalculatorTest {
	
	@Test
	public void testAdditionTwoAndThree(){
		Integer firstSummund = 2;
		Integer secondSummund = 3;
		Integer expectedSum = 5;
		
		Integer actualResult;
		Calculator calculator = new Calculator();
		actualResult = calculator.add(firstSummund , secondSummund);

		assertEquals(expectedSum, actualResult);
	}
	
	@Test(expected = NullPointerException.class )
	public void testAdditionTwoAndNull(){
		Integer firstSummund = 2;
		Integer secondSummund = null;
		
		Calculator calculator = new Calculator();
		calculator.add(firstSummund , secondSummund);
		fail();
	}
	
	@Test
	public void testDeleteFourOnTwo(){
		Integer dividend = 4;
		Integer divisor = 2;
		Integer expectedResult = 2;
		
		Calculator calculator = new Calculator();
		Integer actualResult;
		actualResult = calculator.divide(dividend , divisor);

		assertEquals(expectedResult, actualResult);
	}

	
	@Test(expected = IllegalArgumentException.class)
	public void testDeleteFourOnZero(){
		Integer dividend = 4;
		Integer divisor = 0;

		Calculator calculator = new Calculator();
		calculator.divide(dividend , divisor);

		fail();
	}
	
	@Test
	public void testDeleteFourOnTwoSafely(){
		Integer dividend = 4;
		Integer divisor = 2;
		Optional<Integer> expectedResult = Optional.of(2);
		Optional<Integer> actualResult;
		
		Calculator calculator = new Calculator();
		actualResult = calculator.divideSafely(dividend, divisor);
		assertEquals(expectedResult, actualResult);
		
		if(actualResult.isPresent()){
			System.out.println(actualResult.get());
		}
		
		actualResult.ifPresent( value -> System.out.println(value) );
		
		actualResult.ifPresent( System.out::println);
		
		
	}
	
	@Test
	public void testDeleteFourOnZeroSafely(){
		Integer dividend = 4;
		Integer divisor = 0;
		Optional<Integer> expectedResult = Optional.empty();
		Optional<Integer> actualResult;
		
		Calculator calculator = new Calculator();
		actualResult = calculator.divideSafely(dividend, divisor);
		assertEquals(expectedResult, actualResult);
	}
	
	
	
}
