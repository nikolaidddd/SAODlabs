package com.company;

import java.util.Scanner;

public class avltree {

    int N;

    class tree {
        int value; //строка-значение
        tree left; //ссылочная переменная-указатель на левый элемент
        tree right; //ссылочная переменная-указатель на правый элемент

    }tree pRoot;

    public tree AddNodes(tree pCurrent, int aN) {
        tree pTemp;
        int Nl, Nr;
        if (aN == 0) {
            return null;
        }
        else {
            Nl = aN / 2;
            Nr = aN - Nl - 1;
            pTemp = new tree();
            pTemp.value = (int) (Math.random() * 100);
            pTemp.left = AddNodes(pTemp.left, Nl);
            pTemp.right = AddNodes(pTemp.right, Nr);
            pCurrent = pTemp;
            return pTemp;
        }
    }

    public void Forward(tree pCurrent, int level) { //ПОСТРОЧНЫЙ ВЫВОД В ПРЯМОМ НАПРАВЛЕНИИ
        if (pCurrent != null) {
            String str = ""; //обработка корневой вершины pCurrent
            for (int i = 0; i < level; i++) {
                str += "     ";
            }
            System.out.println(str + pCurrent.value) ;

            Forward (pCurrent.left, level + 1); //обработка  всех левых поддеревьев
            Forward (pCurrent.right, level + 1); //обработка  всех правых поддеревьев
        }

    }

    public void Symmetric (tree pCurrent, int level) {
        if (pCurrent != null) {
            Symmetric (pCurrent.left, level + 1); //обработка  всех левых поддеревьев

            String str = ""; //обработка корневой вершины pCurrent
            for (int i = 0; i < level; i++){
                str += "     ";
            }
            System.out.println( str + pCurrent.value ) ;
            Symmetric (pCurrent.right, level + 1); //обработка  всех правых поддеревьев
        }
    }

    public void BackSymmetric (tree pCurrent, int level) {
        if (pCurrent != null) {
            BackSymmetric (pCurrent.right, level + 1); //обработка  всех правых поддеревьев

            String str = ""; //обработка корневой вершины pCurrent
            for (int i = 0; i < level; i++)
                str += "     ";
            System.out.println( str + pCurrent.value) ;

            BackSymmetric (pCurrent.left, level + 1); //обработка  всех левых поддеревьев
        }
    }

    public void menu(){
        Scanner sc = new Scanner(System.in);
        while(true) {
            System.out.println("\nВведите номер комманды:\n" +
                    "1. Создать древо\n2. Обход в прямом порядке\n" +
                    "3. Обход в симметричном порядке\n"+"4. Обход в обратно-симметричном порядке\n");
            int num = sc.nextInt();
            if (num == 1) {
                System.out.println("Введите число вершин N: ");
                int inp = sc.nextInt();
                pRoot = AddNodes(pRoot, inp);
            }
            else if(num == 2) {
                Forward(pRoot, 0);
            }
            else if(num == 3) {
                Symmetric(pRoot, 0);
            }
            else if(num == 4) {
                BackSymmetric(pRoot, 0);
            }
            else {System.out.println("Ошибка ввода");}
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        avltree Tree = new avltree();
        Tree.menu();

    }

}
