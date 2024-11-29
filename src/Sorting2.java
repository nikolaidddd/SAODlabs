
import java.util.Scanner;

public class Sorting2 {


    int compares = 0, changes = 0;

    int num;
    private int[] a;
    Scanner sc = new Scanner(System.in);



    public Sorting2(){
        System.out.println("Введите размер списка: ");
        num = sc.nextInt();
        a = new int[num];
        for (int i=0; i<num; i++)
            a[i] = (int)(Math.random()*100);
        System.out.println("Массив создан");
    }

    void show(int[] b) {

        for (int i=0; i<num; i++){
            System.out.print(b[i]+" ");
        }
    }

    int[] copy_of_a() {

        int[] b = new int[num];
        for (int i=0; i<num; i++)
            b[i] = a[i];
        return b;
    }



    void sorting_bubble() {
        compares = 0; changes = 0;
        int[] b = copy_of_a();
        int temp;

        for (int i=0; i<num; i++)
            for (int j = num-1; j > i; j--) {
                compares++;
                if (b[j-1] > b[j]) {
                    temp = b[j-1];
                    b[j-1] = b[j];
                    b[j] = temp;
                    changes++;
                }
            }
        System.out.println("Количество сравнений: " + compares + ", Количество пересылок: "+ changes);
        show(b);
        b=null;
    }


    void sorting_inserts() {
        compares = 0; changes = 0;
        int[] b = copy_of_a();
        int temp=0, i=0, j=0;

        for (i = 1; i < num; i++) {
            temp = b[i];
            j = i-1;
            compares++;
            while (j > -1 && temp < b[j]) {
                b[j+1] = b[j];
                if (j != i-1)
                    changes++;
                j--;
                compares++;
            }
            if (b[j + 1] == temp)
                changes--;
            b[j+1] = temp;
            changes++;
        }
        System.out.println("Количество сравнений: " + compares + ", Количество пересылок: "+ changes);
        show(b);
        b = null;
    }


    void sorting_choice() {
        compares = 0; changes = 0;
        int[] b = copy_of_a();
        int min = 0, i = 0, j = 0, k = 0;

        for (i=0; i < num; i++) {
            k = i; min = b[i];
            for (j = i+1; j < num; j++) {
                if (b[j] < min) {
                    k = j; min = b[j];
                }
                compares++;
            }
            if (b[i] == min)
                changes--;
            b[k] = b[i];
            b[i] = min;
            changes++;
        }
        System.out.println("Количество сравнений: " + compares + ", Количество пересылок: "+ changes);
        show(b);
        b = null;
    }

    void sorting_Shell() {
        int[] arr = copy_of_a();
        int temp = 0, j = 0, k = 0, l = 0;
        int steps = (int)((Math.log(num)/Math.log(2) - 1));
        int add4 = steps;
        int[] arrofSteps = new int[steps];
        for (int i = 0; add4 > 0; i++, add4--) { arrofSteps[i] = 2 * add4 - 1; }
        for (int m = 0; m < steps; m++) {
            k = arrofSteps[m];
            for (int i = k; i < num; i++) {
                j = i - k;
                compares++;
                while (j >= 0 && arr[i] < arr[j]) {
                    compares++;
                    j = j - k;
                }
                if (j + k != i) {
                    temp = arr[i];
                    l = i - k;
                    changes++;
                    while (l >= j + k) {
                        arr[l + k] = arr[l];
                        l = l - k;
                        changes++;
                    }
                    arr[j + k] = temp;
                    changes++;
                }
            }
        }

        changes = changes / 3;

        show(arr);
        System.out.println("\nКоличество сравнений: " + compares + ", Количество перестановок: " + changes + "\n");
        compares = 0; changes = 0; arr = null;
    }

    void sorting_quick(int left,int right,int[] array) {
        int i, j, mid, temp;
        i = left; j = right;

        if (array == a) {
            array = copy_of_a();
        }
        mid = array[(left + right) / 2];
        do {
            while (array[i] < mid) {
                i++;
                compares++;
            }
            while (array[j] > mid) {
                j--;
                compares++;
            }
            if (i <= j) {
                temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                i++;
                j--;
                changes+=3;
            }
        } while (i < j);
        if (left < j) { sorting_quick(left, j, array); }
        if (i < right){ sorting_quick(i, right, array); }
        if (left == 0 && right == (num - 1)) {
            changes = changes / 3;
            
            show(array);
            System.out.println("\nКоличество сравнений: " + compares + ", Количество перестановок: " + changes + "\n");
            compares = 0; changes = 0; array = null;
        }
    }

    void Sieve(int left, int right, int[] array) {
        int i = left, j = 2 * left, x = array[left];
        changes++;
        compares++;
        if ((j < right) && (array[j + 1] > array[j])) { j++; }
        while ((j <= right) && (array[j] > x)) {
            array[i] = array[j];
            i = j;
            j = 2 * j;
            compares++;
            changes++;
            if ((j < right) && (array[j + 1] > array[j])){ j++; }
            compares++;
        }
        array[i] = x;
        changes++;
    }

    void sorting_pyramid() {
        int[] array = copy_of_a();
        int left = (num / 2), right = num - 1;
        int temp;
        while (left > 0) {
            left--;
            Sieve(left, right, array);
        }
        while (right > 0) {
            changes+=3;
            temp = array[0];
            array[0] = array[right];
            array[right] = temp;
            right--;
            Sieve(left, right, array);
        }
        changes = changes / 3;
        show(array);
        System.out.println("\nКоличество сравнений: " + compares + ", Количество перестановок: " + changes + "\n");

        compares = 0; changes = 0; array=null;
    }




    public void menu(){
        Scanner sc = new Scanner(System.in);
        while(true) {
            System.out.println("\nВведите номер комманды:\n" +
                    "1. Сортировка обменом \n2. Сортировка вставками\n" +
                    "3. Сортировка выбором\n"+
                    "4. Показать массив\n"+
                    "5. Быстрая сортировка\n"+
                    "6. Пирамидальная сортировка\n"+
                    "7. Сортировка Шелла\n");
            int num2 = sc.nextInt();
            if (num2 == 1) {
                sorting_bubble();
            }
            else if(num2 == 2){
                sorting_inserts();
            }
            else if(num2 == 3){
                sorting_choice();
            }
            else if(num2 == 4){
                show(a);
            }
            else if(num2 == 5){
                sorting_quick(0, num - 1, a);
            }
            else if(num2 == 6){
                sorting_pyramid();
            }
            else if(num2 == 7){
                sorting_Shell();

            }

            else {System.out.println("Ошибка ввода");}
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Sorting2 list = new Sorting2();
        list.menu();
    }
}