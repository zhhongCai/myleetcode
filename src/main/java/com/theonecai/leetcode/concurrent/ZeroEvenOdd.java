package com.theonecai.leetcode.concurrent;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntConsumer;

/**
 * @Author: theonecai
 * @Date: Create in 2019/12/27 08:57
 * @Description:
 */
class ZeroEvenOdd {
    private int n;
    private volatile int num;
    private AtomicInteger count;

    public ZeroEvenOdd(int n) {
        this.n = n;
        this.num = 1;
        count = new AtomicInteger(0);
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public synchronized void zero(IntConsumer printNumber) throws InterruptedException {
        while (num <= n) {
            while (count.get() != 0) {
                if (num > n) {
                    break;
                }
                this.wait();
            }
            if (num > n) {
                break;
            }
            printNumber.accept(0);
            int inc = 1;
            if (num % 2 ==0) {
                inc = 2;
            }
            count.compareAndSet(0, inc);
            this.notifyAll();
        }
    }

    public synchronized void even(IntConsumer printNumber) throws InterruptedException {
        while (num < n) {
            while (count.get() != 2) {
                if (num > n) {
                    break;
                }
                this.wait();
            }
            printNumber.accept(num);
            num++;
            count.compareAndSet(2, 0);
            this.notifyAll();
        }
    }

    public synchronized void odd(IntConsumer printNumber) throws InterruptedException {
        while (num <= n) {
            while (count.get() != 1) {
                if (num > n) {
                    break;
                }
                this.wait();
            }
            if (num > n) {
                break;
            }
            printNumber.accept(num);
            num++;
            count.compareAndSet(1, 0);
            this.notifyAll();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            final int n = i;
            ZeroEvenOdd zeroEvenOdd = new ZeroEvenOdd(n);
            Thread t = new Thread(() -> {
                try {
                    zeroEvenOdd.zero(System.out::print);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            Thread t2 = new Thread(() -> {
                try {
                    zeroEvenOdd.even(System.out::print);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            Thread t3 = new Thread(() -> {
                try {
                    zeroEvenOdd.odd(System.out::print);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });


            t.start();
            t2.start();
            t3.start();

            t.join();
            t2.join();
            t3.join();
            System.out.println();
        }
    }
}
