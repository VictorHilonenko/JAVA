package ua.training;

import lombok.*;

@Getter
@Setter
class Entry<K, V> {
    K key;
    V val;

    @Override
    public int hashCode() {
        int simpleInt = 13;
        int multipier = 11;
        if (key != null) {
            int hashCode = simpleInt * multipier + key.hashCode();
            return hashCode;
        }
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
		if (o == null || this.getClass().getName() != o.getClass().getName()) {
            return false;
        }
        Entry e = (Entry) o;
        if (this.key == e.key) {
            return true;
        }
        return false;
    }
}

public class CustomHashMap<K, V> {
    //private float loadfactor = 0.75f; //TODO implement later
    private int capacity = 100;
    private int size = 0;
    private Entry<K, V> elements[] = new Entry[capacity];

    private int Hashing(int hashCode) {
        int location = hashCode % capacity;
        //System.out.println("Location:"+location);
        return location;
    }

    public CustomHashMap() {
    	//
	}

	public int size() {
        // TODO Auto-generated method stub
        return this.size;
    }

    public boolean isEmpty() {
        if(this.size == 0) {
            return true;
        }
        
        return false;
    }
    
    private int getLocation(Object key) {
    	return Hashing(key.hashCode());
    }
    
    public boolean containsKey(Object key) {
        if(key == null) {
            if(elements[0].getKey() == null) {
                return true;
            }
        }
        
        int location = getLocation(key);
        
        Entry e = null;
        try {
            e = elements[location];
        }catch(NullPointerException ex) {
        	ex.printStackTrace();
        }
        
        if(e!= null && e.getKey() == key) {
            return true;
        }
        
        return false;
    }

    public boolean containsValue(Object value) {
        for(int i=0; i<elements.length; i++) {
            if(elements[i] != null && elements[i].getVal() == value) {
                return true;
            }
        }
        return false;
    }

    public V get(K key) {
        V ret = null;
        if(key == null) {
            Entry<K, V> e = null;
            try{
                e = elements[0];
            }catch(NullPointerException ex) {
            	ex.printStackTrace();
            }
            if(e != null) {
                return (V) e.getVal();
            }
        } else {
            int location = getLocation(key);
            Entry<K, V> e = null;
            try{
            	e = elements[location];
            }catch(NullPointerException ex) {
            	ex.printStackTrace();
            }
            if(e!= null && e.getKey() == key) {
                return (V)e.getVal();
            }
        }
        return ret;
    }

    public V put(K key, V val) {
        V ret = null;
        
        if (key == null) {
            ret = putForNullKey(val);
            return ret;
        } else {
            int location = getLocation(key);
            
            if(location >= capacity) {
                System.out.println("Rehashing required");
                return null;
            }
            
            Entry<K, V> e = null;
            try{
            	e = elements[location];
            }catch(NullPointerException ex) {
            	ex.printStackTrace();
            }
            
            if (e != null && e.getKey() == key) {
            	ret = (V) e.getVal();
            } else {
                //TODO check when to put a new object to a linked list here:
                Entry<K, V> newEntry = new Entry<K,V>();
                newEntry.setKey(key);
                newEntry.setVal(val);
                elements[location] = newEntry;
                size++;
            }
        }
        
        return ret;
    }

    private V putForNullKey(V val) {
        Entry<K, V> e = null;
        
        try {
            e = elements[0];
        }catch(NullPointerException ex) {
        	ex.printStackTrace();
        }
        
        V ret = null;
        if (e != null && e.getKey() == null) {
            ret = (V) e.getVal();
            e.setVal(val);
            return ret;
        } else {
            Entry<K, V> newEntry = new Entry<K,V>();
            newEntry.setKey(null);
            newEntry.setVal(val);
            elements[0] = newEntry;
            size++;
        }
        
        return ret;
    }

    public V remove(K key) {
        int location = getLocation(key);
        
        V ret = null;
        if(elements[location].getKey() == key) {
            for(int i=location; i<elements.length-1; i++) {
                elements[i] = elements[i+1];
            }
            size--;
        }
        
        return ret;
    }
}
