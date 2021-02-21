package com.Multithreading;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class Main {

    public static void main(String[] args) throws Exception {
        BlockingQueue<String> blockingDeque = new PriorityBlockingQueue<>(); // специальный список, для потоков, не дает с ним ничего сделать, пока не будут введены данные

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

        new Thread(){
            @Override
            public void run() {
                blockingDeque.add("String"); // ложим строчку
            }
        }.start();

    }
}
