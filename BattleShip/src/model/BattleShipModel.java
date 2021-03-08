package model;

import java.util.ArrayList;

import controller.BattleShipController.HitMessage;


public class BattleShipModel {
	
	private int boardSize;
	private int numShips;
	private int shipLength;
	private int shipsSunk;
	private int[][] locations;
	private boolean[][] hits;
	//ArrayList<Integer> battleShips;
	//ArrayList<String> hits;
	private Ships[] battleShips;
	
	public BattleShipModel() {

		boardSize = 7;
		numShips = 3;
		shipLength = 3;
		locations = new int[numShips][numShips];
		hits = new boolean[numShips][numShips];
		battleShips = new Ships[3];
		
		
		init();
	}
	
	public void init() {
		int i, j;
		
		shipsSunk = 0;
		
		
		for(i=0; i<numShips; i++) {
			for(j=0; j<numShips; j++) {
				locations[i][j] = -1;		
				hits[i][j] = false;		
			}
		}

	}
	
	
	public void generateShipLocations() {
		
		int i, j;
		int[] tempLocation = new int[numShips];

		//clear previous data
		init();
		
		for (i = 0; i < numShips; i++) {
			
			generateShip(tempLocation);
			while(collision(tempLocation)) {
				generateShip(tempLocation);
			}
			
			for(j=0; j<numShips; j++) {
				locations[i][j] = tempLocation[j];
				System.out.println("shiplocation["+i+"]["+j+"]: "+tempLocation[j]);
			}
		}

		
	}
	
	public void generateShip(int[] tempArr) {

		int direction = (int) Math.floor(Math.random() * 2);
		int row, col;
		
		if(direction ==1) {//horizontal
			row = (int)Math.floor(Math.random() * this.boardSize);
			col = (int)Math.floor(Math.random() * (this.boardSize - this.shipLength + 1));
		}else {//vertical
			row = (int)Math.floor(Math.random() * (this.boardSize - this.shipLength + 1));
			col = (int)Math.floor(Math.random() * this.boardSize);
		}
		
		for(int i = 0; i < numShips ; i++) {
			if (direction == 1) {
				tempArr[i] = row*boardSize + (col + i);
			} else {
				tempArr[i] = (row +i)*boardSize + col;
			}
	

		}
		
		
		
	}


	public boolean collision(int[] tempArr) {
		
		int i, j, k;
		
		for(i=0; i< numShips; i++) {			
			for(j=0; j<numShips; j++) {
				//System.out.println("locat["+i+"]["+j+"]="+locations[i][j]+", tempArr["+j+"]="+tempArr[j]);
				for(k=0; k<tempArr.length; k++) {
					if(locations[i][j] == tempArr[k]) {
						System.out.println("collision["+i+"]["+j+"]="+tempArr[j]);
						return true;
					}
				}
			}
		}
		
		return false;
	}
	

	public HitMessage fire(int guess) {
		int i, j;
		
		for (i = 0; i < this.numShips; i++) {

			// here's an improvement! Check to see if the ship
			// has already been hit, message the user, and return true.
			for(j=0; j< this.numShips; j++) {
				if(locations[i][j] == guess) {
					if(hits[i][j]) {
						System.out.println("Oops, you already hit that location!"); 
						return HitMessage.ALREADY;	
					}else {
						hits[i][j] = true;
						//view.displayHit(guess);
						//view.displayMessage("HIT!");
												
						if(isSunk(i)) {							
							shipsSunk++;
							System.out.println("You sank my battleship!");
							return HitMessage.HIT_AND_SANK;
						}
						
						return HitMessage.HIT;
					}
					
				}
			}
		}
		return HitMessage.MISS;
			
	}
	
	public int getNumOfShips() {
		return numShips;
	}
	
	public int getNumOfShipsSunk() {
		return shipsSunk;
	}

	public boolean isSunk(int row) {
		for (int i = 0; i < this.shipLength; i++)  {
			if (hits[row][i] == false) {
				return false;
			}
		}
	    return true;
	}

}

