package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import view.BattleShipView;
import model.BattleShipModel;


public class BattleShipController  implements ActionListener {

	private BattleShipView bsView;
	private BattleShipModel bsModel;
	
	private int guesses;

	public enum HitMessage{
		HIT, ALREADY,HIT_AND_SANK, MISS
	}
	
	public BattleShipController(BattleShipView view, BattleShipModel model){
		guesses = 0;
		bsView = view;
		bsModel = model;
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().contentEquals("EAST")
				|| e.getActionCommand().contentEquals("WEST") 
				|| e.getActionCommand().contentEquals("NORTH")
				|| e.getActionCommand().contentEquals("SOUTH")
				
				) {
			
			bsView.displayMessage("OUT OF AREA!!");
		} else {
			//bsView.displayMessage(e.getActionCommand());
			
			String value = e.getActionCommand();
			String index = value.substring(5);//the substring after "SHOOT"
			int guessNumber = Integer.parseInt(index);
			processGuess(guessNumber);
			
		}
		System.out.println(e.getActionCommand());
		
	}
	
	
	public void processGuess(int guess) {
	
		this.guesses++;
		
		//int position = parseGuess(guess);
		HitMessage status = bsModel.fire(guess);
		
	
		switch(status) {
		case ALREADY:
			bsView.displayMessage("Oops, you already hit that location!"); 
			break;
		case HIT:
			bsView.displayHit(guess);
			break;
		case HIT_AND_SANK:
			bsView.displayHit(guess);
			
			if(bsModel.getNumOfShipsSunk() == bsModel.getNumOfShips()) {
				bsView.displayMessage("You sank all my battleships, in " + this.guesses + " guesses");
				bsView.clear();
				bsModel.generateShipLocations();
				
				System.out.println("You sank all my battleships, in " + this.guesses + " guesses");
				
			}else {
				bsView.displayMessage("You sank my battleship!");	
				
			}
			break;
		case MISS:
			bsView.displayMiss(guess);
			break;
		default:
			break;
		}
		
	
	}
	

}


