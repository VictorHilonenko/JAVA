package ua.training;

public class Main {

	public static void main(String[] args) {
		
		//w/o builder
//		SimplePOJO1 simplePOJO1 = new SimplePOJO1();
//		simplePOJO1.setStringField("new!");
//		simplePOJO1.setLongField(100500);
//		
//		System.out.println(simplePOJO1.toString());
		
		//with builder
		SimplePOJO1 simplePOJO1 = SimplePOJO1.builder()
                .stringField("new2!")
                .build();
		simplePOJO1.setLongField(900);
		
		System.out.println(simplePOJO1.toString());
		
	}

}
