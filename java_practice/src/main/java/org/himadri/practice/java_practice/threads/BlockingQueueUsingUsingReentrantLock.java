//package org.himadri.practice.java_practice.threads;
//
//import java.util.concurrent.locks.Condition;
//import java.util.concurrent.locks.Lock;
//import java.util.concurrent.locks.ReentrantLock;
//
//
////https://stackoverflow.com/questions/20110013/implement-your-own-blocking-queue-in-java
//
//
//public class BlockingQueueUsingUsingReentrantLock {
//	Condition isFullCondition;
//	Condition isEmptyCondition;
//	Lock lock;
//	int limit;
//	
//	public BlockingQueueUsingUsingReentrantLock() {
//	    this(Integer.MAX_VALUE);
//	}
//
//	public BlockingQueueUsingUsingReentrantLock(int limit) {
//	    this.limit = limit;
//	    lock = new ReentrantLock();
//	    isFullCondition = lock.newCondition();
//	    isEmptyCondition = lock.newCondition();
//	}
//
//	public <T> void put (T t) {
//	    lock.lock();
//	    try {
//	       while (isFull()) {
//	            try {
//	                isFullCondition.await();
//	            } catch (InterruptedException ex) {}
//	        }
//	        q.add(t);
//	        isEmptyCondition.signalAll();
//	    } finally {
//	        lock.unlock();
//	    }
//	 }
//
//	public <T> T get() {
//	    T t = null;
//	    lock.lock();
//	    try {
//	        while (isEmpty()) {
//	            try {
//	                isEmptyCondition.await();
//	            } catch (InterruptedException ex) {}
//	        }
//	        t = q.poll();
//	        isFullCondition.signalAll();
//	    } finally { 
//	        lock.unlock();
//	    }
//	    return t;
//	}
//}
