package model;

import model.interfaces.DicePair;
import model.interfaces.Player;

/**
 * 
 * @author Xinyu YE s3468489
 *
 */
public class SimplePlayer implements Player
{
	private String playerName, playerId;
	
	private int points, bet;
	
	private DicePair rollResult;
	
	public SimplePlayer(String playerId, String playerName, int initialPoints) 
	{
		this.playerId = playerId;
		this.playerName = playerName;
		this.points = initialPoints;
	}

	@Override
	public String getPlayerName() 
	{
		return playerName;
	}

	@Override
	public void setPlayerName(String playerName) 
	{
		this.playerName = playerName;		
	}

	@Override
	public int getPoints() 
	{
		return points;
	}

	@Override
	public void setPoints(int points) 
	{
		this.points = points;
	}

	@Override
	public String getPlayerId() 
	{
		return playerId;
	}

	/**
	 * Check whether the player has 
	 * enough points for placing a bet, and
	 * whether the player placed a valid bet
	 * i.e., more than zero point
	 * If the player has enough points,
	 * i.e., the bet that the player placed 
	 * is greater than zero and 
	 * smaller than or equal to the points it has,
	 * then assign the parameter that 
	 * passed into this method to 
	 * the current player's bet field.
	 * 
	 * After the above assignment operation,
	 * minus the bet from the current player's
	 * points field, and return true
	 * 
	 * Else, return false
	 */
	@Override
	public boolean placeBet(int bet) 
	{
		if (bet > 0 && points >= bet) 
		{
			this.bet = bet;
			points -= bet;
			return true;		
		}	
		else
			return false;
	}

	@Override
	public int getBet() 
	{
		return bet;
	}

	@Override
	public DicePair getRollResult() 
	{
		return rollResult;
	}

	@Override
	public void setRollResult(DicePair rollResult) 
	{
		this.rollResult = rollResult;
	}
	
	/**
	 * Customizing the printed out information
	 * of the current player object
	 */
	@Override
	public String toString() 
	{
		String playerInfo = "";
		
		playerInfo = "Player: id = " + this.playerId +
				", name = " + this.playerName  + ", points = " + this.points;
		
		return playerInfo;
	}
}
