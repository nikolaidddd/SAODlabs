package com.company;

import java.util.Scanner;

public class Queue {

    public Queue() {
        First = new QueueItem(); //выделение памяти для заголовка с помощью указателя First
        First.next = null;// занесение в ссылочную часть заголовка пустого указателя null
        Last = First;// установка указателя конца очереди Last = pFirst
    }

    class QueueItem {  // описание Структуры "элемент очереди"
        char value; //строка-значение
        QueueItem next;
    }
    QueueItem First, Last;
    int nElem;


    void insert(char value) {
        QueueItem Current = new QueueItem(); //выделить память для нового элемента
        Current.value = value; //заполнить поля нового элемента
        Current.next = null; //в частности в связующую часть установить значение null
        Last.next=Current; //изменить связующую часть бывшего последнего элемента таким образом, чтобы она адресовала новый добавленный элемент
        Last = Current; //изменить значение указателя pLast так, чтобы он указывал новый последний элемент
        nElem++;
    }

//Удаление элемента из начала очереди (но после заголовка!)
     void remove(){
        if (!isEmpty()) { //проверить возможности удаления (в очереди есть элементы?)
            QueueItem Current; //ввод вспомогательной ссылочной переменной
            Current = First.next; //установка Current в адрес первого реального элемента (заголовка)
            First.next = Current.next; //изменить связующую часть заголовка так, чтобы она указывала на второй элемент очереди, который теперь должен стать первым
            if (isEmpty()) { //если после удаления в списке не остаётся реальных элементов, то необходимо изменить указатель pLast
                Last = First;
            }
            char value = Current.value;
            Current = null; //обработать удаленный элемент, например - освободить занимаемую им память
            nElem--;

        }
        else {
            System.out.println("Очередь пуста");

        }
    }
    void showQueue() {
        if (!isEmpty()) { //проверить возможности удаления (в очереди есть элементы?)
            QueueItem Current; //ввод вспомогательной ссылочной переменной
            Current = First.next; //установка Current в адрес первого реального элемента (заголовка)
            int  i = 1;
            while (Current != null) {
                System.out.println(Current.value);
                Current = Current.next;
                i++;
            }
        }
        else {
            System.out.println("Очередь пуста");
        }
    }


    public boolean isEmpty() {
        return (First.next == null);
    }

    public void Randomize(){
        int a = 1 + (int) (Math.random()*100);
        int b = 1 + (int) (Math.random()*3);
        if (a % 2 == 0) {
            for (int i=0; i<b; i++){
                char c = (char) (65+Math.random()*25);
                insert(c);
                System.out.println("Добавлен символ: "+c);
            }
            System.out.println("Добавлено символов: "+b);
        }
        else {
            if (isEmpty()){
                System.out.println("Очередь пустая.");
            }
            else {
                if (b > nElem){
                    b = nElem;
                }
                for (int i = 0; i < b; i++) {
                    remove();
                }
                System.out.println("Удалено символов: " + b);
            }

        }
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Queue queue = new Queue();
        while (true) {

            queue.Randomize();
            queue.showQueue();
            System.out.println("Продолжить?");
            int i = sc.nextInt();

        }
    }
}