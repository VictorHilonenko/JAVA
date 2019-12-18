package ua.training;

public interface Linked<E> extends Iterable<E> {
	void addLast(E e);
	void addFirst(E e);
	boolean remove(E e);
	boolean remove(int index);
	E getElementByIndex(int index);
	boolean setElementByIndex(int index, E e);
	int size();
}
