package com.codecool.gladiator;

import com.codecool.gladiator.controller.Colosseum;
import com.codecool.gladiator.model.Combat;
import com.codecool.gladiator.model.Contestants;
import com.codecool.gladiator.model.gladiators.Brutal;
import com.codecool.gladiator.model.gladiators.Gladiator;
import com.codecool.gladiator.model.gladiators.GladiatorFactory;
import com.codecool.gladiator.view.ConsoleView;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        ConsoleView view = new ConsoleView();
        Colosseum colloseum = new Colosseum(view, new GladiatorFactory("Names.txt"));
        colloseum.runSimulation();
        System.out.println();
    }

//        GladiatorFactory gladiatorFactory = new GladiatorFactory("Names.txt");
//        Gladiator gladiator1 = gladiatorFactory.generateRandomGladiator();
//        Gladiator gladiator2 = gladiatorFactory.generateRandomGladiator();
//
//        System.out.println(gladiator1);
//        System.out.println(gladiator2);
//
//
//        Contestants contestants = new Contestants(gladiator1, gladiator2);
//
//        Combat combat = new Combat(contestants);
//
//        Gladiator winner = combat.simulate();
//
//        System.out.println(combat.getCombatLog("\n"));
//
//
//        System.out.println(winner);

}
