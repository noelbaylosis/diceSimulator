package com.example.demo;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Random;
import java.util.HashMap;

@RestController
public class DiceSimulatorController {
	@PostMapping(value= "/api/rollDice")
	public DiceSimulator rollDice(
			@RequestParam(value="numDice", defaultValue="3") int numDice,
			@RequestParam(value="numSides", defaultValue="6") int numSides,
			@RequestParam(value="numRolls", defaultValue="10") int numRolls ) 
	{
		DiceSimulator diceSim = new DiceSimulator (numDice, numSides, numRolls);
		
		if (!isParamValid(numDice, numSides, numRolls)) 
		{
			diceSim.setErrorMessage();
		}

		diceSim.simulate();
		
		return diceSim;
	};
	
	private boolean isParamValid(int numDice, int numSides, int numRolls) 
	{
		int MIN_DICE_NUMBER = 1;
		int MIN_DICE_SIDES = 4;
		int MIN_ROLL_NUMBER = 1;
		
		return numDice >= MIN_DICE_NUMBER && numSides >= MIN_DICE_SIDES && numRolls >= MIN_ROLL_NUMBER;
	}
}
