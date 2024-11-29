
// Online IDE - Code Editor, Compiler, Interpreter

public class Hash
{
    public static void main(String[] args) {
        String RecMessage = "2:1 2 3 14:2 5 6 24:3 2 3 11";

// Разделение строки по двоеточию
        String[] groups = RecMessage.split(":");

// Создание двумерного массива
        int[][] numbers = new int[groups.length][];

// Заполнение двумерного массива числами из каждой группы
        for (int i = 0; i < groups.length; i++) {
            String[] group = groups[i].trim().split(" ");
            numbers[i] = new int[group.length];
            for (int j = 0; j < group.length; j++) {
                numbers[i][j] = Integer.parseInt(group[j]);
            }
        }
        for (int i = 0; i < numbers.length; i++) {
            System.out.print("numbers[" + i + "] = [");
            for (int j = 0; j < numbers[i].length; j++) {
                System.out.print(numbers[i][j]);
                if (j != numbers[i].length - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println("]");
        }
    }
}
