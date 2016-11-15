import java.awt.Color;
import java.awt.Point;
import java.awt.color.*;
import java.util.ArrayList;
import java.util.Arrays;
/*
 * Human Player keeps track of lives subtract lives if loss can 
 * generate instances of the token class. Has assigned player number
 *  @author Dylan Lewis
 */

public class C4HumanPlayer implements C4PlayerI {

	
	
	public int playerNumber;
	
	private boolean isTwoPlayer;
	
	private Color playerColor;
	
	public int lives;
	
	public C4HumanPlayer(int lives, int playerNumber, boolean isTwoPlayers ) throws OutOfLivesException{
		if(lives > 0){
			this.lives = lives;
			this.playerNumber = playerNumber;
			this.isTwoPlayer = isTwoPlayers;
			this.playerColor = setTokenColour();
			
		}
		else{
			OutOfLivesException e = new OutOfLivesException(isTwoPlayers, playerNumber);
			throw e;
			
		}
		
	}
	
	//if lives is less than or equal to 0 it throws the exception
	public void takeAwaylife () throws OutOfLivesException  {
		lives--;
		if(lives <=0){
			throw new OutOfLivesException(isTwoPlayer, playerNumber);
		}

		
		
	}

	@Override
	//creates new token where x is the column and y is the row
	public Token newToken(int x, int y) {
		if((x >= 0) && (y >= 0) &&  (playerColor != null) ){
			//converts array to array list
			Token newToken = new Token(playerColor, x, y);
			return newToken	;
				
		}
		else{
			System.out.println("Not valid co-ordinate");
			return null;
		}
				
			
		
				
	}

	@Override
	public Color setTokenColour() {
		try{
			if(playerNumber == 1){
				
				return StdDraw.YELLOW;
			}
			
			else if (playerNumber == 2){
				
				return StdDraw.RED;
				
			}
			
			else{
				return null;
			}
		}
		catch(NullPointerException e){
			
			return null;
		}
		
	}

	@Override
	public int showLives()  {
		return this.lives;
			
		
	}

	@Override
	public int showPlayerNumber() {
		// TODO Auto-generated method stub
		return this.playerNumber;
	}

	
	
	
	

	
	
	



}
