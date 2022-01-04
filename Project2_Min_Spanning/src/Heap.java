/*
 * Name: Sathvik Gujja
 * EID: srg3394
 */

// Implement your heap here
// Methods may be added to this file, but don't remove anything
// Include this file in your final submission

import java.util.ArrayList;

public class Heap {
    private ArrayList<City> minHeap;

    public Heap() {
        minHeap = new ArrayList<City>();
    }

    /**
     * buildHeap(ArrayList<City> cities)
     * Given an ArrayList of Cities, build a min-heap keyed on each City's minDist
     * Time Complexity - O(nlog(n)) or O(n)
     *
     * @param cities
     */
    public void buildHeap(ArrayList<City> cities) {
    	for(int i = 0; i < cities.size(); i++) {
    		minHeap.add(cities.get(i));
    		minHeap.get(i).setIndex(i);
    	}
    	
    	for(int i = cities.size()/2; i >= 0; i--) {
    		orderDown(i);
    	}

//    	for(int i = 0; i < minHeap.size(); i++) {
//    		System.out.println(minHeap.get(i).getMinDist());
//    	}
//    	System.out.println();
    	
    	
    }

    /**
     * insertNode(City in)
     * Insert a City into the heap.
     * Time Complexity - O(log(n))
     *
     * @param in - the City to insert.
     */
    public void insertNode(City in) {
        // TODO: implement this method
    	minHeap.add(in);
    	in.setIndex(minHeap.size());
    	orderUp(minHeap.size()-1);
    }

    /**
     * findMin()
     * Time Complexity - O(1)
     *
     * @return the minimum element of the heap.
     */
    public City findMin() {
    	City min = minHeap.get(0);
        return min;
    }

    /**
     * extractMin()
     * Time Complexity - O(log(n))
     *
     * @return the minimum element of the heap, AND removes the element from said heap.
     */
    public City extractMin() {
        // TODO: implement this method
    	if(minHeap.size() == 0) {
    		return null;
    	}
    	
    	City c = minHeap.get(0);
    	delete(0);
    	c.setIndex(0);
        return c;
    }

    /**
     * delete(int index)
     * Deletes an element in the min-heap given an index to delete at.
     * Time Complexity - O(log(n))
     *
     * @param index - the index of the item to be deleted in the min-heap.
     */
    public void delete(int index) {
    	int i = index;
        swap(index, minHeap.size()-1);
        minHeap.remove(minHeap.size()-1);
        
        orderDown(i);
    }

    /**
     * changeKey(City r, int newDist)
     * Changes minDist of City s to newDist and updates the heap.
     * Time Complexity - O(log(n))
     *
     * @param r       - the City in the heap that needs to be updated.
     * @param newDist - the new cost of City r in the heap (note that the heap is keyed on the values of minDist)
     */
    public void changeKey(City r, int newDist) {
    	
    	r.setMinDist(newDist);
    	orderUp(r.getIndex());
    	orderDown(r.getIndex());

    }

    public String toString() {
        String output = "";
        for (int i = 0; i < minHeap.size(); i++) {
            output += minHeap.get(i).getName() + " ";
        }
        return output;
    }
    
    public void orderUp(int index) {
    	if(index == 0) {
    		return;
    	}
    	int parent = (int) Math.floor((index - 1)/2);
    	
    	if(minHeap.get(index).getMinDist() <= minHeap.get(parent).getMinDist()) { // if smaller swap
    		if(minHeap.get(index).getMinDist() == minHeap.get(parent).getMinDist()) {
    			if(minHeap.get(index).getName() > minHeap.get(parent).getName()) { // if parent has smaller name continue
    				return;
    			}
    		}
    		//swap
    		swap(index, parent);
    		
    		orderUp(parent);
    		
    	}
    	
    	return;

    }
    
    public void orderDown(int index) {
    	int left = 2 * index + 1;
    	int right = 2 * index + 2;
    	
    	if(left < minHeap.size() && right < minHeap.size()) { // has 2 children
    		if(minHeap.get(left).getMinDist() < minHeap.get(right).getMinDist()) { // left < right
    			if(minHeap.get(left).getMinDist() < minHeap.get(index).getMinDist()) { // left < index
    				swap(index, left);
    				orderDown(left);
    			}
    		}
    		else if(minHeap.get(right).getMinDist() < minHeap.get(left).getMinDist()) { // right < left
    			if(minHeap.get(right).getMinDist() < minHeap.get(index).getMinDist()) { // right < index
    				swap(index, right);
    				orderDown(right);
    			}
    		}
    		else { // left = right
    			if(minHeap.get(left).getName() < minHeap.get(right).getName()) { 
    				if(minHeap.get(left).getName() < minHeap.get(index).getName()) {
    					swap(index, left);
    					orderDown(left);
    				}
    			}
    			else if(minHeap.get(right).getName() < minHeap.get(left).getName()) {
    				if(minHeap.get(right).getName() < minHeap.get(index).getName()) {
    					swap(index, right);
    					orderDown(right);
    				}
    			}
    			else { // left child has priority
    				if(minHeap.get(left).getName() < minHeap.get(index).getName()) {
    					swap(index, left);
    					orderDown(left);
    				}
    			}
    		}
    	}    	
    	else if(left < minHeap.size() || right < minHeap.size()) { // one child
    		if(minHeap.get(left).getMinDist() < minHeap.get(index).getMinDist()) {
    			swap(index, left);
    			orderDown(left);
    		}
    		else if(minHeap.get(left).getMinDist() == minHeap.get(index).getMinDist()) {
    			if(minHeap.get(left).getName() < minHeap.get(index).getName()) {
    				swap(index, left);
    				orderDown(left);
    			}
    		}
    	}
    	
    	
    }
    
    public void swap(int currIndex, int otherIndex) {
    	City temp = minHeap.get(currIndex);
		minHeap.set(currIndex, minHeap.get(otherIndex));
		minHeap.set(otherIndex, temp);
		
		minHeap.get(currIndex).setIndex(currIndex);
		minHeap.get(otherIndex).setIndex(otherIndex);
    }
    
    public boolean isEmpty() {
    	return minHeap.isEmpty();
    }

///////////////////////////////////////////////////////////////////////////////
//                           DANGER ZONE                                     //
//                everything below is used for grading                       //
//                      please do not change :)                              //
///////////////////////////////////////////////////////////////////////////////

    public ArrayList<City> toArrayList() {
        return minHeap;
    }
}
