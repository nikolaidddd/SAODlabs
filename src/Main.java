
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.io.File;


class Storage
{
    boolean InputData(Menu menu) {
        try{
            File file = new File("C:\\5semlabs\\saodlabs\\src\\defaultmenu.txt");
            Scanner sc2 = new Scanner(file);
            String text, name;
            char key;
            menu.createSubmenues();
            text = sc2.nextLine(); // Берем одну строку
            menu.SetHeadline(text);
            while (sc2.hasNextLine()) {
                 // Берем одну строку
                text = sc2.nextLine();
                String[]f = text.split(" "); // Делим строку по пробелам
               // if (f == null) continue; //если строка пустая, перейти на следующую итерацию цикла
                name = f[0];
                menu.AddSubmenu(name); // создаем подменю
                 // Переходим к следующему элементу
                for(int i =1; i<f.length;i+=2) { // пока не пусто
                    text = f[i];
                    key = f[i+1].charAt(0);
                    menu.AddCommand(name, text, key); //Создаем команду, если нашлись данные
                }
            }
            sc2.close();
        }
        catch (FileNotFoundException e){
            System.out.println("Файл не найден. Создано пустое меню");
        }
        return true;
    }

    boolean OutputData(Menu menu) {
        String outdata = "";
        outdata += menu.GetString();
        try{
            FileOutputStream outstream = new FileOutputStream("C:\\5semlabs\\saodlabs\\src\\defaultmenu.txt");
            byte buf[] = outdata.getBytes();
            outstream.write(buf);

        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return true;
    }

    void delStorage(){ }
}

class Menu {
    private String headline;
    private CompSubmenu Submenues;
    public Menu() {
        headline = "МЕНЮ";
    }
    void SetHeadline(String _headline) {
        headline = _headline;
    }
    String GetHeadline() {
        return headline;
    }

    void createSubmenues() {
        Submenues = new CompSubmenu();
    }

    boolean AddSubmenu(String text) {
        Submenues.AddSubmenu(text);
        return true;
    }
    boolean FindSubmenu(String text) {
        if (!Submenues.FindSubmenu(text).equals(Submenues.GetHead()))
            return true;
        else return false;
    }
    boolean DelSubmenu(String name) {
        return Submenues.DelSubmenu(name);
    }
    boolean AddCommand(String name, String text, char _key) {
        return Submenues.AddCommand(name, text, _key);
    }
    boolean FindCommand(String text) {
        if (Submenues.FindCommand(text) != -1) return true;
        else return false;
    }


    boolean DelCommand(String text)
    {
        return Submenues.DelCommand(text);
    }
    void Show() {
        if(Submenues == null){
            System.out.println("Меню пустое. Нечего показать.");
        }
        else {
            System.out.println("-----------------------------------------------------------");
            System.out.println("|         МЕНЮ ПРОГРАММЫ '" + headline + "'");
            System.out.println("-----------------------------------------------------------");
            System.out.println("|   НАЗВАНИЕ    |" + "        КОМАНДЫ");
            System.out.println("|    ПОДМЕНЮ    |" + "        ПОДМЕНЮ");
            Submenues.ShowSubmenues();
            System.out.println("-----------------------------------------------------------");
        }
    }

    String GetString() // Формирование таблицы в виде строки
     {return headline + '\n' + Submenues.GetString();};

    void destCompSubmenu() {

        CompSubmenu temp = Submenues;
        Submenu temp2 =temp.GetHead().right();


        while(temp2 != temp.GetHead()){
            String temp3 = temp2.GetText();
            temp2 = temp2.right();
            temp3 = temp2.GetText();
            DelSubmenu(temp3);
        }
        Submenues=null;
    }
}

class CompSubmenu {
    private Submenu submenuHead;

    public CompSubmenu() {
        submenuHead = new Submenu(" <HEAD>");
        submenuHead.SetLeft(submenuHead);
        submenuHead.SetRight(submenuHead);
    }

    Submenu GetHead() { return submenuHead; }

    // МЕТОДЫ ДЛЯ ПОДМЕНЮ
    //ПОИСК УКАЗАННОГО ПОДМЕНЮ (НУЖНО ДЛЯ УДАЛЕНИЯ)
    Submenu FindSubmenu(String text) {
        Submenu pCurrent;
        pCurrent = submenuHead.right();
        while (pCurrent != submenuHead) {
            if (pCurrent.GetText().equals(text))
                break;
            pCurrent = pCurrent.right();
        }
        return pCurrent;
    }
    //ДОБАВЛЕНИЕ ПОДМЕНЮ ПО СОРТИРОВКЕ
    void AddSubmenu(String text) {
        Submenu pTemp = new Submenu(text);
        Submenu pCurrent = submenuHead.right();
        if (pCurrent == submenuHead) { // добавить первым и завершить
            pTemp.SetRight(pCurrent);
            pTemp.SetLeft(pCurrent);
            pCurrent.SetRight(pTemp);
            pCurrent.SetLeft(pTemp);
            return;
        }
        while (pCurrent != submenuHead) {
            if (text.compareTo(pCurrent.GetText())<0) { // ДОБАВИТЬ ПЕРЕД И ЗАВЕРШИТЬ
                pTemp.SetRight(pCurrent);
                pTemp.SetLeft(pCurrent.left());
                (pCurrent.left()).SetRight(pTemp);
                pCurrent.SetLeft(pTemp);
                return;
            }
            pCurrent = pCurrent.right();
        }
        // ЕСЛИ ВСЕ ИМЕНА ПОДМЕНЮ ОКАЗАЛИСЬ БОЛЬШЕ, ТО ДОБАВЛЯЕМ В КОНЕЦ
        pTemp.SetRight(pCurrent);  // после while pcurrent = head, указатель последнего элемента ставим на заг. элемент
        pTemp.SetLeft(pCurrent.left());
        (pCurrent.left()).SetRight(pTemp);
        pCurrent.SetLeft(pTemp);
    }

    boolean DelSubmenu(String name) {
        Submenu pCurrent = FindSubmenu(name);
        if (pCurrent == submenuHead) // ЕСЛИ ЭЛЕМЕНТ НЕ НАЙДЕН
            return false;

        Command[] list = pCurrent.getTheSubmenu();
        for(int i = 0; i<10;i++ ){
            list[i].Clear();
        }
        (pCurrent.left()).SetRight(pCurrent.right());
        (pCurrent.right()).SetLeft(pCurrent.left());
        pCurrent = null; // УДАЛЯЕМ ПОДМЕНЮ, ОСВОБОЖДАЕМ ПАМЯТЬ
        return true;
    };
    // МЕТОДЫ ДЛЯ КОМАНД

    boolean AddCommand(String name, String text, char _key) {
        Submenu pCurrent = submenuHead.right();
        while (pCurrent != submenuHead) {
            if (pCurrent.GetText().equals(name))
                break;
            pCurrent = pCurrent.right();
        }
        if (pCurrent == submenuHead)
            return false;
        if (pCurrent.AddCommand(text, _key))
            return true;
        return false;
    };

    int FindCommand(String text) {
        Submenu pCurrent = submenuHead.right();
        int x;
        while (pCurrent != submenuHead) {
            x = pCurrent.FindCommand(text);
            if (x != -1)
                return x;
            pCurrent = pCurrent.right();
        }
        return -1;
    };

    boolean DelCommand(String text) {
        Submenu pCurrent = submenuHead.right();
        while (pCurrent != submenuHead) {
            if (pCurrent.DelCommand(text))
                return true;
            pCurrent = pCurrent.right();
        }
        return false;
    };
    void ShowSubmenues()
    {
        Submenu pCurrent = submenuHead.right();
        while (pCurrent != submenuHead) {
            System.out.println( "-----------------------------------------------------------");
            System.out.println( "|      " + pCurrent.GetText() +"      |");
            if (!(pCurrent.ShowCommands()))
                System.out.print( "\n");
            pCurrent = pCurrent.right();
        }
    }
    String GetString()
    {
        Submenu pCurrent = submenuHead.right();
        String str = "";
        while (pCurrent != submenuHead) {
            String ss="";
            ss += pCurrent.GetText();
            str += ss.toString();
            str += pCurrent.GetString();
            pCurrent = pCurrent.right();
            str += '\n';
        }
        return str;
    }
    void delCompSubmenu() {
        Submenu pCurrent = submenuHead.right();
        Submenu pTemp;
        while (pCurrent != submenuHead) {
            pTemp = pCurrent;
            pCurrent = pCurrent.right();
            pTemp = null;
        }
        submenuHead = null;
    };

};


class Submenu {
    private class SubmenuData {
        String text;
        Command[] command = new Command[10]; // Массив команд (для статического стека)
        int top = -1; // ИНДЕКС ВЕРШИНЫ СТЕКА
        int count = 0; // счетчик количества команд
        Submenu right = null;
        Submenu left = null;
    }

    private SubmenuData theSubmenu, Tempmenu;

    Command[] getTheSubmenu(){return theSubmenu.command;}
    Submenu right() { return theSubmenu.right; }
    Submenu left() { return theSubmenu.left; }
    void SetRight(Submenu _right) { theSubmenu.right = _right; }
    void SetLeft(Submenu _left) { theSubmenu.left = _left; }
    String GetText() { return theSubmenu.text; }


    public Submenu(String text) {
        theSubmenu = new SubmenuData();
        for (int i =0; i< 10; i++){
            theSubmenu.command[i]= new Command();
        }
        theSubmenu.command[0].Set(" <ПУСТО>", (char)0);
        theSubmenu.left = theSubmenu.right = null;
        theSubmenu.text = text;
    };

    boolean AddCommand(String text, char _key) {
        if (theSubmenu.count >= 10) {
            System.out.println( " СТЕК КОМАНД ЗАПОЛНЕН. ДОБАВЛЕНИЕ НЕВОЗМОЖНО");
            return false;
        }
        else {
            theSubmenu.command[theSubmenu.top + 1].Set(text, _key); // устанавливаем значения text и key для команды
            theSubmenu.top++; // меняем индекс вершины стека
            theSubmenu.count++;
            return true;
        }
    };

    int FindCommand(String text) {
        int index = -1;
        for (int i = theSubmenu.top; i > -1; i--)
            if (theSubmenu.command[i].GetText().equals(text))
                index = i;
        return index;
    };

    boolean DelCommand(String text) {
        Tempmenu = new SubmenuData();
        for (int i =0; i< 10; i++){
            Tempmenu.command[i]= new Command();
        }
        if (theSubmenu.count == 0) {
            System.out.println( "\n СТЕК " + theSubmenu.text + " ПУСТОЙ. УДАЛЯТЬ НЕЧЕГО");
            return false;
        }
        int index = FindCommand(text);
        if (index == -1) {
            System.out.println( "\n В ПОДМЕНЮ " + theSubmenu.text + " НЕТ ТАКОЙ КОМАНДЫ");
            return false;
        }
        if (index == theSubmenu.top) { // ЕСЛИ УДАЛЯЕМ ЭЛЕМЕНТ-ВЕРШИНУ СТЕКА
            theSubmenu.command[theSubmenu.top].Clear();
            theSubmenu.top--; // МЕНЯЕМ ИНДЕКС ВЕРШИНЫ СТЕКА
            theSubmenu.count--;
            return true;
        }
        else { // ЕСЛИ УДАЛЯЕМЫЙ ЭЛЕМЕНТ - НЕ ВЕРШИНА СТЕКА, ТО СОЗДАЕМ ВСПОМОГАТЕЛЬНЫЙ, ПЕРЕНОСИМ В НЕГО ВСЕ ЭЛЕМЕНТЫ С
            // ВЕРШИНЫ ДО НУЖНОГО (НЕ ВКЛЮЧИТЕЛЬНО), УДАЛЯЕМ НУЖНЫЙ ЭЛЕМЕНТ, ПРИСОЕДИНЯЕМ ВСПОМОГАТЕЛЬНЫЙ СТЕК К ГЛАВНОМУ

            for (int i = theSubmenu.top; i > index; i--) { // ПЕРЕПИСЫВАЕМ ЭЛЕМЕНТЫ ВО ВСПОМОГАТЕЛЬНЫЙ СТЕК
                Tempmenu.command[Tempmenu.top + 1].Set(theSubmenu.command[i].GetText(), theSubmenu.command[i].GetKey());
                Tempmenu.top++;
                Tempmenu.count++;
                theSubmenu.command[theSubmenu.top].Clear();
                theSubmenu.top--; // МЕНЯЕМ ИНДЕКС ВЕРШИНЫ СТЕКА
                theSubmenu.count--;
            }

            theSubmenu.command[theSubmenu.top].Clear(); // УДАЛЯЕМ НУЖНЫЙ ЭЛЕМЕНТ-КОМАНДУ
            theSubmenu.top--; // МЕНЯЕМ ИНДЕКС ВЕРШИНЫ СТЕКА
            theSubmenu.count--;

            for (int i = 0; i < Tempmenu.count; i++) { // ПЕРЕПИСЫВАЕМ ЭЛЕМЕНТЫ ОБРАТНО В ГЛАВНЫЙ СТЕК
                theSubmenu.command[theSubmenu.top + 1].Set(Tempmenu.command[Tempmenu.top].GetText(), Tempmenu.command[Tempmenu.top].GetKey());
                Tempmenu.command[Tempmenu.top].Clear(); // удаляем вершину вспомогательного стека
                Tempmenu.top--;
                theSubmenu.top++;
                theSubmenu.count++;
            }

            return true;
        }
    };

    boolean ShowCommands() {
        if (theSubmenu.count > 0) {
            for (int i = theSubmenu.top; i > -1; i--) {
                System.out.println(  "|                |  " +  theSubmenu.command[i].GetText() + ": г.клавиша: " +  theSubmenu.command[i].GetKey() );
            }
            return true;
        }
        else
            return false;
    }

    String GetString() {
        String str = "";
        for (int i = 0; i < theSubmenu.count; i++) {
            String ss ="";
            ss += theSubmenu.command[i].GetText();
            str += " ";
            str += ss.toString();
            str += " ";
            str += theSubmenu.command[i].GetKey();
        }
        return str;
    };

}

class Command {
    private class CommandData {
    String text; // ТЕКСТ
    char key; // КЛАВИША БЫСТРОГО ВЫЗОВА

} CommandData theCommand;

    public Command(){
        theCommand = new CommandData();
        theCommand.text = "<ПУСТО>";
        theCommand.key = (char) 0;
    }

    void Set(String text, char _key) {
        theCommand.text = text;
        theCommand.key = _key;
    }
    char GetKey() { return theCommand.key; }
    String GetText() { return theCommand.text; }

    void Clear(){
        theCommand.text = "<ПУСТО>";
        theCommand.key = (char) 0;
    };

};

class Main {

    Scanner sc = new Scanner(System.in);
    Menu menu = null;

    void AddSubmenu() {
        String name;
        System.out.println( "\n ВВЕДИТЕ НАЗВАНИЕ НОВОГО ПОДМЕНЮ: ");
        name = sc.next();
        menu.AddSubmenu(name);
        menu.Show();
    }

    void FindSubmenu() {
        String name;
        menu.Show();
        System.out.println( "\n ВВЕДИТЕ НАЗВАНИЕ ПОДМЕНЮ, КОТОРОЕ НУЖНО НАЙТИ: ");
        name = sc.next();
        if (menu .FindSubmenu(name))
            System.out.println( " ПОДМЕНЮ НАЙДЕНО\n");
        else System.out.println( " ПОДМЕНЮ НЕ НАЙДЕНО\n");
    }

    void DelSubmenu() {
        String name;
        menu.Show();
        System.out.println( "\n ВВЕДИТЕ НАЗВАНИЕ ПОДМЕНЮ, КОТОРОЕ НУЖНО УДАЛИТЬ: ");
        name = sc.next();
        if (menu.DelSubmenu(name)) {
            System.out.println( " ПОДМЕНЮ УДАЛЕНО\n");
            menu.Show();
        }
        else System.out.println( " ПОДМЕНЮ НЕ УДАЛЕНО\n");
    }

    void AddCommand() {
        String name, command; char key;
        menu.Show();
        System.out.println( "\n ВВЕДИТЕ НАЗВАНИЕ ПОДМЕНЮ, В КОТОРОЕ ХОТИТЕ ДОБАВИТЬ КОМАНДУ: ");
        name = sc.next();
        if (menu .FindSubmenu(name)) {
            System.out.println( "\n ВВЕДИТЕ НАЗВАНИЕ КОМАНДЫ, КОТОРУЮ ХОТИТЕ ДОБАВИТЬ: ");
            command = sc.next();
            System.out.println( "\n ВВЕДИТЕ ГОРЯЧУЮ КЛАВИШУ: ");
            key = sc.next().charAt(0);
            if (menu.AddCommand(name, command, key)) {
                System.out.println( " КОМАНДА ДОБАВЛЕНА\n");
                menu.Show();
            }
            else System.out.println( " КОМАНДА НЕ ДОБАВЛЕНА\n");
        }
        else System.out.println( " ТАКОЕ ПОДМЕНЮ НЕ НАЙДЕНО. КОМАНДА НЕ ДОБАВЛЕНА\n");
    }

    void FindCommand() {
        String text;
        menu.Show();
        System.out.println( "\n ВВЕДИТЕ НАЗВАНИЕ КОМАНДЫ, КОТОРУЮ ХОТИТЕ НАЙТИ: ");
        text = sc.next();
        if (menu .FindCommand(text))
            System.out.println( " КОМАНДА НАЙДЕНА\n");
        else System.out.println( " КОМАНДА НЕ НАЙДЕНА\n");
    }

    void DelCommand() {
        String text;
        menu.Show();
        System.out.println( "\n ВВЕДИТЕ НАЗВАНИЕ КОМАНДЫ, КОТОРУЮ ХОТИТЕ УДАЛИТЬ: ");
        text = sc.next();
        if (menu .DelCommand(text)) {
            System.out.println( "\n КОМАНДА УДАЛЕНА\n");
            menu.Show();
        }
        else System.out.println( "\n КОМАНДА НЕ УДАЛЕНА\n");
    }

    public void menu2() {

        Scanner sc = new Scanner(System.in);
        File file = new File("C:\\5semlabs\\saodlabs\\src\\defaultmenu.txt");
        menu = new Menu();
        String headline = "";
        if (!file.exists()) {
            System.out.println("Ошибка загрузки данных из файла.");
            System.out.println("Введите название для создания пустого меню: ");
            headline = sc.next();
            menu.SetHeadline(headline);
            menu.createSubmenues();
        }
        else{
            Storage storage = new Storage();
            storage.InputData(menu);
            storage =null;
        }
        while (true) {
            System.out.println("\nВведите номер комманды:\n" +
                    "1. Добавить новое подменю \n" +
                    "2. Найти подменю по названию\n" +
                    "3. Удалить подменю\n" +
                    "4. Добавить новую команду в подменю\n" +
                    "5. Найти команду\n" +
                    "6. Удалить команду\n" +
                    "7. Показать меню\n" +
                    "8. Очистить меню\n"+
                    "9. Деструкторы\n"+
                    "0. Сохранить в файл");
            int num2 = sc.nextInt();
            if (num2 == 1) {
                AddSubmenu();
            } else if (num2 == 2) {
                FindSubmenu();
            } else if (num2 == 3) {
                DelSubmenu();
            } else if (num2 == 4) {
                AddCommand();
            } else if (num2 == 5) {
                FindCommand();
            } else if (num2 == 6) {
                DelCommand();
            } else if (num2 == 7) {

                menu.Show();
            } else if (num2 == 8) {

                menu = null;
                System.out.println("Меню удалено.");
                menu = new Menu();
                System.out.println("Введите название для создания пустого меню: ");
                headline = sc.next();
                menu.SetHeadline(headline);
                menu.createSubmenues();
            }
            else if (num2 == 9) {
                System.out.println("\nДеструктор какого класса вызвать?:\n" +
                        "1. CompSubmenu \n" +
                        "2. Menu\n" );
                int num3= sc.nextInt();
                if (num3==1){
                    menu.destCompSubmenu();
                }
                else if(num3==2) {
                    menu = null;
                }
            }
            else if (num2 == 0) {
                if (!file.exists()) {
                    System.out.println("Файл не найден. Данные не сохранены");
                }
                else{
                    Storage storage = new Storage();
                    storage.OutputData(menu);
                    storage = null;
                }
            }
            else {
                System.out.println("Ошибка ввода");
            }
        }
    }

    public static void main(String[] args) {
        Main list = new Main();
        list.menu2();
    }
}