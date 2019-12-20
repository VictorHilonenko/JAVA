package ua.training;

public class Main {

	public static void main(String[] args) {
		CustomHashMap<Integer, String> customHashMap = new CustomHashMap<>();
		customHashMap.put(1, "ok1");
		customHashMap.put(1, "ok1!");
		customHashMap.put(10, "ok2");
		customHashMap.put(79, "ok3");
        
		System.out.println(customHashMap);

        System.out.println("Val at 1 "+customHashMap.get(1));
        System.out.println("Val at 10 "+customHashMap.get(10));
        System.out.println("Val at 79 "+customHashMap.get(79));
        System.out.println("Val at 2 "+customHashMap.get(2));
        
        customHashMap.put(null, "ok4");
        
        System.out.println("Val at null "+customHashMap.get(null));
        System.out.println("Hashmap has key at null: "+customHashMap.containsKey(null));
        System.out.println("Hashmap has value at null: "+customHashMap.containsValue("ok4"));
        System.out.println("Size of Map:"+customHashMap.size());
        
        customHashMap.remove(10);
        
        System.out.println("Size of Map:"+customHashMap.size());
	}
}
