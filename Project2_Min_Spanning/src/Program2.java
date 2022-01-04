/*
 * Name: Sathvik Gujja
 * EID: srg3394
 */

// Implement your algorithms here
// Methods may be added to this file, but don't remove anything
// Include this file in your final submission

import java.util.ArrayList;

public class Program2 {
    private ArrayList<City> cities;  // this is a list of all Cities, populated by Driver class
    private Heap minHeap;
    private boolean[] visited;
    private ArrayList<City> newCities;

    // additional constructor fields may be added, but don't delete or modify anything already here
    public Program2(int numCities) {
        minHeap = new Heap();
        cities = new ArrayList<City>();
        visited = new boolean[numCities];
        newCities = new ArrayList<City>();
    }

    /**
     * findMinimumRouteDistance(City start, City dest)
     *
     * @param start - the starting City.
     * @param dest  - the end (destination) City.
     * @return the minimum distance possible to get from start to dest.
     * Assume the given graph is always connected.
     */
    public int findMinimumRouteDistance(City start, City dest) {
    	newCities = new ArrayList<>(cities);
    	minHeap = new Heap();
    	visited = new boolean[cities.size()];
    	
    	
    	
    	for(int i = 0; i < newCities.size(); i++) {
    		newCities.get(i).setMinDist(Integer.MAX_VALUE);
    	}
    	
    	newCities.get(newCities.indexOf(start)).setMinDist(0);
    	
    	minHeap.buildHeap(newCities);
    	
    	City c;
    	
    	while((c = minHeap.extractMin()) != null) { // get min
    		
    		for(int i = 0; i < c.getNeighbors().size(); i++) { //loop through all neighbors  			
    			if(c.getMinDist() + c.getWeights().get(i) < c.getNeighbors().get(i).getMinDist()) { // less distance, update
    				if(!visited[c.getNeighbors().get(i).getName()]) {
    					minHeap.changeKey(c.getNeighbors().get(i), c.getMinDist() + c.getWeights().get(i));
    				}
    			}
    		}
    		
    		visited[c.getName()] = true;
    		
    	}
    	
        return dest.getMinDist();
    }

    /**
     * findMinimumLength()
     *
     * @return The minimum total optical line length required to connect (span) each city on the given graph.
     * Assume the given graph is always connected.
     */
    public int findMinimumLength() {
    	newCities = new ArrayList<>(cities);
    	minHeap = new Heap();
    	visited = new boolean[cities.size()];
    	
    	
    	
    	for(int i = 0; i < newCities.size(); i++) {
    		newCities.get(i).setMinDist(Integer.MAX_VALUE);
    	}
    	
    	City start = cities.get(0);
    	newCities.get(newCities.indexOf(start)).setMinDist(0);
    	
    	minHeap.buildHeap(newCities);
    	
    	City c;
    	
    	int minLen = 0;
    	
    	while((c = minHeap.extractMin()) != null) { // get min
    		
    		for(int i = 0; i < c.getNeighbors().size(); i++) { //loop through all neighbors   			
    			if(c.getWeights().get(i) < c.getNeighbors().get(i).getMinDist()) { // less distance, update
    				if(!visited[c.getNeighbors().get(i).getName()]) {
    					minHeap.changeKey(c.getNeighbors().get(i), c.getWeights().get(i));
    					if(i >= newCities.size()) {
    						break;
    					}
    				}
    			}	
    		}
    		
    		visited[c.getName()] = true;
    	}
    	
    	for(int i = 0; i < newCities.size(); i++) {
    		minLen += newCities.get(i).getMinDist();
    	}
    		
        return minLen;
    }

    //returns edges and weights in a string.
    public String toString() {
        String o = "";
        for (City v : cities) {
            boolean first = true;
            o += "City ";
            o += v.getName();
            o += " has neighbors ";
            ArrayList<City> ngbr = v.getNeighbors();
            for (City n : ngbr) {
                o += first ? n.getName() : ", " + n.getName();
                first = false;
            }
            first = true;
            o += " with distances ";
            ArrayList<Integer> wght = v.getWeights();
            for (Integer i : wght) {
                o += first ? i : ", " + i;
                first = false;
            }
            o += System.getProperty("line.separator");

        }

        return o;
    }

///////////////////////////////////////////////////////////////////////////////
//                           DANGER ZONE                                     //
//                everything below is used for grading                       //
//                      please do not change :)                              //
///////////////////////////////////////////////////////////////////////////////

    public Heap getHeap() {
        return minHeap;
    }

    public ArrayList<City> getAllCities() {
        return cities;
    }

    // used by Driver class to populate each City with correct neighbors and corresponding weights
    public void setEdge(City curr, City neighbor, Integer weight) {
        curr.setNeighborAndWeight(neighbor, weight);
    }

    // used by Driver.java and sets cities to reference an ArrayList of all RestStops
    public void setAllNodesArray(ArrayList<City> x) {
        cities = x;
    }
}
