package com.company;

public class WaitingList extends Passenger {
    private static WaitingList[] passengerWaiting = new WaitingList[10];
    private static int size;
    private static int front;
    private static int rear;

    public WaitingList(String firstName, String surName, double expenses){
        this.setFirstName(firstName);
        this.setSurName(surName);
        this.setExpenses(expenses);
    }

    public static void enQueue(WaitingList data){
        if(!isQueueFull()){
            passengerWaiting[rear] = data;
            rear = (rear + 1)%10;
            size++;
        }
    }

    public static int getSize() {
          return size;
    }

    public static boolean isQueueFull(){
        return getSize() == 10;
    }

    public static WaitingList deQueue(){
        WaitingList waitingPassenger = null;
        if (!isQueueEmpty()){
            waitingPassenger = passengerWaiting[front];
            front = (front + 1)%10;
            size--;
        }
        return waitingPassenger;
    }

    public static boolean isQueueEmpty(){
        return getSize() == 0;
    }

}
