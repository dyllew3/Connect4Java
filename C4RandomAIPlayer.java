import java.awt.Color;
import java.awt.Point;
/*
 *
 * AI which randomly places tokens on the grid implements
 * player class , can take away lives and generate tokens
 * Has assigned player number
 *  @author Dylan Lewis
 */
public class C4RandomAIPlayer implements C4PlayerI {
	

	public int playerNumber;
	
	private Color playerColor;
	
	public int lives;
	
	public C4RandomAIPlayer(int lives ) throws OutOfLivesException{
		this.playerNumber = 2;
		if(lives > 0){
			this.lives = lives;
			
			this.playerColor = setTokenColour();
			
		}
		else{
			OutOfLivesException e = new OutOfLivesException(false, playerNumber);
			throw e;
			
		}
		
	}

	@Override
	public void takeAwaylife() throws OutOfLivesException {
		if(lives > 1){
			this.lives--;
		}
		else {
			throw new OutOfLivesException(false, playerNumber);
			 
		}
	}



	@Override
	public Color setTokenColour() {
		// TODO Auto-generated method stub
		
		return (StdDraw.RED);
	}

	@Override
	public int showLives()  {
		return this.lives;
			
		
		
			
	}

	@Override
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
	public int showPlayerNumber() {
		// TODO Auto-generated method stub
		return 2;
	}

}
