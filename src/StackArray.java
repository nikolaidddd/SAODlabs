package com.company;

import java.util.Scanner;

public class StackArray {

    class Stack {
        int value;
        Stack next;

    }
    Stack sp;
    Stack sp_second;

    boolean isEmpty(){
        return (sp == null);
    }

    boolean isSecondEmpty(){
        return(sp == null);
    }

    void show(Stack sp) {
        if (!isEmpty()) {
            Stack current;
            current = sp;
            do {
                System.out.print(current.value + " ");
                current = current.next;
            } while (current != null);
        }
        else {
            System.out.println("Стек пуст");
        }
    }

    public Stack push(Stack sp, int value) {
        Stack item  = new Stack();
        item.value = value;
        item.next = sp;
        sp = item;
        return sp;
    }


    void pop(){
        if (!isEmpty()) {
            int value = sp.value;
            Stack current;
            current = sp;
            sp = sp.next;
            current=null;
            System.out.println("Элемент удален");
        }
        else {
            System.out.println("Стек пуст.");
        }
    }

    void to2stack() {
        Stack current = sp;
        sp = sp.next;
        current.next = sp_second;
        sp_second = current;
    }

    void from2stack() {
        Stack current = sp_second;
        sp_second = sp_second.next;
        current.next = sp;
        sp = current;
    }

    public void menu(){
        Scanner sc = new Scanner(System.in);
        while(true) {
            System.out.println("\nВведите номер комманды:\n" +
                    "1. Вывести состояние главного стека\n2. Добавить элемент в главный стек\n" +
                    "3. Удалить элемент из главного стека\n4. Вывести состояние вспомогательного стека");
            int num = sc.nextInt();
            if (num == 1) {
                show(sp);
            }
            else if(num == 2){
                System.out.println("Уточните. \n1. создание нового элемента \n" +
                        " 2. выбор его с вершины вспомогательного стека");
                int num2 = sc.nextInt();
                if (num2 == 1){
                    System.out.println("Введите число: ");
                    int inp = sc.nextInt();
                    sp = push(sp, inp);
                }
                else if (num2 == 2){
                    if (isSecondEmpty()){
                        System.out.println("Дополнительный стек пуст");
                    }
                    else {
                        from2stack();
                    }
                }
            }
            else if(num == 3){
                System.out.println("Уточните. \n1. Удалить с освобождением памяти \n" +
                        "2. Включить его в вершину вспомогательного стека удаленных элементов");
                int num3 = sc.nextInt();
                if (num3 == 1){
                    pop();
                }
                else if (num3 == 2){
                    if (isSecondEmpty()){
                        System.out.println("Вспомогательный стек пуст.");
                    }
                    else {
                        to2stack();
                    }
                }
            }
            else if(num == 4){
                show(sp_second);
            }
            else {System.out.println("Ошибка ввода");}
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        StackArray stack = new StackArray();
        stack.menu();
    }

}