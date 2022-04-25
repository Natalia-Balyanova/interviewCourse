package src.balyanova.lesson2;

import java.util.Arrays;

public class ArrayListImpl<E> implements MyList<E> {
    private Object[] array;
    private int size;
    private int capacity;

    public ArrayListImpl() {
        capacity = 10;
        size = 0;
        this.array = new Object[capacity];
    }

    public int getCapacity() {
        return capacity;
    }

    public int getSize() {
        return size;
    }

    @Override
    public boolean add(E element) {
        size++;
        if (size > capacity) {
            capacity *= 1.5;
            Object[] newArray = new Object[capacity];
            System.arraycopy(array, 0, newArray, 0, array.length);
            array = newArray;
        }
        array[size - 1] = element;
        return true;
    }

    @Override
    public void removeFirst() {
        if (isEmpty()) {
            throw new ArrayIndexOutOfBoundsException("List is empty");
        }
        remove(0);
    }

    @Override
    public void remove(int index) {
        if (index > size - 1 || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Element with index " + index + " NOT FOUND");
        }
        if (size - 1 - index >= 0) System.arraycopy(array, index + 1, array, index, size - 1 - index);
        array[size - 1] = null;
        size--;
    }

    @Override
    public void removeLast() {
        if (isEmpty()) {
            throw new ArrayIndexOutOfBoundsException("List is empty");
        }
        remove(size - 1);
    }

//    public void clear() {
//        for (int i = 0; i < size; i++) {
//            {array[i] = null;}
//            size = 0;
//        }
//    }

    @Override
    public boolean contains(E element) {
        if (isEmpty()) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (array[i].equals(element)) {
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
        if (index > size - 1 || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Element with index " + index + " not found");
        }
        return (E) array[index];
    }

    @Override
    public void set(int index, E element) {
        if (index > size - 1 || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Element with index " + index + " not found");
        }
        array[index] = element;
    }

    @Override
    public String toString() {
        return "ArrayListImpl{" +
                "array=" + Arrays.toString(array) +
                ", size=" + size +
                ", capacity=" + capacity +
                '}';
    }
}
