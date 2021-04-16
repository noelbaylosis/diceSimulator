package com.example.demo;

import java.util.HashMap;
import java.util.Random;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Column;

@Entity
public class DiceSimulator {
    @Id @GeneratedValue
    private long simulationNumber;
    private int numRolls;
    private int numSides;
    private int numDice;
    String errorMessage;
    @Column(length=1000) 
    HashMap<Integer, Integer> simulationResult;

    HashMap<Integer, Integer> result;

    DiceSimulator() {

    }

    DiceSimulator(int numDice, int numSides, int numRolls) {
        this.numRolls = numRolls;
        this.numSides = numSides;
        this.numDice = numDice;
    }

    public void simulate() {
        HashMap<Integer, Integer> rollResult = new HashMap<Integer, Integer>();
        Random diceRoll = new Random();

        for (int roll = 0; roll < getNumRolls(); roll++) {
            int tempSum = 0;
            for (int dice = 0; dice < getNumDice(); dice++) {
                tempSum += diceRoll.nextInt(getNumSides())+1;
            }

            System.out.println("Roll#" + roll + " Sum= " + tempSum);
            if (null == rollResult.get(tempSum)) {
                rollResult.put(tempSum, 1);
            } else {
                rollResult.put(tempSum, rollResult.get(tempSum) + 1);
            }
        }

        setSimulationResult(rollResult);
    }

    // Getters
    /*
     * public long getSimulationNumber () { return this.simulationNumber; }
     */

    public int getNumRolls() {
        return this.numRolls;
    }

    public int getNumSides() {
        return this.numSides;
    }

    public int getNumDice() {
        return this.numDice;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public HashMap<Integer, Integer> getSimulationResult() {
        return this.simulationResult;
    };

    // Setters
    public void setErrorMessage() {
        this.errorMessage = "Invalid parameter value(s); simulation not performed";
    }

    public void setSimulationResult(HashMap<Integer, Integer> result) {
        this.simulationResult = result;
    };
}
