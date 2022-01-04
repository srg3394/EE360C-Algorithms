/*
 * Name: <your name>
 * EID: <your EID>
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * Your solution goes in this class.
 * 
 * Please do not modify the other files we have provided for you, as we will use
 * our own versions of those files when grading your project. You are
 * responsible for ensuring that your solution works with the original version
 * of all the other files we have provided for you.
 * 
 * That said, please feel free to add additional files and classes to your
 * solution, as you see fit. We will use ALL of your additional files when
 * grading your solution.
 */
public class Program3 extends AbstractProgram3 {

    /**
     * Determines the solution of the optimal response time for the given input TownPlan. Study the
     * project description to understand the variables which represent the input to your solution.
     *
     * @return Updated TownPlan town with the "response" field set to the optimal response time
     */
    @Override
    public TownPlan OptimalResponse(TownPlan town) {
    	 
    	int n = town.getHouseCount();
    	int s = town.getStationCount();
    	
    	float[][] responseTimes = new float[n+1][s+1]; //create n*s array

    	//fill in array values
    	for(int i = 1; i < n + 1; i++) {
    		for(int j = 1; j < s + 1; j++) {
    			
    			if(j == 1) {// if only one base station, we take the average
    				if(i == 1) {
    					responseTimes[i][j] = town.getPositionHouses().get(i-1);
    				}
    				else {
    					//System.out.println("Previous:" + responseTimes[i-1][k]);
    					//System.out.println("# of house - 1:" + (i-1));
    					//System.out.println("Curr Dist:" + town.getPositionHouses().get(i-1));
    					//System.out.println("# of house:" + (i-1));
    					responseTimes[i][j] = (((responseTimes[i-1][j]) * (i-1)) + town.getPositionHouses().get(i-1))/i;
    					
    				}
    				
    			}
    		
    		}
    		
    	}
    	
    	town.setResponse(Float.max(responseTimes[n][s] - town.getPositionHouses().get(0), town.getPositionHouses().get(n-1) - responseTimes[n][s]));
    	return town;
    }

    /**
     * Determines the solution of the set of food station positions that optimize response time for the given input TownPlan. Study the
     * project description to understand the variables which represent the input to your solution.
     *
     * @return Updated TownPlan town with the "position_food_stations" field set to the optimal food station positions
     */
    @Override
    public TownPlan OptimalPosFoodStations(TownPlan town) {
    	int n = town.getHouseCount();
    	int s = town.getStationCount();
    	
    	float[][] posStation = new float[n+1][s+1]; //create n*s array
    	
    	ArrayList<Float> positions = new ArrayList<Float>();

    	//fill in array values
    	for(int i = 1; i < n + 1; i++) {
    		for(int j = 1; j < s + 1; j++) {
    			
    			if(j == 1) {// if only one base station, we take the average
    				
    				if(i == 1) {
    					posStation[i][j] = town.getPositionHouses().get(i-1);
    				}
    				else {
    					//System.out.println("Previous:" + posStation[i-1][k]);
    					//System.out.println("# of house - 1:" + (i-1));
    					//System.out.println("Curr Dist:" + town.getPositionHouses().get(i-1));
    					//System.out.println("# of house:" + (i-1));
    					posStation[i][j] = (((posStation[i-1][j]) * (i-1)) + town.getPositionHouses().get(i-1))/i;
    					
    				}
    				
    			}
    		
    		}
    	}
    	
    	positions.add(posStation[n][s]);
    	town.setPositionFoodStations(positions);
    	return town;
    }
}
