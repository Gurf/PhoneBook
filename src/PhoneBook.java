import java.util.Arrays;
import java.util.Scanner;

public class PhoneBook {

    public static void main(String[] args) {
        //Добавить считывание ввода пользователя в цикле
        String[][] phoneBookBase = new String[1][2];
        System.out.println("Введите абонента в формате: фамилия имя отчество!");
        int count = 0;//счетчик числа записей в phoneBookBase
        Scanner scanner = new Scanner(System.in);

        boolean isCorrectName = false;
        boolean isCorrectNumber = false;
        while (!isCorrectName) {//надо попробовать убрать while!-не получится
            String name = scanner.nextLine(); //Считывает строку из System.in
            isCorrectName = checkName(name);
            if (!isCorrectName) {
                System.out.println("Введите корректное имя!");
            } else {
                String fioTemp = formatName(name);
//                name = "";
//                System.out.println(fioTemp);

                for (int i = 0; i <= count; i++) {
                    if (fioTemp.equals(phoneBookBase[i][0])) {
                        list(phoneBookBase, i);
                        isCorrectName = false;

                        break;

                    } else if (i == count) {

                        System.out.println("Новая запись, введите номер телефона!");
                        isCorrectNumber = false;

                        while (!isCorrectNumber) {
                            String number = scanner.nextLine(); //Считывает строку из System.in
                            isCorrectNumber = checkNumber(number);
                            if (!isCorrectNumber) {
                                System.out.println("Введите корректный номер!");
                            } else {
                                String numberTemp = formatNumber(number);
                                System.out.println(numberTemp);
                                add(phoneBookBase, fioTemp, numberTemp, count);
                                count++;
                                isCorrectName = false;
                            }
                        }
                        break;
                    }
                }


            }
        }
    }

    public static boolean checkName(String name) {
        String[] partOfName = name.trim().split(" ");
        return partOfName.length == 3;
//        return true;
    }

    public static String formatName(String name) {
        String[] fio = name.trim().split(" ");
        for (int i = 0; i < fio.length; i++) {
            fio[i] = fio[i].replaceFirst(fio[i].substring(0, 1), fio[i].substring(0, 1).toUpperCase());
        }
//        sortByLength(fio);
        String newFIO = fio[0];
        for (int i = 1; i < fio.length; i++) {
            newFIO = newFIO + " " + fio[i];
        }

        return newFIO;
//        return "";
    }

    public static boolean checkNumber(String phoneNumber) {
        String clean = phoneNumber.replaceAll("[^0-9]", "");
        return clean.length() == 11;
//        return true;
    }

    public static String formatNumber(String telephoneN) {
        String clean = telephoneN.replaceAll("[^0-9]", "");

        String output = "+7 " + clean.substring(1, 4) + " " + clean.substring(4, 7) + " " + clean.substring(7, 9) + " " + clean.substring(9, 11);

        return output;
//        return "";
    }

    //добавляем новую запись в телефонную книгу
    public static void add(String[][] book, String name, String number, int index) {
        book[index][0] = name;//add logic
        book[index][1] = number;//add logic
    }

    //выводим номер телефона, если ФИО абонента уже имеется в базе
    public static void list(String[][] book, int index) {
        System.out.println(book[index][1]);//print phone book
    }
}
