import java.util.ArrayList;

/**
 * The main class that executes the whole simulation process. Takes input from the user and runs the simulation execution, while
 * using the other classes of this project. The result of this simulation is console output of simulated data.
 * 
 * @author Milan Kovacic
 */
public class Simuation {
	
	/**
	 * Method that iniciates the whole simulation process.
	 * 
	 * @param	args	the input data from user to ensure the desired sun of the simulation [starting player; desired depth of simulation in decision tree;
	 * 				number of players; desired number of simulated iterations]
	 */
	public static void main(String[] args) {
		/**
		 * creates the initial node with default values
		 */
		int holder = Integer.parseInt(args[0]);
		int [] path = {};
		int depth = Integer.parseInt(args[1]);
		int numOfPlayers = Integer.parseInt(args[2]);
		int [] score = createInitialScore(holder, numOfPlayers);
		Node node = new Node(score, depth, holder, numOfPlayers, holder, path);
		
		ArrayList<Integer []> finalNumbers = new ArrayList<>();
		
		/**
		 * creates and initialises next to the same values as holder
		 */
		int next = holder;
		
		/**
		 * loops to simulate as many times as the length of the simulation is desired
		 */
		int simulationLength = Integer.parseInt(args[3]);
		do {
			giveData(next, node);
			
			DecisionTreeSimulation.simulationDecisionTree(node, finalNumbers);
			next = DecisionTreeSimulation.findMax(finalNumbers);
			
			node.holder = next;
			score[next]++;
			
			simulationLength--;
		} while(simulationLength != 0); 
		
		String scoreLine = writeScore(score);
		System.out.println("SCORE: ***" + scoreLine + "***");
	}
	
	/**
	 * Generates the final score values string.
	 * @param score	the final score
	 * @return	the value of the final score in readable string
	 */
	private static String writeScore(int[] score) {
		String scoreLine = "";
		for (int i = 0; i < score.length; i++) {
			scoreLine = scoreLine + " PLAYER" + i + ": " + score[i] + "|";
		}
		return scoreLine;
	}


	/**
	 * Creates dynamically the initial score.
	 * 
	 * @param	holder			the player that holds the token
	 * @param	numOfPlayers 	number of players in the game
	 * @return	returns 		returns the initial score as an int[]
	 */
	private static int[] createInitialScore(int holder, int numOfPlayers) {
		int[] score = new int[numOfPlayers];
		score[holder]++;
		return score;
	}

	/**
	 * Function that generates data to console.
	 * 
	 * @param	next	the following holder of the token
	 * @param	node	the object that holds all the information of recursion calls
	 */
	private static void giveData(int next, Node node) {
		for (int i = 0; i < next; i++) {
			System.out.print("---");
		}
		System.out.println(next);
	}

}
