package org.pract;

import org.pract.characters.Knight;
import org.pract.characters.Warrior;

public class Main {
    public static void main(String[] args) {
        // fluent interface
        var army = new Army()
                .addUnits(Warrior::new, 1)
                .addUnits(new Knight())
                .addUnits(new Warrior())
                .addUnits(new Knight());
    }
}
