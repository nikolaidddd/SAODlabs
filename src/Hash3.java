

import java.util.Scanner;

public class    Hash3 {

    Scanner sc = new Scanner(System.in);


    int m; //РАЗМЕР МАССИВА (ЧИСЛО КЛЮЧЕЙ (И РАЗМЕР ТАБЛИЦЫ))

    class hashp {
        String key = "пусто";
        list first = null, last = null;
    }
    hashp[] a;

    class list{
        String key;
        list next;
    }


    // ПОИСК КОДА КЛЮЧА С ПРИМЕНЕНИЕМ ХЭШ-ФУНКЦИИ
    int get_code(String str) {
        char[] str2 = str.toCharArray();
        int code = 0;
        for (int i = 0; i < str.length(); i++) { // ВЫЧИСЛЕНИЕ СУММЫ КОДОВ ВСЕХ СИМВОЛОВ КЛЮЧА
            code += (str2[i]);
        }
        System.out.println("Числовой код ключа:"+ code);
        code = code % m; // ХЭШ - ФУНКЦИЯ
        System.out.println("Индекс элемента после хэш-функции:" + code+"\n");
        return code;
    }



    // ДОБАВЛЕНИЕ ЭЛЕМЕНТА В ТАБЛИЦУ
    void push(String str) {
        System.out.println("Ключ:"+str);
        int code = 0; // код текстового ключа
        list temp = new list();
        code  = get_code(str);
        if (!a[code].key.equals(str) && a[code].key.equals("пусто")) {
            a[code].key = str;
            System.out.println("Элемент добавлен в ячейку: "+(code));
        }
        else if(!(a[code].first==null)){

            a[code].last.next = temp;
            a[code].last = temp;
            temp.key = str;
        }
        else {
            a[code].first = temp;
            a[code].last = temp;
            temp.key = str;
        }
    }

    void show(){
        for(int i=0; i<m;i++){
            System.out.print(i+") ");
            System.out.println(a[i].key);
            list current = a[i].first;
            System.out.println("    Вспом. список:");
            while (current!=null){
                System.out.println("    "+ current.key);
                if (current == a[i].last) {
                    break;
                }
                current = current.next;

            }
            System.out.println("\n");
        }

    }

    void delp(int code, String str){
        list pred =a[code].first;
        list current = a[code].first;
        list del;

        if(a[code].first==null){            // если доп. список пустой
            a[code].key ="пусто";
        }
        else {
            if (a[code].key.equals(str)){ // если элемент - ячейка массива
                del = a[code].first;
                a[code].key = a[code].first.key;
                a[code].first = a[code].first.next;
                del = null;
            }
            else if(a[code].first!=null){
                while (current != null) {
                    if (current.key.equals(str)){
                        break;
                    }
                    current = current.next;
                }
                while (pred.next != current && pred!=current){
                    pred = pred.next;
                }
                if(current==a[code].last){      // если элемент последний в списке
                    if(pred==current){
                        a[code].first=null;
                    }
                    else {
                        a[code].last = pred;
                    }
                }
                else{                            // если элемент в середине списка
                    pred.next = current.next;
                }
                if (current == a[code].first){    // если элемент первый в списке
                    a[code].first = current.next;
                }
            }
        }
    }

    boolean find(int code, String str){
        int compares = 0;
        compares++;
        list current;
        int n=0;
        int cf = 0;

        if (a[code].key.equals(str)){
            System.out.println("\nЭлемент найден. Ячейка: " + code+" Сравнений: "+compares);
            cf = 1;

        }
        for (int i=0; i<m;i++){
            current = a[i].first;
            while (current!=null){
                if (current.key.equals(str)){
                    System.out.println("Элемент найден. Ячейка массива: "+ i+" Номер в списке: " + n+
                            " Сравнений: " + compares);
                    cf = 1;
                }
                n++;
                compares++;
                current = current.next;
            }
            n=0;
        }
        compares=0;

        if(cf == 0) {
            System.out.println("Элемент не найден. ");
            return false;

        }
        return true;
    }



    public void menu(){

        int code,code3;

        System.out.println("Введите константу m: ");
        m = sc.nextInt();
        int compares=0;


        a = new hashp[m];
        for (int i = 0; i <m; i++)
            a[i] = new hashp();

        while(true) {

            System.out.println("\nВведите номер комманды:\n" +
                    "1. Добавление элемента в таблицу \n" +
                    "2. Поиск заданного ключа\n" +
                    "3. Вывод таблицы \n"+
                    "4. Удаление элемента \n");
            int num = sc.nextInt();
            if (num == 1) {

                System.out.println("Введите строку");
                String str = sc.next();
                int codee = get_code(str);
                if (find(codee,str)){
                    System.out.println("Элемент уже есть в таблице.");
                }
                else {
                    push(str);
                }
            }
            else if(num == 2){
                System.out.println("Введите ключ для поиска");
                String str = sc.next();
                code = get_code(str);
                find(code,str);
            }
            else if(num == 3){
                show();
            }

            else if(num == 4){
                System.out.println("Введите элемент для удаления");
                String str = sc.next();
                code = get_code(str);
                if(find(code,str)){
                    delp(code,str);
                    System.out.println("\nЭлемент удален.");
                }
                else System.out.println("Элемент для удаления не найден");

            }
            else {System.out.println("Ошибка ввода");}
        }
    }

    public static void main(String[] args) {

        Hash3 list = new Hash3();
        list.menu();
    }
}