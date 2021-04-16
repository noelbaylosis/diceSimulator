package com.example.demo;

import java.util.List;

public class DiceSimulatorResults {
    int numSimulation;
    List<DiceCombination> rollCombinations;
    
    DiceSimulatorResults (int numSimulation, List<DiceCombination> allSim)
    {
        this.numSimulation = numSimulation;
        this.rollCombinations = allSim;
    }
    
    // Getters
    public int getNumSimulation()
    {
        return this.numSimulation;
    }
    
    public List<DiceCombination>  getRollCombinations()
    {
        return this.rollCombinations;
    }


}
