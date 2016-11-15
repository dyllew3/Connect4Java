import java.lang.Exception;

//checks if player out of lives and if so ends the game
//@Author Dylan Lewis
public class OutOfLivesException extends Exception {
	/**
	 * This class checks if the player is out of lives if the player is out of lives 
	 * it throws a message. This message depends on whether there are two players or one
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public OutOfLivesException(boolean isTwoPlayers, int playerNumber) {
		if (isTwoPlayers) {
			//The winner can be found by subtracting 3 from player number the absoluting it 
			int winner = Math.abs(playerNumber - 3);
			System.out.println("Player" + winner + " wins");
		} else {
			if(playerNumber == 1){
				System.out.println("You lose");
			}
			else{
				System.out.println("You win");
			}
		}
	}
	public void message(){
		
	}
	
};