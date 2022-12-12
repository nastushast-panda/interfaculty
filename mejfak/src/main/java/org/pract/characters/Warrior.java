package org.pract.characters;

public class Warrior {
    static final int ATTACK = 5;
    private int health;
    private final int saveHealth;
    private Warrior nextBehind;

    public Warrior()
    {
        this(50);
    }

    protected Warrior(int initHealth)
    {
        health = initHealth;
        saveHealth = health;
    }

    public int getAttack()
    {
        return ATTACK;
    }

    public int getHealth()
    {
        return health;
    }

    private int getSaveHealth()
    {
        return saveHealth;
    }

    protected void setHealth(int value)
    {
        health = Math.min(value, getSaveHealth());
    }

    public boolean isAlive()
    {
        return getHealth() > 0;
    }

    public void hit(Warrior opponent)
    {
        opponent.receiveDamage(getAttack());
        processCommand(OurChampionHasHit.INSTANCE, null);
    }

    protected int receiveDamage(int damage)
    {
        setHealth(getHealth() - damage);
        return damage;
    }

    @Override
    public String toString() {
        return "%s {health = %d, attack = %d" .formatted(getClass().getSimpleName(), getHealth(), getAttack());
    }

    protected void processCommand(Command command, Warrior sender)
    {
        var next = getWarriorBehind();
        if(next != null)
        {
            next.processCommand(command, this);
        }
    }

    protected Warrior getWarriorBehind()
    {
        return nextBehind;
    }

    public void setWarriorBehind(Warrior warrior)
    {
        nextBehind = warrior;
    }
}
