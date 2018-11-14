package project2;

/**
 * A sorted collection. The element in this list are stored in order determined
 * by the natural ordering of those elements (i.e., the {@code compareTo()} method
 * defined in the elements' class).  
 * The user can access elements by their integer index (position in
 * the list), and search for elements in the list.<p>
 *
 * Extends Comparable and implements OrderedList.
 * They do not allow null elements.<p>
 *
 * @param <E> the type of elements in this list
 * 
 * @author Josie Williams
 */

public class OrderedLinkedList<E extends Comparable <E>> implements OrderedList<E>{	
	/**
	 * Nested private class. Stores elements and the a reference to the next object in the list
	 * Identical to example on page 126 of "Data Structures and Algorithms" textbook.
	 * Extends OrderedLinkedList.
	 * 
	 * @author Michael T. Goodrich, Roberto Tamassia, Michael H. Goldwasser
	 * @param <E> the type of elements to be stored in OrderedLinkedList. 
	 */
	private static class Node<E> extends OrderedLinkedList {
		private E element;
		private Node<E> next;
		public Node (E e, Node<E> n) {
			element = e;
			next = n;
		}
		
		public E getElement() {
			return element;
		}
		public Node<E> getNext() {
			return next;
		}
		public void setNext(Node<E> n) {
			next = n;
		}
	}
	
	//head node of the list. will be null if empty.
	private Node<E> head = null; 

	//tail node of the list. will be null if empty.
	private Node<E> tail = null; 
	
	//number of nodes in the list.
	private int size = 0; 
	
	//Default constructor, creates an empty OrderedLinkedList object.
	public OrderedLinkedList() {
	
	}
	
	/**
     * Returns the number of elements in this list.
     *
     * @return the number of elements in this list
     */
	
	public int size() {
		return size;
	}
	/**
	 * Returns true if the specified list size is equal to zero.
	 * 
	 * @return true if the specified list size is equal to zero.
	 */
	
	public boolean isEmpty() {
		return size == 0;
	}
	/**
	 * Returns the first element in a list.
	 * 
	 * @return the head of the specified list, null otherwise.
	 */
	
	public E first() {
		if (isEmpty()) {
			return null;
		}
		return head.getElement();
	}
	/**
	 * Returns the last element in the list 
	 * 
	 * @return the tail of the specified list, null otherwise.
	 */
	public E last() {
		if (isEmpty()) {
			return null;
		}
		return tail.getElement();
	}
	
	/**
	 * Sets the element value of head.
	 * 
	 * @param E e, the object that will be the head.
	 */	
	
	public void addFirst(E e) {
		head = new Node<>(e, head);
		if (size == 0) {
			tail = head;
		}
		size++;
	}
	/**
	 * Sets the element value of tail.
	 * 
	 * @param E e, the object that will be the tail.
	 */	
	
	public void addLast(E e) {
		Node<E> newest = new Node<>(e,null);
		if (isEmpty()) {
			head = newest;
		}
		else
			tail.setNext(newest);
		size++;
	}
	
	/**
	 * Removes the head of the specified list and sets the head to the next node.
	 * 
	 * @return the object that was removed.
	 */	
	
	public E removeFirst() {
		if (isEmpty()) {
			return null;
		}
		E answer = head.getElement();
		head = head.getNext();
		size--;
		if (size == 0) {
			tail = null;
		}
		return answer;
	}

	@Override
	 /**
     * Adds the specified element to  this list in a sorted order. 
     *
     * <p>The element added must implement Comparable<E> interface. This list 
     * does not permit null elements. 
     *
     * @param e element to be appended to this list
     * @return <tt>true</tt> if this collection changed as a result of the call
     * @throws ClassCastException if the class of the specified element
     *         does not implement Comparable<E> 
     * @throws NullPointerException if the specified element is null
     */
	
	public boolean add(E e) throws NullPointerException, ClassCastException {
		if (e == null) 
			throw new NullPointerException();
		if (!(e instanceof Comparable<?>))
			throw new ClassCastException();
		
		if (this.isEmpty()) {
			head = new Node(e, null);
			tail = head;
			size++;
			return true;
		}
		
		if (e.compareTo(head.getElement()) <= 0) {
			Node<E> newNode = new Node (e, head);
			head = newNode;
			size++;
			return true;
		}
		
		if (e.compareTo(tail.getElement()) >= 0) {
			tail.next = new Node(e, null);
			tail = tail.next;
			size++;
			return true;
		}
		
		Node<E> current = head;
		
		while (current.next != null) {
			if (e.compareTo(current.getElement()) >= 0 && e.compareTo(current.next.getElement()) <=0) {
				Node<E> newNode = new Node(e, null);
				newNode.setNext(current.next);
				current.next = newNode;
				size++;
				return true;
			}
			current = current.next;
		}
		return false;
	}

    /**
     * Removes all of the elements from this list.
     * The list will be empty after this call returns.
     */
	@Override
	public void clear() {
		if (head == null) 
			size = 0;
		if (!(this.isEmpty())) {
			head.next = null;
			head = null;
			size = 0;
		}
	}
    
	/**
     * Returns <tt>true</tt> if this list contains the specified element.
     *
     * @param o element whose presence in this list is to be tested
     * @return <tt>true</tt> if this list contains the specified element
     * @throws ClassCastException if the type of the specified element
     *         is incompatible with this list
     * @throws NullPointerException if the specified element is null 
     */
	
	@Override
	public boolean contains(Object o) throws ClassCastException, NullPointerException {
		if (o.equals(null))
			throw new NullPointerException();
		
		if (!(this.isEmpty()) && !(this.head.getElement().getClass().equals(o.getClass())))	
			throw new ClassCastException();
	
		Node<E> element = new Node(o, null);
		Node<E> current = head;
		
		for (int i = 0; i < size; i++) {
			if (element.getElement().equals(current.getElement()))
				return true;
			current = current.next;
		}
		return false;
	}
	
    /**
     * Returns the element at the specified position in this list.
     *
     * @param index index of the element to return
     * @return the element at the specified position in this list
     * @throws IndexOutOfBoundsException if the index is out of range 
     * <tt>(index < 0 || index >= size())</tt>
     */
	
	@Override
	public E get(int index) throws IndexOutOfBoundsException{
		if (index > size || index < 0)
			throw new IndexOutOfBoundsException();
		
		Node<E> current = head;
		
		if (index == 0) 
			return current.getElement();
		else if (index != 0) {
			for (int i = 0; i < index; i++) {
				current = current.next;
			}
			return current.getElement();
		}
		else
			return null;
	}
	
    /**
     * Returns the index of the first occurrence of the specified element
     * in this list, or -1 if this list does not contain the element.
     *
     * @param o element to search for
     * @return the index of the first occurrence of the specified element in
     *         this list, or -1 if this list does not contain the element
     */
	
	@SuppressWarnings("unchecked")
	@Override
	public int indexOf(Object o) {
		Node<E> finder = new Node(o, null);
 		Node<E> current = this.head;
 		
 		for (int i = 0; i < this.size; i++) {
 			if((current.getElement()).equals(finder.getElement()))
 				return i;
 			current = current.next;
 		}
 		return -1;
	}
    /**
     * Removes the element at the specified position in this list.  Shifts any
     * subsequent elements to the left (subtracts one from their indices if such exist).
     * Returns the element that was removed from the list.
     *
     * @param index the index of the element to be removed
     * @return the element previously at the specified position
     * @throws IndexOutOfBoundsException  if the index is out of range 
     * <tt>(index < 0 || index >= size())</tt>
     */
    
	@SuppressWarnings("unchecked")
	@Override
	public E remove(int index) throws IndexOutOfBoundsException {
		if ((index > size || index < 0))
			throw new IndexOutOfBoundsException();
		
		Node<E> shift1 = head;
		Node<E> shift2 = head;
		Node<E> removed = head;
		
		if(index == 0 && size == 1) {
			E removeFirst = head.getElement();
			removeFirst();
			return removeFirst;
		}
		
		for (int i = 0; i < this.size; i++) {
			if (i != index - 1) {
				shift1 = shift1.next;
			}
			if (i != index + 1) {
				shift2 = shift2.next;
			}
			if (i != index) {
				removed = removed.next;
			}
		}
		
		shift1.next = shift2;
		size--;
		return removed.getElement();
	}
	
    /**
     * Removes the first occurrence of the specified element from this list,
     * if it is present.  If this list does not contain the element, it is
     * unchanged.  More formally, removes the element with the lowest index
     * {@code i} such that
     * <tt>(o.equals(get(i))</tt>
     * (if such an element exists).  Returns {@code true} if this list
     * contained the specified element (or equivalently, if this list
     * changed as a result of the call).
     *
     * @param o element to be removed from this list, if present
     * @return {@code true} if this list contained the specified element
     * @throws ClassCastException if the type of the specified element
     *         is incompatible with this list
     * @throws NullPointerException if the specified element is null and this
     *         list does not permit null elements
     */
	
	@Override
	public boolean remove(Object o) throws ClassCastException, NullPointerException {
		if (!(this.isEmpty()) && !(this.head.getElement().getClass().equals(o.getClass())))	
			throw new ClassCastException();
		
		if (o.equals(null))
			throw new NullPointerException();
		
		else if (size == 1) {
			head.next = null;
			head = null;
			size = 0;
			return true;
		}
		else if (size == 2) {
			if (o.equals(head.getElement())) {
				removeFirst();
			}
			else {
				tail = null;
				head = tail;
				size--;
				return true;
			}
		}
		else {
			Node<E> remove = new Node (o, null);
			Node<E> current = head;
			
			for (int i = 0; i < this.size; i++) {
				if (current.getElement().equals(remove.getElement())){
					Node<E> previous = getPrevious(remove.getElement());
					previous.next = current.next;
					size--;
					return true;
				}
				current = current.next;
			}
		}
		return false;
	}
    /**
     * Returns a shallow copy of this list. (The elements
     * themselves are not cloned.)
     *
     * @return a shallow copy of this list instance
     * @throws CloneNotSupportedException - if the object's class does 
     *         not support the Cloneable interface
     */
	public Object clone() throws CloneNotSupportedException {
		OrderedLinkedList clone = new OrderedLinkedList();

		if (this.isEmpty())
			return clone;
		
		if (!(this.head instanceof Cloneable))
			throw new CloneNotSupportedException();
				
		if (size == 1) {
			Node<E> newNode = head;
			clone.add(newNode.getElement());
			clone.head = clone.tail;
			return clone;
		}
		else {
			Node<E> current = head;
			for (int i = 0; i < size; i++) {
				Node<E> newNode = current;
				clone.add(newNode.getElement());
				current = current.next;
			}
			return clone;
		}
	}
	
	/**
     * Compares the specified object with this list for equality.  Returns
     * {@code true} if and only if the specified object is also a list, both
     * lists have the same size, and all corresponding pairs of elements in
     * the two lists are <i>equal</i>.  (Two elements {@code e1} and
     * {@code e2} are <i>equal</i> if {@code e1.equals(e2)}.)  
     * In other words, two lists are defined to be
     * equal if they contain the same elements in the same order.<p>
     *
     * @param o the object to be compared for equality with this list
     * @return {@code true} if the specified object is equal to this list
     */
	
	@SuppressWarnings("rawtypes")
	@Override
	public boolean equals (Object o) {
		if (o == null) 
			return false;
		if (getClass()!= o.getClass())
			return false;
		
		OrderedLinkedList element = (OrderedLinkedList) o;
		
		if (size != element.size)
			return false;
		
		Node walkA = head;
		Node walkB = element.head;
		
		while (walkA != null) {
			if (!walkA.getElement().equals(walkB.getElement()))
				return false;
			walkA = walkA.getNext();
			walkB = walkB.getNext();
		}
		return true;
	}
	
	/**
     * Returns a string representation of this list.  The string
     * representation consists of a list of the list's elements in the
     * order they are stored in this list, enclosed in square brackets
     * (<tt>"[]"</tt>).  Adjacent elements are separated by the characters
     * <tt>", "</tt> (comma and space).  Elements are converted to strings 
     * by the {@code toString()} method of those elements.
     *
     * @return a string representation of this list
     */
	@Override
	public String toString() {
		String output = "[";
		
		if (this.isEmpty())
			output += "]";
		
		for (int i = 0; i < size; i++) {
			if (i == size - 1) {
				output += this.get(i) + "]";
				return output;
			}
			output += this.get(i) + ", ";
			}
		return output;
	}
	/**
	 * Returns the node that comes before the element of the specified object.
	 * 
	 * @param E element.
	 * @return Node<E> object.
	 */
	public Node<E> getPrevious (E element) {
		
		int pointer = this.indexOf(element);
		Node<E> current = head;
		
		for (int i = 0; i < pointer - 1; i++) {
			current = current.next;
		}
		return current;
	}
}
