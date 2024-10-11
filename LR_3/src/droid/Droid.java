package droid;

public abstract class Droid {
    private String name;
    private int health;
    private int attackPower;
    private int defensePower;

    // Конструктор
    public Droid(String name, int health, int attackPower, int defensePower) {
        this.name = name;
        this.health = health;
        this.attackPower = attackPower;
        this.defensePower = defensePower;
    }

    // Геттер для отримання сили атаки
    public int getAttackPower() {
        return attackPower;
    }

    public void setAttackPower(int attackPower) {
        if (attackPower < 0) {
            throw new IllegalArgumentException("Сила атаки не може бути негативною.");
        }
        this.attackPower = attackPower;
    }

    // Геттер для отримання значення здоров'я
    public int getHealth() {
        return health;
    }

    // Сеттер для зміни значення здоров'я
    public void setHealth(int health) {
        this.health = health;
    }

    // Геттер для отримання сили захисту
    public int getDefensePower() {
        return defensePower;
    }

    // Геттер для отримання імені дроїда
    public String getName() {
        return name;
    }

    // Метод для перевірки, чи живий дроїд
    public boolean isAlive() {
        return health > 0;
    }

    // Метод для атаки
    public int attack() {
        // Використовуємо випадковий модифікатор для атаки
        int randomAttackPower = (int) (attackPower * (Math.random() * 0.5 + 0.75));
        return randomAttackPower;
    }

    public void takeDamage(int damage) {
        if (damage > 0) {
            health -= damage; // Віднімаємо шкоду від здоров'я
            System.out.println(getName() + " отримав(ла) " + damage + " шкоди!");
        } else {
            System.out.println(getName() + " захистився від атаки!");
        }

        if (health < 0) {
            health = 0; // Не допускаємо негативного здоров'я
        }
    }



}
