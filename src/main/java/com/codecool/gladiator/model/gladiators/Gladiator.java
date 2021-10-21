package com.codecool.gladiator.model.gladiators;

public abstract class Gladiator {

    private final String name;
    private final int baseHp;
    private final int baseSp;
    private final int baseDex;
    private int level;
    private int currentHp;

    /**
     * Constructor for Gladiators
     *
     * @param name the gladiator's name
     * @param baseHp the gladiator's base Health Points
     * @param baseSp the gladiator's base Strength Points
     * @param baseDex the gladiator's base Dexterity Points
     * @param level the gladiator's starting Level
     */
    public Gladiator(String name, int baseHp, int baseSp, int baseDex, int level) {
        this.name = name;
        this.baseHp = baseHp;
        this.baseSp = baseSp;
        this.baseDex = baseDex;
        this.level = level;
        this.healUp();
    }

    /**
     * @return HP multiplier of the gladiator subclass
     */
    protected abstract Multiplier getHpMultiplier();

    /**
     * @return SP multiplier of the gladiator subclass
     */
    protected abstract Multiplier getSpMultiplier();

    /**
     * @return DEX multiplier of the gladiator subclass
     */
    protected abstract Multiplier getDexMultiplier();

    /**
     * @return Gladiator's name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the full name of the gladiator
     * assembled by the subtype and the name
     * (e.g. "Brutal Brutus" or "Archer Leo")
     *
     * @return the full name
     */
    public String getFullName() {

        return getClass().getSimpleName() + " " + name;
    }

    public void levelUp() {
        level++;
    }

    public int getLevel() {
        return level;
    }

    public double getMaxStatisticDex() {
        return baseDex * getDexMultiplier().value * level;
    }

    public double getMaxStatisticHp() {
        return baseHp * getHpMultiplier().value * level;
    }

    public double getMaxStatisticSp() {
        return baseSp * getSpMultiplier().value * level;
    }

    public void decreaseHpBy(int damageValue){
        currentHp = currentHp - damageValue;
    }



    public enum Multiplier {
        Low(0.75),
        Medium(1.0),
        High(1.25);

        private final double value;

        Multiplier(double value) {
            this.value = value;
        }

        public double getValue() {
            return value;
        }
    }

    public boolean isDead(){
        return currentHp <= 0;
    }

    public void healUp(){
        currentHp = (int) getMaxStatisticHp();
    }

    public int getHp() {
        return (int) getMaxStatisticHp();
    }

    public int getSp() {
        return (int) getMaxStatisticSp();
    }

    public int getDex() {
        return (int) getMaxStatisticDex();
    }

    public int getCurrentHp() {
        return currentHp;
    }

    @Override
    public String toString() {
        return getFullName();
    }
}
