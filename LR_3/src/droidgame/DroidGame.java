
package droidgame;

public class DroidGame {
/*
    private List<Droid> droids = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);
    private Random random = new Random();

    // ANSI кольори

    private static final String ANSI_CYAN = "\u001B[36m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_RESET = "\u001B[0m";

    public void start() {
        while (true) {
            System.out.println(ANSI_YELLOW + "1. Створити дроїда" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "2. Показати дроїдів" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "3. Випадковий бій" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "4. Вихід" + ANSI_RESET);
            System.out.print("Виберіть опцію: ");
            int option = scanner.nextInt();

            switch (option) {
                case 1 -> createDroid();
                case 2 -> showDroids();
                case 3 -> randomBattle();
                case 4 -> {
                    System.out.println("Вихід з гри.");
                    return;
                }
                default -> System.out.println(ANSI_RED + "Невірна опція. Спробуйте ще раз." + ANSI_RESET);
            }
        }
    }*/
/*
    private void randomBattle() {
        if (droids.size() < 2) {
            System.out.println(ANSI_RED + "Для бою потрібно мінімум два дроїди!" + ANSI_RESET);
            return;
        }

        Droid droid1 = droids.get(random.nextInt(droids.size()));
        Droid droid2 = droids.get(random.nextInt(droids.size()));

        // Переконатися, що обрані дроїди різні
        while (droid1 == droid2) {
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
*/
    /*
    private void showDroids() {
        for (int i = 0; i < droids.size(); i++) {
            System.out.println((i + 1) + ". " + droids.get(i).getName() + " (HP: " + droids.get(i).getHealth() + ")");
        }
    }*/
/*
    private void createDroid() {
        System.out.print("Введіть ім'я дроїда: ");
        String name = scanner.next();
        System.out.println("Оберіть тип дроїда (1 - Бойовий, 2 - Цілющий, 3 - Стрілець, 4 - Гоблін, 5 - Бос): ");
        int type = getValidDroidType();

        Droid droid;
        switch (type) {
            case 1 -> droid = new BattleDroid(name);
            case 2 -> droid = new HealerDroid(name);
            case 3 -> droid = new ArcherDroid(name);
            case 4 -> droid = new GoblinDroid(name);
            case 5 -> droid = new BossDroid(name);
            default -> throw new IllegalStateException("Unexpected value: " + type);
        }
        droids.add(droid);
        System.out.println(ANSI_GREEN + "Дроїд " + name + " успішно створений!" + ANSI_RESET);
    }*/
/*
    private int getValidDroidType() {
        int type = 0;
        while (type < 1 || type > 5) {
            System.out.print("Оберіть тип дроїда (1-5): ");
            type = scanner.nextInt();
            if (type < 1 || type > 5) {
                System.out.println(ANSI_RED + "Будь ласка, введіть число від 1 до 5." + ANSI_RESET);
            }
        }
        return type;
    }*/


}
