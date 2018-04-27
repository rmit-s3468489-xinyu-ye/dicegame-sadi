package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import model.interfaces.GameEngine;
import model.interfaces.GameEngineCallback;
import model.interfaces.Player;
import model.SimplePlayer;

/**
 * 
 * @author Xinyu YE s3468489
 *
 */
public class GameEngineImpl implements GameEngine
{
	List<Player> players;
	
	GameEngineCallback gameEngineCallback;
	
	public GameEngineImpl() 
	{
		players = new ArrayList<>();
	}

	@Override
	public boolean placeBet(Player player, int bet) 
	{	
		//Invoking the current player's placeBet(int bet) method
		return player.placeBet(bet);
	}

	@Override
	public void rollPlayer(Player player, int initialDelay, int finalDelay, int delayIncrement) 
	{
		for (int i = initialDelay; i < finalDelay; i += delayIncrement) 
		{
			//Generating a random interger from 1 to 6 for
			//both dices
			int dice1 = (int) (Math.random() * 6) + 1;
			int	dice2 = (int) (Math.random() * 6) + 1;
				
			DicePairImpl rollResult = new DicePairImpl(dice1, dice2,dice1 + dice2);
			
			player.setRollResult(rollResult);
			
			//Invoking the intermediateResult method in GameEngineCallBackImpl
			//for the current player, for displaying the player's temporary
			//rolling result
			gameEngineCallback.intermediateResult(player, rollResult, this);
		}
	}

	@Override
	public void rollHouse(int initialDelay, int finalDelay, int delayIncrement) 
	{
		DicePairImpl rollResult = null;
		
		for (int i = initialDelay; i < finalDelay; i += delayIncrement) 
		{
			int dice1 = (int) (Math.random() * 6) + 1;
			int	dice2 = (int) (Math.random() * 6) + 1;
				
			rollResult = new DicePairImpl(dice1, dice2,dice1 + dice2);

			//Invoking the intermediateResult method in GameEngineCallBackImpl
			//for the House, for displaying the House's temporary
			//rolling result
			gameEngineCallback.intermediateHouseResult(rollResult, this);
		}
		
		/**
		 * Invoking the houseResult method in GameEngineCallBackImpl
		 * for the House, for displaying the House's final
		 * rolling result
		 */
		gameEngineCallback.houseResult(rollResult, this);
		

		//Comparing whether the House or the player wins
		for (Player player : players) 
		{
			/**
			 * If the sum of player's number on both dice's resting faces is larger 
			 * than the House's, then the player will get the points equal
			 * to twice its bet, i.e., getting back the bet that it has placed earlier.
			 * plus the points equal to its bet(as the bonus)
			 */
			if (player.getRollResult().getNumFaces() > rollResult.getNumFaces()) 
			{
				player.setPoints(player.getPoints() + player.getBet() * 2);
			}
			/**
			 * If the sum of player's number on both dice's resting faces is smaller
			 * than the House's, then the player will lose its bet, 
			 * i.e., it will not be able to get back the bet it placed earlier 
			 */
			else if (player.getRollResult().getNumFaces() < rollResult.getNumFaces()) 
			{
				player.setPoints(player.getPoints());
			}
			/**
			 * If a draw occurs, the bet will be returned to the drawing player
			 */
			else 
			{
				player.setPoints(player.getPoints() + player.getBet());
			}
			
			/**
			 * Invoking the result method in GameEngineCallBackImpl
			 * for the player, for displaying the player's final
			 * rolling result
			 */
			gameEngineCallback.result(player, player.getRollResult(), this);
		}
	}

	//Add a player into the Player List
	@Override
	public void addPlayer(Player newPlayer) 
	{
		players.add(newPlayer);
	}

	@Override
	public Player getPlayer(String id) 
	{
		
		for (Player player : players) 
		{
			if(player.getPlayerId().equals(id))
				return player;
		}
		return null;
	}

	@Override
	public boolean removePlayer(Player player) 
	{		
		return players.remove(player);
	}

	@Override
	public void addGameEngineCallback(GameEngineCallback gameEngineCallback) 
	{
		this.gameEngineCallback = gameEngineCallback;		
	}

	@Override
	public boolean removeGameEngineCallback(GameEngineCallback gameEngineCallback) 
	{
		if(gameEngineCallback != null) 
		{
			gameEngineCallback = null;
			return true;
		}
		else	
			return false;
	}

	//Get all players
	@Override
	public Collection<Player> getAllPlayers() 
	{
		return players;
	}
}
