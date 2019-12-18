package ua.training;

public class Main {

	public static void main(String[] args) {
		CustomArrayList<String> customArrayList = new CustomArrayList<>(); 
		
		System.out.println(customArrayList.capacity());
		
		customArrayList.add("ok1");
		customArrayList.add("ok2");
		customArrayList.add("ok3");
		customArrayList.add("ok4");
		customArrayList.add("ok5");
		customArrayList.add("ok6");
		
		System.out.println(customArrayList.capacity());
		
		customArrayList.set(5, customArrayList.get(5)+"!");;
		
		customArrayList.remove(3);
		
		for (String str: customArrayList) {
			System.out.println(str);
		}
		
		
	}

}
