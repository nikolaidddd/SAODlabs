
import java.util.List;
import java.util.Scanner;

public class Specialsort {

    int[] a; //будущий массив
    int max_key = 0; // Размер максимального ключа
    Scanner sc = new Scanner(System.in);
    int num;

    class List {
        int key = -1;
        List next = null, tail = null;
    } List[] ls;
    List head = null; // Ссылка на будущий массив структур


    void show(int[] b) {
        
        for (int i=0; i<b.length; i++)
            System.out.print(b[i] + " ");
       
    }



//СОЗДАНИЕ КОПИИ ИСХОДНОГО МАССИВА
    int[] copy_of_a() {

        int[] b = new int[num];
        for (int i=0; i<num; i++)
            b[i] = a[i];
        return b;
    }

    //СОЗДАНИЕ МАССИВА
    void Creator(int n){

        System.out.println("Введите кол-во элементов массива от 0 до 1000");
        num = sc.nextInt();

        if (a != null)  // ЕСЛИ МАССИВ НЕПУСТОЙ (ТО ЕСТЬ ВЫБРАНА КОМАНДА ОБНОВЛЕНИЯ МАССИВА)
            a= null;
        a = new int[num];
        if (n == 1) {
            for (int i = 0; i < num; i++)
                a[i] = i;
            max_key = num;
        }
        else if (n == 2) {
            max_key = num - num / 3;

            for (int i = 0; i < max_key; i++)
                a[i] = i;
            // ЗАПОЛНЯЕМ ОСТАВШИЕСЯ ЯЧЕЙКИ РАНДОМНЫМИ КЛЮЧАМИ 1..k
            for (int i = max_key; i < num; i++)
                a[i] = (int) (Math.random()*32000%max_key);
        }
        int temp, r;
        // ПЕРЕМЕШИВАЕМ МАССИВ
        for (int i = 0; i < num; i++) {
            r = (int)(Math.random()*32000 % num);
            temp = a[i];
            a[i] = a[r];
            a[r] = temp;
        }
        System.out.println("Массив создан");
        show(a);
    }

    // КАРМАННАЯ СОРТИРОВКА
    void sorting_pocket() {
        Creator(1);
        int[] b = copy_of_a(); // СОЗДАЕМ КОПИЮ ИСХОДНОГО МАССИВА
        int temp, n = -1;
        System.out.println("\nИсопользовать второй массив? 1 - да 0 - нет");
        n = sc.nextInt();
        
        if (n == 0) { // БЕЗ ИСПОЛЬЗОВАНИЯ ВТОРОГО МАССИВА
            for (int i = 0; i < num; i++) {
                while (b[i] != i) {
                    temp = b[b[i]];
                    b[b[i]] = b[i];
                    b[i] = temp;
                }
            }
        }
        else {
            int[] rezmas = new int[num];
            for (int i = 0; i < num; i++)
                rezmas[b[i]] = b[i];
            b=null;
            b = rezmas;
        }
        show(b);
        b=null; //после выполнения сортировки удаляем копию исходного массива (он больше не нужен)
    }

    // ОЧИСТКА СПИСКА
    void del_list(List pTemp)
    {
        if (pTemp.next != null)
            del_list(pTemp.next);
        pTemp=null;
    }

// ПЕРЕВОД ИЗ СПИСКА В МАССИВ
    int[] list_to_array(List head) {
        List pCurrent = head;
        int[] c = new int[num];
        for (int i = 0; i < num; i++) {
            c[i] = pCurrent . key;
            pCurrent = pCurrent . next;
        }
        return c;
    }

// ФУНКЦИЯ ОБЪЕДИНЕНИЯ МАССИВА С ДОПОЛНИТЕЛЬНЫМИ СПИСКАМИ В ОДИН СПИСОК
    List combining(List[] ls) {
        List head = new List();
        head.key = ls[0].key;
        head.next = ls[0].next;
        head.tail = ls[0].tail;
        List pCurrent = head;
        for (int i = 0; i < max_key - 1; i++) {
            if (ls[i].tail != null)
                pCurrent = ls[i].tail;
            pCurrent.next = new List();
            pCurrent = pCurrent . next;
            pCurrent.key =ls[i + 1].key;
            pCurrent.next = ls[i + 1].next;
            pCurrent.tail = ls[i + 1].tail;
        }
        return head;
    }

    // ОБОБЩЕННАЯ КАРМАННАЯ СОРТИРОВКА С ПОВТОРЯЮЩИМИСЯ КЛЮЧАМИ И ДОПОЛНИТЕЛЬНЫМИ СПИСКАМИ
    void sorting_pocket2() {
        Creator(2);

        int[] b = copy_of_a(); //создаем копию исходного массива

        ls = new List[max_key];
        for (int z = 0; z < ls.length; z++) {
            ls[z] = new List();
        }

        for (int i = 0; i < num; i++) { // СОЗДАЕМ МАССИВ СПИСКОВ
            if (ls[b[i]].key == -1) // ЯЧЕЙКА ПУСТАЯ
                ls[b[i]].key = b[i];
            else if (ls[b[i]].next == null) { // В ЯЧЕЙКЕ ОДИН ЭЛЕМЕНТ
                ls[b[i]].next = new List();
                ls[b[i]].tail = ls[b[i]].next;
                ls[b[i]].next.key = b[i];
            }
            else if (ls[b[i]].tail.next == null) { // В ЯЧЕЙКЕ 2 И БОЛЕЕ ЭЛЕМЕНТА
                ls[b[i]].tail.next = new List();
                ls[b[i]].tail.next.key = b[i];
                ls[b[i]].tail = ls[b[i]].tail.next;
            }
        }
        head = combining(ls); // ОБЪЕДИНЕНИЕ ВСЕХ СПИСКОВ ДОБАВЛЕНИЕМ В КОНЕЦ ОДНОГО СПИСКА НАЧАЛА ДРУГОГО
        b = list_to_array(head); // ПЕРЕПИСЫВАЕМ РЕЗУЛЬТАТ ОБРАТНО В МАССИВ
        head = null;
        System.out.println("\n Обобщенная карманная сортировка:");
        show(b);
        b=null; //после выполнения сортировки удаляем копию исходного массива (он больше не нужен)
        ls = null;
    }

    // ПОРАЗРЯДНАЯ СОРТИРОВКА
    void sorting_radix() {
        Creator(2);
        int[] b = copy_of_a(); // СОЗДАЕМ КОПИЮ ИСХОДНОГО МАССИВА
        int k = 0, temp = max_key;
        while (temp != 0) { // ПОСЧИТАЕМ КОЛ-ВО РАЗРЯДОВ
            temp = temp / 10;
            k++;
        }
        System.out.println("Число разрядов"+k);
        // СОРТИРУЕМ
        int x, max;
        for (int i = 1; i <= k ; i++) { //ВНЕШНИЙ ЦИКЛ АЛГОРИТМА ПОВТОРЯЕТСЯ K РАЗ (РАЗРЯДНОСТЬ КЛЮЧА).

            ls = new List[10]; // ДЕСЯТИЭЛЕМЕНТНЫЙ МАССИВ И ССЫЛОЧНЫЙ ТИП ДЛЯ ОРГАНИЗАЦИИ СПИСКОВ
            for (int z = 0; z < ls.length; z++) {
                ls[z] = new List();
            }

            for (int j = 0; j < num; j++) {
                x = b[j];
                for (int z = 1; z < i; z++)
                    x = x / 10; // ВЫДЕЛЯЕМ НУЖНЫЙ
                x = x % 10;     // НАМ РАЗРЯД

                if (ls[x].key == -1) // ЯЧЕЙКА ПУСТАЯ
                    ls[x].key = b[j];
                else if (ls[x].next == null) { // В ЯЧЕЙКЕ ОДИН ЭЛЕМЕНТ
                    ls[x].next = new List();
                    ls[x].tail = ls[x].next;
                    ls[x].next . key = b[j];
                }
                else if (ls[x].tail.next == null) { // В ЯЧЕЙКЕ 2 И БОЛЕЕ ЭЛЕМЕНТА
                    ls[x].tail . next = new List();
                    ls[x].tail . next . key = b[j];
                    ls[x].tail = ls[x].tail . next;
                }
            }
            max = max_key;
            max_key = 10; // ЧТОБЫ ПРИМЕНИТЬ ФУНКЦИЮ ОТ ОБОБЩЕННОЙ КАРМАННОЙ СОРТИРОВКИ
            head = combining(ls);
            max_key = max;
            b = list_to_array(head);
            del_list(head);
            head = null;
            System.out.println("\nСортировка по разряду"+i);
            show(b);
            ls = null;
        }

        b=null; //после выполнения сортировки удаляем копию исходного массива (он больше не нужен)
    }



    public void menu(){
        Scanner sc = new Scanner(System.in);
        while(true) {
            System.out.println("\nВведите номер комманды:\n" +
                    "1. Простейшая карманная сортировка \n" +
                    "2. Обобщенная карманная сортировка\n" +
                    "3. Поразрядная сортировка \n");
            int num = sc.nextInt();
            if (num == 1) {
                sorting_pocket();
            }
            else if(num == 2){
                sorting_pocket2();
            }
            else if(num == 3){
                sorting_radix();
            }
            else {System.out.println("Ошибка ввода");}
        }
    }

    public static void main(String[] args) {
        Specialsort list = new Specialsort();
        list.menu();
    }
}