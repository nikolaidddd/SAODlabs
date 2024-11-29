package com.company;

import java.util.Scanner;

public class DynamicListt {

    class DynamicList {
        String value; 
        DynamicList next; 
    }
    DynamicList Head, StackTop; 
    int num1 = 0;
    int num2 = 0;
    Scanner sc = new Scanner(System.in);

    void create(){
        Head = new DynamicList(); 
        Head.next = null;

        StackTop = new DynamicList();
        StackTop.next = null; 

    }


    
    boolean isListEmpty(){
        return (Head.next == null);
    }
    
    boolean isStackEmpty(){
        return (StackTop.next == null);
    }

    
    void showList() {

        if (!isListEmpty()) { 
            System.out.println("в списке " + num1 + " элементов");
            DynamicList Current; 
            Current = Head.next; 
            int i = 1;
            String linkon; 
            while (Current != null) {
                if ((Current.next) != null) {
                    linkon = (Current.next).value;
                }
                else {
                    linkon = "null";
                }
                System.out.println(i + ". " + Current.value + " ссылка next на: " + linkon);
                Current = Current.next;
                i++;

            }
        }
        else {
            System.out.println("Список пуст.");
        }
    }

    
    void showStack() {
        
        if (!isStackEmpty()) { 
            
            DynamicList Current; 
            Current = StackTop.next; 
            int i = 1;
            String linkon; 
            while (Current != null) {
                if ((Current.next) != null) {
                    linkon = (Current.next).value;
                }
                else {
                    linkon = "null";
                }
                System.out.println(i + ") " + Current.value + " ссылается на: " + linkon);
                Current = Current.next;
                i++;
            }
        }
        else {
            System.out.println("Стек пуст.");
        }
    }

    
    void push(){
        String value;
        DynamicList Current = null; 
        DynamicList Temp = new DynamicList(); 
        int choice = -1; 
        while ((choice != 0) && (choice != 1)){
            System.out.println("Куда добавить элемент? 0 - Перед заданным элементом 1 - После заданного элемента");
            choice = sc.nextInt();
            int i = -1; 
            if (choice == 0) { 
                if (!isListEmpty()) {
                    DynamicList  Pred; 
                    Pred = Head; 
                    while (i == -1){
                        Current = Head.next; 
                        System.out.println("Введите значение элемента, перед которым добавить");
                        value = sc.next();
                        while (Current != null) {
                            if (Current.value.equals(value)){
                                break;
                            }
                            Current = Current.next;
                            Pred = Pred.next;
                        }
                        if (Current != null) {
                            i++;
                        }
                        else {
                            System.out.println("Строка не найдена");
                        }
                    }
                    Temp.next = Current; 
                    Pred.next = Temp; 
                }
                else {
                    System.out.println("Добавить перед элементом нельзя, т.к. список пуст");
                    choice = -1;
                }
            }
            else if (choice == 1) { 
                if (!isListEmpty()){
                    while (i == -1) {
                        Current = Head.next; 
                        System.out.println("Введите значение элемента, после которого добавить");
                        value = sc.next();
                        while ((Current != null) && !(Current.value.equals(value))) {
                            Current = Current.next;
                        }
                        if (Current != null){
                            i++;
                        }
                        else { System.out.println("Строка не найдена"); }
                    }
                    Temp.next = Current.next; 
                    Current.next = Temp; 
                }
                else { 
                    Temp.next = null;
                    Head.next = Temp;
                }
            }
            else {
                System.out.println("Ошибка ввода");
                choice = -1;
            }
        }
        System.out.println("Введите значение добавляемого элемента");
        value = sc.next();
        Temp.value = value;
        num1++;
        System.out.println("Элемент добавлен");
    }

    
    void pop(){
        if (!isListEmpty()){
            String value;
            DynamicList  Current; 
            DynamicList  Pred; 
            Current = Head.next; 
            Pred = Head; 
            int choice = -1; 
            int i = -1; 
            while ((choice != 0) && (choice != 1)){
                System.out.println("Удалить элемент или добавить во вспомогательный стек? 0 - Удалить  1 - Добавить во вспомогательный стек");
                choice = sc.nextInt();
                showList();

                while (i == -1) {
                    System.out.println("Введите значение удаляемого элемента");
                    value = sc.next();
                    while (Current != null) {
                        if (Current.value.equals(value)){
                            break;
                        }
                        Current = Current.next;
                        Pred = Pred.next;
                    }
                    if (Current != null) { i++; }
                    else { System.out.println("Элемент не найден. Повторите"); }
                }
                if (choice == 0) {
                    Pred.next = Current.next; 
                    Current = null; 
                    num1--;
                    System.out.println("Элемент удален");
                }
                else if (choice == 1) {
                    Pred.next = Current.next; 
                    DynamicList Temp = Current; 

                    Temp.next = StackTop.next;
                    StackTop.next = Temp;
                    num1--; num2++;
                    System.out.println("Элемент удален и помещен в стек.");
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

    
    void find() {
        if (!isListEmpty()){ 
            String value;
            System.out.println("Введите значение элемента для поиска");
            value = sc.next();
            int i = 1;
            DynamicList  Current; 
            Current = Head.next; 
            while ((Current != null) && !(Current.value.equals(value))){
                Current = Current.next;
                i++;
            }
            if (Current != null) {
                System.out.println(" Строка " + value + " найдена под номером " + i );
            }
            else {
                System.out.println("Строка не найдена");
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
                    "4. Найти ячейку с заданным содержимым\n" +
                    "5. Вывод текущего состояния стека на экран\n");
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
            else if(num == 5){
                showStack();
            }
            else {System.out.println("Ошибка ввода");}
        }
    }

    public static void main(String[] args) {
        DynamicListt list = new DynamicListt();
        list.create();
        list.menu();
    }
}