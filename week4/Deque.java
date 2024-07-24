package week4;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdOut;

/**
 * Deque
 * 
 * @param Item
 */
public class Deque<Item> implements Iterable<Item> {

  private Node first, last;
  private int size;

  // construct an empty deque
  public Deque() {
    first = null;
    last = null;
  }

  // is the deque empty?
  public boolean isEmpty() {
    return size == 0;
  }

  // return the number of items on the deque
  public int size() {
    // Iterator<Item> iterator = iterator();
    // int count = 0;
    // while (iterator.hasNext()) {
    // iterator.next();
    // // StdOut.print(iterator.next().value + " ");
    // count++;
    // }
    // return count;
    return size;
  }

  // add the item to the front
  public void addFirst(Item item) {
    if (item == null) {
      throw new IllegalArgumentException("Null Item");
    }
    if (first != null) {
      Node oldFirst = first;
      first = new Node();
      first.item = item;
      first.next = oldFirst;
      oldFirst.previous = first;
    } else {
      first = new Node();
      first.item = item;
      first.next = null;
      first.previous = null;
      last = first;
    }
    size++;
  }

  // add the item to the back
  public void addLast(Item item) {
    if (item == null) {
      throw new IllegalArgumentException("Null Item");
    }
    Node oldLast = last;
    last = new Node();
    last.item = item;
    last.next = null;
    last.previous = oldLast;
    if (isEmpty()) {
      first = last;
    } else {
      oldLast.next = last;
    }
    size++;
  }

  // remove and return the item from the front
  public Item removeFirst() {
    if (isEmpty()) {
      throw new NoSuchElementException("Empty Deque");
    }
    Item item = first.item;
    first = first.next;
    size--;
    if (isEmpty()) {
      first = null;
      last = null;
    } else {
      first.previous = null;
    }
    return item;
  }

  // remove and return the item from the back
  public Item removeLast() {
    if (isEmpty()) {
      throw new NoSuchElementException("Empty Deque");
    }
    Item item = last.item;
    Node previousLast = last.previous;
    last.previous = null;
    size--;
    if (isEmpty()) {
      first = null;
      last = null;
    } else {
      previousLast.next = null;
      last = previousLast;
    }
    return item;
  }

  @Override
  public Iterator<Item> iterator() {
    return new ListIterator();
    // TODO Auto-generated method stub
    // throw new UnsupportedOperationException("Unimplemented method 'iterator'");
  }

  // unit testing (required)
  public static void main(String[] args) {
    // Deque deque = new Deque();
    // // deque.addFirst(new Item("1"));
    // // deque.addLast(new Item("2"));
    // // // StdOut.println(deque.first.item.value);
    // // // StdOut.println(deque.last.item.value);
    // // deque.removeFirst();
    // // deque.addFirst(new Item("3"));
    // // deque.removeLast();
    // // deque.addLast(new Item("4"));

    // deque.addFirst("1");
    // // deque.addFirst("2");
    // // deque.addFirst("3");
    // // deque.addFirst("4");

    // // StdOut.println(deque.first.item.toString());
    // StdOut.println("Size : " + deque.size());
    // deque.removeLast();
    // // StdOut.println(deque.last.item.toString());
    // StdOut.println("Size : " + deque.size());
    // // deque.removeLast();
    // // StdOut.println(deque.last.item.toString());
    // // StdOut.println("Size : " + deque.size());
  }

  private class Node {
    Item item;
    Node next;
    Node previous;
  }

  private class ListIterator implements Iterator<Item> {

    private Node current = first;

    @Override
    public boolean hasNext() {
      return current != null;
      // TODO Auto-generated method stub
      // throw new UnsupportedOperationException("Unimplemented method 'hasNext'");
    }

    @Override
    public Item next() {
      if (!hasNext()) {
        throw new NoSuchElementException("No next item present");
      }
      Item item = current.item;
      current = current.next;
      return item;
      // TODO Auto-generated method stub
      // throw new UnsupportedOperationException("Unimplemented method 'next'");
    }

    @Override
    public void remove() {
      throw new UnsupportedOperationException("Remove method not supported");
    }
  }
}