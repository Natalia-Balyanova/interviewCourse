package src.balyanova.lesson2;

public class LinkedListImpl<E> implements MyList<E> {
    private Node<E> first;
    private Node<E> last;
    private int size;

    public LinkedListImpl() {
        this.size = 0;
        this.first = null;
        this.last = null;
    }

    @Override
    public boolean add(E element) {
        Node<E> node = new Node<>(element);
        if (size == 0) {
            first = node;
        } else {
            last.setNext(node);
            node.setPrev(last);
        }
        last = node;
        size++;
        return true;
    }

    @Override
    public void removeFirst() {
        Node<E> temp = first.getNext();
        temp.setPrev(null);
        first = temp;
        size--;
    }

    @Override
    public void remove(int index) {
        if (index > size - 1 || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Element with index " + index + " not found");
        }

        if (index == 0) {
            removeFirst();
        } else if (index == size - 1) {
            removeLast();
        } else {
            Node<E> temp = findNode(index);
            Node<E> previous = temp.getPrev();
            previous.setNext(temp.getNext());
            temp.getNext().setPrev(previous);
            size--;
        }
    }

    @Override
    public void removeLast() {
        Node<E> temp = last.getPrev();
        temp.setNext(null);
        last = temp;
        size--;
    }

    private Node<E> findNode(int index) {
        if (index < 0 || index > size -1) {
            throw new ArrayIndexOutOfBoundsException("Element with index " + index + " not found");
        }
        if (index == 0) {
            return first;
        } else if (index == size - 1) {
            return last;
        } else if (index < size / 2) {
            Node<E> temp = first;
            for (int i = 0; i < index; i++) {
                temp = temp.getNext();
            }
            return temp;
        } else {
            Node<E> temp = last;
            for (int i = size - 1; i > index; i--) {
                temp = temp.getPrev();
            }
            return temp;
        }
    }

    @Override
    public boolean contains(E element) {
        if (first.getElement().equals(element) || last.getElement().equals(element)) { //получаем первый или последний эл-т, для быстроты
            return true;
        }
        Node<E> temp = first;
        for (int i = 0; i < size - 1; i++) {
            temp = temp.getNext();
            if (temp.getElement().equals(element)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void display() {
        System.out.println(this);
    }

    @Override
    public E get(int index) {
        return findNode(index).getElement();
    }

    @Override
    public void set(int index, E element) {
        findNode(index).setElement(element);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[LinkedListImpl{" + first.getElement());
        Node<E> temp = first;
        for (int i = 0; i < size - 1; i++) {
            temp = temp.getNext();
            sb.append(", ").append(temp.getElement());
        }
        return sb.append("]").toString();
    }
}