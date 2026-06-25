package Multithreading.ProducerConsumer;

//What's the difference between put() and offer()?
//
//        put()
//        queue.put(10);
//        Blocks if the queue is full.
//        Waits until space is available.
//        offer()
//        boolean success = queue.offer(10);
//        Doesn't block.
//        Returns false immediately if the queue is full.
//take() vs poll()
//        take()
//        int value = queue.take();
//        Blocks if the queue is empty.
//        poll()
//        Integer value = queue.poll();
//        Doesn't block.
//        Returns null if the queue is empty.
//
//        Timed version:
//
//        Integer value = queue.poll(5, TimeUnit.SECONDS);
import java.util.LinkedList;
import java.util.Queue;

class SharedQueue {

    private final Queue<Integer> queue = new LinkedList<>();
    private final int CAPACITY = 5;

    public synchronized void produce(int value) {

        while (queue.size() == CAPACITY) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        queue.offer(value);
        System.out.println("Produced : " + value);

        notifyAll();
    }

    public synchronized int consume() {

        while (queue.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        int value = queue.poll();

        System.out.println("Consumed : " + value);

        notifyAll();

        return value;
    }
}

public class ProducerConsumerUsingWait {

    public static void main(String[] args) {

        SharedQueue queue = new SharedQueue();

        Thread producer = new Thread(() -> {

            int value = 1;

            while (true) {

                queue.produce(value++);

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }

        });

        Thread consumer = new Thread(() -> {

            while (true) {

                queue.consume();

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }

        });

        producer.start();
        consumer.start();
    }
}