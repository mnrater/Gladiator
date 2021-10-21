package com.codecool.gladiator.model;

import com.codecool.gladiator.model.gladiators.Gladiator;
import com.codecool.gladiator.model.gladiators.GladiatorFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Combat class, used for simulating fights between pairs of gladiators
 */
public class Combat {

    private final Gladiator gladiator1;
    private final Gladiator gladiator2;

    private Gladiator winner;
    private Gladiator loser;


    private final List<String> combatLog;

    public Combat(Contestants contestants) {
        this.gladiator1 = contestants.gladiator1;
        this.gladiator2 = contestants.gladiator2;
        this.combatLog = new ArrayList<>();
    }

    /**
     * Simulates the combat and returns the winner.
     * If one of the opponents is null, the winner is the one that is not null
     * If both of the opponents are null, the return value is null
     *
     * @return winner of combat
     */
    public Gladiator simulate() {
        if(gladiator1 == null)
            return gladiator2;

        if(gladiator2 == null)
            return gladiator1;

        Gladiator attacker = gladiator1;
        Gladiator deffender = gladiator2;

        while (!gladiator1.isDead() && !gladiator2.isDead()) {
            serviceTurn(attacker, deffender);

            Gladiator temp = attacker;
            attacker = deffender;
            deffender = temp;
        }

        winner = gladiator1.isDead() ? gladiator2 : gladiator1;
        loser = !gladiator1.isDead() ? gladiator2 : gladiator1;
        combatLog.add(dieLog(loser, winner));

        winner.levelUp();
        winner.healUp();

        return winner;
    }

    private void serviceTurn(Gladiator attacker, Gladiator defender) {
        int dexDiff = attacker.getDex() - defender.getDex();

        int chanceToHit = getChanceToHit(dexDiff);

        if(didAttackHit(chanceToHit)) {
            int damageValue = calculateDamage(attacker.getSp());
            defender.decreaseHpBy(damageValue);
            combatLog.add(attackLog(attacker, damageValue));
        }
        else {
            combatLog.add(missedLog(attacker));
        }

    }

    public int calculateDamage(int gladiatorSp) {
        double r = 0.1 + (0.5 - 0.1) * GladiatorFactory.random.nextDouble();
        double damage = gladiatorSp * r;

        return (int)damage;
    }

    public boolean didAttackHit(int chanceToHit) {
        return GladiatorFactory.random.nextInt(101) < chanceToHit;
    }

    public int getChanceToHit(int dexDiff) {
        int min = 10;
        int max = 100;

        if(dexDiff < min)
            return min;

        if(dexDiff > max)
            return max;

        return dexDiff;
    }



    public Gladiator getGladiator1() {
        return gladiator1;
    }

    public Gladiator getGladiator2() {
        return gladiator2;
    }

    public String getCombatLog(String separator) {
        return String.join(separator, combatLog);
    }

    public String attackLog(Gladiator gladiator1, int damage){
        StringBuilder sb = new StringBuilder();
        sb.append(gladiator1.getFullName());
        sb.append(" deals ");
        sb.append(damage);
        sb.append(" damage");
        return sb.toString();
    }

    public String missedLog(Gladiator gladiator1) {
        StringBuilder sb = new StringBuilder();
        sb.append(gladiator1.getFullName());
        sb.append(" missed");
        return sb.toString();
    }

    public String dieLog(Gladiator loser, Gladiator winner){
        StringBuilder sb = new StringBuilder();
        sb.append(loser.getFullName());
        sb.append(" has died, ");
        sb.append(winner.getFullName());
        sb.append(" wins!");
        return sb.toString();
    }


    public Gladiator getWinner() {
        return winner;
    }

    public Gladiator getLoser() {
        return loser;
    }


}
