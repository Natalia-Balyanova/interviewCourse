package src.balyanova.lesson3;

import java.util.Objects;

@SuppressWarnings("InfiniteLoopStatement")//иначе IDE пишет предупреждение о 'while' statement cannot complete without throwing an exception
public class PingPong {
    private final Object monitor = new Object();
    private String current = "Ping";

    public static void main(String[] args) {
        PingPong pingPong = new PingPong();
        //while (true) {//в задании не сказано как долго программа пишет Ping - Pong
        for (int i = 0; i < 20; i++) {

            new Thread(pingPong::ping).start();

            new Thread(pingPong::pong).start();
        }
    }

    public void ping() {
        synchronized (monitor) {
            try {
                while (true) {
                    while (!Objects.equals(current, "Ping")) {
                    monitor.wait();//освобождаем монитор и переводим поток в состояние ожидания
                }
                System.out.println("Ping");
                current = "Pong";
                monitor.notify();//продолжаем работу потока
            }
        } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void pong() {
        synchronized (monitor) {
            try {
                while (true) {
                    while (!Objects.equals(current, "Pong")) {
                        monitor.wait();
                    }
                    System.out.println("Pong");
                    current = "Ping";
                    monitor.notify();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
