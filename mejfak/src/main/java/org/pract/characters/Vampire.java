package org.pract.characters;

public class Vampire extends Warrior{
    static final int ATTACK = 4;
    public static final int VAMPIRIZM = 50;


    public Vampire() {
        super(40);
    }

    int getVampirizm()
    {
        return VAMPIRIZM;
    }

    @Override
    public void hit(Warrior opponent)
    {
        int damage;
        damage = opponent.receiveDamage(getAttack());
        setHealth(super.getHealth() + (damage * getVampirizm() / 100));
    }

    @Override
    public int getAttack()
    {
        return ATTACK;
    }
}
