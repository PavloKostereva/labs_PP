package battle;

import droid.Droid;

public class BattleMechanics {
    public static void attack(Droid attacker, Droid defender) {
        int damage = Math.max(0, attacker.getAttackPower() - defender.getDefensePower());  // Розрахунок завданої шкоди
        defender.setHealth(defender.getHealth() - damage);  // Зменшення здоров'я захисника
        System.out.println(attacker.getName() + " атакує " + defender.getName() + ", наносячи " + damage + " урону.");
        System.out.println(defender.getName() + " тепер має " + defender.getHealth() + " здоров'я.");
    }
}
