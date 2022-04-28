package src.balyanova.lesson3;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CounterTester {
    private int result; //0

    public static void main(String[] args) {
        CounterTester counter = new CounterTester();
        counter.threadSafeCounter(1,10,1,10);
    }

    private void increment(int value) {
        result += value;
        System.out.println("Increment result = " + result);
    }

    private void decrement(int value) {
        result -= value;
        System.out.println("Decrement result = " + result);
    }

    public void threadSafeCounter(int incrementValue, int quantityOfIncrement, int decrementValue, int quantityOfDecrement){
        final Lock locker = new ReentrantLock();
        new Thread(() -> {
            try {
                locker.lock();
                System.out.println("Method increment begin");
                for (int i = 0; i < quantityOfIncrement; i++) {
                    increment(incrementValue);
                }
            }finally {
                locker.unlock();
            }
        }).start();

        /*new Thread(() -> { //сначала написала такую реализацию (подглядела в интернете), но она не работает с большими числами
        result может быть и с отрицательным значением
            for (int i = 0; i < quantityOfIncrement; i++) {
                locker.lock();//получение блокировки
                increment(incrementValue);
                locker.unlock();//освобождение блокировки
            }
        }).start();
         */
        new Thread(() -> {
            try {
                locker.lock();
                System.out.println("Method decrement begin");
                for (int i = 0; i < quantityOfDecrement; i++) {
                    decrement(decrementValue);
                }
            }finally {
                locker.unlock();
            }
        }).start();
//        new Thread(() -> {
//            for (int i = 0; i < quantityOfDecrement; i++) {
//                locker.lock();
//                decrement(decrementValue);
//                locker.unlock();
//            }
//        }).start();
    }
}
