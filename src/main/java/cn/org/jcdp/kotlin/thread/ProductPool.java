package cn.org.jcdp.kotlin.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Produce
 *
 * @author venson
 * @version 20180709
 */
public class ProductPool {

    private final static int MAX = 30;
    private volatile int count = 0;
    private volatile int sum=1;
    private Product[] pool = new Product[MAX];
    private Lock lock = new ReentrantLock();
    private Condition empty = lock.newCondition();
    private Condition notEmpty = lock.newCondition();

    public ProductPool() {
        init();
    }

    private void init(){
        Produce produce=new Produce();
        produce.setDaemon(true);
        produce.start();
        System.out.println("---------this is init----------");
    }


    private class Produce extends Thread {
        @Override
        public void run() {
            for (; ; ) {
                produce();
            }
        }

    }
    private void produce() {
        lock.lock();
        try {
            while (count >= MAX) {
                System.out.println("已达产量，停止生产。。。。");
                empty.await();
            }
            Product product = new Product();
            product.setId(sum);
            product.setName("name" + sum);
            sum++;
            pool[count++] = product;
            System.out.println("---------this is produce----------"+product);
            notEmpty.signal();
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }


    public Product get() {
        lock.lock();
        try {
            while (count == 0) {
                try {
                    System.out.println("缺货中，请等待商家补货。。。。");
                    notEmpty.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            count--;
            Product product = pool[count];
            System.out.println(Thread.currentThread().getName()+"---------this is get----------"+product);
            pool[count] = null;
            empty.signal();
            return product;
        }  finally {
            lock.unlock();
        }
    }

}
