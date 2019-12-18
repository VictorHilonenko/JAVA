package ua.training;

public class Main {

	public static void main(String[] args) {
		Linked<String> customLinkedList = new CustomLinkedList<>();
		
		customLinkedList.addLast("ok2");
		customLinkedList.addLast("ok3");
		customLinkedList.addLast("ok4");
		customLinkedList.addLast("ok5");
		customLinkedList.addFirst("ok1");
		
		System.out.println(customLinkedList.size());
		
		System.out.println(customLinkedList.getElementByIndex(0));
		System.out.println(customLinkedList.getElementByIndex(4));
		
		customLinkedList.remove(4);
		customLinkedList.remove(2);
		customLinkedList.remove("ok4");
		
		for (String str: customLinkedList) {
			System.out.println(str);
		}
		
		customLinkedList.setElementByIndex(0, "ok1!works");
		customLinkedList.setElementByIndex(1, "ok2!works");
		
		for (String str: customLinkedList) {
			System.out.println(str);
		}
		
	}

}
