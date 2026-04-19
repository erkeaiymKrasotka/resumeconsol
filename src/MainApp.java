package resume;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class MainApp {

    private static ArrayList<Resume> resumeList = new ArrayList<>();
    private static final String FILE_NAME = "database.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        loadFromFile();

        while (true) {
            System.out.println("\n--- Система Управления Резюме ---");
            System.out.println("1. Показать все резюме");
            System.out.println("2. Добавить резюме");
            System.out.println("3. Удалить резюме");
            System.out.println("4. Сохранить и выйти");
            System.out.print("Выберите действие: ");

            String choice = scanner.nextLine();

            try {
                if (choice.equals("1")) {
                    if (resumeList.isEmpty()) System.out.println("Список пуст.");
                    for (int i = 0; i < resumeList.size(); i++) {
                        System.out.println((i + 1) + ". " + resumeList.get(i));
                    }
                } else if (choice.equals("2")) {
                    System.out.print("Введите имя: ");
                    String name = scanner.nextLine();
                    System.out.print("Введите email: ");
                    String email = scanner.nextLine();
                    System.out.print("Введите опыт работы: ");
                    String exp = scanner.nextLine();

                    resumeList.add(new Resume(name, email, exp));
                    System.out.println("Добавлено!");
                } else if (choice.equals("3")) {
                    System.out.print("Введите номер для удаления: ");
                    int index = Integer.parseInt(scanner.nextLine());
                    resumeList.remove(index - 1);
                    System.out.println("Удалено.");
                } else if (choice.equals("4")) {
                    saveToFile();
                    System.out.println("Данные сохранены. Выход...");
                    break;
                }
            } catch (Exception e) { // Обработка исключений (Exceptions)
                System.out.println("Ошибка: введите корректные данные!");
            }
        }
    }


    private static void saveToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Resume r : resumeList) {
                writer.println(r.getName() + "," + r.getEmail() + "," + r.getExperience());
            }
        } catch (IOException e) {
            System.out.println("Ошибка сохранения.");
        }
    }


    private static void loadFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;
        try (Scanner fileScanner = new Scanner(file)) {
            while (fileScanner.hasNextLine()) {
                String[] data = fileScanner.nextLine().split(",");
                resumeList.add(new Resume(data[0], data[1], data[2]));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден.");
        }
    }
}
