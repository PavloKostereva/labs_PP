package battle;

import droid.Droid;

public class OneOnOneBattle {
    private Droid droid1;
    private Droid droid2;

    public OneOnOneBattle(Droid droid1, Droid droid2) {
        this.droid1 = droid1;
        this.droid2 = droid2;
    }


    public void startBattle() {
        while (droid1.isAlive() && droid2.isAlive()) {
            BattleMechanics.attack(droid1, droid2);  // Дроїд 1 атакує дроїда 2
            if (droid2.isAlive()) {
                BattleMechanics.attack(droid2, droid1);  // Дроїд 2 атакує дроїда 1
            }
        }
    }
}
