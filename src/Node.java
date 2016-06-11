
/** 
 * Class that creates object that is passed i the recursion calls of the decision tree. 
 * 
 * @author Milan Kovacic
 */
public class Node {
	
	/**
	 * Score in the game represented as an array of integers, in other words how many "coins" does each player holds in the current simulated step.
	 */
	public int[] score;
	/**
	 * Represents the desired / left number of recursive calls in a current branch.
	 */
	public int depth;
	/**
	 * The player that currenty holds the token.
	 */
	public int holder;
	/**
	 * Number of player in the game.
	 */
	public int numOfPlayers;
	/**
	 * The player that the decision tree is build for.
	 */
	public int forPlayer;
	/**
	 * Memory of recursive calls.
	 */
	public int[] path;
	
	/**
	 * Constructor for class Node. Creates object that is passed i the recursion calls of the decision tree. 
	 *@param	_score			score in the game represented as an array of integers, in other words how many "coins" does each player holds in the current simulated step
	 *@param	_depth			represents the desired / left number of recursive calls in a current branch
	 *@param	_holder			the player that currenty holds the token
	 *@param	_numOfPlayers	number of player in the game
	 *@param	_forPlayer		the player that the decision tree is build for
	 *@param	_path			memory of recursive calls
	 */
	Node (int[] _score, int _depth, int _holder, int _numOfPlayers, int _forPlayer, int[] _path) {
		score = _score;
		depth = _depth;
		holder = _holder;
		numOfPlayers = _numOfPlayers;
		forPlayer = _forPlayer;
		path = _path; 
	}
}
