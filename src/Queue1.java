package com.company;

import java.util.Scanner;

public class Queue {

    private int[] queue;
    private int maxSize; // максимальное количество элементов в очереди
    private int nElem;  // текущее количество элементов в очереди
    private int front;
    private int rear;

    public Queue(int maxSize) {
        this.maxSize = maxSize;
        queue = new int[maxSize];
        rear = -1;
        front = 0;
        nElem = 0;
    }

    public void insert(int elem) {
        if (rear == maxSize - 1) {
            rear = -1;
        }
        queue[++rear] = elem;
        nElem++;
    }

    public void remove() {

        queue[front++] = 0;
        if (front == maxSize) {
            front = 0;
        }
        nElem--;

    }

    public boolean isFull() {
        return (nElem == maxSize);
    }

    public boolean isEmpty() {
        return (nElem == 0);
    }


    public void menu(){
        Scanner sc = new Scanner(System.in);
        while(true) {
            System.out.println("\nВведите номер комманды:\n" +
                    "1. Добавление элемента в конец очереди\n2. Удаление элемента из начала очереди\n" +
                    "3. Вывод текущего состояния очереди на экран\n");
            int num = sc.nextInt();
            if (num == 1) {

                if (isFull()){
                    System.out.println("Очередь переполнена");
                }
                else {
                    System.out.println("Введите число: ");
                    int num1 = sc.nextInt();
                    insert(num1);
                }
            }
            else if(num == 2){
                if (isEmpty()) {
                    System.out.println("Очередь пуста");

                }
                else {
                    remove();
                    System.out.println("Элемент удален");
                }
            }
            else if(num == 3){
                for (int i = 0; i < queue.length; i++) {
                    System.out.print(queue[i] + " ");
                }
            }

            else {System.out.println("Ошибка ввода");}
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите число вершин N: ");
        int inp = sc.nextInt();
        Queue queue = new Queue(inps);
        queue.menu();
    }

}