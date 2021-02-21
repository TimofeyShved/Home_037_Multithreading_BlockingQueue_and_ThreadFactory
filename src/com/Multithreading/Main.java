package com.Multithreading;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadFactory;

public class Main {

    public static void main(String[] args) throws Exception {
        BlockingQueue<String> blockingDeque = new PriorityBlockingQueue<>(); // специальный список, для потоков, не дает с ним ничего сделать, пока не будут введены данные

        //BlockingQueue
        new Thread(){
            @Override
            public void run() {
                try {
                    System.out.println(blockingDeque.take()); //достаем элемент
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        //BlockingQueue
        new Thread(){
            @Override
            public void run() {
                blockingDeque.add("String"); // ложим строчку
            }
        }.start();

        //ThreadFactory
        ThreadFactory threadFactory = new ThreadFactory() { // изиенить настройки потоеп
            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                thread.setPriority(Thread.MAX_PRIORITY);
                return thread;
            }
        };

        threadFactory.newThread(new MyThread()).start();
    }

    //ThreadFactory
    static class MyThread implements Runnable{
        @Override
        public void run() {
            System.out.println(1);
        }
    }
}
