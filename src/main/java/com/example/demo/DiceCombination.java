package com.example.demo;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.text.DecimalFormat;


public class DiceCombination {
    private int numDice;
    private int numSides;
    private int totalRolls;
    private HashMap<Integer, Integer> totalSimulationResult;
    private HashMap<Integer, String> distributionResult;
    
    private String id;

    DiceCombination (int numDice, int numSides)
    {
        this.numDice = numDice;
        this.numSides = numSides;
        this.totalRolls = 0;
        this.id = "D" + numDice + "S" + numSides;
        this.totalSimulationResult = new HashMap<Integer, Integer> ();
        this.distributionResult = new HashMap<Integer, String> ();
        
    }
    
    public boolean equals (Object o)
    {
        if (!(o instanceof DiceCombination)) {
            return false;
        }
          
        DiceCombination d = (DiceCombination)o;
          
     
        return (this.numSides == d.numSides) && (this.numDice == d.numDice);
        
    }
    
    
    
    // Getters
    public int getNumDice () 
    {
        return this.numDice;
    }
    
    public int getNumsides () 
    {
        return this.numSides;
    }
    
    public int getTotalRolls ()
    {
        return this.totalRolls;
    }
    
    /*
     * Debugger
    public HashMap<Integer, Integer> getTotalSimulationResult()
    {
        return this.totalSimulationResult;
    }
    */
    
    public HashMap<Integer, String> getTotalDistribution()
    {
        return this.distributionResult;
    }
    //
    
    public void updateRollCount(int count)
    {
        this.totalRolls += count;
    }
    
    public void updateSimulationResult(HashMap<Integer, Integer> simResult)
    {
        Iterator i = simResult.entrySet().iterator();
        Map.Entry currSim;
        
        while (i.hasNext())
        {
            currSim = (Map.Entry) i.next();
            
            updateTotal((int)currSim.getKey(), (int)currSim.getValue());
        }
        
    }
    
    private void updateTotal(int key, int val)
    {
        if (totalSimulationResult.containsKey(key))
        {
            totalSimulationResult.put(key, totalSimulationResult.get(key)+val);
        }
        else
        {
            totalSimulationResult.put(key, val);
        } 
    }
    
    public void calculateDistribution()
    {
        Iterator i = totalSimulationResult.entrySet().iterator();
        Map.Entry simRes;
        String pattern = "##.##";
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
                
        while(i.hasNext())
        {
            simRes = (Map.Entry) i.next();
            Double val = new Double((Integer)simRes.getValue());
            val = (val / totalRolls) * 100;
            
            distributionResult.put((Integer)simRes.getKey(),decimalFormat.format(val) + "%");
        }
    }
    
    @Override
    public int hashCode() 
    {
        return id.hashCode();
    }
}
