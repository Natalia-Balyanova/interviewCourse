package src.balyanova.lesson2;

public interface MyList<E>  {

    boolean add(E element);

    void removeFirst();

    void remove(int index);

    void removeLast();

    boolean contains(E element);

    int size();

    boolean isEmpty();

    void display();

    E get(int index);

    void set(int index, E element);

//    class Node<E> {
//        E value;
//        Node<E> next;
//
//        public Node(E value, Node<E> next) {
//            this.value = value;
//            this.next = next;
//        }
//    }
}