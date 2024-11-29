package com.company;

import java.util.Scanner;

public class DynamicList2 {

    class Dynamic2List {
        String value;
        Dynamic2List right, left;
    }
    Dynamic2List Head;
    int num1 = 0;
    Scanner sc = new Scanner(System.in);


    void create() {
        Head = new Dynamic2List();
        Head.left = Head;
        Head.right = Head;
        Head.value = "HEAD";
    }


    boolean isListEmpty(){
        return (Head.right == Head);
    }


    void showList() {
        if (!isListEmpty()) {
            int choice = -1;
            while ((choice != 0) && (choice != 1)){
                System.out.println("В каком направлении вывести элементы списка? 0 - В прямом 1 - В обратном");
                choice = sc.nextInt();
                Dynamic2List Current;
                int i;
                String leftlink, rightlink;
                if (choice == 0) {
                    i = 1;
                    Current = Head.right;
                    while (Current != Head) {
                        rightlink = (Current.right).value;
                        leftlink = (Current.left).value;
                        System.out.println(i + ". " + Current.value + " левый сосед: " + leftlink  + " правый сосед: " + rightlink);
                        Current = Current.right;
                        i++;
                    }
                }
                else if (choice == 1) {
                    i = num1;
                    Current = Head.left;
                    while (Current != Head) {
                        rightlink = (Current.right).value;
                        leftlink = (Current.left).value;
                        System.out.println(i + ". " + Current.value + ", левый сосед: " + leftlink  + ", правый сосед: " + rightlink);
                        Current = Current.left;
                        i--;
                    }
                }
                else {
                    System.out.println("Ошибка ввода");
                    choice = -1;
                }
            }
        }
        else System.out.println("Список пуст");
    }


    void push(){
        String value;
        Dynamic2List Current;
        Dynamic2List Temp = new Dynamic2List();
        int choice = -1;
        while ((choice != 0) && (choice != 1)){
            System.out.println("Куда добавить элемент? 0 - Перед заданным элементом 1 - После заданного элемента");
            choice = sc.nextInt();
            int i = -1;
            if (choice == 0) {
                if (!isListEmpty()) {
                    while (i == -1){
                        Current = Head.right;
                        System.out.println("Введите значение элемента, перед которым добавить");
                        value = sc.next();
                        while ((Current != Head) && !(Current.value.equals(value)))
                            Current = Current.right;
                        if (Current != Head) {
                            i++;
                            Temp.right = Current;
                            Temp.left = Current.left;
                            (Current.left).right = Temp;
                            Current.left = Temp;
                        }
                        else System.out.println("Строка не найдена");
                    }
                }
                else {
                    System.out.println("Добавить перед элементом нельзя, т.к. список пуст");
                    choice = -1;
                }
            }
            else if (choice == 1) {
                if (!isListEmpty()){
                    while (i == -1) {
                        Current = Head.right;
                        System.out.println("Введите значение элемента, после которого добавить");
                        value = sc.next();
                        while ((Current != Head) && !(Current.value.equals(value)))
                            Current = Current.right;
                        if (Current != Head) {
                            i++;
                            Temp.right = Current.right;
                            Temp.left = Current;
                            (Current.right).left = Temp;
                            Current.right = Temp;
                        }
                        else System.out.println("Строка не найдена");
                    }
                }
                else {

                    Temp.right = Head; Temp.left = Head;
                    Head.right = Temp; Head.left = Temp;
                }
            }
            else {
                System.out.println("Ошибка ввода");
                choice = -1; }
        }
        System.out.println("Введите значение добавляемого элемента");
        value = sc.next();
        Temp.value = value; num1++;
        System.out.println("Элемент добавлен");
    }


    void pop(){
        if (!isListEmpty()){
            String value;
            Dynamic2List Current;
            Current = Head.right;
            int i = -1;
            while (i == -1) {
                System.out.println("Введите значение удаляемого элемента");
                value = sc.next();
                while ((Current != Head) && !(Current.value.equals(value)))
                    Current = Current.right;
                if (Current != Head) {
                    i++;
                    (Current.left).right = Current.right;
                    (Current.right).left = Current.left;
                    Current = null;
                    num1--;
                    System.out.println("Элемент удален");
                }
                else System.out.println("Строка не найдена");
            }
        }
        else System.out.println("Список пуст");
    }


    void find() {
        if (!isListEmpty()){

            String value;
            System.out.println("Введите значение элемента для поиска");
            value = sc.next();
            int choice = -1, i;
            Dynamic2List Current;
            while ((choice != 0) && (choice != 1)){
                System.out.println("В каком направлении произвести поиск элемента?\n 0 - В прямом\n 1 - В обратном");
                choice = sc.nextInt();
                if (choice == 0) {
                    i = 1;
                    Current = Head.right;
                    while ((Current != Head) && !(Current.value.equals(value))){
                        Current = Current.right;
                        i++;
                    }
                    if (Current != Head) System.out.println(" Строка " + value + " найдена под номером " + i );
                    else System.out.println("Строка не найдена");
                }
                else if (choice == 1){
                    i = num1;
                    Current = Head.left;
                    while ((Current != Head) && !(Current.value.equals(value))){
                        Current = Current.left;
                        i--;
                    }
                    if (Current != Head) System.out.println(" Строка " + value + " найдена под номером " + i );
                    else System.out.println("Строка не найдена");
                }
                else {
                    System.out.println("Ошибка ввода");
                    choice = -1;
                }
            }
        }
        else {
            System.out.println("Список пуст");
        }
    }



    public void menu(){
        Scanner sc = new Scanner(System.in);
        while(true) {
            System.out.println("\nВведите номер комманды:\n" +
                    "1. Добавление элемента \n2. Удаление элемента\n" +
                    "3. Вывод текущего состояния списка на экран\n"+
                    "4. Найти ячейку с заданным содержимым\n");
            int num = sc.nextInt();
            if (num == 1) {
                push();
            }
            else if(num == 2){
                pop();
            }
            else if(num == 3){
                showList();
            }
            else if(num == 4){
                find();
            }
            else {System.out.println("Ошибка ввода");}
        }
    }

    public static void main(String[] args) {
        DynamicList2 list = new DynamicList2();
        list.create();
        list.menu();
    }
}