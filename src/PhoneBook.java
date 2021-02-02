import java.util.Arrays;
import java.util.Scanner;

public class PhoneBook {

    public static void main(String[] args) {
        //Добавить считывание ввода пользователя в цикле
        String[][] phoneBookBase = new String[2][2];
        System.out.println("Введите абонента в формате: фамилия имя отчество!");
        int count = 0;//счетчик числа записей в phoneBookBase
        Scanner scanner = new Scanner(System.in);
        String fioTemp = null;
        boolean isCorrectName = false;
        boolean isCorrectNumber = false;
        while (!isCorrectName) {
            String name = scanner.nextLine(); //Считывает строку из System.in

            isCorrectName = checkName(name);
            if (!isCorrectName) {
                System.out.println("Введите корректное имя!");
            } else {
                fioTemp = formatName(name);
//                System.out.println(fioTemp);

                for (int i = 0; i <= count; i++) {
                    if (fioTemp.equals(phoneBookBase[i][0])) {
                        list(phoneBookBase, i);
                        isCorrectName = false;
                        break;

                    } else if (i == count) {
                        System.out.println(fioTemp);
                        System.out.println("Новая запись, введите номер телефона (11 цифр)!");
                        isCorrectNumber = false;
                    }
                }
            }
            while (!isCorrectNumber) {
                String number = scanner.nextLine(); //Считывает строку из System.in
                isCorrectNumber = checkNumber(number);
                if (!isCorrectNumber) {
                    System.out.println("Введите корректный номер!");
                } else {
                    String numberTemp = formatNumber(number);
                    System.out.println("Сохранено: " + numberTemp);
                    add(phoneBookBase, fioTemp, numberTemp, count);
                    count++;//пинаем счетчик
//                    System.out.println(phoneBookBase.length);
//                    System.out.println(count);
                    System.out.println(Arrays.deepToString(phoneBookBase));//ВЫводим на печать массив

                    isCorrectName = false;
                }
            }

            //увеличиваем длину  массива на половину при его наполнении и инициализируем "свободный хвост массива" в цикле for
            if (count == phoneBookBase.length) { //если количество записей равно теущей длине массива
                newLengthArr(phoneBookBase);//c вызовом этого метода не работет

                //то увеличиваем длину массива
                /*phoneBookBase = Arrays.copyOf(phoneBookBase, phoneBookBase.length + phoneBookBase.length / 2);
                for (int i = count; i < phoneBookBase.length; i++) {
                    phoneBookBase[i] = new String[2];
                }*/

                System.out.println(Arrays.deepToString(phoneBookBase));//ВЫводим на печать массив
            }
        }
    }

    //======М Е Т О Д Ы===================================================
    //увеличиваем длину массива
    public static void newLengthArr(String[][] arr) {
        int oldLength = arr.length;
        arr = Arrays.copyOf(arr, arr.length + arr.length / 2);
        for (int i = oldLength; i < arr.length; i++) {
            arr[i] = new String[2];
        }
    }

    public static boolean checkName(String name) {
        String[] partOfName = name.trim().split(" ");
        return partOfName.length == 3;
    }

    public static String formatName(String name) {
        String[] fio = name.trim().split(" ");
        for (int i = 0; i < fio.length; i++) {
            fio[i] = fio[i].replaceFirst(fio[i].substring(0, 1), fio[i].substring(0, 1).toUpperCase());
        }
//        sortByLength(fio);Убрал т.к. не прикольно когда фамилия имя и отчество могут встать на разные места
        String newFIO = fio[0];
        for (int i = 1; i < fio.length; i++) {
            newFIO = newFIO + " " + fio[i];
        }

        return newFIO;
    }

    public static boolean checkNumber(String phoneNumber) {
        String clean = phoneNumber.replaceAll("[^0-9]", "");
        return clean.length() == 2;
    }

    public static String formatNumber(String telephoneN) {
        String clean = telephoneN.replaceAll("[^0-9]", "");

        String output = "+7 " + clean/*clean.substring(1, 4) + " " + clean.substring(4, 7) + " " + clean.substring(7, 9) + " " + clean.substring(9, 11)*/;

        return output;
    }

    //добавляем новую запись в телефонную книгу
    public static void add(String[][] book, String name, String number, int index) {
        book[index][0] = name;
        book[index][1] = number;
    }

    //выводим ФИО и номер телефона, если ФИО абонента уже имеется в базе
    public static void list(String[][] book, int index) {
        System.out.println(book[index][0] + ": " + book[index][1]);

    }
}
