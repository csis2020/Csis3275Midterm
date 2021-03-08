import java.awt.EventQueue;


import view.BattleShipView;
import model.BattleShipModel;
import controller.BattleShipController;

public class BattleShipMain {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//create model, view, and controller
					BattleShipModel bsModel = new BattleShipModel();
					BattleShipView bsView = new BattleShipView();
					BattleShipController bsController = new BattleShipController(bsView,bsModel);
					
					//place the ships on the game board
					bsModel.generateShipLocations();
					
					//tell View about Controller		
					bsView.addController(bsController);
					
					bsView.setVisible();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
		

	}

}
