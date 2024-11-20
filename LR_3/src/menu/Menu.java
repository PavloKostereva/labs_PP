package menu;

import battle.OneOnOneBattle;
import battle.TeamBattle;
import droid.*;
import droid.CustomDroid;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class Menu {
    private List<Droid> droids = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);
    private Random random = new Random();
    private List<String> battleLog = new ArrayList<>();

    // ANSI коди для кольорів
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_CYAN = "\u001B[36m";



    public void showMenu() {
        while (true) {
            System.out.println(ANSI_CYAN + "\nМеню:" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "1. Показати список дроїдів" + ANSI_RESET);
            System.out.println(ANSI_GREEN + "2. Створити дроїда" + ANSI_RESET);
            System.out.println(ANSI_GREEN + "3. Створити дроїда за власним зразком" + ANSI_RESET);
            System.out.println(ANSI_GREEN + "4. Запустити гру з дроїдами Дилема двох в'язнів (з комп'ютером)" + ANSI_RESET);
            System.out.println(ANSI_GREEN + "5. Запустити бій 1 на 1" + ANSI_RESET);
            System.out.println(ANSI_GREEN + "6. Запустити бій команда на команду" + ANSI_RESET);
            System.out.println(ANSI_GREEN + "7. Запустити випадковий бій" + ANSI_RESET);
            System.out.println(ANSI_GREEN + "8. Читати правила гри" + ANSI_RESET);
            /*System.out.println(ANSI_GREEN + "9. Записати проведений бій у файл" + ANSI_RESET);*/
            System.out.println(ANSI_GREEN + "10. Відтворити проведений бій зі збереженого файлу" + ANSI_RESET);
            System.out.println(ANSI_RED + "11. Вийти з програми" + ANSI_RESET);

            int choice = scanner.nextInt();
            switch (choice) {
                case 1 -> showDroids();
                case 2 -> createDroid();
                case 3 -> createOwnDroid();
                case 4 -> startPrisonerDilemmaWithComputer();
                case 5 -> startOneOnOneBattle();
                case 6 -> startTeamBattle();
                case 7 -> startRandomBattle();
                case 8 -> readRules();
                /*case 9 -> saveBattleToFile();*/
                case 10 -> loadBattleFromFile();
                case 11 -> {
                    System.out.println(ANSI_RED + "Вихід..." + ANSI_RESET);
                    return;
                }
                default -> System.out.println(ANSI_RED + "Неправильний вибір." + ANSI_RESET);
            }
        }
    }

    private void startPrisonerDilemmaWithComputer() {
        if (droids.size() < 1) {
            System.out.println(ANSI_RED + "Недостатньо дроїдів для гри в дилему." + ANSI_RESET);
            return;
        }

        System.out.println(ANSI_CYAN + "Виберіть дроїда для гри з комп'ютером:" + ANSI_RESET);
        showDroids();
        int playerDroidIndex = scanner.nextInt() - 1;

        Droid playerDroid = droids.get(playerDroidIndex);
        Droid computerDroid = new BattleDroid("Комп'ютер");

        int rounds = 10;
        int playerScore = 0;
        int computerScore = 0;

        for (int i = 1; i <= rounds; i++) {
            System.out.println(ANSI_CYAN + "Раунд " + i + ANSI_RESET);
            System.out.println(playerDroid.getName() + ": оберіть 1 - атакувати, 2 - мир");
            int playerChoice = scanner.nextInt();

            int computerChoice = random.nextInt(2) + 1;
            System.out.println(computerDroid.getName() + " обирає: " + (computerChoice == 1 ? "атакувати" : "мир"));

            if (playerChoice == 1 && computerChoice == 1) {
                playerScore += 1;
                computerScore += 1;
                battleLog.add("Раунд " + i + ": Обидва атакували, кожен отримав по 1 балу.");
            } else if (playerChoice == 1 && computerChoice == 2) {
                playerScore += 3;
                battleLog.add("Раунд " + i + ": Ви атакували, комп'ютер обрав мир. Ви отримали 3 бали.");
            } else if (playerChoice == 2 && computerChoice == 1) {
                computerScore += 3;
                battleLog.add("Раунд " + i + ": Комп'ютер атакував, ви обрали мир. Комп'ютер отримав 3 бали.");
            } else {
                playerScore += 2;
                computerScore += 2;
                battleLog.add("Раунд " + i + ": Обидва обрали мир, кожен отримав по 2 бали.");
            }
        }

        System.out.println(ANSI_GREEN + "Підсумкові бали: Ви - " + playerScore + ", Комп'ютер - " + computerScore + ANSI_RESET);
        battleLog.add("Підсумкові бали: Ви - " + playerScore + ", Комп'ютер - " + computerScore);
    }

    private void startOneOnOneBattle() {
        if (droids.size() < 2) {
            System.out.println(ANSI_RED + "Недостатньо дроїдів для бою 1 на 1." + ANSI_RESET);
            return;
        }

        System.out.println(ANSI_CYAN + "Виберіть двох дроїдів для бою:" + ANSI_RESET);
        showDroids();
        int droid1Index = scanner.nextInt() - 1;
        int droid2Index = scanner.nextInt() - 1;

        Droid droid1 = droids.get(droid1Index);
        Droid droid2 = droids.get(droid2Index);

        StringBuilder battleLog = new StringBuilder(); // Для запису бою

        // Запис початку бою
        battleLog.append("Бій між ").append(droid1.getName()).append(" та ").append(droid2.getName()).append(":\n");

        while (droid1.isAlive() && droid2.isAlive()) {
            // Діємо дроїда 1
            int damage1 = droid1.attack();
            droid2.takeDamage(damage1);
            battleLog.append(droid1.getName()).append(" атакує ").append(droid2.getName())
                    .append(", наносячи ").append(damage1).append(" урону.\n")
                    .append(droid2.getName()).append(" тепер має ").append(droid2.getHealth()).append(" здоров'я.\n");

            if (!droid2.isAlive()) break;

            // Діємо дроїда 2
            int damage2 = droid2.attack();
            droid1.takeDamage(damage2);
            battleLog.append(droid2.getName()).append(" атакує ").append(droid1.getName())
                    .append(", наносячи ").append(damage2).append(" урону.\n")
                    .append(droid1.getName()).append(" тепер має ").append(droid1.getHealth()).append(" здоров'я.\n");
        }

        // Визначаємо переможця
        if (droid1.isAlive()) {
            battleLog.append(droid1.getName()).append(" виграв!\n");
        } else {
            battleLog.append(droid2.getName()).append(" виграв!\n");
        }

        System.out.println(ANSI_CYAN + "Результат бою:" + ANSI_RESET);
        System.out.println(battleLog.toString());

        // Запис результату та детального запису бою у файл
        saveBattleDetails(battleLog.toString());
    }

    private void saveBattleDetails(String battleLog) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("battle_log.txt", true))) {
            writer.write(battleLog);
            writer.write("\n"); // Додаємо новий рядок для розділення боїв
        } catch (IOException e) {
            System.out.println(ANSI_RED + "Помилка збереження бою: " + e.getMessage() + ANSI_RESET);
        }
    }
    private void replayBattleFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("battle_log.txt"))) {
            String line;
            System.out.println("Збережений бій:");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println(ANSI_RED + "Помилка зчитування бою: " + e.getMessage() + ANSI_RESET);
        }
    }



    private void saveBattleResult(Droid droid1, Droid droid2) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("battle_log.txt", true))) {
            writer.write("Бій між " + droid1.getName() + " та " + droid2.getName() + ": ");
            if (droid1.isAlive() && !droid2.isAlive()) {
                writer.write(droid1.getName() + " виграв!\n");
            } else if (!droid1.isAlive() && droid2.isAlive()) {
                writer.write(droid2.getName() + " виграв!\n");
            } else {
                writer.write("Бій закінчився внічию.\n");
            }
        } catch (IOException e) {
            System.out.println(ANSI_RED + "Помилка збереження бою: " + e.getMessage() + ANSI_RESET);
        }
    }


    private void startTeamBattle() {
        if (droids.size() < 4) {
            System.out.println(ANSI_RED + "Недостатньо дроїдів для командного бою." + ANSI_RESET);
            return;
        }

        System.out.println(ANSI_CYAN + "Виберіть дроїдів для команди 1 (2 дроїда):" + ANSI_RESET);
        List<Droid> team1 = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            showDroids();
            int droidIndex = scanner.nextInt() - 1;
            team1.add(droids.get(droidIndex));
        }

        System.out.println(ANSI_CYAN + "Виберіть дроїдів для команди 2 (2 дроїда):" + ANSI_RESET);
        List<Droid> team2 = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            showDroids();
            int droidIndex = scanner.nextInt() - 1;
            team2.add(droids.get(droidIndex));
        }

        TeamBattle teamBattle = new TeamBattle(team1, team2);
        teamBattle.startBattle();
    }

    private void startRandomBattle() {
        if (droids.size() < 2) {
            System.out.println(ANSI_RED + "Недостатньо дроїдів для випадкового бою." + ANSI_RESET);
            return;
        }

        System.out.println(ANSI_CYAN + "Випадковий бій..." + ANSI_RESET);
        Droid droid1 = droids.get(random.nextInt(droids.size()));
        Droid droid2 = droids.get(random.nextInt(droids.size()));

        while (droid1 == droid2) { // Забезпечити, що обрано різні дроїди
            droid2 = droids.get(random.nextInt(droids.size()));
        }

        OneOnOneBattle battle = new OneOnOneBattle(droid1, droid2);
        battle.startBattle();

        // Показати результат бою
        System.out.println(ANSI_CYAN + "Результат випадкового бою:" + ANSI_RESET);
        if (droid1.isAlive() && !droid2.isAlive()) {
            System.out.println(ANSI_GREEN + droid1.getName() + " виграв!" + ANSI_RESET);
        } else if (!droid1.isAlive() && droid2.isAlive()) {
            System.out.println(ANSI_GREEN + droid2.getName() + " виграв!" + ANSI_RESET);
        } else {
            System.out.println(ANSI_RED + "Бій закінчився внічию." + ANSI_RESET);
        }
    }

    private void readRules() {
        System.out.println(ANSI_YELLOW + "Правила гри:" + ANSI_RESET);
        System.out.println("1. Створіть дроїдів, вибравши їх типи.");
        System.out.println("2. Виберіть дроїдів для бою.");
        System.out.println("3. Бій проходитиме по раундам.");
        System.out.println("4. Переможе дроїд, який залишиться живим.");
    }
/*
    private void saveBattleToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("battle_log.txt"))) {
            for (String log : battleLog) {
                writer.write(log);
                writer.newLine();
            }
            System.out.println(ANSI_GREEN + "Бій успішно збережено в файл." + ANSI_RESET);
        } catch (IOException e) {
            System.out.println(ANSI_RED + "Помилка збереження бою: " + e.getMessage() + ANSI_RESET);
        }
    }*/

    private void loadBattleFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\Admin\\IdeaProjects\\LR_3\\battle_log.txt"))) {
            String line;
            System.out.println(ANSI_YELLOW + "Збережений бій:" + ANSI_RESET);
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println(ANSI_RED + "Помилка завантаження бою: " + e.getMessage() + ANSI_RESET);
        }
    }

    private void createDroid() {
        System.out.println(ANSI_CYAN + "Виберіть тип дроїда:" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "1. Бойовий дроїд" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "2. Лікуючий дроїд" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "3. Стрілець" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "4. Гоблін" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "5. Бос" + ANSI_RESET);

        int droidType = scanner.nextInt();
        System.out.println(ANSI_CYAN + "Введіть ім'я дроїда:" + ANSI_RESET);
        String name = scanner.next();

        Droid newDroid;
        switch (droidType) {
            case 1 -> newDroid = new BattleDroid(name);
            case 2 -> newDroid = new HealerDroid(name);
            case 3 -> newDroid = new ArcherDroid(name);
            case 4 -> newDroid = new GoblinDroid(name);
            case 5 -> newDroid = new BossDroid(name);
            default -> {
                System.out.println(ANSI_RED + "Неправильний тип дроїда." + ANSI_RESET);
                return;
            }
        }
        droids.add(newDroid);
        System.out.println(ANSI_GREEN + "Дроїд " + name + " успішно створено." + ANSI_RESET);
    }

    private void createOwnDroid() {
        System.out.println(ANSI_CYAN + "Введіть ім'я вашого дроїда:" + ANSI_RESET);
        String name = scanner.next();
        System.out.println(ANSI_CYAN + "Введіть значення атаки:" + ANSI_RESET);
        int attack = scanner.nextInt();
        System.out.println(ANSI_CYAN + "Введіть значення захисту:" + ANSI_RESET);
        int defense = scanner.nextInt();
        System.out.println(ANSI_CYAN + "Введіть значення здоров'я:" + ANSI_RESET);
        int health = scanner.nextInt();

        Droid customDroid = new CustomDroid(name, attack, defense, health);
        droids.add(customDroid);
        System.out.println(ANSI_GREEN + "Ваш дроїд " + name + " успішно створено." + ANSI_RESET);
    }

    private void showDroids() {
        if (droids.isEmpty()) {
            System.out.println(ANSI_RED + "Список дроїдів порожній." + ANSI_RESET);
            return;
        }
        for (int i = 0; i < droids.size(); i++) {
            System.out.println((i + 1) + ". " + droids.get(i).getName() + " (HP: " + droids.get(i).getHealth() + ")");
        }
    }




}