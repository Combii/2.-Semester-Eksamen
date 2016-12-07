package BusinessLogic.Account.List;

import java.util.Iterator;


/**
 * Created by ${Boris} Grunwald} on 03/12/2016.
 */
public class MyLinkedList<E> implements Iterable<E> {

    private MyListNode<E> front;
    private int size = 0;




    public MyLinkedList() {

        front = null;

    }

    public void add(E data) {

        if (front == null) {

            front = new MyListNode<>(data);

        } else {


            MyListNode curr = front;

            while (curr.next != null) {

                curr = curr.next;

            }

            curr.next = new MyListNode<E>(data);
        }

        size++;


    }


    public void add(E data, int index) {

        if (front == null && index != 0) {
            throw new IndexOutOfBoundsException();
        }

        MyListNode curr = nodeAt(index - 1);

        curr.next = new MyListNode<E>(data, curr.next);

        size++;

    }

    public void remove(int index) {

        if (front == null) {
            throw new IndexOutOfBoundsException("Cannot remove from empty list");
        }

        if (index == 0) {
            front = front.next;
        } else {
            MyListNode<E> curr = nodeAt(index - 1);
            removeNode(curr);
        }

    }

    public E get(int index) {

        MyListNode<E> curr = nodeAt(index);

        return curr.data;

    }

    public int size() {
        return size;
    }

    public void clear() {

        front = null;

    }

    private MyListNode<E> nodeAt(int index) {

        if (index > size) {
            throw new IndexOutOfBoundsException("Index > size");
        }

        MyListNode curr = front;

        for (int i = 0; i < index; i++) {

            curr = curr.next;

        }

        return curr;


    }

    private void removeNode(MyListNode<E> toRemove) {

        if (toRemove.next == null) {
            throw new IndexOutOfBoundsException("Index > size");
            //If it is the last node in the list.
        } else if (toRemove.next.next == null) {
            toRemove.next = null;
            //If it is neither last or front node.
        } else {
            toRemove.next = toRemove.next.next;
        }

        size--;

    }


    public boolean isEmpty() {

        if (front == null) {
            return true;
        }

        return false;

    }


    @Override
    public String toString() {

        if (front == null) {

            return "[]";

        } else {

            String toReturn = "[" + front.data;

            MyListNode curr = front.next;

            while (curr != null) {
                toReturn += ", " + curr.data;
                curr = curr.next;
            }

            return toReturn + "]";

        }

    }

//___________________________ITERATOR________________________//


    public Iterator<E> iterator() {
        return new It();
    }

    /**
     *
     *
     *    prev                  current
     *     |                       |
     *     |                       |
     *     \/                      \/
     *
     *                          front
     *    _____      _____      _____      _____      _____      _____      _____
     *   |     |    |     |    |     |    |     |    |     |    |     |    |     |
     *   |_____| -> |_____| -> |_____| -> |_____| -> |_____| -> |_____| -> |_____|
     *
     *
     *
     *
     */


    private class It implements Iterator<E> {

        MyListNode<E> current;
        MyListNode<E> prev;

        public It() {
            current = front;
            prev = new MyListNode<E>(null, new MyListNode<E>(null, front));
        }

        public boolean hasNext() {

            return current != null;
        }

        public void remove() {

            MyLinkedList.this.removeNode(prev);

        }

        public E next() {
            E temp = current.data;
            current = current.next;
            prev = prev.next;
            return temp;
        }
    }
}

