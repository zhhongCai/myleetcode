package com.theonecai.leetcode.concurrent;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * leetcode 1115
 * @Author: theonecai
 * @Date: Create in 2019/12/26 16:39
 * @Description:
 */
public class FooBar {
    private int n;
    private AtomicInteger condition;
    private final Object obj = new Object();

    public FooBar(int n) {
        this.n = n;
        condition = new AtomicInteger(1);
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            if (condition.get() == 1) {
                // printFoo.run() outputs "foo". Do not change or remove this line.
                printFoo.run();
            } else {
               i--;
               synchronized (obj) {
                   obj.wait();
               }
               continue;
            }
            condition.compareAndSet(1, 0);

        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            if (condition.get() == 0) {
                // printBar.run() outputs "bar". Do not change or remove this line.
                printBar.run();
            } else {
                i--;
                continue;
            }
            condition.compareAndSet(0, 1);
            synchronized (obj) {
                obj.notify();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        FooBar fooBar = new FooBar(100);
        Thread t = new Thread(() -> {
             try {
                 fooBar.foo(() -> {
                     System.out.print("foo");
                 });
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
         });

        Thread t2 = new Thread(() -> {
            try {
                fooBar.bar(() -> {
                    System.out.println("bar");
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t.start();
        t2.start();

        t.join();
        t2.join();
    }

}
