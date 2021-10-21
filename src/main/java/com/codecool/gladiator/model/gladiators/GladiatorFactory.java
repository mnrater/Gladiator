package com.codecool.gladiator.model.gladiators;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GladiatorFactory {

    private List<String> names;
    private List<GladiatorClasses> gladiatorClasses = new ArrayList<>();
    private int minStatisticValue = 25;
    private int maxStatisticValue = 100;
    private int minStatisticValueLevel = 1;
    private int maxStatisticValueLevel = 5;
    public static Random random = new Random();

    public GladiatorFactory(String fileOfNames) {
        try {
            File file = new File(getClass().getClassLoader().getResource(fileOfNames).getFile());
            names = Files.readAllLines(file.toPath());
        } catch (IOException|NullPointerException e) {
            System.out.println("Names file not found or corrupted!");
            System.exit(1);
        }
        makeGladiatorClassesList();
    }

    /**
     * Picks a random name from the file given in the constructor
     *
     * @return gladiator name
     */
    private String getRandomName() {
        return names.get(random.nextInt(names.size()));
    }

    /**
     * Instantiates a new gladiator with random name and type.
     * Creating an Archer, an Assassin, or a Brutal has the same chance,
     * while the chance of creating a Swordsman is the double of the chance of creating an Archer.
     * @return new Gladiator
     */
    public Gladiator generateRandomGladiator() {
        int classToMake = random.nextInt(gladiatorClasses.size());
        int hp = getRandomStatisticValues();
        int sp = getRandomStatisticValues();
        int dex = getRandomStatisticValues();
        int lvl = getRandomStatisticValueLevel();

        switch (gladiatorClasses.get(classToMake)) {
            case Archer:
                return new Archer(getRandomName(), hp, sp, dex, lvl);
            case Assassin:
                return new Assassin(getRandomName(), hp, sp, dex, lvl);
            case Brutal:
                return new Brutal(getRandomName(), hp, sp, dex, lvl);
            case Swordsman:
                return new Swordsman(getRandomName(), hp, sp, dex, lvl);
            default:
                throw new RuntimeException();
        }


    }

    enum GladiatorClasses {
        Archer, Assassin, Brutal, Swordsman
    }

    private void makeGladiatorClassesList() {
        gladiatorClasses.add(GladiatorClasses.Archer);
        gladiatorClasses.add(GladiatorClasses.Assassin);
        gladiatorClasses.add(GladiatorClasses.Brutal);
        gladiatorClasses.add(GladiatorClasses.Swordsman);
        gladiatorClasses.add(GladiatorClasses.Swordsman);
    }

    private int getRandomStatisticValues() {
        int randomNumber = random.nextInt(maxStatisticValue + 1 - minStatisticValue) + minStatisticValue;
        return randomNumber;
    }
    private int getRandomStatisticValueLevel() {
        int randomNumber = random.nextInt(maxStatisticValueLevel + 1 - minStatisticValueLevel) + minStatisticValueLevel;
        return randomNumber;
    }
}
