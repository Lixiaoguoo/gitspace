package com.gcy;

public class hello extends Thread{
    public hello(){
        System.out.println("Thread.currentThread().getname()="+Thread.currentThread().getName());
        System.out.println("This.getName="+this.getName());
    }
    public void run(){
        System.out.println("Thread.currentThread().getname()="+Thread.currentThread().getName());
        System.out.println("This.getName="+this.getName());
    }
    public static void main(String[] args){
        hello thread =new hello();
        Thread t1 =new Thread(thread);
        t1.setName("A");
        t1.start();
        System.out.println("==================");
        hello thread2 =new hello();
        Thread t2 =new Thread(thread2);
        t2.setName("B");
        t2.start();
    }
}
