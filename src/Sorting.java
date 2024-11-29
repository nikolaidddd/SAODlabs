import java.util.Scanner;

public class Sorting {

    int compares = 0, changes = 0;
    int num;
    private int[] a;
    Scanner sc = new Scanner(System.in);



    public Sorting(){
        System.out.println("Введите размер списка: ");
        num = sc.nextInt();
        a = new int[num];
        for (int i=0; i<num; i++)
            a[i] = (int)(Math.random() * 100);
        System.out.println("Массив создан");
    }

    void show(int[] a) {

        for (int i=0; i<num; i++){
            System.out.print(a[i]+" ");
        }
    }

    int[] copy_of_a() {

        int[] b = new int[num];
        for (int i=0; i<num; i++)
            b[i] = a[i];
        return b;
    }

    void sorting_bubble() {
        compares = 0;
        changes = 0;
        int[] arr1 = copy_of_a();
        int temp;
        for (int i = 0; i < num; i++) {
            for (int j = num - 1; j > i; j--) {
                compares++;
                if (arr1[j - 1] > arr1[j]) {
                    temp = arr1[j - 1];
                    arr1[j - 1] = arr1[j];
                    arr1[j] = temp;
                    changes+=3;
                }
            }
        }
        changes = changes / 3;
        System.out.println( "\nСортировка обменом выполнена\n");
        show(arr1);
        System.out.println( "\nКоличество сравнений: " + compares + ", Количество перестановок: " + changes + "\n");
        
    }

    void sorting_inserts() {
        compares = 0;
        changes = 0;
        int[] arr2 = copy_of_a();
        int temp = 0, l = 0;
        for (int i = 1; i < num; i++) {
            int j = i - 1;

            while ( j >= 0 && arr2[i]< arr2[j]) {
                j-=1;
                compares++;
            }
            if (j + 1 != i) {
                temp = arr2[i];
                l = i - 1;
                changes++;
                while (l >= j + 1) {
                    arr2[l + 1] = arr2[l];
                    l--;
                    changes++;
                }
                arr2[j + 1] = temp;
                changes++;
            }
        }
        changes = changes / 3;
        System.out.println( "\nСортировка вставками выполнена\n");
        show(arr2);
        System.out.println( "\nКоличество сравнений: " + compares + ", Количество перестановок: " + changes + "\n");

    }

    void sorting_choice() {
        compares = 0; changes = 0;
        int[] array = copy_of_a();
        int min = 0;
        int temp = 0;
        for (int i = 0; i < num; i++) {
            min = i;
            for (int j = i + 1; j < num; j++) {
                if (array[j] < array[min]) {
                    min = j;
                }
                compares++;
            }
            if (min!=i) {
                temp = array[i];
                array[i] = array[min];
                array[min] = temp;
                changes+=3;
            }
        }
        changes = changes / 3;
        System.out.println( "\nСортировка выбором выполнена\n");
        show(array);
        System.out.println( "\nКоличество сравнений: " + compares + ", Количество перестановок: " + changes + "\n");
    }


    public void menu(){
        Scanner sc = new Scanner(System.in);
        while(true) {
            System.out.println("\nВведите номер комманды:\n" +
                    "1. Сортировка обменом \n2. Сортировка вставками\n" +
                    "3. Сортировка выбором\n"+
                    "4. Показать массив\n");
            int num = sc.nextInt();
            if (num == 1) {
                sorting_bubble();
            }
            else if(num == 2){
                sorting_inserts();
            }
            else if(num == 3){
                sorting_choice();
            }
            else if(num == 4){
                show(a);
            }

            else {System.out.println("Ошибка ввода");}
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Sorting list = new Sorting();
        list.menu();
    }
}