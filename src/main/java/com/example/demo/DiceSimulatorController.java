package com.example.demo;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class DiceSimulatorController {
    private final DiceSimulatorRepository repository;
    
    DiceSimulatorController (DiceSimulatorRepository repository) {
        this.repository = repository;
    }
    
    @PostMapping(value = "/api/rollDice")
    public DiceSimulator rollDice(
            @RequestParam(value = "numDice", defaultValue = "3") int numDice,
            @RequestParam(value = "numSides", defaultValue = "6") int numSides,
            @RequestParam(value = "numRolls", defaultValue = "100") int numRolls)
    {
        DiceSimulator diceSim = new DiceSimulator(numDice, numSides, numRolls);

        if (!isParamValid(numDice, numSides, numRolls)) {
            diceSim.setErrorMessage();
        }

        diceSim.simulate();
        repository.save(diceSim);

        return diceSim;
    };
    
    @GetMapping(value = "api/getRolls")
    public DiceSimulatorResults getRolls()
    {
        DiceSimulatorResults sim;
        List<DiceSimulator> allSim = repository.findAll();
        int totalSimulations = allSim.size();

        sim = new DiceSimulatorResults(totalSimulations, getDiceCombination(allSim));
        
        return sim;
    };
    

    private boolean isParamValid(int numDice, int numSides, int numRolls) {
        int MIN_DICE_NUMBER = 1;
        int MIN_DICE_SIDES = 4;
        int MIN_ROLL_NUMBER = 1;

        return numDice >= MIN_DICE_NUMBER && numSides >= MIN_DICE_SIDES && numRolls >= MIN_ROLL_NUMBER;
    }
    

    private List<DiceCombination> getDiceCombination (List<DiceSimulator> allSim) 
    {
        
        List<DiceCombination> diceCombinations = new ArrayList<DiceCombination>();
        
        for (int i = 0; i < allSim.size(); i++)
        {
            DiceSimulator currSim = allSim.get(i);
            DiceCombination diceCombination = new DiceCombination(currSim.getNumDice(), currSim.getNumSides());
            
            if(!diceCombinations.contains(diceCombination))
            {
                System.out.println("Updating Num rolls:"+currSim.getNumRolls());
                diceCombination.updateRollCount(currSim.getNumRolls());
                diceCombination.updateSimulationResult(currSim.getSimulationResult());
                diceCombinations.add(diceCombination);
             
            }
            else
            {
                diceCombinations.get(diceCombinations.indexOf(diceCombination)).updateRollCount(currSim.getNumRolls());
                diceCombinations.get(diceCombinations.indexOf(diceCombination)).updateSimulationResult(currSim.getSimulationResult());
            }
            
            diceCombinations.get(diceCombinations.indexOf(diceCombination)).calculateDistribution();
            
        }
        
        return diceCombinations;
    }
}
