package textgen;

import java.util.AbstractList;


/**
 * A class that implements a doubly linked list
 *
 * @param <E> The type of the elements stored in the list
 * @author UC San Diego Intermediate Programming MOOC team
 */
public class MyLinkedList<E> extends AbstractList<E> {
    LLNode<E> head;
    LLNode<E> tail;
    int size;

    /**
     * Create a new empty LinkedList
     */
    public MyLinkedList() {
        head = new LLNode<>(null);
        tail = new LLNode<>(null);
        head.next = tail;
        tail.prev = head;
        size = 0;
    }

    /**
     * Appends an element to the end of the list
     *
     * @param element The element to add
     */
    public boolean add(E element) {
        if(element == null){
            throw new NullPointerException();
        }
        LLNode<E> newNode = new LLNode<>(element);

        if(tail == null){
            return false;
        }
        tail.prev.next = newNode;
        newNode.prev = tail.prev;
        newNode.next = tail;
        tail.prev = newNode;

        size++;
        return true;
    }

    /**
     * Get the element at position index
     *
     * @throws IndexOutOfBoundsException if the index is out of bounds.
     */
    public E get(int index) {
        if (index < 0 || size == 0) {
            throw new IndexOutOfBoundsException();
        }

        if (head == null) {
            throw new IndexOutOfBoundsException();
        }

        LLNode curr = head.next;
        int count = 0;
        while (curr != tail) {
            if (index == count) {
                return (E) curr.data;
            }
            curr = curr.next;
            count++;
        }
        if (index > size-1 ) {
            throw new IndexOutOfBoundsException();
        }
        return null;
    }

    /**
     * Add an element to the list at the specified index
     *
     * @param the index where the element should be added
     * @param element The element to add
     */
    public void add(int index, E element) {

        if(element == null){
            throw new NullPointerException();
        }
        if(index > size  || index < 0){
            throw new IndexOutOfBoundsException();
        }

        int count = 0;
        LLNode curr = head.next;
        LLNode<E> newNode = new LLNode<>(element);

        while(curr!= null){
            if(index == count){
                newNode.next = curr;
                newNode.prev = curr.prev;
                curr.prev.next = newNode;
                curr.prev = newNode;
                size++;
                break;
            }

            count++;
            curr = curr.next;
        }
    }


    /**
     * Return the size of the list
     */
    public int size() {
        return this.size;
    }

    /**
     * Remove a node at the specified index and return its data element.
     *
     * @param index The index of the element to remove
     * @return The data element removed
     * @throws IndexOutOfBoundsException If index is outside the bounds of the list
     */
    public E remove(int index) {
        if (index > size - 1 || index < 0) {
            throw new IndexOutOfBoundsException();
        }

        LLNode curr = head.next;
        LLNode prev = head;
        int count = 0;
        while (curr != null) {
            if (index == count) {
                prev.next = curr.next;
                curr.next.prev = prev;
                size--;
                return (E) curr.data;
            }
            prev = curr;
            curr = curr.next;
            count++;
        }
        return null;
    }

    /**
     * Set an index position in the list to a new element
     *
     * @param index   The index of the element to change
     * @param element The new element
     * @return The element that was replaced
     * @throws IndexOutOfBoundsException if the index is out of bounds.
     */
    public E set(int index, E element) {

        if(element == null){
            throw new NullPointerException();
        }
        if(index > size || index < 0){
            throw new IndexOutOfBoundsException();
        }

        LLNode curr = head.next;
        int count = 0;
        while (curr != null) {
            if (index == count) {
                curr.data = element;
                return (E) curr.data;
            }
            curr = curr.next;
            count++;
        }

        return null;
    }
}

class LLNode<E> {
    LLNode<E> prev;
    LLNode<E> next;
    E data;


    public LLNode(E e) {
        this.data = e;
        this.prev = null;
        this.next = null;
    }


}
