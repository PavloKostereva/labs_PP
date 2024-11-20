package battle;

import droid.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class TeamBattle {
    private List<Droid> team1;  // Перша команда дроїдів
    private List<Droid> team2;  // Друга команда дроїдів
    private Scanner scanner = new Scanner(System.in);  // Сканер для введення даних
    private int attackPower;  // Змінна для зберігання сили атаки
    private BufferedWriter writer;  // Об'єкт для запису в файл

    // Сеттер для сили атаки
    public void setAttackPower(int attackPower) {
        if (attackPower < 0) {
            throw new IllegalArgumentException("Сила атаки не може бути негативною.");
        }
        this.attackPower = attackPower;
    }

    // Конструктор класу TeamBattle
    public TeamBattle(List<Droid> team1, List<Droid> team2) {
        this.team1 = team1;
        this.team2 = team2;
        applySpecialAbilities(team1);  // Застосовуємо спеціальні здібності команди 1
        applySpecialAbilities(team2);  // Застосовуємо спеціальні здібності команди 2
        try {
            writer = new BufferedWriter(new FileWriter("battle_log.txt"));  // Відкриваємо файл для запису
        } catch (IOException e) {
            System.err.println("Не вдалося відкрити файл для запису: " + e.getMessage());
        }
    }

    // Метод для початку бою
    public void startBattle() {
        boolean isTeam1Turn = Math.random() < 0.5;  // Випадковий вибір, яка команда починає

        while (areTeamsAlive()) {  // Перевірка, чи обидві команди ще живі
            if (isTeam1Turn) {
                Droid attacker = chooseAttacker(team1);  // Вибір атакуючого з команди 1
                Droid target = chooseTarget(team2);  // Вибір цілі з команди 2
                processAttack(attacker, target, team2);  // Процес атаки з урахуванням можливостей гобліна
            } else {
                Droid attacker = chooseAttacker(team2);  // Вибір атакуючого з команди 2
                Droid target = chooseTarget(team1);  // Вибір цілі з команди 1
                processAttack(attacker, target, team1);  // Процес атаки з урахуванням можливостей гобліна
            }

            isTeam1Turn = !isTeam1Turn;  // Зміна черги команди
        }

        String winnerMessage = determineWinner();  // Визначення переможця
        System.out.println(winnerMessage);
        try {
            writer.write(winnerMessage + "\n");  // Записуємо результат бою в файл
            writer.close();  // Закриваємо файл
        } catch (IOException e) {
            System.err.println("Не вдалося записати в файл: " + e.getMessage());
        }
    }

    // Метод для вибору атакуючого дроїда
    private Droid chooseAttacker(List<Droid> team) {
        Droid chosenDroid = null;
        while (chosenDroid == null) {
            System.out.println("Оберіть атакуючого з команди:");
            for (int i = 0; i < team.size(); i++) {
                if (team.get(i).isAlive()) {  // Показуємо лише живих дроїдів
                    System.out.println((i + 1) + ". " + team.get(i).getName() + " (HP: " + team.get(i).getHealth() + ")");
                }
            }
            int index = scanner.nextInt() - 1;  // Введення номера дроїда
            if (team.get(index).isAlive()) {  // Перевіряємо, чи обраний дроїд живий
                chosenDroid = team.get(index);
            } else {
                System.out.println("Ви не можете вибрати мертвого дроїда! Оберіть іншого.");
            }
        }
        return chosenDroid;  // Повертаємо обраного дроїда
    }

    // Метод для вибору цілі атаки
    private Droid chooseTarget(List<Droid> team) {
        Droid chosenDroid = null;
        while (chosenDroid == null) {
            System.out.println("Оберіть ціль для атаки:");
            for (int i = 0; i < team.size(); i++) {
                if (team.get(i).isAlive()) {  // Показуємо лише живих дроїдів
                    System.out.println((i + 1) + ". " + team.get(i).getName() + " (HP: " + team.get(i).getHealth() + ")");
                }
            }
            int index = scanner.nextInt() - 1;  // Введення номера дроїда
            if (team.get(index).isAlive()) {  // Перевіряємо, чи обрана ціль жива
                chosenDroid = team.get(index);
            } else {
                System.out.println("Ви не можете вибрати мертвого дроїда! Оберіть іншого.");
            }
        }
        return chosenDroid;  // Повертаємо обрану ціль
    }

    // Метод для перевірки, чи є живі дроїди в обох командах
    private boolean areTeamsAlive() {
        return team1.stream().anyMatch(Droid::isAlive) && team2.stream().anyMatch(Droid::isAlive);
    }

    // Метод для визначення переможця бою
    private String determineWinner() {
        List<Droid> winningTeam = getWinningTeam();  // Отримуємо переможну команду
        if (winningTeam != null) {
            return "Команда " + (winningTeam == team1 ? "1" : "2") + " перемогла!";
        } else {
            return "Нічия!";  // Якщо обидві команди мертві
        }
    }

    // Метод для отримання переможної команди
    public List<Droid> getWinningTeam() {
        // Логіка для визначення переможної команди
        if (team1.stream().anyMatch(Droid::isAlive) && !team2.stream().anyMatch(Droid::isAlive)) {
            return team1;  // Повертаємо команду 1, якщо вона жива
        } else if (!team1.stream().anyMatch(Droid::isAlive) && team2.stream().anyMatch(Droid::isAlive)) {
            return team2;  // Повертаємо команду 2, якщо вона жива
        } else {
            return null;  // Якщо обидві команди мертві, повертаємо null
        }
    }

    // Застосування спеціальних здібностей команд
    private void applySpecialAbilities(List<Droid> team) {
        boolean hasHealer = false;
        boolean hasGoblin = false;
        boolean hasBoss = false;
        boolean hasArcher = false;

        for (Droid droid : team) {
            if (droid instanceof HealerDroid) {
                hasHealer = true;  // Перевірка наявності лікаря
            } else if (droid instanceof GoblinDroid) {
                hasGoblin = true;  // Перевірка наявності гобліна
            } else if (droid instanceof BossDroid) {
                hasBoss = true;  // Перевірка наявності боса
            } else if (droid instanceof ArcherDroid) {
                hasArcher = true;  // Перевірка наявності стрільця
            }
        }

        if (hasHealer) {
            healHalfTeam(team);  // Лікуємо половину команди, якщо є лікар
        }
        if (hasBoss) {
            increaseAttackPower(team, 5);  // Додаємо +5 до атаки всім, якщо є бос
        }
        if (hasArcher) {
            increaseAttackPower(team, 15);  // Додаємо +15 до атаки всім, якщо є стрілець
            decreaseHealth(team, 5);        // Зменшуємо здоров'я всім на 5
        }
        if(hasGoblin){
            increaseAttackPower(team, 20);  // Додаємо +15 до атаки всім, якщо є стрілець
            decreaseHealth(team, 10);
        }
    }

    // Логіка для лікування половини команди
    private void healHalfTeam(List<Droid> team) {
        int healCount = team.size() / 2;  // Лікуємо половину команди
        for (int i = 0; i < healCount; i++) {
            if (team.get(i).isAlive()) {
                team.get(i).setHealth(team.get(i).getHealth() + 20);  // Додаємо +20 здоров'я
                System.out.println(team.get(i).getName() + " вилікувався на 20 HP!");
            }
        }
    }

    // Збільшення сили атаки команди
    private void increaseAttackPower(List<Droid> team, int increment) {
        for (Droid droid : team) {
            droid.setAttackPower(droid.getAttackPower() + increment);  // Збільшення сили атаки
            System.out.println(droid.getName() + " отримав " + increment + " до сили атаки!");
        }
    }

    // Зменшення здоров'я дроїдів
    private void decreaseHealth(List<Droid> team, int decrement) {
        for (Droid droid : team) {
            if (droid.isAlive()) {
                droid.setHealth(Math.max(0, droid.getHealth() - decrement));  // Зменшуємо здоров'я, щоб не опустити нижче 0
                System.out.println(droid.getName() + " втратив " + decrement + " HP!");
            }
        }
    }


    private void processAttack(Droid attacker, Droid target, List<Droid> opposingTeam) {
        if (attacker.isAlive()) {
            // Використовуємо силу атаки дроїда, віднімаємо силу захисту цілі
            int actualDamage = Math.max(0, attacker.getAttackPower() - target.getDefensePower());

            // Завдаємо шкоди цілі
            target.takeDamage(actualDamage);
            System.out.println(attacker.getName() + " атакує " + target.getName() + " на " + actualDamage + " HP!");

            // Якщо дроїд мертвий, видаємо повідомлення
            if (!target.isAlive()) {
                System.out.println(target.getName() + " був знищений!");
            }

            // Записуємо інформацію в лог-файл
            try {
                writer.write(attacker.getName() + " атакує " + target.getName() + " на " + actualDamage + " HP!\n");
                if (!target.isAlive()) {
                    writer.write(target.getName() + " був знищений!\n");
                }
            } catch (IOException e) {
                System.err.println("Не вдалося записати в файл: " + e.getMessage());
            }
        }
    }

}
