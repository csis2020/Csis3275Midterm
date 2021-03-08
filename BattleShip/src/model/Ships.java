package model;

import java.util.ArrayList;

public class Ships {
	
	 private ArrayList<Integer> locations;
	 private ArrayList<String> hits;
	 
	 /*
	 public Ships(ArrayList<Integer> locations, ArrayList<String> hits) {
		 this.locations = locations;
		 this.hits = hits;
	 }
	 */	 
	 public Ships() {
		 this.locations = new ArrayList<Integer>();
		 this.hits = new ArrayList<String>();
	 }
	 
	 public void setLocations(ArrayList<Integer> locations) {
		 this.locations = locations;
	 }
	 
	 public ArrayList<Integer> getLocations() {
		 return locations;
	 }
	 
	 
	 
	 
	 

}
