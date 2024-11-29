package com.company;

import java.util.Scanner;

public class DynamicList3 {

    int num1;
    Scanner sc = new Scanner(System.in);

    class SubList {
        String value;
        SubList next;
    };


    class MainList {
        MainList nextMain;
        SubList firstSub;
    }
    MainList HeadMain;



    void create() {
        HeadMain = new MainList();
        HeadMain.nextMain = null;
        HeadMain.firstSub = null;
        num1 = 0;
    }


    boolean isMainListEmpty(){
        return (HeadMain.nextMain == null);
    }


    boolean isSubListEmpty(MainList CurrentMain){
        return (CurrentMain.firstSub.next == null);
    }


    void showList() {
        if (!isMainListEmpty()) {
            MainList MainCurrent = HeadMain.nextMain;
            while (MainCurrent != null) {
                System.out.println("Содержимое элемента " + MainCurrent.firstSub.value + " основного списка:");
                int i = 1;
                if (!isSubListEmpty(MainCurrent)) {
                    SubList SubCurrent = MainCurrent.firstSub.next;
                    while (SubCurrent != null) {
                        System.out.println("   " + i + ") " + SubCurrent.value);
                        SubCurrent = SubCurrent.next;
                        i++;
                    }
                }
                System.out.println("   " + "<Пусто>");
                MainCurrent = MainCurrent.nextMain;
            }
        }
        else {
            System.out.println("Главный список пуст");
        }
    }


    void pushInMain() {
        String value;
        MainList Current = null;
        MainList Temp = new MainList();
        Temp.firstSub = new SubList();
        int choice = -1;
        while ((choice != 0) && (choice != 1)){
            System.out.println("Куда добавить элемент? 0 - Перед заданным элементом 1 - После заданного элемента");
            choice = sc.nextInt();
            int i = -1;
            if (choice == 0) {
                if (!isMainListEmpty()){
                    MainList Pred = HeadMain;
                    while (i == -1){
                        Current = HeadMain.nextMain;
                        System.out.println("Введите значение элемента, перед которым добавить");
                        value = sc.next();
                        while (Current != null) {
                            if (Current.firstSub.value.equals(value)) break;
                            Current = Current.nextMain;
                            Pred = Pred.nextMain;
                        }
                        if (Current != null) i++;
                        else { System.out.println("Строка не найдена"); }
                    }
                    Temp.nextMain = Current;
                    Temp.firstSub.next = null;
                    Pred.nextMain = Temp;
                }
                else {
                    System.out.println("Добавить перед элементом нельзя, т.к. список пуст");
                    choice = -1;
                }
            }
            else if (choice == 1) {
                if (!isMainListEmpty()){
                    while (i == -1) {
                        Current = HeadMain.nextMain;
                        System.out.println("Введите значение элемента, после которого добавить");
                        value = sc.next();
                        while ((Current != null) && !(Current.firstSub.value.equals(value)))
                            Current = Current.nextMain;
                        if (Current != null) i++;
                        else { System.out.println("Строка не найдена"); }
                    }

                    Temp.nextMain = Current.nextMain;
                    Temp.firstSub.next = null;
                    Current.nextMain = Temp;
                }
                else {
                    Temp.nextMain = null;
                    Temp.firstSub.next = null;
                    HeadMain.nextMain = Temp;
                }
            }
            else {
                System.out.println("Ошибка ввода");
                choice = -1;
            }

        }
        System.out.println(" Введите название элемента главного списка: ");

        value = sc.next();
        Temp.firstSub.value = value; num1++;

        System.out.println("Элемент добавлен в основной список");
    }


    void pushInSub(){
        if (!isMainListEmpty()){
            String value;
            MainList CurrentMain = null;
            int i = -1;

            while (i == -1) {
                CurrentMain = HeadMain.nextMain;
                System.out.println("Введите название заголовка связанного списка, в который добавить элемент:");
                value = sc.next();
                while ((CurrentMain != null) && !(CurrentMain.firstSub.value.equals(value)))
                    CurrentMain = CurrentMain.nextMain;
                if (CurrentMain != null) i++;
                else { System.out.println("Строка не найдена"); }
            }
            SubList Head = CurrentMain.firstSub;
            SubList CurrentSub = null;
            SubList Temp = new SubList();
            int choice = -1;
            while ((choice != 0) && (choice != 1)){
                System.out.println("Куда добавить элемент? 0 - Перед заданным элементом 1 - После заданного элемента");
                choice = sc.nextInt();
                if (choice == 0) {
                    if (!isSubListEmpty(CurrentMain)) {
                        SubList Pred = Head;
                        i = -1;
                        while (i == -1){
                            CurrentSub = Head.next;
                            System.out.println("Введите значение элемента, перед которым добавить");
                            value = sc.next();
                            while (CurrentSub != null) {
                                if (CurrentSub.value.equals(value)) break;
                                CurrentSub = CurrentSub.next;
                                Pred = Pred.next;
                            }
                            if (CurrentSub != null) i++;
                            else { System.out.println("Строка не найдена"); }
                        }
                        Temp.next = CurrentSub;
                        Pred.next = Temp;
                    }
                    else {
                        System.out.println("Добавить перед элементом нельзя, т.к. список пуст");
                        choice = -1;
                    }
                }
                else if (choice == 1) {
                    if (!isSubListEmpty(CurrentMain)) {
                        i = -1;
                        while (i == -1) {
                            CurrentSub = Head.next;
                            System.out.println("Введите значение элемента, после которого добавить");
                            value = sc.next();
                            while ((CurrentSub != null) && !(CurrentSub.value.equals(value)))
                                CurrentSub = CurrentSub.next;
                            if (CurrentSub != null) i++;
                            else { System.out.println("Строка не найдена"); }
                        }
                        Temp.next = CurrentSub.next;
                        CurrentSub.next = Temp;
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
            System.out.println("Элемент добавлен");
        }
        else {
            System.out.println("Связанный список не создан");
        }
    }


    void popFromMain(){
        if (!isMainListEmpty()){
            String value;
            MainList MainCurrent = null;
            MainList MainPred = null;
            SubList SubCurrent;
            int i = -1;

            while (i == -1) {
                MainCurrent = HeadMain.nextMain;
                MainPred = HeadMain;
                System.out.println("Введите название заголовка связанного списка, чтобы удалить его: ");
                value = sc.next();
                while (MainCurrent != null) {
                    if (MainCurrent.firstSub.value.equals(value)) break;
                    MainCurrent = MainCurrent.nextMain;
                    MainPred = MainPred.nextMain;
                }
                if (MainCurrent != null) { i++; System.out.println("Строка удалена"); }
                else { System.out.println("Строка не найдена"); }
            }

            while (MainCurrent.firstSub.next != null) {
                SubCurrent = MainCurrent.firstSub;
                MainCurrent.firstSub = MainCurrent.firstSub.next;
                SubCurrent = null;
            }

            MainCurrent.firstSub = null;
            System.out.println("Вспомогательный список удален");

            MainPred.nextMain = MainCurrent.nextMain;
            MainCurrent = null;
            System.out.println("Элемент главного списка удален");
        }
        else {
            System.out.println("Список пуст");
        }
    }


    void popFromSub(){
        if (!isMainListEmpty()){
            showList();
            String value;
            MainList CurrentMain = null;
            int i = -1;

            while (i == -1) {
                CurrentMain = HeadMain.nextMain;
                System.out.println("Введите значение заголовка связного списка, в котором хотите удалить элемент: ");
                value = sc.next();
                while ((CurrentMain != null) && (CurrentMain.firstSub.value != value))
                    CurrentMain = CurrentMain.nextMain;
                if (CurrentMain != null) i++;
                else { System.out.println("Строка не найдена"); }
            }
            if (!isSubListEmpty(CurrentMain)) {
                SubList Head = CurrentMain.firstSub;
                SubList Current = null;
                SubList Pred = null;
                i = -1;
                while (i == -1) {
                    Current = Head.next;
                    Pred = Head;
                    System.out.println("Введите значение удаляемого элемента");
                    value = sc.next();
                    while (Current != null) {
                        if (Current.value.equals(value)) break;
                        Current = Current.next;
                        Pred = Pred.next;
                    }
                    if (Current != null) { i++; }
                    else { System.out.println("Строка не найдена"); }
                }
                Pred.next = Current.next;
                System.out.println("Элемент удален");
                Current = null;
            }
            else {
                System.out.println("Список пуст");
            }
        }
        else {
            System.out.println("Список пуст");
        }
    }


    void find() {
        if (!isMainListEmpty()){
            showList();
            String value;
            System.out.println("Введите значение элемента для поиска");
            value = sc.next();
            int j = 0;
            MainList CurrentMain = HeadMain.nextMain;
            while (CurrentMain != null) {

                if (!isSubListEmpty(CurrentMain)) {
                    int i = 1;
                    SubList SubCurrent = CurrentMain.firstSub.next;
                    while ((SubCurrent != null) && !(SubCurrent.value.equals(value))) {
                        SubCurrent = SubCurrent.next;
                        i++;
                    }
                    if (SubCurrent != null) { j++;}
                    else System.out.println("Строка не найдена");
                }
                else {
                    System.out.println("Связанный список пуст");
                }
                CurrentMain = CurrentMain.nextMain;
            }
            if (j==0) System.out.println("Строка не найдена");
            else System.out.println("Найдено " + j + " элементов");
        }
        else {
            System.out.println("Список пуст");
        }
    }



    public void menu(){
        Scanner sc = new Scanner(System.in);
        while(true) {
            System.out.println("\nВведите номер комманды:\n" +
                    "1. Добавление элемента в основной список \n" +
                    "2. Добавление элемента в связанный список\n" +
                    "3. Вывод текущего состояния списка на экран\n"+
                    "4. Найти ячейку с заданным содержимым\n"+
                    "5. Удалить элемент из главного списка\n"+
                    "6. Удалить элемент из связанного списка\n");
            int num = sc.nextInt();
            if (num == 1) {
                pushInMain();
            }
            else if(num == 2){
                pushInSub();
            }
            else if(num == 3){
                showList();
            }
            else if(num == 4){
                find();
            }
            else if(num == 5){
                popFromMain();
            }
            else if(num == 6){
                popFromSub();
            }
            else {System.out.println("Ошибка ввода");}
        }
    }

    public static void main(String[] args) {
        DynamicList3 list = new DynamicList3();
        list.create();
        list.menu();
    }
}