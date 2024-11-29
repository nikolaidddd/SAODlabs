package com.company;

import java.util.Scanner;

public class List {

    private int[] list;
    private int maxSize;

    public List(int maxSize) {
        this.maxSize = maxSize;
        list = new int[maxSize+1];
    }

    public void insert(int elem) {
        if(isFull()){
            System.out.println("Список полон.");
        }
        else {
            for (int i = 1; i < list.length; i++) {
                if (list[i] > elem) {
                    for (int i2 = list.length - 2; i2 >= i; i2--) {
                        list[i2 + 1] = list[i2];
                        list[i2] = 0;
                    }
                    list[i] = elem;
                    break;
                } else if (i == list.length - 1) {
                    for (int i3 = 1; i3 < list.length; i3++) {
                        if (list[i3] == 0) {
                            list[i3] = elem;
                            break;
                        }
                    }
                }
            }
        }
    }

    public void remove(int inpdel) {
        if(isEmpty()){
            System.out.println("Список пуст.");
        }
        else {

            for(int i =inpdel; i<list.length-1;i++){
                list[i]=list[i+1];
                list[i+1]=0;
            }
        }
    }

    public int search(int a){
        int b = 0;
        for(int i=1;i<list.length;i++){
            if(list[i]==a){
                b = i;
            }
        }
        return b;
    }

    public boolean isFull() {
        return (list[list.length-1] != 0);
    }

    public boolean isEmpty() {
        return (list[1]==0);
    }


    public void menu(){
        Scanner sc = new Scanner(System.in);
        while(true) {
            System.out.println("\nВведите номер комманды:\n" +
                    "1. Добавление элемента \n2. Удаление элемента\n" +
                    "3. Вывод текущего состояния очереди на экран\n"+
                    "4. Найти ячейку с заданным содержимым");
            int num = sc.nextInt();
            if (num == 1) {
                Scanner sc2 = new Scanner(System.in);
                System.out.println("Введите элемент: ");
                int input = sc2.nextInt();
                insert(input);
            }
            else if(num == 2){
                Scanner sc3 = new Scanner(System.in);
                System.out.println("Введите номер ячейки для удаления: ");
                int inpdel = sc3.nextInt();
                remove(inpdel);

            }
            else if(num == 3){
                for(int i=1;i<list.length;i++){
                    System.out.println(list[i]);
                }

            }
            else if(num == 4){
                Scanner sc4 = new Scanner(System.in);
                System.out.println("Введите значение: ");
                int a = sc4.nextInt();
                if (search(a)==0){
                    System.out.println("Элемент не найден.");
                }
                else {
                    System.out.println("Номер ячейки со значением "+a+": - "+search(a));
                }
            }
            else {System.out.println("Ошибка ввода");}
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите размер списка: ");
        int inps = sc.nextInt();
        List list = new List(inps);
        list.menu();
    }

}