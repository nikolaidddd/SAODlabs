package com.company;

import java.util.Scanner;

public class avltree3 {

    class Tp {
    int value; 
    int counter; 
    Tp Left; 
    Tp Right; 
}

Tp pRoot=null;
Tp pParent=null;
Scanner sc = new Scanner(System.in);


void Symmetric (Tp pCurrent) {
    if (pCurrent != null) {
        Symmetric (pCurrent.Left); 
        System.out.println(pCurrent.value + "  (" +   pCurrent.counter+")");
        Symmetric (pCurrent.Right); 
    }
}


void BackSymmetric (Tp pCurrent, int level) {
    if (pCurrent != null) {
        BackSymmetric (pCurrent.Right, level + 1); 
        String str = "";
        for (int i = 0; i < level; i++)
            str += "     ";
        System.out.println(" " + str + pCurrent.value);
        BackSymmetric (pCurrent.Left, level + 1); 
    }
}


Tp Find(int value) {
    Tp pCurrent = pRoot; 
    pParent = null;
    boolean Stop = false;
    while ((pCurrent != null) && (!Stop)) {
        if (value < pCurrent.value) {
            pParent = pCurrent;
            pCurrent = pCurrent.Left;
        }
        else if (value > pCurrent.value) {
            pParent = pCurrent;
            pCurrent = pCurrent.Right;
        }
        else
            Stop = true;
    }
    return pCurrent;
}


void FindDialog() {
    System.out.println("Введите элемент, который хотите найти");
    int value = sc.nextInt();
    Tp pTemp = Find(value);
    if (pTemp != null)
        System.out.println("Элемент найден.");
    else
        System.out.println("Элемент не найден.");
}


void show(int n){
    if (pRoot != null) {
        if (n==1)  {
            System.out.println("Построчный вывод дерева по возрастанию ключей");
           Symmetric(pRoot);

        }
        if (n==2) {
            System.out.println("Построчный вывод в обратно-сим. порядке");
            BackSymmetric(pRoot, 0);
        }
    }
    else {
        System.out.println("Дерево пустое");
    }
}


Tp AddRecursive(Tp pCurrent, int value) {
    if (pCurrent == null) 
    {
        pCurrent = new Tp();
        pCurrent.value = value;
        pCurrent.Left = pCurrent.Right = null;
        pCurrent.counter = 1;
    }
    else { 
        if (value < pCurrent.value)
            pCurrent.Left = AddRecursive(pCurrent.Left, value);
        else if (value > pCurrent.value)
            pCurrent.Right = AddRecursive(pCurrent.Right, value);
        else
            pCurrent.counter++; 
    }
    return pCurrent; 
}


void AddNonRecursive(int value) {
    if (pRoot == null) {
        pRoot = new Tp();
        pRoot.Left = pRoot.Right = null;
        pRoot.value = value;
        pRoot.counter = 1;
    }
    else {
        Tp pParent = null, pCurrent = pRoot; 
        while (pCurrent != null ) {
            pParent = pCurrent; 
            if ( value < pCurrent.value)
                pCurrent = pCurrent.Left;
            else if ( value > pCurrent.value)
                pCurrent = pCurrent.Right;
            else {
                pCurrent = null; 
                pCurrent.counter++;
            }
        }
        if ( value < pParent.value) {
            pCurrent = new Tp();
            pCurrent.Left = pCurrent.Right = null;
            pCurrent.counter = 1;
            pCurrent.value = value;
            pParent.Left = pCurrent;
        }
        else if ( value > pParent.value) {
            pCurrent = new Tp();
            pCurrent.Left = pCurrent.Right = null;
            pCurrent.counter = 1;
            pCurrent.value = value;
            pParent.Right = pCurrent;
        }
    }
}


void AddNode() {

    System.out.println("Введите значение, которое нужно присвоить вершине.");
    int value = sc.nextInt();
    System.out.println("Какой способ? 1 - рекурсивный 2 - нерекурсивный");
    int n = sc.nextInt();
    while ((n != 1) && (n != 2)) {
        System.out.println("Неверный ввод");
    }
    if (n == 1) {
        pRoot = AddRecursive(pRoot, value);
    }
    if (n == 2) {
        AddNonRecursive(value);
    }
    System.out.println("Вершина добавлена");
}



void TreeCreator() {

    int num = -1;
    while (num <= 0) {
        System.out.println("Введите кол-во вершин");
        num = sc.nextInt();
        if (num<=0) System.out.println("Повторите ввод");
        else {
            for (int i=0; i<num; i++)
                pRoot = AddRecursive(pRoot,(int) (Math.random()*99));
            System.out.println("Дерево создано");
        }
    }
}


void delTp(Tp pCurrent) {
    if (pCurrent != null) {
        delTp(pCurrent.Left); 
        delTp(pCurrent.Right); 
        pCurrent = null;
        System.out.println("Поддрево удалено");
    }
}


Tp Changer(Tp p) {
    if (p.Right != null)
        p = Changer(p.Right);
    return p;
}

Tp DeleteNode(Tp pCurrent, int value){
    if (pCurrent == null)
        System.out.println("Вершина не найдена");
    else if (value < pCurrent.value) 
        pCurrent.Left = DeleteNode(pCurrent.Left, value);
    else if (value > pCurrent.value) 
        pCurrent.Right = DeleteNode(pCurrent.Right, value);
    else { 
        if (pCurrent.counter > 1) 
            pCurrent.counter--;
        else { 
            Tp pTemp = pCurrent;
            if (pTemp.Right == null) 
                pCurrent = pTemp.Left;
            else if (pTemp.Left == null) 
                pCurrent = pTemp.Right;
            else if ((pTemp.Right == null) && (pTemp.Left == null)) 
                pCurrent = null; 
            else {
                Tp p = Changer(pCurrent.Left); 
                p = Find(p.value); 
                pParent.Right = p.Left;
                pCurrent.value = p.value; 

                pCurrent.Left = DeleteNode(pCurrent.Left, p.value);
                pTemp = p;

                return pCurrent;
                
            }
            pTemp = null;
        }
    }
    return pCurrent;
}

void DelNode(){
    show(2);
    System.out.println("Введите значение удаляемой вершины");
    int value = sc.nextInt();
    pRoot = DeleteNode(pRoot, value);
    System.out.println("Вершина удалена");
}

    public void menu() {

        while(true) {
            System.out.println("\nВведите номер комманды:\n" +
                    "1. Создать случайное дерево\n2. Вывод в порядке возрастания ключей\n" +
                    "3. Удаление вершины \n" + "4. Обход в обратно-симметричном порядке\n" +
                    "5. Добавить вершину с заданным значением\n"+
                    "6. Поиск вершины\n"+
                    "7. Удаление древа\n");
            int num = sc.nextInt();
            if (num == 1) {
                System.out.println("Введите число вершин N: ");
                TreeCreator();
            } else if (num == 2) {
                show(1);
            } else if (num == 3) {
                DelNode();
            } else if (num == 4) {
                show(2);
            } else if (num == 5) {
                AddNode();

            } else if (num == 6) {
                System.out.println("Введите элемент, который хотите найти");
                FindDialog();
            } else if (num == 7) {
                delTp(pRoot);
                pRoot = null;
            }
            else {
                System.out.println("Ошибка ввода");
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        avltree3 Tree = new avltree3();
        Tree.menu();

    }
}

