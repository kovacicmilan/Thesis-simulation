import java.util.ArrayList;

/**
 * Class simulates the decision tree recursively. In other words build by recursive calls a tree that exhausts all possibilities of the game and than
 * chooses the most desirable one.
 * 
 * @author Milan Kovacic
 */
public class DecisionTreeSimulation {

	//test
	DecisionTreeSimulation() {
		
	}
	
	/**
	 * Recursive method used to build a decision tree for every single step in simulation. At every iteration it
	 * calls the number of players recursive calls. The number of calls therefore grows exponentially. This is performed to 
	 * desired depth.
	 * 
	 * @param	node	object that carries all needed information of the recursion call
	 * @param	finalNumbers		array list of all the terminal tree leafs 
	 * @see		Node	see the class if deeper understending of the recursion is required
	 */
	static void simulationDecisionTree(Node node, ArrayList<Integer []> finalNumbers) {
		/**
		 * In case of depth == 0 the decision tree on this branch is finished,therefore program notes the values
		 * into finalNumbers and ends this simulationDecisionTree branch
		 */
		if (node.depth == 0) {
			Integer [] tmp = new Integer[2];
			tmp[0] = node.score[node.forPlayer];
			tmp[1] = node.path[0];
			finalNumbers.add(tmp);
			
		} else {
			
			/**
			 * Lowers the depth number (to prevent never ending recursion).
			 */
			int newDepth = node.depth - 1;
			
			/**
			 * For each player calls recursion simulationDecisionTree(Node n) with adapted n
			 */
			for (int i = 0; i < node.numOfPlayers; i++) {
				
				simulateSubTrees(node, newDepth, i, finalNumbers);
				
			}
		}
	}
	
	/**
	 * Simulates recursion call on a new player (simulatedPlayer) that got the token passed, with lower depth (newDepth) and gets
	 * another parameter (node) to have the information to call recursion properly
	 *
	 * @param	simulatedPlayer 	is a player that got the ball passed
	 * @param	node object 		that carries information about the state of recursion
	 * @param	newDepth 			the depth of recursion calls 
	 * @param	finalNumbers		array list of all the terminal tree leafs 
	 */
	static void simulateSubTrees(Node node, int newDepth, int simulatedPlayer, ArrayList<Integer []> finalNumbers) {
		/*Clones score*/
		int[] newScore = node.score.clone();
	
		/*
		 * Creates a path off recursion calls to map how did the program behaved in calling simulationDecisionTree(Node n)
		 * */
		int[] newPath = new int[node.path.length + 1];
		for (int j = 0; j < node.path.length; j++) {
			newPath[j] = node.path[j];
		}
		newPath[node.path.length] = simulatedPlayer;
			
		/*
		 * If the successor is the player that holds the token now do not do nothing,
		 * since you can not pass the token to your self
		 * */
		if (simulatedPlayer == node.holder) {
			//Do nothing
		} 
		/*
		 * Incrase score of the player that gets the ball,
		 * call the function recursively 
		 * */
		else {
			newScore[simulatedPlayer]++;
			Node newNode = new Node(newScore, newDepth, simulatedPlayer, node.numOfPlayers, node.forPlayer, newPath);
			simulationDecisionTree(newNode, finalNumbers);
		}
	}
	
	/**
	 * Method takes arrayList searches it and gives the best possible next step. If there are more options that have
	 * the same utility value, the method always chooses the last one (in respect to search order). 
	 * 
	 * @return 	returnValue		integer represents the best possible solution for the next step
	 * @param	finalNumbers		array list of all the terminal tree leafs 
	 */
	static int findMax(ArrayList<Integer []> finalNumbers) {
		int max = 0;
		int returnValue = 0;
		for (int i = 0; i < finalNumbers.size(); i++) { 
			int key = finalNumbers.get(i)[0];
			int value = finalNumbers.get(i)[1];
			if (key > max) {
				returnValue = value;
			}
		}
		finalNumbers.clear();
		return returnValue;
	}
}
