/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.dto;

/**
 *
 * @author christopherwatnee
 */
public class Change {
    
    private int changeDue;
    private static final int QUARTER_VALUE = 25;
    private static final int DIME_VALUE = 10;
    private static final int NICKEL_VALUE = 5;
    
    public Change(int changeDue) {
        this.changeDue = changeDue;
    }

    public int getQuarters() {
        return changeDue / QUARTER_VALUE;
    }

    public int getDimes() {
        int changeInQuarters = getQuarters() * QUARTER_VALUE;
        return (changeDue - changeInQuarters) / DIME_VALUE;
    }

    public int getNickels() {
        int changeInQuarters = getQuarters() * QUARTER_VALUE;
        int changeInDimes = getDimes() * DIME_VALUE;
        return (changeDue - changeInQuarters - changeInDimes) / NICKEL_VALUE;
    }

    public int getPennies() {
        int changeInQuarters = getQuarters() * QUARTER_VALUE;
        int changeInDimes = getDimes() * DIME_VALUE;
        int changeInNickels = getNickels() * NICKEL_VALUE;
        
        return changeDue - changeInQuarters - changeInDimes - changeInNickels;
    }
    
    @Override
    public String toString() {
        String changeString = "";
        
        if (getQuarters() > 0) {
            changeString += getQuarters() + " " + (getQuarters() > 1 ? "Quarters" : "Quarter") + " ";
        }
        if (getDimes() > 0) {
            changeString += getDimes() + " " + (getDimes() > 1 ? "Dimes" : "Dime") + " ";
        }
        if (getNickels() > 0) {
            changeString += getNickels() + " " + (getNickels() > 1 ? "Nickels" : "Nickel") + " ";
        }
        if (getPennies() > 0) {
            changeString += getPennies() + " " + (getPennies() > 1 ? "Pennies" : "Penny") + " ";
        }
        return changeString;
    }
}
