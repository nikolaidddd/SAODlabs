package com.company;

import java.util.Scanner;

public class avltree2 {

    Tp pRoot;
    Tp pParent;  // pRoot - указатель на корень дерева, pParent - указатель на родительскую вершину, глобальные переменные
    boolean founded = false;
    Scanner sc = new Scanner(System.in);

    class Tp {
        int value; //строка-значение
        Tp left; //ссылочная переменная-указатель на левый элемент
        Tp right; //ссылочная переменная-указатель на правый элемент

    }


    void Find(Tp pCurrent, int value) {
        if (!founded)
            if (pCurrent != null) {
                if (pCurrent.value == value) {
                    founded = true;
                    pParent = pCurrent;
                }
                else {
                    Find (pCurrent.left, value); // поиск в левом поддереве
                    Find (pCurrent.right, value); // поиск в  правых поддереве
                }
            }
    }

    void FindDialog(int value) {


        founded = false;
        Find(pRoot, value);
        if (founded){
            System.out.println("Элемент найден.");
            founded = false;
        }
        else
            System.out.println("Элемент не найден.");
    }


//ДОБАВЛЕНИЕ ВЕРШИНЫ
    Tp AddTp(Tp pTemp, int value) {
        pTemp = new Tp();
        pTemp.left = pTemp.right = null; //у новой вершины еще нет левого и правого потомков
        pTemp.value = value;
        return pTemp; //возвращаем адрес созданного корня
    }

    //ДОБАВЛЕНИЕ ВЕРШИНЫ (ДИАЛОГОВАЯ ФУНКЦИЯ)
    void AddNode(int value, int parentvalue) {

        if (pRoot != null) { //если дерево непустое
            founded = false;
            Find(pRoot, parentvalue); //ищем вершину-родитель для нового элемента
            if (founded) {
                founded = false;
                if (pParent.left == null || pParent.right == null) { }//ПРОВЕРКА ЧИСЛА ПОТОМКОВ У НАЙДЕННОЙ РОДИТЕЛЬСКОЙ ВЕРШИНЫ

                else { System.out.println("Родитель уже имеет двух потомков."); }

                if (pParent.left == null && pParent.right == null) {
                    System.out.println("Вершина не имеет потомков. 1- добавить левый потомок. 2- добавить правый потомок.");
                    int n = sc.nextInt();

                    while ((n != 1) && (n != 2)) {
                        System.out.println("Неверный ввод. Повторите.");
                    }
                    if (n == 1) {
                        pParent.left = AddTp(pParent.left, value);
                        System.out.println("Левый потомок добавлен.");
                    }
                    if (n == 2) {
                        pParent.right = AddTp(pParent.right, value);
                        System.out.println("Правый потомок добавлен.");
                    }
                } else if (pParent.left == null) {
                    pParent.left = AddTp(pParent.left, value);
                    System.out.println("Левый потомок добавлен.");
                } else if (pParent.right == null) {
                    pParent.right = AddTp(pParent.right, value);
                    System.out.println("Правый потомок добавлен.");
                }

            }
            else {System.out.println("Вершина-родитель не найдена");}
        }
        else { //если дерево пустое, то создать корень дерева
            pRoot = new Tp();
            System.out.println("Введите значение вершины-корня.");
            pRoot.left = pRoot.right = null;
            pRoot.value = value;
            System.out.println("Корень создан.");;
        }
    }

    public Tp AddNodes(Tp pCurrent, int aN) {
        Tp pTemp = new Tp();
        int Nl, Nr;
        if (aN == 0) {
            return null;
        } else {
            Nl = aN / 2;
            Nr = aN - Nl - 1;
            pTemp = new Tp();
            pTemp.value = (int) (Math.random() * 100);
            pTemp.left = AddNodes(pTemp.left, Nl);
            pTemp.right = AddNodes(pTemp.right, Nr);
            pCurrent = pTemp;
            return pTemp;
        }
    }

    void delTp(Tp pCurrent) {
        if (pCurrent != null) {
            delTp(pCurrent.left); // сначала удалять все элементы с левого конца
            delTp(pCurrent.right); // потом удалять все элементы с правого конца
            pCurrent = null; // в конце удалить сам элемент-корень
            System.out.println("Поддрево удалено.");;
        }
    };




    public void Forward(Tp pCurrent, int level) { //ПОСТРОЧНЫЙ ВЫВОД В ПРЯМОМ НАПРАВЛЕНИИ
        if (pCurrent != null) {
            String str = ""; //обработка корневой вершины pCurrent
            for (int i = 0; i < level; i++) {
                str += "     ";
            }
            System.out.println(str + pCurrent.value);

            Forward(pCurrent.left, level + 1); //обработка  всех левых поддеревьев
            Forward(pCurrent.right, level + 1); //обработка  всех правых поддеревьев
        }

    }

    public void Symmetric(Tp pCurrent, int level) {
        if (pCurrent != null) {
            Symmetric(pCurrent.left, level + 1); //обработка  всех левых поддеревьев

            String str = ""; //обработка корневой вершины pCurrent
            for (int i = 0; i < level; i++) {
                str += "     ";
            }
            System.out.println(str + pCurrent.value);
            Symmetric(pCurrent.right, level + 1); //обработка  всех правых поддеревьев
        }
    }

    public void BackSymmetric(Tp pCurrent, int level) {
        if (pCurrent != null) {
            BackSymmetric(pCurrent.right, level + 1); //обработка  всех правых поддеревьев

            String str = ""; //обработка корневой вершины pCurrent
            for (int i = 0; i < level; i++)
                str += "     ";
            System.out.println(str + pCurrent.value);

            BackSymmetric(pCurrent.left, level + 1); //обработка  всех левых поддеревьев
        }
    }

    public void menu() {

        while (true) {
            System.out.println("\nВведите номер комманды:\n" +
                    "1. Создать древо\n2. Обход в прямом порядке\n" +
                    "3. Обход в симметричном порядке\n" + "4. Обход в обратно-симметричном порядке\n" +
                    "5. Добавить потомка к заданной вершине\n"+
                    "6. Поиск верщины\n"+
                    "7. Удаление древа\n");
            int num = sc.nextInt();
            if (num == 1) {
                System.out.println("Введите число вершин N: ");
                int inp = sc.nextInt();
                pRoot = AddNodes(pRoot, inp);
            } else if (num == 2) {
                Forward(pRoot, 0);
            } else if (num == 3) {
                Symmetric(pRoot, 0);
            } else if (num == 4) {
                BackSymmetric(pRoot, 0);
            } else if (num == 5) {
                System.out.println("Введите значение, которое нужно присвоить вершине.");
                int i = sc.nextInt();
                System.out.println("Введите значение вершины-родителя для нового элемента.");
                int i2 = sc.nextInt();
                AddNode(i,i2);
            } else if (num == 6) {
                System.out.println("Введите элемент, который хотите найти");
                int i3 = sc.nextInt();
                FindDialog(i3);
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
        avltree2 Tree = new avltree2();
        Tree.menu();

    }
}

