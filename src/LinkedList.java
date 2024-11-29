package com.company;

import java.util.Scanner;

public class LinkedList {

    int arr_size = 11;
    int num;
    Scanner sc = new Scanner(System.in);
    List[] list = new List[arr_size];


    class List {
        String value;
        int next;
    }

    void create(){
        for(int i = 0; i<arr_size;i++){
            list[i] = new List();
        }
        list[0].value = "HEAD"; //заголовок
        list[0].next = 0;
        for (int i = 1; i < arr_size; i++){
            list[i].next = -1; //все ячейки после заголовка отмечаются как свободные
        }
        num = 0;

    }

    boolean isEmpty() {
        return (list[0].next == 0);

    }

    boolean isFull(){
        return (num == arr_size-1);
    }

    void showList() {

        if (!isEmpty()) {

            int i=1, current = list[0].next;
            while (current != 0) {
                System.out.println(i + ". " + list[current].value + "     ячейка: " + current);
                current = list[current].next;
                i++;
            }
        }
        else {
            System.out.println("Список пуст.");
        }
    }

    void push(){
        if (!isFull()) {
            String value;
            if (!isEmpty()){
                int choice = -1, i = -1, j = 0, s = -1;
                while ((choice != 0) && (choice != 1)){
                    System.out.println("Куда добавить элемент? 0 - Перед заданным элементом 1 - После заданного элемента");
                    choice = sc.nextInt();
                    if (choice == 0) {
                        i = -1; s = -1;
                        while ((i == -1) || (s == -1)){
                            System.out.println("Введите значение элемента");
                            value = sc.next();
                            i = find(value, 1);
                            s = find(value, 0);
                            if ((i == -1) && (s == -1)) {
                                System.out.println("Строка не найдена");
                            }
                        }
                        j = list[0].next;
                        for (j=1; j<arr_size; j++) if(list[j].next == -1) break;
                        list[j].next = i;
                        list[s].next = j;
                    }
                    else if (choice == 1) {

                        while (i == -1) {
                            System.out.println("Введите значение элемента");
                            value = sc.next();
                            i = find(value, 1);
                            if (i == -1) {
                                System.out.println("Строка не найдена");
                            }
                        }
                        for (j=1; j<arr_size; j++) if(list[j].next == -1) break;
                        list[j].next = list[i].next;
                        list[i].next = j;
                    }
                    else {
                        choice = -1;
                        System.out.println("Ошибка ввода");
                    }
                }
                System.out.println("Введите значение добавляемого элемента");
                value = sc.next();
                list[j].value = value;
                System.out.println("Элемент добавлен");
            }
            else {
                System.out.println("Введите значение добавляемого элемента");
                value = sc.next();
                list[1].value = value;
                list[1].next = 0;
                list[0].next = 1;
                System.out.println("Элемент добавлен");
            }
            num++;
        }
        else {
            System.out.println("Список переполнен");
        }
    }

    void pop(){
        if (!isEmpty()){
            showList();
            String value;
            int i=-1, s=-1;

            while ((i == -1) || (s == -1)){
                System.out.println("Введите значение удаляемого элемента");
                value = sc.next();
                i = find(value, 1);
                s = find(value, 0);
                if ((i == -1) || (s == -1)) {
                    System.out.println("Элемент не найден. Повторите");
                }
            }
            list[s].next = list[i].next;
            list[i].next = -1;
            num--;
            System.out.println("Элемент удален");
        }
        else {
            System.out.println("Список пуст");
        }
    }

    void find() {
        if (!isEmpty()){
            showList();
            String value;
            System.out.println("Введите значение элемента для поиска");
            value = sc.next();
            int i=1, current = list[0].next;
            while ((current != 0) && !((list[current].value).equals(value))) {
                current = list[current].next;
                i++;
            }
            if (current != 0) {
                System.out.println(" Строка " + value + " найдена под номером " + i );
            } else {
                System.out.println("Строка не найдена");
            }
        }
        else {
            System.out.println("Список пуст");
        }
    }

    int find(String value, int mode) {
        int current = list[0].next;
        if (mode == 1)
            while ((current != 0) && !((list[current].value).equals(value))){
                current = list[current].next;
            }
        else if (mode == 0)
        {
            int pred = 0;
            while (current != 0) {
                if ((list[current].value).equals(value)) break;
                current = list[current].next;
                pred = list[pred].next;
            }
            return pred;
        }
        if (current != 0) {return current;}
        else {return -1;}
    }


    public void menu(){
        Scanner sc = new Scanner(System.in);
        while(true) {
            System.out.println("\nВведите номер комманды:\n" +
                    "1. Добавление элемента \n2. Удаление элемента\n" +
                    "3. Вывод текущего состояния списка на экран\n"+
                    "4. Найти ячейку с заданным содержимым");
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
        LinkedList list = new LinkedList();
        list.create();
        list.menu();
    }
}