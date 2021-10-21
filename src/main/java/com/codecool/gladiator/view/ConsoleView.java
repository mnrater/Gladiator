package com.codecool.gladiator.view;

import java.util.Scanner;
/**
 * Basic console view implementation
 */
public class ConsoleView implements Viewable {
    private Scanner sc = new Scanner(System.in);

    @Override
    public void display(String text) {
        // Todo
        System.out.println(text);
    }

    @Override
    public int getNumberBetween(int min, int max) {
        // Todo

        int input = getNumber();
        while(input < min || input > max) {
            input = getNumber();
        }
        return input;
    }


    private int getNumber(){
        while(!sc.hasNextInt()){
            sc.next();
        }
        return sc.nextInt();
    }

}
