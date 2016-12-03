package BusinessLogic.Account.List;

/**
 * Created by ${Boris} Grunwald} on 03/12/2016.
 */
public class MyListNode<E> {

    public E data;
    public MyListNode next;

    public MyListNode(E data, MyListNode<E> next) {

        this.data = data;
        this.next = next;

    } 

    public MyListNode(E data) {

        this(data, null);

    }

    public String toString() {

        return this.data + " next: " + this.next;

    }
    
}
