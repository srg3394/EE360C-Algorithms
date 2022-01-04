/*
 * Name: Sathvik Gujja
 * EID: srg3394
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
 * grading your solution. However, do not add extra import statements to this file.
 */
public class Program1 extends AbstractProgram1 {

    /**
     * Determines whether a candidate Matching represents a solution to the stable matching problem.
     * Study the description of a Matching in the project documentation to help you with this.
     */
    @Override
    public boolean isStableMatching(Matching problem) {
        /* TODO implement this function */
    	System.out.println("Checking stable matching: ");
    	
    	ArrayList<Integer> internMatch = problem.getInternMatching();
    	ArrayList<Integer> companyPositions = problem.getCompanyPositions();
    	ArrayList<ArrayList<Integer>> companyList = problem.getCompanyPreference();
    	ArrayList<ArrayList<Integer>> internList = problem.getInternPreference();
    	
    	System.out.println("Intern Match: " + internMatch);
    	System.out.println("Company List: " + companyList);
    	System.out.println("Intern List: " + internList);
    	System.out.println("Company Position Count: " + companyPositions);
    	
    	for(int i = 0; i < problem.getInternCount(); i++) {
    		ArrayList<ArrayList<Integer>> betterMatches = new ArrayList<ArrayList<Integer>>();
    		int currMatch = internList.get(i).indexOf(internMatch.get(i));
    		  		
    		for(int init = 0; init < problem.getInternCount(); init++) {
    			betterMatches.add(new ArrayList<Integer>());
    		}
    		
    		ArrayList<Integer> internPrefs = internList.get(i);
    		
    		for(int preference = 0; preference < internPrefs.size(); preference++) {
    			if(currMatch == -1) {
    				betterMatches.get(i).add(internPrefs.get(preference));
    			}
    			if(preference != internMatch.get(i) && internPrefs.get(preference) < currMatch) {
    				betterMatches.get(i).add(internPrefs.get(preference)); // if preference list of intern has the company over current, add to prefCompanies 
    			}
    		}    	
    	
    		for(ArrayList<Integer> betterMatch : betterMatches) {
    			for(int company = 0; company < betterMatch.size(); company++) {
    				if(companyList.get(betterMatch.get(company)).indexOf(i) < companyList.get(betterMatch.get(company)).indexOf(internMatch.indexOf(betterMatch.get(company)))) {
    					return false;
    				}
    			}			
    		}
    	}
    	
        return true;
    }

    /**
     * Determines a solution to the stable matching problem from the given input set. Study the
     * project description to understand the variables which represent the input to your solution.
     *
     * @return A stable Matching.
     */
    @Override
    public Matching stableMatchingGaleShapley_companyoptimal(Matching problem) {
        /* TODO implement this function */
    	ArrayList<Integer> companyPositions = problem.getCompanyPositions();
    	ArrayList<ArrayList<Integer>> companyList = problem.getCompanyPreference();
    	ArrayList<ArrayList<Integer>> internList = problem.getInternPreference();
    	int companyCount = problem.getCompanyCount();
    	int internCount = problem.getInternCount();
    	
    	
    	
    	ArrayList<Integer> matched = new ArrayList<Integer>(); //holds the matches
    	
    	for(int i = 0; i < internCount; i++) {
    		matched.add(-1);
    	}
    	
    	ArrayList<ArrayList<Integer>> updatedInternList = problem.getInternPreference();
    	
    	int intern = 0;
    	int emptyCheck = 0;
    		while(intern < internList.size() && !updatedInternList.isEmpty()) {
    			
    			if(updatedInternList.get(intern).isEmpty()) {
    				for(int i = 0; i < matched.size(); i++) {
    					if(!updatedInternList.get(i).isEmpty()) {
    						intern = i;
    					}
    					else {
    						emptyCheck++;
    					}
    				}
    				if(emptyCheck == internCount) {
    					break;
    				}
    				continue;
    			}
    			int currCompany = updatedInternList.get(intern).get(0); // highest preference company
    			if(matched.get(intern) == -1) { // not matched
    				if(!matched.contains(currCompany)) { // nobody else is matched with the company
    					matched.set(intern, currCompany);    					
    					   					
    				}
    				else { // someone is matched with the company
    					int currMatch = matched.indexOf(updatedInternList.get(intern).get(0));
    					if(companyList.get(currCompany).indexOf(intern) < companyList.get(currCompany).indexOf(currMatch)) {
    						matched.set(intern, currCompany);
    						matched.set(currMatch, -1);
    						
    					}
    				}
    			}
    			else {
    				int currMatch = matched.indexOf(updatedInternList.get(intern).get(0));
					if(companyList.get(currCompany).indexOf(intern) < companyList.get(currCompany).indexOf(currMatch)) {
						matched.set(intern, currCompany);
						matched.set(currMatch, -1);
						
					}
    			}
    			
    			if(updatedInternList.get(intern) != null) {
    				updatedInternList.get(intern).remove(0);
    			}
    			
    			if(matched.get(intern) != -1) {
    				for(int i = 0; i < matched.size(); i++) {
    					intern = matched.indexOf(-1);
    				}
    			}
    			
    			
    		}
    	
    	
    	
        return new Matching(problem, matched);
    }

    /**
     * Determines a solution to the stable matching problem from the given input set. Study the
     * project description to understand the variables which represent the input to your solution.
     *
     * @return A stable Matching.
     */
    @Override
    public Matching stableMatchingGaleShapley_internoptimal(Matching problem) {
        /* TODO implement this function */
    	
    	
    	ArrayList<Integer> companyPositions = problem.getCompanyPositions();
    	ArrayList<ArrayList<Integer>> companyList = problem.getCompanyPreference();
    	ArrayList<ArrayList<Integer>> internList = problem.getInternPreference();
    	int companyCount = problem.getCompanyCount();
    	int internCount = problem.getInternCount();
    	
    	
    	
    	ArrayList<Integer> matched = new ArrayList<Integer>(); //holds the matches
    	
    	for(int i = 0; i < internCount; i++) {
    		matched.add(-1);
    	}
    	
    	ArrayList<ArrayList<Integer>> updatedInternList = problem.getInternPreference();
    	
    	int intern = 0;
    	int emptyCheck = 0;
    		while(intern < internList.size() && !updatedInternList.isEmpty()) {
    			
    			if(updatedInternList.get(intern).isEmpty()) {
    				for(int i = 0; i < matched.size(); i++) {
    					if(!updatedInternList.get(i).isEmpty()) {
    						intern = i;
    					}
    					else {
    						emptyCheck++;
    					}
    				}
    				if(emptyCheck == internCount) {
    					break;
    				}
    				continue;
    			}
    			int currCompany = updatedInternList.get(intern).get(0); // highest preference company
    			if(matched.get(intern) == -1) { // not matched
    				if(!matched.contains(currCompany)) { // nobody else is matched with the company
    					matched.set(intern, currCompany);    					
    					   					
    				}
    				else { // someone is matched with the company
    					int currMatch = matched.indexOf(updatedInternList.get(intern).get(0));
    					if(companyList.get(currCompany).indexOf(intern) < companyList.get(currCompany).indexOf(currMatch)) {
    						matched.set(intern, currCompany);
    						matched.set(currMatch, -1);
    						
    					}
    				}
    			}
    			else {
    				int currMatch = matched.indexOf(updatedInternList.get(intern).get(0));
					if(companyList.get(currCompany).indexOf(intern) < companyList.get(currCompany).indexOf(currMatch)) {
						matched.set(intern, currCompany);
						matched.set(currMatch, -1);
						
					}
    			}
    			
    			if(updatedInternList.get(intern) != null) {
    				updatedInternList.get(intern).remove(0);
    			}
    			
    			if(matched.get(intern) != -1) {
    				for(int i = 0; i < matched.size(); i++) {
    					intern = matched.indexOf(-1);
    				}
    			}
    			
    			
    		}
    	
    	
    	
        return new Matching(problem, matched);
    }
}