package org.himadri.practice.java_practice.threads;

import java.util.*;



public class GenericBlockingQueue<T> {

    private Queue<T> q = new LinkedList<T>();
    private int limit;

    public GenericBlockingQueue(int limit) {
        this.limit = limit;
    }

    public synchronized void put (T t) throws InterruptedException {
        while (isFull()) {
            wait();
        }
        boolean e = isEmpty();
        q.add(t);
        if (e)
            notifyAll();
    }


    public synchronized T get () throws InterruptedException {
        while (isEmpty()) {
            wait();
        }
        boolean f = isFull();
        T t = q.poll();
        if (f)
            notifyAll();
        return t;
    }

    private boolean isEmpty() {
        return q.size() == 0;
    }
    private boolean isFull() {
        return q.size() == limit;
    }
}