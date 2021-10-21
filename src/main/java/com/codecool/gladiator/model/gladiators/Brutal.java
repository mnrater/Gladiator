package com.codecool.gladiator.model.gladiators;

public class Brutal extends Gladiator {
    public Brutal(String name, int baseHp, int baseSp, int baseDex, int level) {
        super(name, baseHp, baseSp, baseDex, level);
    }

    @Override
    protected Multiplier getHpMultiplier() {
        return Gladiator.Multiplier.High;
    }

    @Override
    protected Multiplier getSpMultiplier() {
        return Gladiator.Multiplier.High;
    }

    @Override
    protected Multiplier getDexMultiplier() {
        return Gladiator.Multiplier.Low;
    }
}
