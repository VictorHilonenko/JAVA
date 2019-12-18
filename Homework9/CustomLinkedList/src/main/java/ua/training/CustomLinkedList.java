package ua.training;

import java.util.Iterator;

public class CustomLinkedList<E> implements Linked<E> {
	private Node<E> firstNode;
	private Node<E> lastNode;
	private int size = 0;
	
	public CustomLinkedList() {
		firstNode = null;
		lastNode = null;
	}

	private static class Node<E> {
		E item;
		Node<E> next;
		Node<E> prev;
		
		private Node(E item, Node<E> next, Node<E> prev) {
			super();
			this.item = item;
			this.next = next;
			this.prev = prev;
		}

		public E getItem() {
			return item;
		}

		public void setItem(E item) {
			this.item = item;
		}

		public Node<E> getNext() {
			return next;
		}

		public void setNext(Node<E> next) {
			this.next = next;
		}

		public Node<E> getPrev() {
			return prev;
		}

		public void setPrev(Node<E> prev) {
			this.prev = prev;
		}
		
	}
	
	public void addLast(E e) {
		Node<E> newNode = new Node<E>(e, null, null);
		
		if(lastNode == null) {
			newNode.setNext(newNode);
			newNode.setPrev(newNode);
			firstNode = newNode;
		} else {
			lastNode.setNext(newNode);
			newNode.setPrev(lastNode);
		}
		
		lastNode = newNode;
		
		size++;
	}

	public void addFirst(E e) {
		Node<E> newNode = new Node<E>(e, null, null);
		
		if(firstNode == null) {
			newNode.setNext(newNode);
			newNode.setPrev(newNode);
			lastNode = newNode;
		} else {
			firstNode.setPrev(newNode);
			newNode.setNext(firstNode);
		}
		
		firstNode = newNode;
		
		size++;
	}

	public int size() {
		return size;
	}

	public E getElementByIndex(int index) {
		Node<E> nodeByIndex = getNodeByIndex(index); 
		if(nodeByIndex == null) {
			return null;
		} else {
			return nodeByIndex.getItem();
		}
	}
	
	public boolean setElementByIndex(int index, E e) {
		Node<E> nodeByIndex = getNodeByIndex(index); 
		
		if (nodeByIndex == null) {
			return false;
		}
		
		nodeByIndex.setItem(e);
		
		return true;
	}
	
	private Node<E> getNodeByIndex(int index) {
		Node<E> currentNode = firstNode;
		
		for (int i = 0; i < index; i++) {
			currentNode = currentNode.next;
		}
		
		return currentNode;
	}
	
	private Node<E> getNodeByValue(E e) {
		Node<E> currentNode = firstNode;
		
		if(e == null) {
			return null;
		}
		
		do {
			if(currentNode.getItem() == null) {
				return null;
			} else {
				if(e.equals(currentNode.getItem())) {
					return currentNode;
				}
			}
			currentNode = currentNode.next;
		} while (currentNode != null);
		
		return currentNode;
	}
	
	public boolean remove(int index) {
		if((index < 0) || (index > size - 1)) {
			throw new IllegalArgumentException();
		}
		
		if(size == 0) {
			return true;
		}
			
		Node<E> currentNode = (Node<E>) getNodeByIndex(index);
		
		Node<E> prevNode = currentNode.getPrev(); 
		Node<E> nextNode = currentNode.getNext();
		if(prevNode != null) {
			prevNode.setNext(nextNode);
		}
		if(nextNode != null) {
			nextNode.setPrev(prevNode);
		}
		
		size--;
		
		return true;
	}

	public boolean remove(E e) {
		if(size == 0) {
			return true;
		}
			
		Node<E> currentNode = (Node<E>) getNodeByValue(e);
		
		Node<E> prevNode = currentNode.getPrev(); 
		Node<E> nextNode = currentNode.getNext();
		if(prevNode != null) {
			prevNode.setNext(nextNode);
		}
		if(nextNode != null) {
			nextNode.setPrev(prevNode);
		}
		
		size--;
		
		return true;
	}

	@Override
	public Iterator<E> iterator() {
		return new Iterator<E>() {
			int index = 0;
			
			@Override
			public boolean hasNext() {
				return index < size;
			}

			@Override
			public E next() {
				return getElementByIndex(index++);
			}
		};
	}
}
