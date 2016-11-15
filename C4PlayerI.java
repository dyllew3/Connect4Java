import java.awt.Color;
import java.awt.Point;
import java.io.*;;
/*
 * Runs a game of Connect 4 with an random Ai and human player or 
 * two human players.
 *  @author Dylan Lewis
 */
public interface C4PlayerI {
	
	//checks if player out of lives
	
	
	public void takeAwaylife() throws OutOfLivesException ;
	
	public int showLives()  ;
	
	//creates new token for player in question
	public Token newToken(int x, int y);
	
	//sets the colour of the token
	public Color setTokenColour();
	
	
	public int showPlayerNumber();
	
	;
	


}
