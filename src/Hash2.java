import java.util.Scanner;

public class Hash2 {

    Scanner sc = new Scanner(System.in);
    int k = 0;
    String[] a; //ССЫЛКА НА БУДУЩИЙ МАССИВ
    int m; //РАЗМЕР МАССИВА (ЧИСЛО КЛЮЧЕЙ (И РАЗМЕР ТАБЛИЦЫ))
    int get_code(String str) {
        char[] str2 = str.toCharArray();
        int code = 0;
        for (int i = 0; i < str.length(); i++) { // ВЫЧИСЛЕНИЕ СУММЫ КОДОВ ВСЕХ СИМВОЛОВ КЛЮЧА
            code += (str2[i]);
        }
        System.out.println("Числовой код ключа:"+ code);
        code = code % m;
        System.out.println("Индекс элемента после хэш-функции:" + code);
        return code;
    }

    // одинаковые ключи не добавлять, в случае одинаковых хеш функций исправлять конфликт

    // ДОБАВЛЕНИЕ ЭЛЕМЕНТА В ТАБЛИЦУ
    void push(String str) {
        System.out.println("Ключ:"+str);
        int code = 0; // код текстового ключа
        int code2 = 0;
        code  = get_code(str);
        if (!a[code].equals(str) && a[code].equals("пусто")) {
            a[code] = str;
            System.out.println("Элемент добавлен в ячейку: "+(code));
            k++;
        }

        else if (!a[code].equals(str)){
            for (int i2 = 0; i2 < (m);i2++){
                code2 = ((code + i2)%m);
                if (a[code2].equals("пусто")){
                    a[code2] = str;
                    System.out.println("Элемент добавлен в ячейку: "+code2);
                    k++;
                    break;
                }
            }
        }
        else {
            System.out.println("Этот элемент уже есть в таблице.");
        }
    }

    boolean find(int code, String str){

        int compares = 0,code4;


        int cf =0;
        code = get_code(str);
        compares++;

        if (a[code].equals(str)){
            System.out.println("\nЭлемент найден. Ячейка: " + (code));
            System.out.println("Кол-во сравнений: "+compares);
        }
        else {
            for (int i2 = 1; i2 < (m); i2++) {
                code4 = ((code + i2) % m);
                compares++;
                if (a[code4].equals(str)){
                    System.out.println("Элемент найден в ячейке: "+code4+"\nКол-во сранений: "+compares);
                    cf = 1;
                }
            }
            compares=0;
        }
        if(cf == 0){
            System.out.println("Элемент не найден");
            return false;
        }
        return true;
    }

    public void menu(){

        int code,code3;

        System.out.println("Введите константу m: ");
        m = sc.nextInt();
        int compares=0;


        a = new String[m];
        for (int i = 0; i <m; i++)
            a[i] = "пусто";

        while(true) {

            System.out.println("\nВведите номер комманды:\n" +
                    "1. Добавление элемента в таблицу \n" +
                    "2. Поиск заданного ключа\n" +
                    "3. Вывод таблицы \n"+
                    "4. Удаление элемента \n");
            int num = sc.nextInt();
            if (num == 1) {
                if (k < m) {
                    System.out.println("Введите строку");
                    String str = sc.next();
                    int codee = get_code(str);
                    if (find(codee, str)) {
                        System.out.println("Элемент уже есть в таблице.");
                    } else {
                        push(str);
                    }
                }
                else {
                    System.out.println("Таблица уже заполнена.");
                }
            }
            else if(num == 2){
                System.out.println("Введите ключ для поиска");
                String str = sc.next();
                int codee = get_code(str);
                find(codee,str);

            }
            else if(num == 3){
                for (int i = 0; i < m; i++)
                    System.out.println( i + ") " + a[i] );

            }

            else if(num == 4) {
                System.out.println("Введите элемент для удаления");
                String str = sc.next();
                code = get_code(str);
                int cf = 0;
                if (a[code].equals(str)) {
                    a[code] = "пусто";
                    System.out.println("Элемент удален");
                    k--;

                }
                else {
                    for (int i2 = 1; i2 < (m); i2++) {
                        code3 = ((code + i2) % m);
                        if (a[code3].equals(str)) {
                            a[code3] = "пусто";
                            System.out.println("Элемент удален");
                            cf = 1;
                            k--;
                            break;

                        }
                    }
                }
                if (cf == 0){
                    System.out.println("Элемнет не найден");
                }

            } else {
                System.out.println("Ошибка ввода");
            }
        }


    }

    public static void main(String[] args) {

        Hash2 list = new Hash2();
        list.menu();
    }
}