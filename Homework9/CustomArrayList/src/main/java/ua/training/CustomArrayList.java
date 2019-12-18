package ua.training;

import java.util.Iterator;

public class CustomArrayList<T> implements Iterable<T> {
	private int size;
	private final static int defaultSize = 4;
	private T[] elementsArray;
	
	public CustomArrayList() {
		this(defaultSize);
	}
	
	public CustomArrayList(int initialCapacity) {
		super();
        if (initialCapacity < 0)
            throw new IllegalArgumentException("Illegal Capacity: "+ initialCapacity);
        this.elementsArray = (T[])new Object[initialCapacity];
        
		this.size = 0;
	}

	public boolean add(Object obj) {
		expandCapacity(size + 1);
        elementsArray[size++] = (T) obj;
        return true;
    }

    public Object get(int index) {
        Object obj = elementsArray[index];
        return obj;
    }

    public void set(int index, Object obj) {
        elementsArray[index] = (T) obj;
    }

    public boolean remove(int index) {
        if (index < 0) {
            throw new IllegalArgumentException("Index: "+index);
        }
        if (index >= size) {
            throw new IndexOutOfBoundsException("Index: "+index+", Size: "+size);
        }

    	T oldData[] = elementsArray;
        
        elementsArray = (T[])new Object[capacity() - 1];
        
        int i = 0;
        
        boolean removed = false; 
        
        for (T element: oldData) {
        	if(!removed && (i == index)) {
        		removed = true; 
        		continue;
        	}
    		elementsArray[i] = element;
        	i++;
        }
        
        size--;
        
        return removed;
    }
    
    public int size() {
        return size;
    }
    
    public int capacity() {
        return elementsArray.length;
    }
    
	public void expandCapacity(int minCapacity) {
        int oldCapacity = elementsArray.length;
    	if(minCapacity > oldCapacity) {
	    	Object oldData[] = elementsArray;
	        
	    	int newCapacity = (oldCapacity * 3)/2 + 1;
	    	
	        elementsArray = (T[])new Object[newCapacity];
	        
	        System.arraycopy(oldData, 0, elementsArray, 0, oldCapacity);
        }
    }

	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			int index = 0;
			
			@Override
			public boolean hasNext() {
				return index < size;
			}

			@Override
			public T next() {
				return getElementByIndex(index++);
			}
		};
	}

	public T getElementByIndex(int index) {
		return elementsArray[index];
	}
	
}
