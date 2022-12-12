package org.pract.characters;

public class Defender extends Warrior{
    static final int ATTACK = 3;
    public static final int DEFENSE = 2;


    public Defender()
    {
        super(60);
    }

    int getDefense()
    {
        return DEFENSE;
    }

    //void ?
    @Override
    protected int receiveDamage(int damage)
    {
        int dam = Math.max(0, damage - getDefense());
        super.receiveDamage(dam);
        return dam;
    }

    @Override
    public int getAttack()
    {
        return ATTACK;
    }
}
